package net.jxvtc.eshop.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
private static DataSource dataSource=new ComboPooledDataSource();
private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
   public static DataSource getDataSource() {
	return dataSource;
   }
   
   public static Connection getConnection() throws SQLException {
	   Connection conn=tl.get();
	   
	   
	   if(conn==null) {
		   conn=dataSource.getConnection();
		   tl.set(conn);
	   }
	   return conn;
   }
   public static void startTransaction() throws SQLException {
	   Connection conn=tl.get();
	   if(conn!=null) {
		   conn.setAutoCommit(false);
	   }
   }
   
   public static void rollback() throws SQLException {
	   Connection conn=tl.get();
	   if(conn!=null) {
		   conn.rollback();
	   }
   }
   
   public static void releaseAndCloseConnection() throws SQLException {
	   Connection conn=tl.get();
	   if(conn!=null) {
		   conn.commit();
		   tl.remove();
		   conn.close();
	   }
   }
   
}

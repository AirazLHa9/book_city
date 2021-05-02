package net.jxvtc.eshop.service;
import java.sql.SQLException;
import java.util.Date;
import javax.security.auth.login.LoginException;
import net.jxvtc.eshop.dao.UserDao;
import net.jxvtc.eshop.domain.User;
import net.jxvtc.eshop.exception.RegisterException;
import net.jxvtc.eshop.utils.MailUtils;

public class UserService {
	private UserDao dao = new UserDao();
	
	public void register(User user) throws RegisterException {
		
		try {
			dao.addUser(user);
			
			String emailMsg = "感谢您注册网上书城，点击"
					+ "<a href='http://localhost:8080/bookstore/activeUser?activeCode="
					+ user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用"
							+ "<br />为保障您的账户安全，请在24小时内完成激活操作";
			MailUtils.sendMail(user.getEmail(), emailMsg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注冊失败");
		}
	}
	
	
	public User login(String username, String password) throws LoginException {
		try {
			
			User user = dao.findUserByUsernameAndPassword(username, password);
			
			if (user != null) {
				
				if (user.getState() == 1) {
					return user;
				}
				throw new LoginException("用户未激活");
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
	}
}

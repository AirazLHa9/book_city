package net.jxvtc.eshop.web.servlet;
import java.io.IOException;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jxvtc.eshop.domain.User;
import net.jxvtc.eshop.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService service = new UserService();
		try {
			User user = service.login(username, password);
			
			request.getSession().setAttribute("user", user);
			
			String role = user.getRole();
			
			if ("超级用户".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
				return;
			}
		} catch (LoginException e) {
			
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			return;
		}
	}
}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDataManager;
import model.Admin;

/**
 * Servlet implementation class AdminLogin
 */

//������ �α��ΰ� ������ ���������� ���� �������Դϴ�.
@WebServlet(urlPatterns = { "/AdminLogin" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost:3306/module5?useUnicode=true&amp;characterEncoding=UTF-8"),
		@WebInitParam(name = "dbUser", value = "root"),
		@WebInitParam(name = "dbPass", value = "webclass") })
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserDataManager userDataMan;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userDataMan = new UserDataManager();
		userDataMan.setDbUrl(config.getInitParameter("dbUrl"));
		userDataMan.setDbUser(config.getInitParameter("dbUser"));
		userDataMan.setDbPass(config.getInitParameter("dbPass"));
		try {
			Class.forName(config.getInitParameter("jdbcDriver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//�α��� ���������� �Է��� ���̵�� ��й�ȣ�� �޾ƿɴϴ�.
		String loginid = request.getParameter("loginid");
		String loginpw = request.getParameter("loginpassword");

		//���̵�� ��й�ȣ�� null�� �ƴ� ��쿡�� �۵��ؾ��մϴ�.
		
		if (loginid != null && loginpw!=null) {
		try{
			
			//�����ͺ��̽��� ����Ͽ� �Ѵ� �����ڿ� ��ġ�� ��쿡�� �۵��մϴ�.
			if(userDataMan.Adminlogin(loginid,loginpw)==true)
			{
				PrintWriter out2 = response.getWriter();
				out2.println("<script>alert('Login Success');</script>");
				Admin admin = new Admin();
				admin.setId(loginid);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");	//admin�������� �Ѿ�ϴ�.
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				dispatcher.forward(request, response);
				
			}
			//�����ڰ� �ƴ� ��쿡�� �����޽����� üũ�ϰ� �ٽ� �α��� ȭ������ ���ư��ϴ�.
			else 
			{
				PrintWriter out1 = response.getWriter();
				out1.println("<script>alert('Login Fail'); history.go(-1);</script>");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
	
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import model.UmbrellaBox;
import model.DelayUser;
import model.UserDataManager;

/**
 * Servlet implementation class UserServlet
 */
//��� �����⸦ ����ϴ� ���� ������ �Դϴ�.
//�º��� �ȵ���̵�� ����Ͽ� �����⸦ �����Ͽ��� ��쿡 �۵��մϴ�.
@WebServlet(urlPatterns = { "/BorrowUmbrella" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost:3306/module5?useUnicode=true&amp;characterEncoding=UTF-8"),
		@WebInitParam(name = "dbUser", value = "root"),
		@WebInitParam(name = "dbPass", value = "webclass") })
public class BorrowUmbrella extends HttpServlet {
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
	 * Default constructor.
	 */
	public BorrowUmbrella() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	int count = 0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("d");
		processRequest(request, response);		
		
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		response.setHeader("Cache-Control", "no-cache"); // no cache for
		// HTTP
		// 1.1
		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
	try{
		System.out.println("e");
		
		if(userDataMan.checkstatus(1)==true)
		{
			userDataMan.borrow(1);
			
			out.println("<html>");
			out.println("<head>");
			out.println("<meta name = \"viewport\" content = \"width=device-width,initial-scale=1\">");
			out.println("<title> List of Delay USER</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("Borrow Umbrella Success");
			out.println("</body>");
			out.println("</html>");
		}
		else
		{
			out.println("<script>alert('I have no umbrella'); history.go(-1);</script>");

		}
	

	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}
	

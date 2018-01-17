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
import model.Admin;
import model.UserDataManager;

/**
 * Servlet implementation class UserLogin
 */
//NFC�� ���� �α����Ҷ� ����� �α��ΰ� ���õ� ���� �������Դϴ�.
@WebServlet(urlPatterns = { "/UserLogin"}, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost:3306/module5?useUnicode=true&amp;characterEncoding=UTF-8"),
		@WebInitParam(name = "dbUser", value = "root"),
		@WebInitParam(name = "dbPass", value = "webclass") })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
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
	
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nfctagid = request.getParameter("nfctagid"); //�ȵ���̵�κ��� nfctagid�� �޾ƿɴϴ�.
		System.out.println("Dfsa");
		System.out.println(nfctagid);
		if (nfctagid != null) {
		try{			
			if(userDataMan.Userlogin(nfctagid)==true)
			{
				System.out.println("dsasdff");
				PrintWriter out2 = response.getWriter();
				//out2.println("<script>alert('Login Success');</script>");
				User user = userDataMan.getUser(nfctagid);
				
				//HttpSession session = request.getSession();
				System.out.println("�ΰٿ�����");
				System.out.println(user.nfctagid);
				request.setAttribute("nfctagid", user.nfctagid);
				request.setAttribute("customerid", user.customerid);
				request.setAttribute("phonenumber", user.phonenumber);
				request.setAttribute("usertype", user.usertype);
				request.setAttribute("issued", user.issued);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginNFCJson.jsp");	//admin�������� �Ѿ�ϴ�.
				dispatcher.forward(request, response);
				
			}
			
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

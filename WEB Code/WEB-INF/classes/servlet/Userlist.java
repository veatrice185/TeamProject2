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

import model.User;
import model.UmbrellaBox;
import model.DelayUser;
import model.UserDataManager;
import model.History;

/**
 * Servlet implementation class UserServlet
 */
//관리자 용 웹 페이지에서 데이터베이스에 기록된 이용 로그를 보여주거나 현재 우산박스별 현황 정보를 보여주는 기능을 하는 서블릿 페이지입니다.
@WebServlet(urlPatterns = { "/Userinfo" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost:3306/module5?useUnicode=true&amp;characterEncoding=UTF-8"),
		@WebInitParam(name = "dbUser", value = "root"),
		@WebInitParam(name = "dbPass", value = "webclass") })
public class Userlist extends HttpServlet {
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
	public Userlist() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		processRequest(request, response);		
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<History> slist = userDataMan.getHistory();

		HttpSession session = request.getSession(true);		
		session.setAttribute("userListSimple", slist);
		
		response.setHeader("Cache-Control", "no-cache"); // no cache for
															// HTTP
															// 1.1
		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<meta name = \"viewport\" content = \"width=device-width,initial-scale=1\">");
		out.println("<title> List of Delay USER</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table id=\"table\" border=\"1\" style=\"width: 300px;\">");
		out.println("<th colspan=\"6\" style=\"height: 30px\"><b>HISTORY</b></th>");
		out.println("<tr>");
		out.println("<td>NUMBER</td>");
		out.println("<td>CUSTOMER ID</td>");
		out.println("<td>RENT TIME</td>");
		out.println("<td>ISSUED</td>");
		out.println("</tr>");
		for (History data : slist) {
			out.println("<tr>");
			out.println("<td>"+data.number +"</td>");
			out.println("<td>"+data.customerid +"</td>");
			out.println("<td>"+data.renttime +"</td>");
			out.println("<td>"+data.issuedcheck +"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<br>");
		//count2 = 0;
		//out.println("<a href=\"index.html\">"+ "Click here to go back to index page " + "</a>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


			ArrayList<UmbrellaBox> slist = userDataMan.getUmbrellaBox();

 			HttpSession session = request.getSession(true);		
			session.setAttribute("userListSimple", slist);
			
			response.setHeader("Cache-Control", "no-cache"); // no cache for
																// HTTP
																// 1.1
			response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
			response.setDateHeader("Expires", 0); // always expires

			response.setContentType("text/html");

			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<meta name = \"viewport\" content = \"width=device-width,initial-scale=1\">");
			out.println("<title> Welcome from a servlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<table id=\"table\" border=\"1\" style=\"width: 300px;\">");
			out.println("<th colspan=\"6\" style=\"height: 30px\"><b>Box list</b></th>");
			out.println("<tr>");
			out.println("<td>BOX ID</td>");
			out.println("<td>Location</td>");
			out.println("<td>Quantity of Umbrella</td>");
			out.println("<td>Broken Umbrella</td>");
			out.println("</tr>");
			for (UmbrellaBox data : slist) {
				out.println("<tr>");
				out.println("<td>"+data.boxid +"</td>");
				out.println("<td>"+data.location +"</td>");
				out.println("<td>"+data.quantityUMB +"</td>");
				out.println("<td>"+data.brokenUMB +"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<br>");
			//count2 = 0;
			//out.println("<a href=\"index.html\">"+ "Click here to go back to index page " + "</a>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
		
		
		
	
}
	

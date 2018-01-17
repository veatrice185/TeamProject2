package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDataManager;

/**
 * Servlet implementation class BrokenBoxAdd
 */
//고장난 우산 신고를 담당하는 서블릿 페이지 입니다.
//태블릿의 안드로이드에서 고장난 우산 신고를 선택하였을 경우에 작동합니다.
@WebServlet(urlPatterns = { "/BrokenBoxAdd" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost:3306/module5?useUnicode=true&amp;characterEncoding=UTF-8"),
		@WebInitParam(name = "dbUser", value = "root"),
		@WebInitParam(name = "dbPass", value = "webclass") })
public class BrokenBoxAdd extends HttpServlet {
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
	
	
    public BrokenBoxAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		response.setHeader("Cache-Control", "no-cache"); // no cache for
		// HTTP
		// 1.1
		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
	try{
		System.out.println("e");
		

			userDataMan.AddBrokenBox(1);
			out.println("<html>");
			out.println("<head>");
			out.println("<meta name = \"viewport\" content = \"width=device-width,initial-scale=1\">");
			out.println("<title> List of Delay USER</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("Broken Umbrella Adding Success");
			out.println("</body>");
			out.println("</html>");
		
	

	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

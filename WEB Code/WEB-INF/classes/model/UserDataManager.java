package model;

//데이터베이스와 통신하기 위해 생성한 데이터매니저 클래스입니다.

import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;



import javax.servlet.http.HttpServletRequest;


public class UserDataManager {
	private String dburl = "";
	private String dbuser = "";
	private String dbpass = "";
	
	public void setDbUrl(String url) {
		dburl = url;
	}
	
	public  String getDbUrl() {
		return dburl;
	}
	
	public void setDbUser(String user) {
		dbuser = user;
	}
	
	public  String getDbUser() {
		return dbuser;
	}
	
	public void setDbPass(String pass) {
		dbpass = pass;
	}
	
	public  String getDbPass () {
		return dbpass;
	}
	
	/**
	 * Function for opening connection to the database
	 */
	private Statement state =null;
	
	//데이터베이스와 연결을 시도합니다.
	public  Connection getConnection() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(getDbUrl(),getDbUser(),getDbPass());			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * Function to close open connection to database
	 */
	public void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	/**
	 * Obtain the list of all users
	 * Simple mode
	 */
		
	//관리자인지 아닌지를 데이터베이스로부터 확인합니다.
	public boolean Adminlogin(String id, String password) throws SQLException {		//로그인할 경우이다.
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = null;
		rs = st.executeQuery("SELECT * FROM manager where id='" + id
				+ "'");
		if (rs.next()) {
			if (password.equals(rs.getString("password"))) {
				System.out.println("로그인에 성공하였습니다");
				return true;
			} else {
				System.out.println("비밀번호가 잘못되었습니다");
				return false;
			}
		} else {
			System.out.println("아이디가 없습니다");
			return false;
		}
	}
	
	//사용자로 등록된 사람인지를 체크하기 위해 데이터베이스로부터 확인합니다.
	public boolean Userlogin(String nfctagid) throws SQLException {		//로그인할 경우이다.
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = null;
		rs = st.executeQuery("SELECT * FROM user where nfctagid='" + nfctagid
				+ "'");
		if (rs.next()) {
			if(nfctagid.equals(rs.getString("nfctagid")))
			{
			System.out.println("로그인에 성공하였습니다");
			return true;
			}
	
			else 
			{
			System.out.println("아이디가 없습니다");
			return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	//사용자인 경우, 데이터베이스로부터 관련 정보를 받아옵니다.
	public User getUser(String nfctagid){

		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User tmp = new User();
		if(conn != null) {
			try {
				System.out.println("겟유저가 문제");
				String sqlQuery = "SELECT * from user where nfctagid ='"+nfctagid+ "'";
				ps = conn.prepareStatement(sqlQuery);
			
				rs = ps.executeQuery();
				System.out.println("겟유저가 문제2");
				if(rs.next()) {
				
				tmp.setNfcTagId(nfctagid);
				tmp.setCustomerId(rs.getString("customerid"));
					//System.out.println("qqq");
				tmp.setPhonenumber(rs.getString("phonenumber"));
				tmp.setUserType(rs.getInt("usertype"));
				tmp.setIssued(rs.getInt("issued"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);	
			}
		}
		return tmp;
	}
	
	
	//각 우산함의 상태를 웹으로 출력해야하므로 어레이리스트를 사용하여 모두 받아옵니다.
	public ArrayList<UmbrellaBox> getUmbrellaBox(){
		ArrayList<UmbrellaBox> list = new ArrayList<UmbrellaBox>();
		
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
				String sqlQuery = "SELECT * from umbrellabox";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				while(rs.next()) {
					UmbrellaBox tmp = new UmbrellaBox();
					tmp.setboxId(rs.getInt("boxid"));
					//System.out.println("qqq");
					tmp.setLocation(rs.getString("location"));
					tmp.setQuantityUMB(rs.getInt("quantityUMB"));
					tmp.setBrokenUMB(rs.getInt("brokenUMB"));
					list.add(tmp);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);	
			}
		}
		return list;
	}
	
	
	
	
	//연체된 사용자들에 대한 정보를 모두 받아 어레이리스트로 저장합니다.
	public ArrayList<DelayUser> getDelayUser(){
		ArrayList<DelayUser> list = new ArrayList<DelayUser>();
		
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
				String sqlQuery = "SELECT * from delay_info";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				while(rs.next()) {
					DelayUser tmp = new DelayUser();
					tmp.setUserId(rs.getString("userid"));
					//System.out.println("qqq");
					tmp.setPhonenumber(rs.getString("phonenumber"));
					tmp.setDay(rs.getString("day"));
					list.add(tmp);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);	
			}
		}
		return list;
	}
	
	//대여에 대한 로그를 어레이리스트로 저장합니다.
	public ArrayList<History> getHistory(){
		ArrayList<History> list = new ArrayList<History>();
		
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
				String sqlQuery = "SELECT * from history";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				while(rs.next()) {
					History tmp = new History();
					tmp.setNumber(rs.getInt("number"));
					//System.out.println("qqq");
					tmp.setCustomerId(rs.getString("customerid"));
					tmp.setRentTime(rs.getString("renttime"));
					tmp.setIssuedCheck(rs.getInt("issuedcheck"));
					list.add(tmp);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);	
			}
		}
		return list;
	}
	
	
	//우산을 빌릴 경우의 쿼리문입니다.우산을 빌렸으므로 우산함에서 우산의 양을 하나 줄여줍니다.
	public void borrow(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE umbrellabox SET quantityUMB = quantityUMB-1 where boxid = '"+boxid+"'");
		System.out.println("k");
	}
	
	//우산을 반납할 경우의 쿼리문입니다. 우산을 반납하였으므로 우산함에서 우산의 양을 하나 늘려줍니다.
	public void returnUMB(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE umbrellabox SET quantityUMB = quantityUMB+1 where boxid = '"+boxid+"'");
		System.out.println("v");
	}
	
	//고장난 우산 수거를 하였을때의 쿼리문입니다. 수거하였으므로 고장난 우산의 개수를 0으로 바꿔줍니다.
	
	public void returnBrokenBox(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE umbrellabox SET brokenUMB = 0 where boxid = '"+boxid+"'");
		System.out.println("u");
	}
	
	//고장난 우산 신고를 선택하였을때의 쿼리문입니다. 고장난 우산의 개수를 하나 늘려줍니다.
	public void AddBrokenBox(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE umbrellabox SET brokenUMB = brokenUMB+1 where boxid = '"+boxid+"'");
		System.out.println("u");
	}
	
	//우산을 빌렸을때의 쿼리문입니다. 우산을 빌렸으므로 해당 nfc tag id를 가진 사람이 우산을 가졌다고 표시해줍니다.
	
	public void issued(String nfctagid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE user SET issued = 1 where nfctagid = '"+nfctagid+"'");
		System.out.println("u");
	}
	
	//우산을 반납하였을때의 쿼리문입니다. 우산을 반납하였으므로 해당 nfc tag id를 가진 사람이 우산을 빌리지 않았다고 표시합니다.
	
	public void unissued(String nfctagid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE user SET issued = 0 where nfctagid = '"+nfctagid+"'");
		System.out.println("u");
	}
	
	
	//우산함으로부터 우산의 개수를 받아오는 쿼리문입니다. 우산의 개수가 1개 이하이거나 6개 이상이면 false를 리턴합니다.
	
	public boolean checkstatus(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		int temp;
		ResultSet rs = state.executeQuery("SELECT quantityUMB FROM umbrellabox where boxid = '"+boxid+"'");
		System.out.println("q");
		if(rs.next())
		{
			
			temp = rs.getInt("quantityUMB");
			if(temp<1 || temp>6)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
		
	}
	
	//고장난 우산개수를 출력합니다. 고장난 우산의 개수가 0이면 없으므로 false를 리턴합니다.
	
	public boolean brokencheckstatus(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		int temp;
		ResultSet rs = state.executeQuery("SELECT brokenUMB FROM umbrellabox where boxid = '"+boxid+"'");
		System.out.println("q");
		if(rs.next())
		{			
			temp = rs.getInt("brokenUMB");
			if(temp==0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
		
	}
	
	//우산을 빌린 시간에 대한 쿼리문입니다. 발생한 년 - 월 - 일 - 시간 순으로 된 데이터를 history에 추가합니다.
	
	public void rentTime(String customerid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();

		long curTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = sdf.format(new Date(curTime));
		
		System.out.println(now);
		System.out.println(customerid);
		//state.executeUpdate("INSERT INTO history (customerid,renttime,issuedcheck) values ('"+customerid+"' , '"+now+"',0)");
		state.executeUpdate("INSERT INTO history (customerid,renttime,issuedcheck) values ('"+customerid+"' , '"+now+"',1)");

		System.out.println("u");
	}
	
	//우산을 반납한 시간에 대한 쿼리문입니다. 발생한 년 - 월 - 일 - 시간 순으로 된 데이터를 history에 추가합니다.
	
	public void returnTime(String customerid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();

		long curTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = sdf.format(new Date(curTime));
		
		System.out.println(now);
		System.out.println(customerid);
		state.executeUpdate("INSERT INTO history (customerid,renttime,issuedcheck) values ('"+customerid+"' , '"+now+"',0)");
		System.out.println("u");
	}
	
	//고장난 우산 신고를 했을때의 쿼리문입니다. 발생한 년 - 월 - 일 - 시간 순으로 된 데이터를 history에 추가합니다.
	
	public void brokenTime(String customerid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();

		long curTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = sdf.format(new Date(curTime));
		
		System.out.println(now);
		System.out.println(customerid);
		state.executeUpdate("INSERT INTO history (customerid,renttime,issuedcheck) values ('"+customerid+"' , '"+now+"',0)");
		System.out.println("u");
	}
	

	
}


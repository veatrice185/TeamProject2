package model;

//�����ͺ��̽��� ����ϱ� ���� ������ �����͸Ŵ��� Ŭ�����Դϴ�.

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
	
	//�����ͺ��̽��� ������ �õ��մϴ�.
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
		
	//���������� �ƴ����� �����ͺ��̽��κ��� Ȯ���մϴ�.
	public boolean Adminlogin(String id, String password) throws SQLException {		//�α����� ����̴�.
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = null;
		rs = st.executeQuery("SELECT * FROM manager where id='" + id
				+ "'");
		if (rs.next()) {
			if (password.equals(rs.getString("password"))) {
				System.out.println("�α��ο� �����Ͽ����ϴ�");
				return true;
			} else {
				System.out.println("��й�ȣ�� �߸��Ǿ����ϴ�");
				return false;
			}
		} else {
			System.out.println("���̵� �����ϴ�");
			return false;
		}
	}
	
	//����ڷ� ��ϵ� ��������� üũ�ϱ� ���� �����ͺ��̽��κ��� Ȯ���մϴ�.
	public boolean Userlogin(String nfctagid) throws SQLException {		//�α����� ����̴�.
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = null;
		rs = st.executeQuery("SELECT * FROM user where nfctagid='" + nfctagid
				+ "'");
		if (rs.next()) {
			if(nfctagid.equals(rs.getString("nfctagid")))
			{
			System.out.println("�α��ο� �����Ͽ����ϴ�");
			return true;
			}
	
			else 
			{
			System.out.println("���̵� �����ϴ�");
			return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	//������� ���, �����ͺ��̽��κ��� ���� ������ �޾ƿɴϴ�.
	public User getUser(String nfctagid){

		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User tmp = new User();
		if(conn != null) {
			try {
				System.out.println("�������� ����");
				String sqlQuery = "SELECT * from user where nfctagid ='"+nfctagid+ "'";
				ps = conn.prepareStatement(sqlQuery);
			
				rs = ps.executeQuery();
				System.out.println("�������� ����2");
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
	
	
	//�� ������� ���¸� ������ ����ؾ��ϹǷ� ��̸���Ʈ�� ����Ͽ� ��� �޾ƿɴϴ�.
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
	
	
	
	
	//��ü�� ����ڵ鿡 ���� ������ ��� �޾� ��̸���Ʈ�� �����մϴ�.
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
	
	//�뿩�� ���� �α׸� ��̸���Ʈ�� �����մϴ�.
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
	
	
	//����� ���� ����� �������Դϴ�.����� �������Ƿ� ����Կ��� ����� ���� �ϳ� �ٿ��ݴϴ�.
	public void borrow(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE umbrellabox SET quantityUMB = quantityUMB-1 where boxid = '"+boxid+"'");
		System.out.println("k");
	}
	
	//����� �ݳ��� ����� �������Դϴ�. ����� �ݳ��Ͽ����Ƿ� ����Կ��� ����� ���� �ϳ� �÷��ݴϴ�.
	public void returnUMB(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE umbrellabox SET quantityUMB = quantityUMB+1 where boxid = '"+boxid+"'");
		System.out.println("v");
	}
	
	//���峭 ��� ���Ÿ� �Ͽ������� �������Դϴ�. �����Ͽ����Ƿ� ���峭 ����� ������ 0���� �ٲ��ݴϴ�.
	
	public void returnBrokenBox(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE umbrellabox SET brokenUMB = 0 where boxid = '"+boxid+"'");
		System.out.println("u");
	}
	
	//���峭 ��� �Ű� �����Ͽ������� �������Դϴ�. ���峭 ����� ������ �ϳ� �÷��ݴϴ�.
	public void AddBrokenBox(int boxid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE umbrellabox SET brokenUMB = brokenUMB+1 where boxid = '"+boxid+"'");
		System.out.println("u");
	}
	
	//����� ���������� �������Դϴ�. ����� �������Ƿ� �ش� nfc tag id�� ���� ����� ����� �����ٰ� ǥ�����ݴϴ�.
	
	public void issued(String nfctagid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE user SET issued = 1 where nfctagid = '"+nfctagid+"'");
		System.out.println("u");
	}
	
	//����� �ݳ��Ͽ������� �������Դϴ�. ����� �ݳ��Ͽ����Ƿ� �ش� nfc tag id�� ���� ����� ����� ������ �ʾҴٰ� ǥ���մϴ�.
	
	public void unissued(String nfctagid) throws SQLException {		
		
		Connection conn = getConnection();
		state = conn.createStatement();
		
		state.executeUpdate("UPDATE user SET issued = 0 where nfctagid = '"+nfctagid+"'");
		System.out.println("u");
	}
	
	
	//��������κ��� ����� ������ �޾ƿ��� �������Դϴ�. ����� ������ 1�� �����̰ų� 6�� �̻��̸� false�� �����մϴ�.
	
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
	
	//���峭 ��갳���� ����մϴ�. ���峭 ����� ������ 0�̸� �����Ƿ� false�� �����մϴ�.
	
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
	
	//����� ���� �ð��� ���� �������Դϴ�. �߻��� �� - �� - �� - �ð� ������ �� �����͸� history�� �߰��մϴ�.
	
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
	
	//����� �ݳ��� �ð��� ���� �������Դϴ�. �߻��� �� - �� - �� - �ð� ������ �� �����͸� history�� �߰��մϴ�.
	
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
	
	//���峭 ��� �Ű� �������� �������Դϴ�. �߻��� �� - �� - �� - �ð� ������ �� �����͸� history�� �߰��մϴ�.
	
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


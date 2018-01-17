<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import ="java.sql.*"%>
<%@ page import ="java.io.*"%>
<%@ page import ="java.util.*"%>
<%@ page import ="java.net.*"%>
<%@ page import = "org.json.simple.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>User JSON</title>
</head>
<body>

</body>
</html>
<%
	String DB_URL = "jdbc:mysql://localhost:3306/module5?useUnicode=true&amp;characterEncoding=UTF-8";
	String DB_USER = "root";
	String DB_PASSWORD = "webclass";
	
	Connection conn= null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver ok");
		conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
		System.out.println("ok");
		
		String sqlQuery = "SELECT * from user";
		ps = conn.prepareStatement(sqlQuery);
		rs = ps.executeQuery();

		
		JSONArray arr = new JSONArray();
		
		while(rs.next())
		{

			int customerid = rs.getInt("customerid");
			int nfctagid = rs.getInt("nfctagid");
			String phonenumber = rs.getString("phonenumber");
			int usertype = rs.getInt("usertype");
			int issued = rs.getInt("issued");
			JSONObject obj = new JSONObject();
			
			obj.put("customerid",customerid);
			obj.put("nfctagid",nfctagid);
			obj.put("phonenumber",phonenumber);
			obj.put("usertype",usertype);
			obj.put("issued",issued);
			if(obj!=null)
			{
				arr.add(obj);
			}
		}
		out.print(arr);
	}catch(Exception e){
		System.out.println("접속실패");
		e.printStackTrace();
	}
%>	
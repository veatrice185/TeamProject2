<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import ="java.sql.*"%>
<%@ page import ="java.io.*"%>
<%@ page import ="java.util.*"%>
<%@ page import ="java.net.*"%>
<%@ page import = "model.User" %>
<%@ page import = "org.json.simple.*"%>

<%
	String nfctagid = (String)request.getAttribute("nfctagid");
	String customerid = (String)request.getAttribute("customerid");
	String phonenumber = (String)request.getAttribute("phonenumber");	
	int usertype = (int)request.getAttribute("usertype");
	int issued = (int)request.getAttribute("issued");

	System.out.println(nfctagid);
	JSONObject obj = new JSONObject();
		
	
	obj.put("customerid",customerid);
	obj.put("nfctagid",nfctagid);
	obj.put("phonenumber",phonenumber);
	obj.put("usertype",usertype);
	obj.put("issued",issued);
	
	out.print(obj);

%>	
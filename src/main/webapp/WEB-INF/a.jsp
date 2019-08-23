<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p> 진홍 승환 형래 현욱 하린 기준 </p>
	<p> <% out.println("Server Info : " + org.apache.catalina.util.ServerInfo.getServerInfo()); %> </p>
	<p> <% out.println("Server built: "  +org.apache.catalina.util.ServerInfo.getServerBuilt()); %> </p>
	<p>  <% out.println("Server Number : " + org.apache.catalina.util.ServerInfo.getServerNumber()); %> </p>
</body>
</html>
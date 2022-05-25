<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.example.javaEEproject.model.DVDItem"%>
<html>
<head>
    <title>Success Page</title>
</head>
<body>
<%
    DVDItem dvd = (DVDItem) request.getAttribute("dvd");
%>
<h1>
    Add DVD Success
</h1>
<br>
<p>You have add the following DVD:</p>
<p><b>Title:</b> <%=dvd.getTitle()%></p>
<p><b>Year:</b> <%=dvd.getYear()%></p>
<p><b>Genre:</b> <%=dvd.getGenre()%></p>
<br><br>
<a href="index.html">Home</a>
</body>
</html>
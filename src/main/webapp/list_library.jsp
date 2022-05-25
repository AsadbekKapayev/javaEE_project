<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@page import="com.example.javaEEproject.model.DVDItem" %>
<%@page import="com.example.javaEEproject.model.DVDLibrary" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>List Library</title>
</head>
<body bgcolor="white">
<br>
<%
    String log = (String) request.getAttribute("log");
    DVDLibrary dvdLibrary = new DVDLibrary("admin");
    List dvdList = dvdLibrary.getDVDCollection();
%>
<p>
    You currently have <b><%=dvdList.size()%></b> DVDs in your collection
</p>
<br>
<table border="2px solid black">
    <%;
        Iterator items = dvdList.iterator();
        boolean result[] = {true,true,true};
        try {
            if(request.getAttribute("result")!=null) {
                result = (boolean[]) request.getAttribute("result");
            }else if(session.getAttribute("result")!=null){
                result = (boolean[]) session.getAttribute("result");
            }else if(request.getParameter("bool")!=null){
                String ns = request.getParameter("bool").toString();
                if(ns.charAt(0)=='0'){
                    result[0] = false;
                }
                if(ns.charAt(1)=='0'){
                    result[1] = false;
                }
                if(ns.charAt(2)=='0'){
                    result[2] = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
    <tr padding='5px'>
        <%if(result[0]){%><th align='center' style='padding:8px'>Title</th><%}
        if(result[1]){%><th align='center' style='padding:8px'>Year</th><%}
        if(result[2]){%><th align='center' style='padding:8px'>Genre</th><%}%>
    </tr>
    <%
        while(items.hasNext()) {
            DVDItem item = (DVDItem) items.next();
    %>
    <tr padding='5px'>
        <% if(result[0]){%><td align='center' style='padding:8px'><%=item.getTitle()%></td>
        <%} if(result[1]){%><td align='center' style='padding:8px'><%=item.getYear()%></td>
        <%} if(result[2]){%><td align='center' style='padding:8px'><%=item.getGenre()%></td><%}}%>
    </tr>
</table>
<br><br>
<a href="index.html">Home</a>
</body>
</html>
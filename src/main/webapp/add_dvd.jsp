<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add DVD</title>
</head>
<body>
<%
    String[] genres = {"Action", "Comedy", "Drama", "Fantasy", "Horror", "Romance", "Cartoon", "Adventure"};
    List errors = (ArrayList) request.getAttribute("errors");
    if(errors!=null) {
%>
<p style="color: red">Please correct the following errors:</p>
<ul>
    <%
        Iterator i = errors.iterator();
        while (i.hasNext()){
            String error = (String) i.next();
    %>
    <li><%=error%></li>
    <%}%>
</ul>
<br>
<%}%>
<h1>Add DVD</h1>
<br>
<form action="add-dvd.do" method="POST">
    <%
        String title = request.getParameter("title");
        if(title == null){
            title="";
        }
    %>
    <label for="title"><b>Title:</b></label>
    <input type="text" id="title" name="title" value='<%=title%>'>
    <br><br>
    <%
        String year = request.getParameter("year");
        if(year == null){
            year="";
        }
    %>
    <label for="year"><b>Year:</b></label>
    <input type="text" id="year" name="year" value='<%=year%>'>
    <br><br>
    <label><b>Genre:</b>
        <select name="Genre">
            <%
                String genre = request.getParameter("Genre");
                if(genre == null || genre.equals("UNKNOWN")){
            %>
            <option value="UNKNOWN">Select...</option>
            <%}
                for(int i = 0; i < genres.length; i++) {
            %>
            <option value="<%=genres[i]%>"
                    <%
                        if (genres[i].equals(genre)){
                    %> selected<%
                }
            %>><%=genres[i]%></option>
            <%}%>
        </select>
    </label>
    <br><br>
    <input type="submit" name="Add" id="Add" value="Add DVD">
</form>
</body>
</html>
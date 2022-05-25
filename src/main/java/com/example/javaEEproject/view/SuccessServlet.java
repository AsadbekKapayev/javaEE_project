package com.example.javaEEproject.view;

import com.example.javaEEproject.model.DVDItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SuccessServlet", value = "/success.view")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        DVDItem dvdItem = (DVDItem) req.getAttribute("dvdItem");

        String pageTitle = "Add DVD Success";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Generate the HTML response
        out.println("<html>");
        out.println("<head>");
        out.println("  <title>" + pageTitle + "</title>");
        out.println("</head>");
        out.println("<body bgcolor='white'>");

        // Generate page heading
        out.println("<!-- Page Heading -->");

        out.println("<h1>" + pageTitle + "</h1>");

        out.println("<p>You have add the following dvd: </p>");
        out.println("<p>Title: ");
        out.println(dvdItem.getTitle() + "</p>");
        out.println("<p>Year: ");
        out.println(dvdItem.getYear() + "</p>");
        out.println("<p>Genre of film: ");
        out.println(dvdItem.getGenre() + "</p>");
        out.println("<br><a href='index.html'>Home</a>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

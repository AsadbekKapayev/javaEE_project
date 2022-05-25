package com.example.javaEEproject.view;

import com.example.javaEEproject.model.DVDLibrary;
import com.example.javaEEproject.model.DVDLibraryInterface;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "AddDVDFormServlet", value = "/add_dvd.view")
public class AddDVDFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String header = req.getHeader("authorization");
        String landp = header.substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(landp);
        String s = new String(decodedBytes, StandardCharsets.UTF_8);
        String name = s.substring(0, s.indexOf(":"));
        System.out.println("name: " + name);

        List<String> errors = (List)req.getAttribute("errors");
        ServletContext ctx = getServletContext();
        //DVDLibraryInterface dvd = new DVDLibrary();
        //List<String> genres = (List)ctx.getAttribute("genres");
        DVDLibraryInterface dvdLibrary = new DVDLibrary(name);
        List<String> genres = dvdLibrary.getGenres();
        String pageTitle = "Add DVD";
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

        out.println("<form action=\"add-dvd.do\" method=\"post\">\n");

        String title = (String)req.getAttribute("title");
        if (title == null) {
            title = "";
        }
        out.println("    <label for=\"title\">Title: </label>\n" +
                "    <input type=\"text\" id=\"title\" name=\"title\" value=\"" + title + "\"><br>\n" );

        String year = (String)req.getAttribute("year");
        if (year == null) {
            year = "";
        }
        out.println("    <label for=\"year\">Year: </label>\n" +
                "    <input type=\"text\" id=\"year\" name=\"year\" value=\"" + year + "\"><br>\n");

        String selectedGenre = (String)req.getAttribute("genres");
        out.println("    <label for=\"genres\">Genre: </label>\n");
        out.println("<select name=\"genres\" id=\"genres\">\n");
        for (String genre: genres) {
            out.println("<option value=\"" + genre + "\"");
            if (genre.equals(selectedGenre)) {
                out.println(" selected");
            }
            out.println(">" + genre + "</option>\n");
        }
        out.println("</select>\n");

        String genre = (String)req.getAttribute("genre");
        if (genre == null) {
            genre = "";
        }
        out.println( "    <label for=\"genre\"> or new genre: </label>\n" +
                "    <input type=\"text\" id=\"genre\" name=\"genre\" value=\"" + genre + "\">\n" );

        out.println("    <input type=\"submit\" value=\"Add DVD\">\n" +
                "</form>");

        if (!errors.isEmpty()) {
            out.println("<p>Please correct the following errors: </p>");
            for (String error: errors) {
                out.println("<p>" + error);
                out.println("</p>");
            }
        }

        out.println("</body>");
        out.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

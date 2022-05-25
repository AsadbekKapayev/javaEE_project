package com.example.javaEEproject.view;

import com.example.javaEEproject.model.DVDItem;
import com.example.javaEEproject.model.DVDLibrary;
import com.example.javaEEproject.model.DVDLibraryInterface;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ListLibraryServlet", value = "/list-library")
public class ListLibraryServlet extends HttpServlet {
    private List libraryList = null;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {

        String header = request.getHeader("authorization");
        String landp = header.substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(landp);
        String s = new String(decodedBytes, StandardCharsets.UTF_8);
        String name = s.substring(0, s.indexOf(":"));
        System.out.println("name: " + name);

        DVDLibraryInterface dvdLibrary = new DVDLibrary(name);

        /*
        ServletContext ctx = getServletContext();
        List<String> prefs = (List)ctx.getAttribute("prefs");

        HashMap<String, List<DVDItem>> dvdsList = (HashMap)ctx.getAttribute("dvds");*/

        libraryList = dvdLibrary.getDVDCollection();
        List<String> prefs = (List) getServletContext().getAttribute("prefs");

        // Set page title
        String pageTitle = "Library of DVDs";

        // Specify the content type is HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Generate the HTML response
        out.println("<html>");
        out.println("<head>");
        out.println("  <title>" + pageTitle + "</title>");
        out.println("</head>");
        out.println("<body bgcolor='white'>");

        out.println("<p>You currently have " + libraryList.size() + " DVDs in your collection: </p><br>");

        // Generate page heading
        out.println("<!-- Page Heading -->");
        out.println("<table>");

        // Generate main body
        out.println("<tr>");
        for (String pref: prefs) {
            if (pref.equals("title")) {
                out.println("<th>Title</th>");
            } else if (pref.equals("year")) {
                out.println("<th>year</th>");
            } else if (pref.equals("genre")) {
                out.println("<th>Genre</th>");
            }
        }
        out.println("</tr>");

        Iterator items = libraryList.iterator();
        while (items.hasNext()) {
            DVDItem DVDItem = (DVDItem) items.next();
            out.println("<tr>");
            for (String pref: prefs) {
                if (pref.equals("title")) {
                    out.println("<td>" + DVDItem.getTitle() + "</td>");
                } else if (pref.equals("year")) {
                    out.println("<td>" + DVDItem.getYear() + "</td>");
                } else if (pref.equals("genre")) {
                    out.println("<td>" + DVDItem.getGenre() + "</td>");
                }
            }
            out.println("</tr>");
        }

        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
    } // END of doGet method
}
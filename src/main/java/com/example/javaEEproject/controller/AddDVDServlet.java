package com.example.javaEEproject.controller;

import com.example.javaEEproject.model.DVDItem;
import com.example.javaEEproject.model.DVDLibrary;
import com.example.javaEEproject.model.DVDLibraryInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "AddDVDServlet", value = "/add-dvd.do")
public class AddDVDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList();
        //DVDLibraryInterface dvdLibrary = new DVDLibrary();

        String header = request.getHeader("authorization");
        String landp = header.substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(landp);
        String s = new String(decodedBytes, StandardCharsets.UTF_8);
        String name = s.substring(0, s.indexOf(":"));
        System.out.println("name: " + name);

        DVDLibraryInterface dvdLibrary = new DVDLibrary(name);

        String title = request.getParameter("title");
        if( title.trim().length() == 0 ) {
            errors.add("Title must be supplied");
        }
        String year = request.getParameter("year");
        if ( year.trim().length() == 0 ) {
            errors.add("Year must be supplied");
        }
        String genre = request.getParameter("Genre");
        /*if ( genre.trim().length() == 0 ) {
            genre = request.getParameter("genres");
        }*/
        if (errors.isEmpty()) {
            DVDItem dvdItem = dvdLibrary.addDVD(title, year, genre);
            request.setAttribute("dvd", dvdItem);
            ServletContext servletContext = getServletContext();
            /*List dvds = (List)servletContext.getAttribute("dvds");
            dvds.add(dvdItem);
            servletContext.setAttribute("dvds", dvds);*/
            RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
            rd.forward(request, resp);
        } else {
            request.setAttribute("title", title);
            request.setAttribute("year", year);
            request.setAttribute("genre", request.getParameter("genre"));
            request.setAttribute("genres", request.getParameter("genres"));
            request.setAttribute("errors", errors);
            //ServletContext servletContext = getServletContext();
            /*RequestDispatcher rd = servletContext.getRequestDispatcher("/add_dvd.view");
            rd.forward(request, resp);*/
            request.setAttribute("errors", errors);
            RequestDispatcher rd = request.getRequestDispatcher("add_dvd.jsp");
            rd.forward(request, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

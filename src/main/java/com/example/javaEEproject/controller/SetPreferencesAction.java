package com.example.javaEEproject.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SetPreferencesAction", value = "/set_prefs.do")
public class SetPreferencesAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String[] prefsReq = req.getParameterValues("dvd");
        ServletContext servletContext = getServletContext();
        List prefs = new ArrayList();
        for (String pref: prefsReq) {
            prefs.add(pref);
        }
        servletContext.setAttribute("prefs", prefs);
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

        out.println("<p>You have chose these preference: </p>");
        for (String pref: prefsReq) {
            out.println("<p>" + pref + "</p>");
        }
        out.println("<br><a href='index.html'>Home</a>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            boolean result[] = {false,false,false};
            String[] checkBox = request.getParameterValues("dvd");
            for(String i:checkBox){
                if(i.equals("title")){
                    result[0]=true;
                }else if(i.equals("year")){
                    result[1]=true;
                }else if (i.equals("genre")){
                    result[2]=true;
                }
            }

            request.setAttribute("result", result);
            session.setAttribute("result",result);
            RequestDispatcher rd = request.getRequestDispatcher("list_library.jsp");
            rd.include(request,response);
            try {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                String n = "";
                if (result[0]){n+=1;}else{n+=0;}
                if (result[1]){n+=1;}else{n+=0;}
                if (result[2]){n+=1;}else{n+=0;}
                out.print("<a href='http://localhost:8080/Lab6_war_exploded;bool=" + n + "'>Main</a>");
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

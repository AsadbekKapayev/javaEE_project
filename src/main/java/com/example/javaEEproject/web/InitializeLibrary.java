package com.example.javaEEproject.web;

import com.example.javaEEproject.model.DVDItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebListener
public class InitializeLibrary implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //DVDLibrary dvdLibrary = new DVDLibrary();

        HashMap<String, List<DVDItem>> dvdsList = new HashMap<>();

        //List<DVDItem> dvds = dvdLibrary.getDVDCollection();
        //List<DVDItem> dvds2 = dvdLibrary.getDVDCollection();

       /* dvds.add(new DVDItem("admin", "admin", "admin"));
        dvdsList.put("admin", dvds);

        dvdsList.put("root", dvds2);

        List<String> genres = dvdLibrary.getGenre();*/
        List<String> prefs = new ArrayList<>();
        prefs.add("title");
        prefs.add("year");
        prefs.add("genre");

        ServletContext ctx = sce.getServletContext();
        String path = ctx.getRealPath("library-file");
        /*try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            dvds.add(new DVDItem(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ctx.setAttribute("dvds", dvdsList);
        //ctx.setAttribute("genres", genres);
        ctx.setAttribute("prefs", prefs);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

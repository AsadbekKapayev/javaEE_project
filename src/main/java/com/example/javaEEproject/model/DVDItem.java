package com.example.javaEEproject.model;

import java.io.Serializable;

public class DVDItem implements Serializable {
    private String title;
    private String year;
    private String genre;

    public DVDItem(String title, String year, String genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }
}
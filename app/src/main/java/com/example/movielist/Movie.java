package com.example.movielist;

import java.util.ArrayList;
import java.util.Date;
public class Movie {

    public static ArrayList<Movie> movieArrayList = new ArrayList<>();
    public static String MOVIE_EDIT_EXTRA = "movieEdit";
    private int id;
    private String title;
    private String description;
    private Date deleted;

    public Movie(int id, String title, String description, Date deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }
    public Movie(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    public static Movie getMovieForID(int passMovieID) {
        for(Movie m : movieArrayList){
            if(m.getId() == passMovieID){
                return m;
            }
        }
        return null;
    }

    public static ArrayList<Movie> nonDeletedMovie(){
        ArrayList<Movie> notDeleted = new ArrayList<Movie>();
        for(Movie m : movieArrayList){
            if(m.getDeleted() == null){
                notDeleted.add(m);
            }
        }
        return notDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}

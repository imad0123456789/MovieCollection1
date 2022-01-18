package com.company.be;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {

    private int Id;
    private String name;
    private Double rating;
    private Double imdbRating;
    private String filelink;
    private Date lastview;
    private List<Category> categories = new ArrayList<>();


    public Movie(int id, String name, Double rating, String filelink, Date lastview) {
        Id = id;
        this.name = name;
        this.rating = rating;
        this.filelink = filelink;
        this.lastview = lastview;
    }
    public Movie(int id, String name, Double rating, Double imdbRating, String filelink, Date lastview) {
        Id = id;
        this.name = name;
        this.rating = rating;
        this.imdbRating = imdbRating;
        this.filelink = filelink;
        this.lastview = lastview;
    }


    public Movie(int id, String name, String filelink) {
        Id = id;
        this.name = name;
        this.filelink = filelink;
    }

    public int getId() {
        return Id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }


    public Date getLastview() {
        return lastview;
    }

    public void setLastview(Date lastview) {
        this.lastview = lastview;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", filelink='" + filelink + '\'' +
                ", lastview=" + lastview +
                '}';
    }
}

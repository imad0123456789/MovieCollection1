package com.company.be;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int Id;
    private String name;
    private List<Movie> allMoviesInCategory = new ArrayList<>();

    public Category(int id, String name) {
        Id = id;
        this.name = name;
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

    public List<Movie> getAllMoviesInCategory() {
        return allMoviesInCategory;
    }

    public void setAllMoviesInCategory(List<Movie> allMoviesInCategory) {
        this.allMoviesInCategory = allMoviesInCategory;
    }
    public void addMovieToCategory(Movie movieToAdd) {
        this.allMoviesInCategory.add(movieToAdd);
    }
}

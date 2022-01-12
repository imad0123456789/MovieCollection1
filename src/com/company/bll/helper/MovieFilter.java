package com.company.bll.helper;

import com.company.be.Movie;
import com.company.dal.DalManager;
import com.company.dal.IDALManager;
import com.company.dal.dao.ExceotionDAO;
import com.company.dal.dao.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieFilter {
    private IDALManager dalManager;
    private final ObservableList<Movie> movies;


    public MovieFilter() throws ExceotionDAO {
        dalManager = new DalManager();
        movies = FXCollections.observableArrayList();
        movies.addAll(dalManager.getAllMovies());
    }


    public ObservableList<Movie> filter (String query){

        System.out.println(" Testing Filter ...");
        ObservableList<Movie> searchMovie;
        searchMovie = FXCollections.observableArrayList();

        for (Movie m: movies){
            String Name = m.getName().toLowerCase();
            String qry = query.toLowerCase();
            if (Name.contains(qry)){
                searchMovie.add(m);
            }
        }
        return searchMovie;
    }
}

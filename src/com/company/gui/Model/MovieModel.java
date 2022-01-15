package com.company.gui.Model;


import com.company.be.Movie;
import com.company.bll.LogicInterfaceManager;
import com.company.bll.Manger;
import com.company.dal.dao.ExceotionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieModel {

    private Manger movieManger;
    private final ObservableList<Movie> movies;


    public MovieModel() throws ExceotionDAO {
        movieManger = new Manger();
        movies = FXCollections.observableArrayList();
        movies.addAll(movieManger.getAllMovies());
    }


    public ObservableList<Movie> getMovies(){
        return movies;
    }



    public void addMovie (String name, String filelink, double imdbRate, double personalRate  ) throws ExceotionDAO{
        movieManger.addMovie(name, filelink/*, imdbRate, personalRate*/);
        updatethelist();

    }

    public void updateMovie(Movie movie, int selectedIndex) throws ExceotionDAO{
        movieManger.updateMovie(movie);
        updatethelist();
    }

    public void deleteMovie (Movie movie) throws ExceotionDAO{
        movieManger.deleteMovie(movie);
        updatethelist();
    }

    private void updatethelist() throws ExceotionDAO{
        movies.setAll(movieManger.getAllMovies());
    }

    public void changeLastViewed(Movie movie) throws ExceotionDAO {
        movieManger.changeDate(movie);
    }
}

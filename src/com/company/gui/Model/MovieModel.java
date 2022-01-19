package com.company.gui.Model;


import com.company.be.Movie;
import com.company.bll.LogicInterfaceManager;
import com.company.bll.Manger;
import com.company.dal.dao.ExceotionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieModel {

    private Manger movieManger;
    private ObservableList<Movie> movies;

    private static MovieModel single_instance = null;

    // Static method
    // Static method to create instance of Singleton class
    public static MovieModel getInstance() {
        if (single_instance == null) {
            try {
                single_instance = new MovieModel();
            } catch (ExceotionDAO e) {
                e.printStackTrace();
            }
        }

        return single_instance;
    }

    public MovieModel() throws ExceotionDAO {
        movieManger = new Manger();
        movies = FXCollections.observableArrayList();
        movies.addAll(movieManger.getAllMovies());
    }


    public ObservableList<Movie> getMovies() {
        // System.out.println(movies);
        return movies;
    }

    public ObservableList<Movie> getRateMovies(String minRating) throws ExceotionDAO {
        // System.out.println(movies);

        ObservableList<Movie> RateMoviesmovies = FXCollections.observableArrayList();
        RateMoviesmovies.addAll( movieManger.getRateMovies(minRating));
        return RateMoviesmovies;
    }


    public void addMovie(String name, String filelink, double imdbRate, double personalRate) throws ExceotionDAO {
        movieManger.addMovie(name, filelink, imdbRate, personalRate);
        updatethelist();

    }

    public void updateMovie(Movie movie/*, int selectedIndex*/) throws ExceotionDAO {
        movieManger.updateMovie(movie);
        updatethelist();
    }

    public void deleteMovie(Movie movie) throws ExceotionDAO {
        movieManger.deleteMovie(movie);
        updatethelist();
    }

    private void updatethelist() throws ExceotionDAO {
        movies = FXCollections.observableArrayList();
        movies.addAll(movieManger.getAllMovies());
    }

    public void changeLastViewed(Movie movie) throws ExceotionDAO {
        movieManger.changeDate(movie);
        updatethelist();
    }

    public void updateMovieRating(Movie selectedItem, Double newRating) throws ExceotionDAO {
        try {
            movieManger.updateMovieRating(selectedItem, newRating);
            updatethelist();

        } catch (ExceotionDAO exceotionDAO) {
            exceotionDAO.printStackTrace();
        }


    }
}


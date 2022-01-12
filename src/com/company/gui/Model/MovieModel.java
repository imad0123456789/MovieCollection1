package com.company.gui.Model;


import com.company.be.Movie;
import com.company.bll.IMovieManager;
import com.company.bll.MovieManger;
import com.company.dal.dao.ExceotionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieModel {

    private IMovieManager movieManger;
    private final ObservableList<Movie> movies;


    public MovieModel() throws ExceotionDAO {
        movieManger = new MovieManger();
        movies = FXCollections.observableArrayList();
        movies.addAll(movieManger.getAllMovies());
    }


    public ObservableList<Movie> getMovies(){
        return movies;
    }


    /*
    public void addMovie (String name, Double rating , String filelink , String lastview ) throws ExceotionDAO{
        movieManger.add(new Movie(1, name,rating,filelink,lastview));
        updatethelist();

    }
*/

    public void addMovie (String name, String filelink  ) throws ExceotionDAO{
        movieManger.add(name, filelink);
        updatethelist();

    }

    public void updateMovie(Movie movie, int selectedIndex) throws ExceotionDAO{
        movieManger.update(movie);
        updatethelist();
    }

    public void deleteMovie (Movie movie) throws ExceotionDAO{
        movieManger.delete(movie);
        updatethelist();
    }

    private void updatethelist() throws ExceotionDAO{
        movies.setAll(movieManger.getAllMovies());
    }

    public void changeLastViewed(Movie movie) throws ExceotionDAO {
        movieManger.changeDate(movie);
    }
}

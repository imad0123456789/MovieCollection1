package com.company.dal;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;

import java.util.List;

public interface IDALManager {
    List<Movie> getAllMovies() throws ExceotionDAO;
    List<Movie> getRateMovies(String minRating) throws ExceotionDAO;

    void addMovie (String name, String filepath) throws ExceotionDAO;

    void addMovie(String name, String filepath, Double rating, Double imbdRating) throws ExceotionDAO;

    void updateMovie (Movie movie) throws ExceotionDAO;

    void deleteMovie (Movie movie) throws ExceotionDAO;

    // method to change the rate
    Movie changeRate (Movie movie , Double newRate) throws ExceotionDAO;

    // method to change the date

    Movie changeDate (Movie movie) throws ExceotionDAO;


    List<Category> getAllCategory() throws ExceotionDAO;


    void addCategory (String name ) throws ExceotionDAO;

    void updateCategory (Category category) throws ExceotionDAO;

    void deleteCategory (Category category) throws ExceotionDAO;

    public List<Movie> GetMovieInCat(int Id) throws ExceotionDAO;

    public void addMovieToCat(Category category , Movie movie) throws ExceotionDAO;



    //remove Category

    public  void removeMovieFromCategory ( Movie movie)  throws ExceotionDAO;


    public void removeFromCategory (Category category , Movie movie)throws ExceotionDAO;


    public void removeFromCat (Category category) throws ExceotionDAO;

    public Movie updateMovieRating(Movie selectedItem, Double newRating) throws ExceotionDAO;
}

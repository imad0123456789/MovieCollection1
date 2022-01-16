package com.company.bll;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.bll.helper.MovieFilter;
import com.company.dal.DalManager;
import com.company.dal.IDALManager;
import com.company.dal.dao.ExceotionDAO;
import javafx.collections.ObservableList;

import java.util.List;

public class Manger implements LogicInterfaceManager {

    private final IDALManager dalManager;

    private final MovieFilter searchforMovie;


    //IDALManager dalManager = new DalManager();

    public Manger() throws ExceotionDAO {
        dalManager = new DalManager();
        searchforMovie = new MovieFilter();
    }



    @Override
    public List<Movie> getAllMovies() throws ExceotionDAO {
        return dalManager.getAllMovies();
    }

    @Override
    public void addMovie(String name, String filepath) throws ExceotionDAO {
        dalManager.addMovie(name,filepath);
    }


    @Override
    public void updateMovie(Movie movie) throws ExceotionDAO {
        dalManager.updateMovie(movie);
    }

    @Override
    public void deleteMovie(Movie movie) throws ExceotionDAO {
        dalManager.deleteMovie(movie);
    }


    @Override
    public Movie changeRate(Movie movie, Double newRate) throws ExceotionDAO {
        return null;
    }

    @Override
    public Movie changeDate(Movie movie) throws ExceotionDAO {
        return dalManager.changeDate(movie);
    }

    @Override
    public ObservableList<Movie> searchMovie(ObservableList<Movie> currentMovies, String movieToFind) {
        return null;
    }

    @Override
    public List<Category> getAllCategory() throws ExceotionDAO {
       try {
           return dalManager.getAllCategory();
       } catch (ExceotionDAO exceotionDAO) {
           exceotionDAO.printStackTrace();
       }
        return null;
    }


    @Override
    public void addCategory(Category category) throws ExceotionDAO {
        dalManager.addCategory(category);
    }

    @Override
    public void updateCategory(Category category) throws ExceotionDAO {
        dalManager.updateCategory(category);
    }

    @Override
    public void deleteCategory(int categoryId)  {
    try {
        dalManager.deleteCategory(categoryId);
    } catch (ExceotionDAO exceotionDAO) {
        exceotionDAO.printStackTrace();
    }
    }



    @Override
    public List<Movie> GetMovieInCat(int Id) throws ExceotionDAO {
        return null;
    }

    @Override
    public void addMovieToCat(Category category, Movie movie) throws ExceotionDAO {
        dalManager.addMovieToCat(category,movie);
    }

    @Override
    public void removeMovieFromCategory(Movie movie) throws ExceotionDAO {

    }

    @Override
    public void removeFromCategory(Category category, Movie movie) throws ExceotionDAO {

    }

    @Override
    public void removeFromCat(Category category) throws ExceotionDAO {

    }



    @Override
    public Movie updateMovieRating(Movie selectedItem, Double newRating) throws ExceotionDAO {
        try {
            return dalManager.updateMovieRating(selectedItem, newRating);
        } catch (ExceotionDAO ex) {
            throw new ExceotionDAO(ex.getMessage());
        }

    }
}

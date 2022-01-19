package com.company.dal;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.CatMovDAO;
import com.company.dal.dao.CategoryDAO;
import com.company.dal.dao.ExceotionDAO;
import com.company.dal.dao.MovieDAO;

import java.util.List;

public class DalManager implements IDALManager{

    private MovieDAO movieDao;
    private CategoryDAO categoryDAO;
    private CatMovDAO catMovDAO;
    private static MyDatabaseConnector databaseConnector;

    public DalManager() {
        databaseConnector = new MyDatabaseConnector();
        this.catMovDAO = new CatMovDAO(databaseConnector);
        this.movieDao = new MovieDAO(this.catMovDAO, databaseConnector);
        this.categoryDAO = new CategoryDAO(this.catMovDAO,databaseConnector);
    }

    @Override
    public List<Movie> getAllMovies() throws ExceotionDAO {
        return movieDao.getAllMovies();
    }

    @Override
    public List<Movie> getRateMovies(String minRating) throws ExceotionDAO {
        return movieDao.getRateMovies( minRating);
    }

    @Override
    public void addMovie(String name, String filepath) throws ExceotionDAO {

    }


    @Override
    public void addMovie(String name, String filepath, Double rating, Double imbdRating) throws ExceotionDAO {
        movieDao.add(name,filepath, rating, imbdRating);
    }

    @Override
    public void updateMovie(Movie movie) throws ExceotionDAO {
        movieDao.update(movie);
    }

    @Override
    public void deleteMovie(Movie movie) throws ExceotionDAO {
        movieDao.delete(movie);
    }

    @Override
    public Movie changeRate(Movie movie, Double newRate) throws ExceotionDAO {
        return null;
    }

    @Override
    public Movie changeDate(Movie movie) throws ExceotionDAO {
        return movieDao.changeDate(movie);
    }

    @Override
    public List<Category> getAllCategory() throws ExceotionDAO {
        return categoryDAO.getAllCategory();
    }

    @Override
    public void addCategory(String name) throws ExceotionDAO {
        categoryDAO.add(name);
    }

    @Override
    public void updateCategory(Category category) throws ExceotionDAO {

    }

    @Override
    public void deleteCategory(Category category) throws ExceotionDAO {
        categoryDAO.delete(category);

    }

    @Override
    public List<Movie> GetMovieInCat(int Id) throws ExceotionDAO {
        return null;
    }

    @Override
    public void addMovieToCat(Category category, Movie movie) throws ExceotionDAO {
        catMovDAO.addMovieToCat(category,movie);
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
        return movieDao.updateMovieRating(selectedItem, newRating);
    }
}

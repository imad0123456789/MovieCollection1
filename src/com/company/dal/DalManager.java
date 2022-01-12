package com.company.dal;

import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;
import com.company.dal.dao.MovieDAO;

import java.util.List;

public class DalManager implements IDALManager{

    private MovieDAO movieDao;
    private static MyDatabaseConnector databaseConnector;

    public DalManager() {
        databaseConnector = new MyDatabaseConnector();
        this.movieDao = new MovieDAO(databaseConnector);
    }

    @Override
    public List<Movie> getAllMovies() throws ExceotionDAO {
        return movieDao.getAllMovies();
    }

    @Override
    public void add(String name, String filepath) throws ExceotionDAO {
        movieDao.add(name,filepath);
    }

    @Override
    public void update(Movie movie) throws ExceotionDAO {
        movieDao.update(movie);
    }

    @Override
    public void delete(Movie movie) throws ExceotionDAO {
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
}

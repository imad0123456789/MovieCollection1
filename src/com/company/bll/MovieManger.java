package com.company.bll;

import com.company.be.Movie;
import com.company.dal.DalManager;
import com.company.dal.IDALManager;
import com.company.dal.dao.ExceotionDAO;
import com.company.dal.dao.MovieDAO;

import java.util.List;

public class MovieManger implements IMovieManager {

    IDALManager dalManager = new DalManager();

    @Override
    public List<Movie> getAllMovies() throws ExceotionDAO {
        return dalManager.getAllMovies();
    }

    @Override
    public void add(String name, String filepath) throws ExceotionDAO {
        dalManager.add(name,filepath);
    }


    @Override
    public void update(Movie movie) throws ExceotionDAO {
        dalManager.update(movie);
    }

    @Override
    public void delete(Movie movie) throws ExceotionDAO {
        dalManager.delete(movie);
    }


    @Override
    public Movie changeRate(Movie movie, Double newRate) throws ExceotionDAO {
        return null;
    }

    @Override
    public Movie changeDate(Movie movie) throws ExceotionDAO {
        return dalManager.changeDate(movie);
    }
}

package com.company.dal;

import com.company.be.Movie;

import java.util.List;

public class MovieDAO implements DALMovie {


    @Override
    public List<Movie> getAllMovies() throws ExceotionDAO {
        return null;
    }

    @Override
    public void add(Movie movie) throws ExceotionDAO {

    }

    @Override
    public void update(Movie movie) throws ExceotionDAO {

    }

    @Override
    public void delete(Movie movie) throws ExceotionDAO {

    }

    @Override
    public Movie changeRate(Movie movie, Double newRate) throws ExceotionDAO {
        return null;
    }

    @Override
    public Movie changeDate(Movie movie) throws ExceotionDAO {
        return null;
    }
}

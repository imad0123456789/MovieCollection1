package com.company.dal;

import com.company.be.Movie;

import java.util.List;


public interface DALMovie {

    List<Movie> getAllMovies() throws ExceotionDAO;

    void add (Movie movie) throws ExceotionDAO;

    void update (Movie movie) throws ExceotionDAO;

    void delete (Movie movie) throws ExceotionDAO;

    // method to change the rate
    Movie changeRate (Movie movie , Double newRate) throws ExceotionDAO;

    // method to change the date

    Movie changeDate (Movie movie) throws ExceotionDAO;

}

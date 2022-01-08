package com.company.dal;


import com.company.be.Category;
import com.company.be.Movie;

import java.util.List;

public interface DALCatMov {

    public List<Movie> GetMovieInCat() throws ExceotionDAO;

    public void addMovieToCat(Category category , Movie movie) throws ExceotionDAO;


    //remove Category

    public  void removeMovieFromCategory ( Movie movie)  throws ExceotionDAO;

    public void removeFromCategory (Category category , Movie movie)throws ExceotionDAO;

    public void removeFromCat (Category category) throws ExceotionDAO;



}

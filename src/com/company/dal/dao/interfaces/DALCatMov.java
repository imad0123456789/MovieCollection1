package com.company.dal.dao.interfaces;


import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;

import java.util.List;

public interface DALCatMov {

    public List<Movie> getMovieInCat(int Id) throws ExceotionDAO;

    public List<Category> getCatInMovie(int Id) throws ExceotionDAO;

    public void addMovieToCat(Category category , Movie movie) throws ExceotionDAO;



    //remove Category

    public  void removeMovieFromCategory ( Movie movie)  throws ExceotionDAO;


    public void removeFromCategory (Category category , Movie movie)throws ExceotionDAO;


    public void removeFromCat (Category category) throws ExceotionDAO;



}

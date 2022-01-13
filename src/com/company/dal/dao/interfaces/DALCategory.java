package com.company.dal.dao.interfaces;


import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;

import java.util.List;

public interface DALCategory {


    List<Category> getAllCategory() throws ExceotionDAO;


    void add (Category category ) throws ExceotionDAO;

    void update (Category category) throws ExceotionDAO;

    void delete (int categoryId) throws ExceotionDAO;

    public void addToCategory(Category selectedItem, Movie selectedMovie) throws ExceotionDAO;



}

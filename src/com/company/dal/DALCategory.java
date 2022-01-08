package com.company.dal;


import com.company.be.Category;

import java.util.List;

public interface DALCategory {
    List<Category> getAllMovies() throws ExceotionDAO;

    void add (Category category ) throws ExceotionDAO;

    void update (Category category) throws ExceotionDAO;

    void delete (Category category) throws ExceotionDAO;


}

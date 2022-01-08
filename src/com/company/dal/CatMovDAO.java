package com.company.dal;

import com.company.be.Category;
import com.company.be.Movie;

import java.util.List;

public class CatMovDAO implements DALCatMov {
    @Override
    public List<Movie> GetMovieInCat() throws ExceotionDAO {
        return null;
    }

    @Override
    public void addMovieToCat(Category category, Movie movie) throws ExceotionDAO {

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
}

package com.company.gui.Model;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.bll.LogicInterfaceManager;
import com.company.bll.Manger;
import com.company.dal.dao.ExceotionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CategoryModel {


    private LogicInterfaceManager bllManager;
    private Manger categoryManger;

    private final ObservableList<Category> categories;

    private static CategoryModel single_instance = null;

    // Static method
    // Static method to create instance of Singleton class
    public static CategoryModel getInstance()
    {
        if (single_instance == null) {
            try {
                single_instance = new CategoryModel();
            } catch (ExceotionDAO e) {
                e.printStackTrace();
            }
        }

        return single_instance;
    }
    public CategoryModel() throws ExceotionDAO {
        this.bllManager = new Manger();

        categories = FXCollections.observableArrayList();
        categories.setAll(this.bllManager.getAllCategory());
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }


    public void addCategory(String name) throws ExceotionDAO {
        bllManager.addCategory(name);
        updateTheList();
    }

    public void deleteCategory(Category category) throws ExceotionDAO {
        bllManager.deleteCategory(category);
        updateTheList();
    }

    public void updateTheList() throws ExceotionDAO {
        categories.setAll(bllManager.getAllCategory());
    }


    public void addToCategory(Category selectedItem, int selectedIndex, Movie selectedMovie) throws ExceotionDAO {
        try {
            bllManager.addMovieToCat(selectedItem, selectedMovie);
            selectedItem.addMovieToCategory(selectedMovie);
        } catch (ExceotionDAO ex) {
            throw new ExceotionDAO(ex.getMessage());
        }
    }

    public void removeMovieFromCategory (Category selectedItem, int selectedIndex, Movie selectedMovie , int movieIndex) throws ExceotionDAO {
        try{
            bllManager.removeFromCategory(selectedItem,selectedMovie);
            List<Movie> newList = selectedItem.getAllMoviesInCategory();
            newList.remove(movieIndex);
            selectedItem.setAllMoviesInCategory(newList);
            categories.set(selectedIndex, selectedItem);
        } catch (ExceotionDAO exceotionDAO) {
            exceotionDAO.printStackTrace();
        }
    }


    public ObservableList<Category> getCurrentCategories() throws ExceotionDAO {
        return categories;
    }
}

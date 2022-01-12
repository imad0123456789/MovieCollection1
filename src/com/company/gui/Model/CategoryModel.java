package com.company.gui.Model;

import com.company.be.Category;
import com.company.bll.CategoryManger;
import com.company.dal.dao.ExceotionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryModel {


    private CategoryManger categoryManger;
    private final ObservableList<Category> categories;

    public CategoryModel() throws ExceotionDAO {
        categoryManger = new CategoryManger();
        categories = FXCollections.observableArrayList();
        categories.setAll(categoryManger.getAllCategory());
    }

    public ObservableList<Category> getCategories(){
        return categories;
    }

    public void addCategory(Category category) throws ExceotionDAO{
        categoryManger.add(category);
    }

    public void deleteCategory(int id) throws ExceotionDAO {
        categoryManger.delete(id);
    }

    public void updateTheList()throws ExceotionDAO{
        categories.setAll(categoryManger.getAllCategory());
    }
}

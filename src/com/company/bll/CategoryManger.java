package com.company.bll;

import com.company.be.Category;
import com.company.dal.dao.CategoryDAO;
import com.company.dal.dao.interfaces.DALCategory;
import com.company.dal.dao.ExceotionDAO;

import java.util.List;

public class CategoryManger implements DALCategory {
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public List<Category> getAllCategory() throws ExceotionDAO {
        return categoryDAO.getAllCategory();
    }

    @Override
    public void add(Category category) throws ExceotionDAO {
        categoryDAO.add(category);
    }

    @Override
    public void update(Category category) throws ExceotionDAO {
        categoryDAO.update(category);
    }

    @Override
    public void delete(int categoryId) throws ExceotionDAO {
        categoryDAO.delete(categoryId);
    }
}

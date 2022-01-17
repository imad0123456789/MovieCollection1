package com.company.dal.dao;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.interfaces.DALCategory;
import com.company.dal.MyDatabaseConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DALCategory {

    private final CatMovDAO catMovDAO;

    private MyDatabaseConnector databaseConnector;


    public CategoryDAO(CatMovDAO catMovDAO, MyDatabaseConnector databaseConnector) {
        this.catMovDAO = catMovDAO;

        this.databaseConnector = databaseConnector;
    }

    private Connection con;

    @Override
    public List<Category> getAllCategory() throws ExceotionDAO {
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Category";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");

                Category category = new Category(id, name);
                category.setAllMoviesInCategory(this.catMovDAO.getMovieInCat(id));
                categories.add(category);
            }


        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return categories;
    }


    @Override
    public void add(String name) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Category (name) VALUES (?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);

            pst.executeUpdate();


        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    @Override
    public void update(Category category) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "UPDATE Category set name=? WHERE Id = (?) ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, category.getName());
            pst.executeUpdate();


        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }



    @Override
    public void delete(Category category) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Category WHERE Id = (?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, category.getId());
            pst.executeUpdate();

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void addToCategory(Category selectedItem, Movie selectedMovie) throws ExceotionDAO {
        try {
            catMovDAO.addMovieToCat(selectedItem, selectedMovie);
        } catch (ExceotionDAO exceotionDAO) {
            exceotionDAO.printStackTrace();
        }
    }

}

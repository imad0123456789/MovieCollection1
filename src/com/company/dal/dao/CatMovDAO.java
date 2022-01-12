package com.company.dal.dao;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.interfaces.DALCatMov;
import com.company.dal.MyDatabaseConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatMovDAO implements DALCatMov {

    private static MyDatabaseConnector databaseConnector;

    public CatMovDAO() {
        databaseConnector = new MyDatabaseConnector();
    }

    private Connection con;


    @Override
    public List<Movie> GetMovieInCat(int Id) throws ExceotionDAO {
        ArrayList<Movie> moviesInCat = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT Movie.* FROM CatMovie INNER JOIN Movie ON CatMovie.MovieId = Movie.Id WHERE  CatMovie.CategoryId=? Order BY CategoryId desc;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                int id = rs.getInt("Id");
                String name = rs.getString("name");
                Double rating = rs.getDouble("rating");
                String filelink = rs.getString("filelink");
                Date lastview = rs.getDate("lastview");

                Movie movie = new Movie(id, name, rating, filelink, lastview);
                moviesInCat.add(movie);

            }


        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return moviesInCat;
    }


    @Override
    public void addMovieToCat(Category category, Movie movie) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "INSERT INTO CatMovie(CategoryId, MovieId) VALUES (?,?) ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, category.getId());
            pst.setInt(2, movie.getId());
            pst.executeUpdate();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    @Override
    public void removeMovieFromCategory(Movie movie) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE FROM CatMovie WHERE   MovieId = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, movie.getId());

            pst.executeUpdate();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }



    @Override
    public void removeFromCategory(Category category, Movie movie) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE FROM CatMovie WHERE CategoryId= ? MovieId=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, category.getId());
            pst.setInt(2, movie.getId());
            pst.executeUpdate();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }


    @Override
    public void removeFromCat(Category category) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE FROM CatMovie WHERE CategoryId= ?  ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, category.getId());
            pst.executeUpdate();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}


package com.company.dal.dao;

import com.company.be.Movie;
import com.company.dal.MyDatabaseConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO  {

    private final CatMovDAO catMovDAO;
    private MyDatabaseConnector databaseConnector;

    public MovieDAO(CatMovDAO catMovDAO, MyDatabaseConnector databaseConnector) {
        this.catMovDAO = catMovDAO;
        this.databaseConnector = databaseConnector;
    }

    private Connection con;



    public List<Movie> getAllMovies() throws ExceotionDAO {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Movie";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                Double rating = rs.getDouble("rating");
                String filelink = rs.getString("filelink");
                Date lastView = rs.getDate("lastView");

                Movie movie = new Movie(id, name, rating, filelink, lastView);
                movie.setCategories(this.catMovDAO.getCatInMovie(id));
                movies.add(movie);
            }


        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return movies;
    }


    public void add(String name, String filepath) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Movie (name , filelink ) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, filepath);

            pst.executeUpdate();


        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    public void update(Movie movie) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie set name=?, rating=? , filelink=? , lastview=? WHERE Id = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, movie.getName());
            pst.setDouble(2, movie.getRating());
            pst.setString(3, movie.getFilelink());
            pst.setDate(4, (Date) movie.getLastview());

            pst.executeUpdate();

            //Movie mov = new Movie(id, name , rating , filelink , lastview);


        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    public void delete(Movie movie) throws ExceotionDAO {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Movie WHERE Id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, movie.getId());
            pst.executeUpdate();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    public Movie changeRate(Movie movie, Double newRate) throws ExceotionDAO {
        return null;
    }


    public Movie changeDate(Movie movie) throws ExceotionDAO {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie set lastview=? WHERE Id = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, date);
            pst.setInt(2, movie.getId());
            pst.executeUpdate();
            movie.setLastview(date);



        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return movie;
    }
}

package com.company.gui.Controller;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;
import com.company.gui.Model.CategoryModel;
import com.company.gui.Model.MovieModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private Button NewMovie;
    @FXML
    private TableColumn<Movie, String> CatMovieName;
    @FXML
    private TableView<Category> categoryTableView ;
    @FXML
    private TableColumn<Category, String> CategoryNames;
    @FXML
    private TableColumn<Movie, Date> movieLastviewColumn;
    @FXML
    private TableColumn<Movie, String> movieNameColumn;
    @FXML
    private TableColumn<Movie, Double> movieRatingColumn;
    @FXML
    private TableColumn<Movie, String> movieUrlColumn;
    @FXML
    private TableView<Movie> moviesTabelView;
    @FXML
    private TableView<Movie> movieInPlaylist;
    @FXML
    private Button PlayBut;


    private ObservableList<Movie> observableListMovie;
    private ObservableList<Category> observableListCategory;

    private MovieModel movieModel = new MovieModel();
    private CategoryModel categoryModel = new CategoryModel();
    private Object IllegalArgumentException;
    private Object IOException;
    private Object ExceotionDAO;

    public MainController() throws ExceotionDAO {
        //List<Movie> listMov = movieModel.getMovies();
        //System.out.println(listMov);
        //movieModel.changeLastViewed(listMov.get(1));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //categoryModel = CategoryModel.getInstance();
        //movieModel = MovieModel.getInstance();

        try {
            observableListMovie = movieModel.getMovies();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try {
            observableListCategory = categoryModel.getCategories();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        moviesTabelView.setItems(observableListMovie);
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        movieRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        movieUrlColumn.setCellValueFactory(new PropertyValueFactory<>("filelink"));
        movieLastviewColumn.setCellValueFactory(new PropertyValueFactory<>("lastview"));


        categoryTableView.setItems(observableListCategory);
        CategoryNames.setCellValueFactory(new PropertyValueFactory<>("name"));

        CatMovieName.setCellValueFactory(new PropertyValueFactory<>("name"));


    }





    public void clickNewMovie(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/company/gui/View/Movie.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void displayMovieInPlaylist(MouseEvent event) {
        movieInPlaylist.getItems().clear();
        List<Movie> toBeAddedMovieList = categoryTableView.getSelectionModel().getSelectedItem().getAllMoviesInCategory();
        for ( Movie m : categoryTableView.getSelectionModel().getSelectedItem().getAllMoviesInCategory()){
            movieInPlaylist.getItems().add(m);
        }

    }


    public void displayMovieInPlaylist(javafx.scene.input.MouseEvent event) {
        movieInPlaylist.getItems().clear();
        List<Movie> toBeAddedMovieList = categoryTableView.getSelectionModel().getSelectedItem().getAllMoviesInCategory();
        for ( Movie m : categoryTableView.getSelectionModel().getSelectedItem().getAllMoviesInCategory()){
            movieInPlaylist.getItems().add(m);
        }
    }

    public void playMovie(ActionEvent actionEvent) {
        try {
            play();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private void play() throws IOException, ExceotionDAO {

        //Desktop.getDesktop().open(new File(movieInPlaylist.getSelectionModel().getSelectedItem().getFilelink()));
        Desktop.getDesktop().open(new File(moviesTabelView.getSelectionModel().getSelectedItem().getFilelink()));
        movieModel.changeLastViewed(moviesTabelView.getSelectionModel().getSelectedItem());

        //movieModel.updateMovie(movieInPlaylist.getSelectionModel().getSelectedItem(), movieInPlaylist.getSelectionModel().getSelectedIndex());
    }


    }

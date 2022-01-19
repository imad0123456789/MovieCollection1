package com.company.gui.Controller;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;
import com.company.dal.dao.interfaces.DALCategory;
import com.company.gui.Model.CategoryModel;
import com.company.gui.Model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    public TableColumn<Movie, Integer> moviesInCategory;
    @FXML
    private Button NewMovie;
    @FXML
    private TableColumn<Movie, String> CatMovieName;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> CategoryNames;
    @FXML
    private TableColumn<Movie, Date> movieLastviewColumn;
    @FXML
    private TableColumn<Movie, String> movieNameColumn;
    @FXML
    private TableColumn<Movie, Double> movieRatingColumn;
    @FXML
    private TableColumn<Movie, Double> movieImdbRatingColumn;
    @FXML
    private TableColumn<Movie, String> movieUrlColumn;
    @FXML
    private TableView<Movie> moviesTabelView;
    @FXML
    private TableView<Movie> movieInPlaylist;
    @FXML
    private Button PlayBut;
    @FXML
    private Button removeMovieBut;
    @FXML
    private ComboBox<String> choices;



    private ObservableList<Movie> observableListMovie;
    private ObservableList<Category> observableListCategory;
    private ObservableList<Movie> observableRateMovie;


    private MovieModel movieModel = MovieModel.getInstance();

    private CategoryModel categoryModel = CategoryModel.getInstance();


    public MainController() throws ExceotionDAO {
        //List<Movie> listMov = movieModel.getMovies();
        //System.out.println(listMov);
        //movieModel.changeLastViewed(listMov.get(1));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        choices.setItems(FXCollections.observableArrayList("0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
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

        // After Rating
        try {
            observableRateMovie = movieModel.getRateMovies();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        moviesTabelView.setItems(observableListMovie);
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        movieRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        movieUrlColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
        movieLastviewColumn.setCellValueFactory(new PropertyValueFactory<>("lastview"));
        movieImdbRatingColumn.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));


        categoryTableView.setItems(observableListCategory);
        CategoryNames.setCellValueFactory(new PropertyValueFactory<>("name"));

        //moviesInCategory.setCellValueFactory(new PropertyValueFactory<>("Id"));
        CatMovieName.setCellValueFactory(new PropertyValueFactory<>("name"));


    }


    public void clickNewMovie(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/company/gui/View/Movie.fxml"));
        Parent root = (Parent) loader.load();
        loader.<MovieController>getController().setController(this);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
    public void clickNewCategory(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/company/gui/View/Category.fxml"));
        Parent root = (Parent) loader.load();
        loader.<CategoryController>getController().setController(this);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void displayMovieInPlaylist (javafx.scene.input.MouseEvent event) {
        movieInPlaylist.getItems().clear();
        for (Movie m : categoryTableView.getSelectionModel().getSelectedItem().getAllMoviesInCategory()) {
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

    private void refresh(){
        movieInPlaylist.getItems().clear();
        for (Movie m : categoryTableView.getSelectionModel().getSelectedItem().getAllMoviesInCategory()) {
            movieInPlaylist.getItems().add(m);
        }
    }

    public  void refreshMovie(boolean isEditing){
        moviesTabelView.getItems().clear();
        try {
            moviesTabelView.setItems(movieModel.getMovies());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (isEditing){
            movieInPlaylist.getItems().clear();
            refresh();
        }
    }

    /*
    public  void refreshCategory(boolean isEditing){
        categoryTableView.getItems().clear();
        try{
            categoryTableView.setItems(categoryModel.getCategories());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
*/
    public  void refreshCategory(boolean isEditing) {
        try {
            observableListCategory = categoryModel.getCurrentCategories();
            categoryTableView.setItems(observableListCategory);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


        private void play() throws IOException, ExceotionDAO {

        //Desktop.getDesktop().open(new File(movieInPlaylist.getSelectionModel().getSelectedItem().getFilelink()));
        Desktop.getDesktop().open(new File(movieInPlaylist.getSelectionModel().getSelectedItem().getFilelink()));
        movieModel.changeLastViewed(movieInPlaylist.getSelectionModel().getSelectedItem());
        refreshMovie(false);


        //movieModel.updateMovie(movieInPlaylist.getSelectionModel().getSelectedItem(), movieInPlaylist.getSelectionModel().getSelectedIndex());
    }


    public void addMovieToCategory(ActionEvent actionEvent) {
        if (categoryTableView.getSelectionModel().getSelectedIndex() != -1 && moviesTabelView.getSelectionModel().getSelectedIndex() != -1) {
            try {
                categoryModel.addToCategory(categoryTableView.getSelectionModel().getSelectedItem(), categoryTableView.getSelectionModel().getFocusedIndex(), moviesTabelView.getSelectionModel().getSelectedItem());
                refresh();

            } catch (com.company.dal.dao.ExceotionDAO exceotionDAO) {
                exceotionDAO.printStackTrace();
            }
        }
        //refreshMovie(false);
    }

    public void deleteMovie(ActionEvent actionEvent) throws ExceotionDAO {
        if (moviesTabelView.getSelectionModel().getSelectedIndex() != -1) {
            try {
                movieModel.deleteMovie(moviesTabelView.getSelectionModel().getSelectedItem());
                refreshMovie(false);
            } catch (com.company.dal.dao.ExceotionDAO exceotionDAO) {
                exceotionDAO.printStackTrace();

            }
        }
    }


    public void deleteCategory(ActionEvent actionEvent) throws ExceotionDAO {
        if (categoryTableView.getSelectionModel().getSelectedIndex() != -1) {
            try {
                categoryModel.deleteCategory(categoryTableView.getSelectionModel().getSelectedItem());

            } catch (com.company.dal.dao.ExceotionDAO exceotionDAO) {
                exceotionDAO.printStackTrace();
                refreshCategory(false);
            }
        }
    }


    public void clickEditMovie(javafx.event.ActionEvent actionEvent) throws IOException {
        if (moviesTabelView.getSelectionModel().getSelectedIndex() != -1) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/company/gui/View/editMovie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            MovieController mc = loader.getController();

            mc.setCurrentMovie(moviesTabelView.getSelectionModel().getSelectedItem());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }


    public void removeMovie(ActionEvent actionEvent) {
        if (movieInPlaylist.getSelectionModel().getSelectedIndex() != -1 && categoryTableView.getSelectionModel().getSelectedIndex() !=-1 ){
            try{
                //System.out.println(movieInPlaylist.getSelectionModel().getSelectedItem().getId());
                categoryModel.removeMovieFromCategory(categoryTableView.getSelectionModel().getSelectedItem(), categoryTableView.getSelectionModel().getFocusedIndex() , movieInPlaylist.getSelectionModel().getSelectedItem() , movieInPlaylist.getSelectionModel().getSelectedIndex());
                refresh();
            } catch (ExceotionDAO exceotionDAO) {
                exceotionDAO.printStackTrace();
            }
        }
    }


/*
    public void rateMovie(ActionEvent actionEvent) {
        String personalRateString = choices.getSelectionModel().getSelectedItem();
        double personalRate = Double.parseDouble(personalRateString);
        Movie movieRat = movieInPlaylist.getSelectionModel().getSelectedItem();

        if (movieRat != null){
            try {
                movieModel.updateMovieRating(movieRat , personalRate);
                refreshMovie(false);
            } catch (ExceotionDAO exceotionDAO) {
                exceotionDAO.printStackTrace();
            }
        }




    }

 */

    public void displayRateMovie (javafx.scene.input.MouseEvent event) {
        moviesTabelView.getItems().clear();
        for (Movie m : categoryTableView.getSelectionModel().getSelectedItem().getAllMoviesInCategory()) {
            moviesTabelView.getItems().add(m);

        }
    }
}


package com.company.gui.Controller;

import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;
import com.company.gui.Model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class MovieController implements Initializable {
    @FXML
    private Button ClikeChoose;
    @FXML
    private Button Save;
    @FXML
    private AnchorPane addMovie;
    @FXML
    private TextField txt_file_url;
    @FXML
    private TextField txt_name;
    @FXML
    private ComboBox<String> imdb_rate;
    @FXML
    private ComboBox<String> personal_rate;


    private Movie currentMovie;
    private MovieModel movieModel = MovieModel.getInstance();
    private MainController mainController ;
    private final ObservableList<Movie> contactList = FXCollections.observableArrayList();
    private Object Date;

    public MovieController() throws ExceotionDAO {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        imdb_rate.setItems(FXCollections.observableArrayList("0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        personal_rate.setItems(FXCollections.observableArrayList("0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));

    }


    private void getAllEntries() throws SQLException, ExceotionDAO {
        contactList.setAll(movieModel.getMovies());
    }

    public void ChooseLocation(javafx.event.ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("MP4", "*.mp4"),
                new FileChooser.ExtensionFilter("MPEG4", "*.mpeg4")

        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            // pickUpPathField it's your TextField fx:id
            txt_file_url.setText(file.getPath());
        } else {
            System.out.println("error"); // or something else
        }
    }


    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(Movie currentMovie) {
        this.currentMovie = currentMovie;
        txt_name.setText(currentMovie.getName());
        txt_file_url.setText(currentMovie.getFilelink());

    }

    public void clickToAddMovie(ActionEvent actionEvent) throws ExceotionDAO {

        String name = txt_name.getText();
        String filelink = txt_file_url.getText();
        String imdbRateString = imdb_rate.getSelectionModel().getSelectedItem();
        String personalRateString = personal_rate.getSelectionModel().getSelectedItem();

        double personalRate = Double.parseDouble(personalRateString);
        double imdbRate = Double.parseDouble(imdbRateString);

        List<Movie> listMov = movieModel.getMovies();
        System.out.println(listMov);

        movieModel.addMovie(name, filelink, personalRate, imdbRate);
        this.mainController.refreshMovie(false);
        Stage stage = (Stage) Save.getScene().getWindow();
        stage.close();

    }

    public void clickToEditMovie(ActionEvent actionEvent) throws ExceotionDAO {

        currentMovie.setName(txt_name.getText());
        currentMovie.setFilelink(txt_file_url.getText());
        String imdbRateString = imdb_rate.getSelectionModel().getSelectedItem();
        String personalRateString = personal_rate.getSelectionModel().getSelectedItem();

        currentMovie.setRating(Double.parseDouble(personalRateString));
        currentMovie.setImdbRating(Double.parseDouble(imdbRateString));



        List<Movie> listMov = movieModel.getMovies();
        System.out.println(listMov);

        movieModel.updateMovie(currentMovie);
        //this.mainController.refreshMovie(false);
        Stage stage = (Stage) Save.getScene().getWindow();
        stage.close();
    }


    public void setController(MainController mainController) {
        this.mainController = mainController;
    }


}

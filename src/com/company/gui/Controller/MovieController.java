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
    private TextField imdb_rate;

    @FXML
    private TextField personal_rate;



    private MovieModel movieModel = new MovieModel();
    private final ObservableList<Movie> contactList = FXCollections.observableArrayList();
    private Object Date;

    public MovieController() throws ExceotionDAO {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    public void clickToEditMovie(ActionEvent actionEvent) throws ExceotionDAO {

        String name = txt_name.getText();
        String filelink = txt_file_url.getText();
        String imdbRateString = imdb_rate.getText();
        String personalRateString = personal_rate.getText();

        double personalRate = Double.parseDouble(personalRateString);
        double imdbRate = Double.parseDouble(imdbRateString);

        List<Movie> listMov = movieModel.getMovies();
        System.out.println(listMov);

        //movieModel.updateMovie(name, filelink, personalRate, imdbRate);
        Stage stage = (Stage) Save.getScene().getWindow();
        stage.close();
    }


    public void clickToAddMovie(ActionEvent actionEvent) throws ExceotionDAO {

        String name = txt_name.getText();
        String filelink = txt_file_url.getText();
        String imdbRateString = imdb_rate.getText();
        String personalRateString = personal_rate.getText();

        double personalRate = Double.parseDouble(personalRateString);
        double imdbRate = Double.parseDouble(imdbRateString);

        List<Movie> listMov = movieModel.getMovies();
        System.out.println(listMov);

        movieModel.addMovie(name, filelink, personalRate, imdbRate);
        Stage stage = (Stage) Save.getScene().getWindow();
        stage.close();
    }
}

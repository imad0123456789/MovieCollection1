package com.company;


import com.company.dal.ExceotionDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gui/View/MainView.fxml"));
        stage.setTitle("Movies");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {
        // write your code here
        launch(args);
    }
}

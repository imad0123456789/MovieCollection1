package com.company.gui.Controller;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;
import com.company.gui.Model.CategoryModel;
import com.company.gui.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    @FXML
    private TextField txt_name;
    @FXML
    private Button Save;


    private CategoryModel categoryModel = new CategoryModel();

    public CategoryController() throws ExceotionDAO {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void clickToAddCategory(ActionEvent actionEvent) throws ExceotionDAO {

        String name = txt_name.getText();



        //categoryModel.addCategory(name);
        Stage stage = (Stage) Save.getScene().getWindow();
        stage.close();
    }
}

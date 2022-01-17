package com.company.gui.Controller;

import com.company.be.Category;
import com.company.be.Movie;
import com.company.dal.dao.ExceotionDAO;
import com.company.gui.Model.CategoryModel;
import com.company.gui.Model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


    private CategoryModel categoryModel =  CategoryModel.getInstance();
    private final ObservableList<Category> contactList = FXCollections.observableArrayList();
    MainController mainController;

    public CategoryController() throws ExceotionDAO {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void getAllEntries () throws ExceotionDAO {
        contactList.setAll(categoryModel.getCategories());
    }


    public void clickToAddCategory(ActionEvent actionEvent) throws ExceotionDAO {

        String name = txt_name.getText();


        List<Category> listCat = categoryModel.getCategories();
        System.out.println(listCat);


        categoryModel.addCategory(name);
        mainController.refreshCategory(false);
        Stage stage = (Stage) Save.getScene().getWindow();
        stage.close();
    }

    public void setController(MainController mainController) {
        this.mainController = mainController;
    }
}



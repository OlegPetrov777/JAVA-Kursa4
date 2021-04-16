package main.java.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import main.java.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu porductsButton;

    @FXML
    private Menu customersButton;

    @FXML
    private Menu ordersButton;

    @FXML
    private Menu aboutButton;

    @FXML
    private Menu profileButton;

    @FXML
    private Label labelG;

    @FXML
    private MenuItem exitButton;

    @FXML
    void initialize() {
        exitButton.setOnAction(vent -> {
            exit();
        });
    }

    @FXML
    private void exit() {
        System.out.println("Close MAIN page");
        labelG.getScene().getWindow().hide();
    }

    public static void loadView() {
        try {
            System.out.println("Open MAIN page");
            Stage primaryStage = new Stage();
            primaryStage.setTitle("MenuBar");
            Parent root = FXMLLoader.load(MenuBarController.class.getResource("/views/MenuBar.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

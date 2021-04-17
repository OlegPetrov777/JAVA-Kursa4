package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuBarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane viewPane;

    @FXML
    private Menu productsButton;

    @FXML
    private MenuItem openProducts;

    @FXML
    private Menu customersButton;

    @FXML
    private Menu ordersButton;

    @FXML
    private Menu aboutButton;

    @FXML
    private Menu profileButton;

    @FXML
    private MenuItem exitButton;


    @FXML
    void initialize() {
        loadViewAnotherPage("main");

        openProducts.setOnAction(event -> {
            loadViewAnotherPage("products");
        });

        exitButton.setOnAction(event -> {
            exit();
        });
    }


    @FXML
    private void exit() {
        System.out.println("Close MAIN page");
        borderPane.getScene().getWindow().hide();
    }

    private void loadViewAnotherPage(String text) {
        try {
            viewPane.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + text + ".fxml"));
            Parent root = loader.load();

            viewPane.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadView() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainController.class.getResource("/views/menuBar.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

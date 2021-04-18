package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controllers.Products.ProductsController;

public class MenuBarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem mainpageButton;

    @FXML
    private MenuItem closeButton;

    @FXML
    private Menu productsButton;

    @FXML
    private MenuItem openProducts;

    @FXML
    private Menu customersButton;

    @FXML
    private MenuItem openCustomers;

    @FXML
    private Menu ordersButton;

    @FXML
    private MenuItem openOrders;

    @FXML
    private Menu aboutButton;

    @FXML
    private MenuItem openAboutUs;

    @FXML
    private Menu profileButton;

    @FXML
    private MenuItem openProdfile;

    @FXML
    private MenuItem exitButton;

    @FXML
    private AnchorPane viewPane;

    public static Stage primaryStage = new Stage();

    @FXML
    void initialize() {
        loadViewAnotherPage("main");

        mainpageButton.setOnAction(event -> {
            loadViewAnotherPage("main");
        });

        closeButton.setOnAction(event -> {
            exit();
        });

        openProducts.setOnAction(event -> {
            loadViewAnotherPage("products");
        });

        customersButton.setOnAction(event -> {
            loadViewAnotherPage("customers");
        });

        openOrders.setOnAction(event -> {
            loadViewAnotherPage("orders");
        });

        openAboutUs.setOnAction(event -> {
            loadViewAnotherPage("about");
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
            primaryStage.setTitle("GreensPark APP");
            Parent root = FXMLLoader.load(ProductsController.class.getResource("/views/menuBar.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.controller.LoginController;


public class Main extends Application {
    private Stage primaryStage;


    public static void main(String[] args) {
        System.out.println("Launching Application");
        launch(args);
    }

    @Override
    public void init() throws Exception {
        System.out.println("Application inits");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Application starts");
        LoginController.loadView(primaryStage);
    }
}

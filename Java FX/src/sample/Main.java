package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controllers.LoginController;

import java.io.IOException;

public class Main extends Application {

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

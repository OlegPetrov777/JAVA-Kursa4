package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.controller.LoginController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        LoginController.loadView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

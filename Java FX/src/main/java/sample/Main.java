package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.LoginController;
import sample.utils.RestAPI;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    public static RestAPI session = new RestAPI();

    /**
     * Точка входа в программу
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Старт
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GreensPark APP");
        this.primaryStage.setResizable(false);  // Не позволяет изменять размеры окна
        showLoginPage();
    }

    /**
     * Загрузка страницы Login
     */
    public void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/views/Login.fxml"));
            Parent root = loader.load();

            primaryStage.setScene(new Scene(root));

            LoginController controller = loader.getController();
            controller.clickButtons();

            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

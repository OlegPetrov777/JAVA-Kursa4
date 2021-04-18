package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Main;
import sample.exception.AppException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label alarmLabel;


    @FXML
    public void initialize() {
    }

    @FXML
    private void clickButtons() {
        /* НАЖАТИЕ КНОПОК */
        loginButton.setOnAction(event -> {
            signIn();
        });

        exitButton.setOnAction(event -> {
            exit();
        });
    }

    @FXML
    private void signIn() {
        /* ВХОД В АККАУНТ */
        try {
            String login = "oleg";  // login
            String password = "1234";  // password

            if (loginTextField.getText().equals(login) && passwordField.getText().equals(password)) {

                /* ОТКРЫВАЕТСЯ ГЛАВНЫЙ ЭКРАН (MainScreen) */
                MenuBarController.loadView();

                /* ЗАКРЫВАЕТСЯ СТРАНИЦА LOGIN */
                exit();
            } else if (loginTextField.getText().isEmpty()) {
                throw new AppException("Введите логин.");
            } else if (passwordField.getText().isEmpty()) {
                throw new AppException("Введите пароль.");
            } else {
                throw new AppException("Неверный логин или пароль.");
            }
        }
        catch (AppException e) {
            alarmLabel.setText(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            exit();
        }
    }

    @FXML
    private void exit() {
        exitButton.getScene().getWindow().hide();
    }

    public static void loadView(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/views/login.fxml"));
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

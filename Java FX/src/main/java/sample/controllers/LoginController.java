package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import sample.Main;
import sample.exception.AppException;

public class LoginController {

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
    public void initialize() {}

    /**
     * Отслеживание нажитсй кнопок (Sign In / Exit)
     */
    @FXML
    public void clickButtons() {
        loginButton.setOnAction(event -> signIn());
        exitButton.setOnAction(event -> exit());

        // АКТИВАЦИЯ "Sign In" ЧЕРЕЗ "Enter"
        loginTextField.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                signIn();
            }
        });
    }

    /**
     *  Проверка введенногологина и пароля
     *  Если условие - True, тогда откроется новое окно MenuBar
     */
    @FXML
    private void signIn() {

        try {
            String login = "root";  // login
            String password = "root";  // password

            if (loginTextField.getText().equals(login) && passwordField.getText().equals(password)) {

                /* ОТКРЫВАЕТСЯ ГЛАВНЫЙ ОКНО (MenuBar) */
                MenuBarController.showMenuBarPage();

                /* ЗАКРЫВАЕТСЯ СТРАНИЦА LOGIN */
                exit();

                /* ОБРАБОТКА ОШИБОК */
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

    /**
     *  Закрываю страницу Login
     */
    @FXML
    private void exit() {
        exitButton.getScene().getWindow().hide();
    }
}

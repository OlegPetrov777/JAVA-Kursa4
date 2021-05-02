package sample.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MenuBarController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button mainButton;

    @FXML
    private Button productsButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button techinfoButton;

    @FXML
    private Button statButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane viewPane;
    public static Stage primaryStage = new Stage();

    /**
     * Загрузка основного раздела на главный Pane (view Pane)
     */
    @FXML
    void initialize() {
        showAnotherPage("MainPage");
    }

    /**
     * Обработка нажатий кнопок в меню баре
     */
    @FXML
    public void clickButtons() {
        mainButton.setOnAction(event -> showAnotherPage("MainPage"));

        exitButton.setOnAction(event -> exit());

        productsButton.setOnAction(event -> showAnotherPage("Products/Products"));

        techinfoButton.setOnAction(event -> showAnotherPage("TechnicalInfo/TechnicalInfo"));

        statButton.setOnAction(event -> showAnotherPage("Statistics"));

        ordersButton.setOnAction(event -> showAnotherPage("Orders/Orders"));

        aboutButton.setOnAction(event -> showAnotherPage("About"));
    }

    /**
     * Передаю название FXML файла в зависимоти от нажатой кнопки
     * Загружаем на главный Pane (view Pane)
     */
    private void showAnotherPage(String text) {
        try {
            viewPane.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + text + ".fxml"));
            Parent root = loader.load();

            viewPane.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Загрузка страницы MenuBar
     */
    public static void showMenuBarPage() {
        try {
            primaryStage.setTitle("GreenSpark APP");

            FXMLLoader loader = new FXMLLoader(MenuBarController.class.getResource("/views/MenuBar.fxml"));

            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);  // Не позволяет изменять размеры окна

            MenuBarController controller = loader.getController();
            controller.clickButtons();

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Закрывается главная страница MenuBar
     */
    @FXML
    private void exit() {
        borderPane.getScene().getWindow().hide();
    }
}

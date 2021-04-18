package sample.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MenuBarController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem mainpageButton;

    @FXML
    private MenuItem closeButton;

    @FXML
    private MenuItem openProducts;

    @FXML
    private MenuItem openCustomers;

    @FXML
    private MenuItem openOrders;

    @FXML
    private MenuItem openAboutUs;

    @FXML
    private MenuItem openProdfile;

    @FXML
    private MenuItem exitButton;

    @FXML
    private AnchorPane viewPane;
    public static Stage primaryStage = new Stage();

    /**
     * Загрузка основного раздела на главный Pane (view Pane)
     */
    @FXML
    void initialize() {
        showAnotherPage("main");
    }

    /**
     * Обработка нажатий кнопок в меню баре
     */
    @FXML
    public void clickButtons() {
        mainpageButton.setOnAction(event -> showAnotherPage("main"));

        closeButton.setOnAction(event -> exit());

        openProducts.setOnAction(event -> showAnotherPage("products"));

        openCustomers.setOnAction(event -> showAnotherPage("customers"));

        openOrders.setOnAction(event -> showAnotherPage("orders"));

        openAboutUs.setOnAction(event -> showAnotherPage("about"));

        exitButton.setOnAction(event -> exit());

        openProdfile.setOnAction(event -> exit());
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
            primaryStage.setTitle("GreensPark APP");

            FXMLLoader loader = new FXMLLoader(MenuBarController.class.getResource("/views/menuBar.fxml"));

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

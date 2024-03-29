package sample.controllers.Products;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.controllers.Orders.OrderAddPage;
import sample.models.Product.ProductTable;

import java.util.List;


public class ProductsController {

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Label message;

    @FXML
    private TableView<ProductTable> productsTable;

    @FXML
    private TableColumn<ProductTable, String> ID;

    @FXML
    private TableColumn<ProductTable, String> modelColumn;

    @FXML
    private TableColumn<ProductTable, String> priceColumn;

    @FXML
    private TableColumn<ProductTable, String> colorColumn;

    @FXML
    private TableColumn<ProductTable, String> countColumn;

    @FXML
    private Button updateButton;

    @FXML
    private Button newOrderButton;

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    /* ПЕРЕМЕННЫЕ */
    ObservableList<ProductTable> productTableData = FXCollections.observableArrayList();


    /**
     * Инициализация
     */
    @FXML
    public void initialize() {
        clickButtons();
        setTable();
        setColumn();
    }

    /**
     * Метод обрабатывает поисковый запрос
     * Изменяет отображенные данные, в зависимости от введённого текста
     */
    private void search() {
        List<ProductTable> productsList = Main.session.GetProduct();
        productTableData.clear();

        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getModel().startsWith(searchField.getText()))  // if model start with searchField) ...
                productTableData.add(productsList.get(i));
        }
        /* Обработка ошибок (Ничего не найдено) */
        if (productTableData.size() == 0) {
            setTable();
            message.setText("Not Found");
        } else {
            productsTable.setItems(productTableData);
            message.setText(" ");
        }
    }

    /**
     * Задаю значения колонкам
     */
    private void setColumn() {
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    /**
     * Запихиваю в таблицу ObservableList (productTableData)
     */
    private void setTable() {
        productTableData.clear();
        productTableData.addAll(Main.session.GetProduct());
        productsTable.setItems(productTableData);
    }

    /**
     * Всплывающее окно, говорящее об ошибке
     */
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(MenuBarController.primaryStage);
        alert.setTitle("ERROR");
        alert.setHeaderText("Not Found");
        alert.setContentText("You have not selected a field in the table.\nDo it, please!");
        alert.showAndWait();
    }

    /**
     * Обработка нажатий кнопок в Products
     */
    @FXML
    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ UPDATE */
        updateButton.setOnAction(event -> initialize());

        /* НАЖАТИЕ НА КНОПКУ SEARCH */
        searchButton.setOnAction(event -> search());

        /* НАЖАТИЕ НА КНОПКУ NEW ORDER */
        newOrderButton.setOnAction(event -> {
            int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                ProductTable productTable = productsTable.getItems().get(selectedIndex);
                OrderAddPage.showOrderAddPage(productTable.getId());
            } else
                showAlert();
        });

        /* НАЖАТИЕ НА КНОПКУ NEW */
        newButton.setOnAction(event -> ProductAddPage.showProductAddPage());

        /* НАЖАТИЕ НА КНОПКУ EDIT */
        editButton.setOnAction(event -> {
            int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                ProductTable productTable = productsTable.getItems().get(selectedIndex);
                ProductEditPage.showProductEditPage(productTable);
            } else
                showAlert();
        });

        /* НАЖАТИЕ НА КНОПКУ DELETE */
        deleteButton.setOnAction(event -> {
            int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                ProductTable currentPerson = productsTable.getItems().get(selectedIndex);

                if (Main.session.DeleteProduct(currentPerson))
                    productsTable.getItems().remove(selectedIndex);

            } else
                showAlert();
        });
    }

}

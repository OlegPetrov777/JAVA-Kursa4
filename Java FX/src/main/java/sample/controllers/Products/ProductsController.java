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


public class ProductsController {

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
    private Button newOrderButton;

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;


    ObservableList<ProductTable> productTableData = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        clickButtons();
        showTable();
        showColumn();
    }

    private void showColumn() {
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    private void showTable(){
        productTableData.clear();
        productTableData.addAll(Main.session.GetProduct());
        productsTable.setItems(productTableData);
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(MenuBarController.primaryStage);
        alert.setTitle("ОШИБКА");
        alert.setHeaderText("Пользователи не выбраны");
        alert.setContentText("Пожалуйста, выберите пользователя");

        alert.showAndWait();
    }


    /**
     * Обработка нажатий кнопок в Products
     */
    @FXML
    public void clickButtons() {
        newOrderButton.setOnAction(event -> {
            int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                ProductTable productTable = productsTable.getItems().get(selectedIndex);
                OrderAddPage.showOrderAddPage(productTable.getId());
            } else
                showAlert();
        });

        /* НАЖАТИЕ НА КНОПКУ NEW */
        newButton.setOnAction(event -> {
            ProductAddPage.showProductAddPage();
        });

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

package sample.controllers.Products;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.Model;
import sample.models.Product;
import sample.utils.RestAPI;


public class ProductsController {

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, String> ID;

    @FXML
    private TableColumn<Product, String> modelColumn;

    @FXML
    private TableColumn<Product, String> priceColumn;

    @FXML
    private TableColumn<Product, String> colorColumn;

    @FXML
    private TableColumn<Product, String> countColumn;

    @FXML
    private ComboBox<Model> modelBox;

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;


    RestAPI session = new RestAPI();
    ObservableList<Product> personData = FXCollections.observableArrayList();
    ObservableList<Model> modelData = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        clickButtons();
        showTable();
        showColumn();

        modelData.clear();
        modelData.addAll(session.GetModel());
        modelBox.setItems(modelData);
    }


    private void showColumn() {
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    private void showTable(){
        personData.clear();
        personData.addAll(session.GetProduct());
        productsTable.setItems(personData);
    }


    /**
     * Обработка нажатий кнопок в Products
     */
    @FXML
    public void clickButtons() {

        /* НАЖАТИЕ НА КНОПКУ NEW */
        newButton.setOnAction(event -> {
//            Product product = new Product();
//            ProductAddPage.showProductAddPage();
        });

        /* НАЖАТИЕ НА КНОПКУ EDIT */
        editButton.setOnAction(event -> {

        });

        /* НАЖАТИЕ НА КНОПКУ DELETE */
        deleteButton.setOnAction(event -> {

        });
    }
}

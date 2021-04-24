package sample.controllers.Products;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.controllers.MenuBarController;
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
    private ComboBox<String> modelBox;

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;


    ObservableList<Product> productData = FXCollections.observableArrayList();
    ObservableList<Model> modelData = FXCollections.observableArrayList();
    ObservableList<String> modelNameData = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        clickButtons();
        showTable();
        showColumn();
        showComboBox();
    }

    private void showComboBox() {
        modelData.clear();
        modelData.addAll(Main.session.GetModel());

        modelNameData.clear();
        for (Model model: modelData){
            modelNameData.add(model.getName());
        }
        modelBox.setItems(modelNameData);
    }

    public void setCompany(Model model){
        if (model.getName() != null){
            modelBox.getSelectionModel().select(model.getName());
            initialize();
        }
    }


    private void showColumn() {
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    private void showTable(){
        productData.clear();
        productData.addAll(Main.session.GetProduct());
        productsTable.setItems(productData);
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

        /* НАЖАТИЕ НА КНОПКУ NEW */
        newButton.setOnAction(event -> {
            Product product = new Product();
            ProductAddPage.showProductAddPage();
        });

        /* НАЖАТИЕ НА КНОПКУ EDIT */
        editButton.setOnAction(event -> {
            int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Product product = productsTable.getItems().get(selectedIndex);
                ProductEditPage.showProductEditPage(product);
            } else
                showAlert();
        });


        /* НАЖАТИЕ НА КНОПКУ DELETE */
        deleteButton.setOnAction(event -> {
            int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Product currentPerson = productsTable.getItems().get(selectedIndex);

                if (Main.session.DeleteProduct(currentPerson))
                    productsTable.getItems().remove(selectedIndex);

            } else
                showAlert();
        });
    }

}

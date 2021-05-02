package sample.controllers.Orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.models.Order;
import sample.models.Product.ProductTable;

import java.time.LocalDate;

public class OrderController {

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, String> ID;

    @FXML
    private TableColumn<Order, LocalDate> createColumn;

    @FXML
    private TableColumn<Order, LocalDate> completionColumn;

    @FXML
    private TableColumn<Order, String> idProduct;

    @FXML
    private TableColumn<Order, String> amountColumn;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label idLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private Label countLabel;


    ObservableList<Order> orderData = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        showTable();
        showColumn();
        clickButtons();

        showProduct(null);

        orderTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showProduct(newValue))
        );
    }

    private void showProduct(Order order){
        if (order != null) {
            int index = order.getProduct_id();

            ProductTable currentProduct = Main.session.GetProductById(index);

            idLabel.setText(currentProduct.getId().toString());
            modelLabel.setText(currentProduct.getModel());
            priceLabel.setText("$" + currentProduct.getPrice());
            colorLabel.setText(currentProduct.getColor());
            countLabel.setText(currentProduct.getCount() + " pcs");
        } else {
            idLabel.setText(" ");
            modelLabel.setText(" ");
            priceLabel.setText(" ");
            colorLabel.setText(" ");
            countLabel.setText(" ");
        }
    }

    private void showColumn() {
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        createColumn.setCellValueFactory(new PropertyValueFactory<>("date_of_create"));
        completionColumn.setCellValueFactory(new PropertyValueFactory<>("date_of_ready"));
        idProduct.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void showTable(){
        orderData.clear();
        orderData.addAll(Main.session.GetOrder());
        orderTable.setItems(orderData);
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(MenuBarController.primaryStage);
        alert.setTitle("ERROR");
        alert.setHeaderText("Not Found");
        alert.setContentText("You have not selected a field in the table.\nDo it, please!");

        alert.showAndWait();
    }

    @FXML
    public void clickButtons() {

        /* НАЖАТИЕ НА КНОПКУ EDIT */
        editButton.setOnAction(event -> {
            int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Order currentOrder = orderTable.getItems().get(selectedIndex);
                OrderEditPage.showOrderEditPage(currentOrder);
            } else
                showAlert();
        });

        /* НАЖАТИЕ НА КНОПКУ DELETE */
        deleteButton.setOnAction(event -> {
            int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Order currentOrder = orderTable.getItems().get(selectedIndex);

                if (Main.session.DeleteOrder(currentOrder))
                    orderTable.getItems().remove(selectedIndex);

            } else
                showAlert();
        });
    }
}

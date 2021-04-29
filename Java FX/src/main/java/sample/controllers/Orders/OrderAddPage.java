package sample.controllers.Orders;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.controllers.Products.ProductAddPage;
import sample.models.Model.ModelCreate;
import sample.models.Order;
import sample.models.Product.ProductCreate;

import java.util.List;

public class OrderAddPage {

    @FXML
    private DatePicker createDate;

    @FXML
    private DatePicker  completionDate;

    @FXML
    private TextField productIdField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label message;

    @FXML
    void initialize() { }


    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {
            if (isInputValid()){

                Integer index = Integer.parseInt(productIdField.getText().trim()); // Конвертация string в int

                Order order = new Order(completionDate.getValue(), completionDate.getValue(), index);
                Main.session.CreateOrder(order);

                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ Cancel */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide());
    }

    private void setProductId(Integer index) {
        productIdField.setText(index.toString());
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (productIdField.getText() == null || productIdField.getText().length() == 0) {
            errorMessage += "Error: not found Product ID";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            message.setText(errorMessage);
            return false;
        }
    }

    public static void showOrderAddPage(Integer index) {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Add Order");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductAddPage.class.getResource("/views/Orders/OrderAddPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            OrderAddPage controller = loader.getController();
            controller.clickButtons();
            controller.setProductId(index);

            dialogueStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
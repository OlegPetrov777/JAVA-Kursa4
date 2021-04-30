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
import sample.models.Order;

public class OrderEditPage  {

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

                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ Cancel */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide());
    }

    private void setOrder(Order order) {
        createDate.setValue(order.getDate_of_create());
        completionDate.setValue(order.getDate_of_ready());
        productIdField.setText(order.getProduct_id().toString());
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (productIdField.getText() == null || productIdField.getText().length() == 0) {
            errorMessage += "Error: not found Product ID";
        } else if (createDate.getValue() == null) {
            errorMessage += "Error: not found Date";
        } else if (completionDate.getValue() == null) {
            errorMessage += "Error: not found Date";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            message.setText(errorMessage);
            return false;
        }
    }

    public static void showOrderEditPage(Order order) {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Add Order");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductAddPage.class.getResource("/views/Orders/OrderEditPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            OrderEditPage controller = loader.getController();
            controller.clickButtons();
            controller.setOrder(order);

            dialogueStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

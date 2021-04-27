package sample.controllers.Products;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.controllers.MenuBarController;
import sample.models.Product;
import sample.utils.RestAPI;

public class ProductAddPage {

    @FXML
    private TextField modelField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField countField;

    @FXML
    private Label message;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() { clickButtons(); }


    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {

        });

        /* НАЖАТИЕ НА КНОПКУ Cancel */
        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
        });
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (modelField.getText() == null || modelField.getText().length() == 0) {
            errorMessage += "Error: modelField";
        }
        else if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Error: priceField";
        }
        else if (colorField.getText() == null || colorField.getText().length() == 0) {
            errorMessage += "Error: colorField";
        }
        else if (countField.getText() == null || countField.getText().length() == 0) {
            errorMessage += "Error: countField";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            message.setText(errorMessage);
            return false;
        }
    }

    public static void showProductAddPage() {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Add Product");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductAddPage.class.getResource("/views/Products/ProductAddPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            dialogueStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

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
import sample.controllers.MenuBarController;
import sample.models.Product;
import sample.utils.RestAPI;


public class ProductEditPage {

    @FXML
    private Label message;

    @FXML
    private TextField priceField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField countField;

    @FXML
    private TextField modelField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private RestAPI ApiSession = new RestAPI();
    private Product product;

    @FXML
    void initialize() {
        clickButtons();
    }


    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {

        });

        /* НАЖАТИЕ НА КНОПКУ CANCEL */
        cancelButton.setOnAction(event -> {
            cancelButton.getScene().getWindow().hide();
        });

    }


    public void setProduct(Product product) {
        this.product = product;
        modelField.setText(product.getModel());
        priceField.setText(product.getPrice());
        colorField.setText(product.getColor());
        countField.setText(product.getCount());
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

    /**
     * Загрузка окна изменения продусктов  EditProducts
     * @param product
     */
    public static void showProductEditPage(Product product) {
        try {

            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Edit Product");
            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductEditPage.class.getResource("/views/Products/ProductEditPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            ProductEditPage controller = loader.getController();
            controller.setProduct(product);

            dialogueStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

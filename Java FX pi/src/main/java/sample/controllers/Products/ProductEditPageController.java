package sample.controllers.Products;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Product;

public class ProductEditPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField priceField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField countField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField orderField;

    @FXML
    void initialize() { }

    /* ПЕРЕМЕННЫЕ */
    private Stage dialogueStage;
    private Product product;
    private boolean okClicked = false;


    public void setDialogueStage(Stage dialogueStage) {
        this.dialogueStage = dialogueStage;
    }


    public void setProduct(Product product){
        this.product = product;
        priceField.setText(product.getPrice());
        colorField.setText(product.getColor());
        countField.setText(product.getCount());
        modelField.setText(product.getModel());
        orderField.setText(product.getOrders());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    // НАЖАЛИК КНОПКУ ОКЕЙ
    @FXML
    private void handleOk() {
        if(isInputValid()){
            product.setPrice(priceField.getText());
            product.setColor(colorField.getText());
            product.setCount(countField.getText());
            product.setModel(modelField.getText());
            product.setOrders(orderField.getText());

            okClicked = true;
            dialogueStage.close();
        }
    }

    // НАЖАЛИК КНОПКУ КАНСЕЛ
    @FXML
    void handleCancel(ActionEvent event) {
        dialogueStage.close();
    }


    private boolean isInputValid(){
        String errorMessage = "";
        if(priceField.getText() == null || priceField.getText().length() == 0){
            errorMessage += "No name input\n";
        }

        if(colorField.getText() == null || colorField.getText().length() == 0){
            errorMessage += "No last name input\n";
        }

        if(countField.getText() == null || countField.getText().length() == 0){
            errorMessage += "No street input\n";
        }

        if(modelField.getText() == null || modelField.getText().length() == 0){
            errorMessage += "No city input\n";
        }

        if(orderField.getText() == null || orderField.getText().length() == 0){
            errorMessage += "No postal code input\n";
        }


        if (errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogueStage);
            alert.setTitle("Error!");
            alert.setHeaderText("Wrong input!");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }

    }
}

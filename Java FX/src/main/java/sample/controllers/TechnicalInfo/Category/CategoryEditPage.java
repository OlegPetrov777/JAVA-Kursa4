package sample.controllers.TechnicalInfo.Category;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.controllers.Products.ProductAddPage;
import sample.models.Category;

public class CategoryEditPage {

    @FXML
    private TextField nameField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {    }


    private Category category;


    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {
            if (isInputValid()){
                category.setName(nameField.getText());

                Main.session.UpdateCategory(category);

                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ Cancel */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide() );
    }

    public void setCategory(Category category) {
        this.category = category;
        nameField.setText(category.getName());
    }


    private boolean isInputValid() {
        String errorMessage = "";
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Error: Name Field";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            errorLabel.setText(errorMessage);
            return false;
        }
    }

    public static void showCategoryEditPage(Category category) {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Edit Category");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductAddPage.class.getResource("/views/TechnicalInfo/Category/CategoryEditPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            CategoryEditPage controller = loader.getController();
            controller.setCategory(category);
            controller.clickButtons();

            dialogueStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
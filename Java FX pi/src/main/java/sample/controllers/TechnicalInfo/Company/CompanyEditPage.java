package sample.controllers.TechnicalInfo.Company;

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
import sample.models.Company;

public class CompanyEditPage {
    @FXML
    private TextField nameField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {
    }


    private Company company;


    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {
            if (isInputValid()) {
                company.setName(nameField.getText());

                Main.session.UpdateCompany(company);

                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ Cancel */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide() );
    }

    public void setCompany(Company company) {
        this.company = company;
        nameField.setText(company.getName());
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

    public static void showCompanyEditPage(Company company) {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Edit Company");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductAddPage.class.getResource("/views/TechnicalInfo/Company/CompanyEditPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            CompanyEditPage controller = loader.getController();
            controller.setCompany(company);
            controller.clickButtons();

            dialogueStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

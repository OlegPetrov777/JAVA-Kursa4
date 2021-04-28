package sample.controllers.TechnicalInfo.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.models.Category;
import sample.models.Company;
import sample.models.Model.ModelCreate;
import sample.models.Model.ModelTable;

import java.util.List;

public class ModelEditPage {
    @FXML
    private Label errorLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> companyBox;

    @FXML
    private ComboBox<String> categoryBox;

    Integer modelID;

    ObservableList<Category> categoryData = FXCollections.observableArrayList();
    ObservableList<Company> companyData = FXCollections.observableArrayList();

    ObservableList<String> categoryNameData = FXCollections.observableArrayList();
    ObservableList<String> companyNameData = FXCollections.observableArrayList();

    @FXML
    void initialize() {
    }

    public void clickButtons() {

        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {

            if (isInputValid()){

                /* COMPANY */
                List<Company> oneCompanyInList = Main.session.GetCompaniesByName(companyBox.getValue());
                Company currentCompany = oneCompanyInList.get(0);

                /* CATEGORY */
                List<Category> oneCategoryInList = Main.session.GetCategoriesByName(categoryBox.getValue());
                Category currentCategory = oneCategoryInList.get(0);

                /* MODEL */
                ModelCreate modelCreate = new ModelCreate(modelID, nameField.getText(), currentCategory, currentCompany);

                System.out.println(modelCreate.toJsonPUT());
                Main.session.UpdateModel(modelCreate);

                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ Cancel */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide() );
    }

    private void showComboBoxCategory() {
        categoryData.clear();
        categoryData.addAll(Main.session.GetCategory());

        categoryNameData.clear();
        for (Category category: categoryData){
            categoryNameData.add(category.getName());
        }
        categoryBox.setItems(categoryNameData);
    }

    private void showComboBoxCompany() {
        companyData.clear();
        companyData.addAll(Main.session.GetCompany());

        companyNameData.clear();
        for (Company company: companyData){
            companyNameData.add(company.getName());
        }
        companyBox.setItems(companyNameData);
    }

    public void setModel(ModelTable model) {
        modelID = model.getId();
        nameField.setText(model.getName());
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

    public static void showModelEditPage(ModelTable modelTable) {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Edit Model");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ModelEditPage.class.getResource("/views/TechnicalInfo/Model/ModelEditPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            ModelEditPage controller = loader.getController();
            controller.setModel(modelTable);
            controller.clickButtons();
            controller.showComboBoxCategory();
            controller.showComboBoxCompany();

            dialogueStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

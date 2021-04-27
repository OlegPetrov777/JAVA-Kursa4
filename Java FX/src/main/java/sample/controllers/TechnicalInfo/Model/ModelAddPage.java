package sample.controllers.TechnicalInfo.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.models.Category;
import sample.models.Company;
import sample.models.ModelCreate;
import sample.models.ModelTable;

import java.util.List;

public class ModelAddPage {
    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> categoryBox;

    @FXML
    private ComboBox<String> companyBox;

    @FXML
    private Label errorLabel;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    ObservableList<Category> categoryData = FXCollections.observableArrayList();
    ObservableList<Company> companyData = FXCollections.observableArrayList();

    ObservableList<String> categoryNameData = FXCollections.observableArrayList();
    ObservableList<String> companyNameData = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        clickButtons();
        showComboBoxCategory();
        showComboBoxCompany();
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


    public void clickButtons() {

        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {
            if (isInputValid()){
                /* COMPANY */
                List<Company> oneCompanyInList = Main.session.GetCompaniesByName(companyBox.getValue());
                Company currentCompany = oneCompanyInList.get(0);

                /* CATEGORY */
                List<Category> oneCategoryInList = Main.session.GetCategoryByName(categoryBox.getValue());
                Category currentCategory = oneCategoryInList.get(0);

                /* MODEL */
                ModelCreate modelCreate = new ModelCreate(nameField.getText(), currentCategory, currentCompany);

                Main.session.CreateModel(modelCreate);

                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ Cancel */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide());
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

    public static void showModelAddPage() {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Add Model");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ModelAddPage.class.getResource("/views/TechnicalInfo/Model/ModelAddPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            dialogueStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

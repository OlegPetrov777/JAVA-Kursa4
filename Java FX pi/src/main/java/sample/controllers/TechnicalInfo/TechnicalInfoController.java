package sample.controllers.TechnicalInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.controllers.TechnicalInfo.Category.CategoryAddPage;
import sample.controllers.TechnicalInfo.Category.CategoryEditPage;
import sample.controllers.TechnicalInfo.Company.CompanyAddPage;
import sample.controllers.TechnicalInfo.Company.CompanyEditPage;
import sample.controllers.TechnicalInfo.Model.ModelAddPage;
import sample.models.Category;
import sample.models.Company;
import sample.models.ModelTable;


public class TechnicalInfoController {


    @FXML
    private TableView<ModelTable> modelTable;

    @FXML
    private TableColumn<ModelTable, String> modelID;

    @FXML
    private TableColumn<ModelTable, String> nameModel;

    @FXML
    private TableView<Company> companyTable;

    @FXML
    private TableColumn<Company, String> idCompany;

    @FXML
    private TableColumn<Company, String> nameCompany;

    @FXML
    private TableView<Category> categoryTable;

    @FXML
    private TableColumn<Category, String> idCategory;

    @FXML
    private TableColumn<Category, String> nameCategory;

    @FXML
    private Button NewModelBut;

    @FXML
    private Button EditModelBut;

    @FXML
    private Button DeleteModelBut;

    @FXML
    private Button NewCompanyBut;

    @FXML
    private Button EditCompanyBut;

    @FXML
    private Button DeleteCompanyBut;

    @FXML
    private Button NewCategoryBut;

    @FXML
    private Button EditCategoryBut;

    @FXML
    private Button DeleteCategoryBut;

    @FXML
    private Button updateButton;

    ObservableList<Category> categoryData = FXCollections.observableArrayList();
    ObservableList<ModelTable> modelTableData = FXCollections.observableArrayList();
    ObservableList<Company> companyData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        showTables();
        showColumns();
        clickButtons();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(MenuBarController.primaryStage);
        alert.setTitle("ОШИБКА");
        alert.setHeaderText("Пользователи не выбраны");
        alert.setContentText("Пожалуйста, выберите пользователя");

        alert.showAndWait();
    }

    private void showTables(){
        /* CATEGORY */
        categoryData.clear();
        categoryData.addAll(Main.session.GetCategory());
        categoryTable.setItems(categoryData);

        /* COMPANY */
        companyData.clear();
        companyData.addAll(Main.session.GetCompany());
        companyTable.setItems(companyData);

        /* MODEL */
        modelTableData.clear();
        modelTableData.addAll(Main.session.GetModel());
        modelTable.setItems(modelTableData);
    }

    private void showColumns() {
        /* CATEGORY */
        idCategory.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<>("name"));

        /* COMPANY */
        idCompany.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCompany.setCellValueFactory(new PropertyValueFactory<>("name"));

        /* MODEL */
        modelID.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameModel.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    public void clickButtons() {

        updateButton.setOnAction(event -> {
            showTables();
            showColumns();
        });


        NewModelBut.setOnAction(event -> ModelAddPage.showModelAddPage());

        EditModelBut.setOnAction(event -> {
            int selectedIndex = modelTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                ModelTable currentModelTable = modelTable.getItems().get(selectedIndex);
                //ModelEditPage.showModelEditPage(currentModel);
            } else
                showAlert();
        });

        DeleteModelBut.setOnAction(event -> {
            int selectedIndex = modelTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                ModelTable currentModelTable = modelTable.getItems().get(selectedIndex);

                if (Main.session.DeleteModel(currentModelTable))
                    modelTable.getItems().remove(selectedIndex);
            } else
                showAlert();
        });



        NewCompanyBut.setOnAction(event -> CompanyAddPage.showCompanyAddPage());

        EditCompanyBut.setOnAction(event -> {
            int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Company currentCompany = companyTable.getItems().get(selectedIndex);
                CompanyEditPage.showCompanyEditPage(currentCompany);
            } else
                showAlert();
        });

        DeleteCompanyBut.setOnAction(event -> {
            int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Company currentCompany = companyTable.getItems().get(selectedIndex);

                if (Main.session.DeleteCompany(currentCompany))
                    companyTable.getItems().remove(selectedIndex);
            } else
                showAlert();
        });



        NewCategoryBut.setOnAction(event -> CategoryAddPage.showCategoryAddPage());

        EditCategoryBut.setOnAction(event -> {
            int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Category currentCategory = categoryTable.getItems().get(selectedIndex);
                CategoryEditPage.showCategoryEditPage(currentCategory);
            } else
                showAlert();
        });

        DeleteCategoryBut.setOnAction(event -> {
            int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Category currentCategory = categoryTable.getItems().get(selectedIndex);

                if (Main.session.DeleteCategory(currentCategory))
                    categoryTable.getItems().remove(selectedIndex);
            } else
                showAlert();
        });
    }
}

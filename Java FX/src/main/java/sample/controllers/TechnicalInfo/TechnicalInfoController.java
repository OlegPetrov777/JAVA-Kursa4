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
import sample.controllers.TechnicalInfo.Model.ModelEditPage;
import sample.models.Category;
import sample.models.Company;
import sample.models.Model.ModelTable;


public class TechnicalInfoController {


    @FXML
    private TableView<ModelTable> modelTable;

    @FXML
    private TableColumn<ModelTable, String> modelID;

    @FXML
    private TableColumn<ModelTable, String> nameModel;

    @FXML
    private TableColumn<ModelTable, String> comIdModel;

    @FXML
    private TableColumn<ModelTable, String> catIdModel;

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

    /* ПЕРЕМЕННЫЕ */
    ObservableList<Category> categoryData = FXCollections.observableArrayList();
    ObservableList<ModelTable> modelTableData = FXCollections.observableArrayList();
    ObservableList<Company> companyData = FXCollections.observableArrayList();


    /**
     * Инициализация
     */
    @FXML
    public void initialize() {
        setTables();
        setColumns();
        clickButtons();
    }

    /**
     * Запихиваю в каждую таблицу свой ObservableList
     */
    private void setTables(){
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

    /**
     * Задаю значения колонкам всех таблиц
     */
    private void setColumns() {
        /* CATEGORY */
        idCategory.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<>("name"));

        /* COMPANY */
        idCompany.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCompany.setCellValueFactory(new PropertyValueFactory<>("name"));

        /* MODEL */
        modelID.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameModel.setCellValueFactory(new PropertyValueFactory<>("name"));
        catIdModel.setCellValueFactory(new PropertyValueFactory<>("category"));
        comIdModel.setCellValueFactory(new PropertyValueFactory<>("company"));
    }

    /**
     * Всплывающее окно, говорящее об ошибке
     */
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(MenuBarController.primaryStage);
        alert.setTitle("ERROR");
        alert.setHeaderText("Not Found");
        alert.setContentText("You have not selected a field in the table.\nDo it, please!");

        alert.showAndWait();
    }

    /**
     * Обработка нажатий кнопок
     */
    @FXML
    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ UPDATE */
        updateButton.setOnAction(event -> {
            setTables();
            setColumns();
        });

        /* MODEL -- NEW */
        NewModelBut.setOnAction(event -> ModelAddPage.showModelAddPage());

        /* MODEL -- EDIT */
        EditModelBut.setOnAction(event -> {
            int selectedIndex = modelTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                ModelTable currentModel = modelTable.getItems().get(selectedIndex);
                ModelEditPage.showModelEditPage(currentModel);
            } else
                showAlert();
        });

        /* MODEL -- DELETE */
        DeleteModelBut.setOnAction(event -> {
            int selectedIndex = modelTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                ModelTable currentModelTable = modelTable.getItems().get(selectedIndex);

                if (Main.session.DeleteModel(currentModelTable))
                    modelTable.getItems().remove(selectedIndex);
            } else
                showAlert();
        });

        /* COMPANY -- NEW */
        NewCompanyBut.setOnAction(event -> CompanyAddPage.showCompanyAddPage());

        /* COMPANY -- EDIT */
        EditCompanyBut.setOnAction(event -> {
            int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Company currentCompany = companyTable.getItems().get(selectedIndex);
                CompanyEditPage.showCompanyEditPage(currentCompany);
            } else
                showAlert();
        });

        /* COMPANY -- DELETE */
        DeleteCompanyBut.setOnAction(event -> {
            int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Company currentCompany = companyTable.getItems().get(selectedIndex);

                if (Main.session.DeleteCompany(currentCompany))
                    companyTable.getItems().remove(selectedIndex);
            } else
                showAlert();
        });

        /* CATEGORY -- NEW */
        NewCategoryBut.setOnAction(event -> CategoryAddPage.showCategoryAddPage());

        /* CATEGORY -- EDIT */
        EditCategoryBut.setOnAction(event -> {
            int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Category currentCategory = categoryTable.getItems().get(selectedIndex);
                CategoryEditPage.showCategoryEditPage(currentCategory);
            } else
                showAlert();
        });

        /* CATEGORY -- DELETE */
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

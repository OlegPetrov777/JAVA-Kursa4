package sample.controllers.Products;

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
import sample.models.Model.ModelCreate;
import sample.models.Model.ModelTable;
import sample.models.Product.ProductCreate;
import sample.models.Product.ProductTable;

import java.util.List;

public class ProductAddPage {

    @FXML
    private ComboBox<String> modelBox;

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

    /* ПЕРЕМЕННЫЕ */
    ObservableList<ModelTable> modelData = FXCollections.observableArrayList();
    ObservableList<String> modelNameData = FXCollections.observableArrayList();


    /**
     * Обработка нажатий кнопок
     */
    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {
            if (isInputValid()){

                /* НАХОЖУ МОДЕЛЬ ПО ИМЕНИ (ПОЛУЧАЮ ЗНАЧЕНИЕ ИЗ КОМБО-БОКСА */
                List<ModelCreate> oneModelInList = Main.session.GetModelsByName(modelBox.getValue());
                ModelCreate currentCompany = oneModelInList.get(0);

                /* СОЗДАЮ ОБЪЕКТ ПРОДУКТА (PRODUCT) */
                ProductCreate productCreate = new ProductCreate();
                productCreate.setModel(currentCompany);
                productCreate.setPrice(priceField.getText());
                productCreate.setColor(colorField.getText());
                productCreate.setCount(countField.getText());

                /* ВЫЗЫВАЮ МЕТОД CREATE, ДЛЯ ОСУЩЕСТВЛЕНИЯ POST-ЗАПРОСА НА СЕРВЕР */
                Main.session.CreateProduct(productCreate);

                /* ЗАКРЫВАЮ ОКНО */
                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ CANCEL; ЗАКРЫВАЮ ОКНО */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide());
    }

    /**
     * Заполнение Комбо-Бокса названиями моделей
     */
    private void showComboBoxModel() {
        modelData.clear();
        modelData.addAll(Main.session.GetModel());

        modelNameData.clear();
        for (ModelTable modelTable: modelData){
            modelNameData.add(modelTable.getName());
        }
        modelBox.setItems(modelNameData);
    }

    /**
     * Валидация введённых данных
     * @return
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Error: not found Price";
        }
        else if (colorField.getText() == null || colorField.getText().length() == 0) {
            errorMessage += "Error: not found Color";
        }
        else if (countField.getText() == null || countField.getText().length() == 0) {
            errorMessage += "Error: not found Amount";
        }
        else if (modelBox.getValue() == null) {
            errorMessage += "Error: not found Model";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            message.setText(errorMessage);
            return false;
        }
    }

    /**
     * Загрузка окна создания продускта (ProductAddPage)
     */
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

            ProductAddPage controller = loader.getController();
            controller.clickButtons();
            controller.showComboBoxModel();

            dialogueStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

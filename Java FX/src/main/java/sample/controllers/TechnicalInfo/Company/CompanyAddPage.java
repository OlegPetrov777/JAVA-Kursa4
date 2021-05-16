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

public class CompanyAddPage {
    @FXML
    private TextField nameField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;


    /**
     * Обработка нажатий кнопок
     */
    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {
            if (isInputValid()){

                /* СОЗДАЮ ОБЪЕКТ КОМПАНИИ (COMPANY) */
                Company company = new Company(nameField.getText());

                /* ВЫЗЫВАЮ МЕТОД CREATE, ДЛЯ ОСУЩЕСТВЛЕНИЯ POST-ЗАПРОСА НА СЕРВЕР */
                Main.session.CreateCompany(company);

                /* ЗАКРЫВАЮ ОКНО */
                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ CANCEL; ЗАКРЫВАЮ ОКНО */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide());
    }

    /**
     * Валидация введённых данных
     * @return
     */
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

    /**
     * Загрузка окна создания Компании (CompanyAddPage)
     */
    public static void showCompanyAddPage() {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Add Company");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CompanyAddPage.class.getResource("/views/TechnicalInfo/Company/CompanyAddPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            CompanyAddPage controller = loader.getController();
            controller.clickButtons();

            dialogueStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



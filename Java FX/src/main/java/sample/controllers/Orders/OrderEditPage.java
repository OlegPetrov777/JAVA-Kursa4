package sample.controllers.Orders;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.controllers.Products.ProductAddPage;
import sample.models.Order;

public class OrderEditPage  {

    @FXML
    private DatePicker createDate;

    @FXML
    private DatePicker  completionDate;

    @FXML
    private TextField productIdField;

    @FXML
    private TextField amountField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label message;

    /* Переменные */
    private Order order;


    /**
     * Обработка нажатий кнопок
     */
    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ OK */
        okButton.setOnAction(event -> {
            if (isInputValid()){

                Integer prod_id = Integer.parseInt(productIdField.getText().trim()); // Конвертация string в int
                Integer amount = Integer.parseInt(amountField.getText().trim());  // Конвертация string в int

                /* РЕДАКТИРОВАНИЕ ЗАКАЗА */
                order.setDate_of_create(createDate.getValue());
                order.setDate_of_ready(completionDate.getValue());
                order.setProduct_id(prod_id);
                order.setAmount(amount);

                /* ВЫЗЫВАЮ МЕТОД UPDATE, ДЛЯ ОСУЩЕСТВЛЕНИЯ PUT-ЗАПРОСА НА СЕРВЕР */
                Main.session.UpdateOrder(order);

                /* ЗАКРЫВАЮ ОКНО */
                cancelButton.getScene().getWindow().hide();
            }
        });

        /* НАЖАТИЕ НА КНОПКУ CANCEL; ЗАКРЫВАЮ ОКНО */
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide());
    }

    /**
     * Заполнение полей старыми данными
     * @param order
     */
    private void setOrder(Order order) {
        this.order = order;
        createDate.setValue(order.getDate_of_create());
        completionDate.setValue(order.getDate_of_ready());
        productIdField.setText(order.getProduct_id().toString());
        amountField.setText(order.getAmount().toString());
    }

    /**
     * Валидация введённых данных
     * @return
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (productIdField.getText() == null || productIdField.getText().length() == 0) {
            errorMessage += "Error: not found Product ID";
        } else if (createDate.getValue() == null) {
            errorMessage += "Error: not found Date";
        } else if (completionDate.getValue() == null) {
            errorMessage += "Error: not found Date";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            message.setText(errorMessage);
            return false;
        }
    }

    /**
     * Загрузка окна изменения заказа (OrderEditPage)
     * @param order
     */
    public static void showOrderEditPage(Order order) {
        try {
            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Add Order");

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductAddPage.class.getResource("/views/Orders/OrderEditPage.fxml"));
            Parent page = loader.load();

            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            OrderEditPage controller = loader.getController();
            controller.clickButtons();
            controller.setOrder(order);

            dialogueStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

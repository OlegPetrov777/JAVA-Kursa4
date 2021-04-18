package sample.controllers.Products;

import java.io.IOException;
import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.MenuBarController;
import sample.models.Product;

public class ProductsController {

//    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, String> priceColumn;

    @FXML
    private TableColumn<Product, String> colorColumn;

    @FXML
    private TableColumn<Product, String> countColumn;

    @FXML
    private TableColumn<Product, String> modelColumn;

    @FXML
    private TableColumn<Product, String> orderColumn;

    @FXML
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;


    @FXML
    void initialize() {
        clickButtons();

        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getColorProperty());
        colorColumn.setCellValueFactory(cellData -> cellData.getValue().getCountProperty());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().getModelProperty());
        orderColumn.setCellValueFactory(cellData -> cellData.getValue().getOrderProperty());

    }

    /**
     * Обработка нажатий кнопок в EditProducts
     */
    @FXML
    public void clickButtons() {
        /* НАЖАТИЕ НА КНОПКУ NEW */
        newButton.setOnAction(event -> {
            Product product = new Product();
            showPersonEditPage(product, "New Product");
        });

        /* НАЖАТИЕ НА КНОПКУ EDIT */
        editButton.setOnAction(event -> {
        });

        /* НАЖАТИЕ НА КНОПКУ DELETE */
        deleteButton.setOnAction(event -> {
        });
    }

    /**
     * Загрузка окна создания/изменения продусктов Products
     */
    public void showPersonEditPage(Product product, String title){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/productEditPage.fxml"));
            Parent page = loader.load();

            Stage dialogueStage = new Stage();
            dialogueStage.setTitle(title);

            dialogueStage.initOwner(MenuBarController.primaryStage);
            dialogueStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            ProductEditPageController controller = loader.getController();
            controller.setDialogueStage(dialogueStage);
            controller.setProduct(product);
            dialogueStage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

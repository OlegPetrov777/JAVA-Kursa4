package sample.controllers.Products;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Product> productsTable;

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

    private RestApi myApiSession;
    private static ObservableList<Product> productData = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        /* НАЖАТИЕ НА КНОПКУ NEW */
        newButton.setOnAction(event -> {
            Product product = new Product();
            boolean isOkClicked = showPersonEditPage(product, "New Product");
            if (isOkClicked){
                productData.add(product);
            }
        });

        /* НАЖАТИЕ НА КНОПКУ EDIT */
        editButton.setOnAction(event -> {

        });

        /* НАЖАТИЕ НА КНОПКУ DELETE */
        deleteButton.setOnAction(event -> {

        });


        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getColorProperty());
        colorColumn.setCellValueFactory(cellData -> cellData.getValue().getCountProperty());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().getModelProperty());
        orderColumn.setCellValueFactory(cellData -> cellData.getValue().getOrderProperty());

    }

    /**
     * ОКНО СОЗДАНИЯ/ИЗМЕНЕНИЯ ПРОДУКТОВ
     */
    public boolean showPersonEditPage(Product product, String title){
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
            return controller.isOkClicked();

        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}

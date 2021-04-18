package sample.controllers.Products;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private Button newButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;


    @FXML
    void initialize() {

        newButton.setOnAction(event -> {
            Product product = new Product();
            boolean isOkClicked = showPersonEditPage(product, "New Product");
            if (isOkClicked){
                getPersonData().add(product);
            }
        });

        editButton.setOnAction(event -> {

        });

        deleteButton.setOnAction(event -> {

        });
    }

    private ObservableList<Product> productData = FXCollections.observableArrayList();
    public ObservableList<Product> getPersonData() {
        return productData;
    }


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

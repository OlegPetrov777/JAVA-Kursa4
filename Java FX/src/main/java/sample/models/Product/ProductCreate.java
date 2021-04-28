package sample.models.Product;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.beans.property.*;
import sample.models.APIModel;
import sample.models.Model.ModelCreate;

import java.util.HashMap;
import java.util.Map;

public class ProductCreate implements APIModel {


    private IntegerProperty id;
    private ObjectProperty<ModelCreate> model;
    private StringProperty price;
    private StringProperty color;
    private StringProperty count;


    public ProductCreate() {
        this(null, null, null, null);
    }

    public ProductCreate(ModelCreate model, String price, String color, String count) {
        this.model = new SimpleObjectProperty(model);
        this.price = new SimpleStringProperty(price);
        this.color = new SimpleStringProperty(color);
        this.count = new SimpleStringProperty(count);
    }

    public ProductCreate(Integer id, ModelCreate model, String price, String color, String count) {
        this.id = new SimpleIntegerProperty(id);
        this.model = new SimpleObjectProperty(model);
        this.price = new SimpleStringProperty(price);
        this.color = new SimpleStringProperty(color);
        this.count = new SimpleStringProperty(count);
    }


    @Override
    public String toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("model", new Gson().fromJson(getModel().toJsonPUT(), JsonObject.class));
        map.put("price", price.get());
        map.put("color", color.get());
        map.put("count", count.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    @Override
    public String toJsonPUT() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.get());
        map.put("model", new Gson().fromJson(getModel().toJsonPUT(), JsonObject.class));
        map.put("price", price.get());
        map.put("color", color.get());
        map.put("count", count.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    /* Getters */
    public Integer getId() {
        return id.get();
    }

    public ModelCreate getModel() {
        return model.get();
    }

    public String getPrice() {
        return price.get();
    }

    public String getColor() {
        return color.get();
    }

    public String getCount() {
        return count.get();
    }


    /* Setters */
    public void setId(Integer id) {
        this.id.set(id);
    }

    public void setModel(ModelCreate model) {
        this.model = new SimpleObjectProperty<ModelCreate>(model);
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public void setCount(String count) {
        this.count.set(count);
    }
}



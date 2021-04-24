package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Product implements APIModel {

    private IntegerProperty id;
    private StringProperty model;
    private StringProperty price;
    private StringProperty color;
    private StringProperty count;


    public Product() {
        this(null, null, null, null, null);
    }


    public Product(Integer id, String model, String price, String color, String count) {
        this.id = new SimpleIntegerProperty(id);
        this.model = new SimpleStringProperty(model);
        this.price = new SimpleStringProperty(price);
        this.color = new SimpleStringProperty(color);
        this.count = new SimpleStringProperty(count);
    }


    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("model", model.get());
        map.put("price", price.get());
        map.put("color", color.get());
        map.put("count", count.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    @Override
    public String toJsonPUT() {
        return null;
    }

    /* Getters */
    public Integer getId() {
        return id.get();
    }

    public String getModel() {
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

    public void setModel(String model) {
        this.model = new SimpleStringProperty(model);
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

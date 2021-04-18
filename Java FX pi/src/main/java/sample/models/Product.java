package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Product implements APIModel {

    private final StringProperty price;
    private final StringProperty color;
    private final StringProperty count;
    private final StringProperty model;
    private final StringProperty orders;

    private final LongProperty id;

    public Product() {
        this(null, null, null, null, null);
    }


    public Product(String price, String color, String count, String model, String orders, Long id) {
        this.price = new SimpleStringProperty(price);
        this.color = new SimpleStringProperty(color);
        this.count = new SimpleStringProperty(count);
        this.model = new SimpleStringProperty(model);
        this.orders = new SimpleStringProperty(orders);
        this.id = new SimpleLongProperty(id);
    }

    public Product(String price, String color, String count, String model, String orders) {
        this.price = new SimpleStringProperty(price);
        this.color = new SimpleStringProperty(color);
        this.count = new SimpleStringProperty(count);
        this.model = new SimpleStringProperty(model);
        this.orders = new SimpleStringProperty(orders);
        this.id = null;
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", price.get());
        map.put("lastName", color.get());
        map.put("street", count.get());
        map.put("postalCode", String.valueOf(model.get()));
        map.put("city", orders.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    /* Getters */
    public String getColor() {
        return color.get();
    }
    public StringProperty getColorProperty() {
        return color;
    }

    public String getCount() {
        return count.get();
    }
    public StringProperty getCountProperty() {
        return count;
    }

    public String getPrice() {
        return price.get();
    }
    public StringProperty getPriceProperty() {
        return price;
    }

    public String getModel() {
        return model.get();
    }
    public StringProperty getModelProperty() {
        return model;
    }

    public String getOrders() {
        return orders.get();
    }
    public StringProperty getOrderProperty() {
        return orders;
    }


    /* Setters */
    public void setColor(String color) {
        this.color.set(color);
    }

    public void setCount(String count) {
        this.count.set(count);
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public void setOrders(String orders) {
        this.orders.set(orders);
    }

}

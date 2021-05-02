package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import sample.utils.DateUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order implements APIModel{

    private IntegerProperty id;
    private ObjectProperty<LocalDate> date_of_create;
    private ObjectProperty<LocalDate> date_of_ready;
    private IntegerProperty product_id;
    private IntegerProperty amount;


    public Order() {
        this(null, null, null, null);
    }

    public Order(LocalDate date_of_create, LocalDate date_of_ready, Integer product_id, Integer amount) {
        this.date_of_create = new SimpleObjectProperty<>(date_of_create);
        this.date_of_ready = new SimpleObjectProperty<>(date_of_ready);
        this.product_id = new SimpleIntegerProperty(product_id);
        this.amount = new SimpleIntegerProperty(amount);

    }

    public Order(Integer id, LocalDate date_of_create, LocalDate date_of_ready, Integer product_id, Integer amount) {
        this.id = new SimpleIntegerProperty(id);;
        this.date_of_create = new SimpleObjectProperty<>(date_of_create);
        this.date_of_ready = new SimpleObjectProperty<>(date_of_ready);
        this.product_id = new SimpleIntegerProperty(product_id);
        this.amount = new SimpleIntegerProperty(amount);
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("date_of_create", DateUtil.format(date_of_create.get()));
        map.put("date_of_ready", DateUtil.format(date_of_ready.get()));
        map.put("product_id", String.valueOf(product_id.get()));
        map.put("amount", String.valueOf(amount.get()));

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    @Override
    public String toJsonPUT() {
        Map<String, String> map = new HashMap<>();

        map.put("id", String.valueOf(id.get()));
        map.put("date_of_create", DateUtil.format(date_of_create.get()));
        map.put("date_of_ready", DateUtil.format(date_of_ready.get()));
        map.put("product_id", String.valueOf(product_id.get()));
        map.put("amount", String.valueOf(amount.get()));

        Gson gson = new Gson();
        return gson.toJson(map);
    }


    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public LocalDate getDate_of_create() {
        return date_of_create.get();
    }

    public void setDate_of_create(LocalDate date_of_create) {
        this.date_of_create.set(date_of_create);
    }

    public LocalDate getDate_of_ready() {
        return date_of_ready.get();
    }

    public void setDate_of_ready(LocalDate date_of_ready) {
        this.date_of_ready.set(date_of_ready);
    }

    public Integer getProduct_id() {
        return product_id.get();
    }

    public void setProduct_id(Integer product_id) {
        this.product_id.set(product_id);
    }

    public Integer getAmount() {
        return amount.get();
    }

    public void setAmount(Integer amount) {
        this.amount.set(amount);
    }
}

package sample.models;

import com.google.gson.Gson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class Category  implements APIModel {

    private IntegerProperty id;
    private StringProperty name;


    public Category() {
        this(null, null);
    }

    public Category(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Category(Integer id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }


    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    @Override
    public String toJsonPUT() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(getId()));
        map.put("name", name.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }


    /* Getters */
    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }


    /* Setters */
    public void setId(Integer id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
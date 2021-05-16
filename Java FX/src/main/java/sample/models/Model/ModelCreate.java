package sample.models.Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.beans.property.*;
import sample.models.APIModel;
import sample.models.Category;
import sample.models.Company;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ModelCreate implements APIModel {

    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<Category> category;
    private ObjectProperty<Company> company;


    public ModelCreate() {
        this(null, null, null, null);
    }

    public ModelCreate(String name, Category category, Company company) {
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleObjectProperty(category);
        this.company = new SimpleObjectProperty(company);
    }

    public ModelCreate(Integer id, String name, Category category, Company company) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleObjectProperty(category);
        this.company = new SimpleObjectProperty(company);
    }


    @Override
    public String toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.get());
        map.put("category", new Gson().fromJson(getCategory().toJsonPUT(), JsonObject.class));
        map.put("company", new Gson().fromJson(getCompany().toJsonPUT(), JsonObject.class));

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    @Override
    public String toJsonPUT() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("category", new Gson().fromJson(getCategory().toJsonPUT(), JsonObject.class));
        map.put("company", new Gson().fromJson(getCompany().toJsonPUT(), JsonObject.class));

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

    public Category getCategory() {
        return category.get();
    }

    public Company getCompany() {
        return company.get();
    }


    /* Setters */
    public void setId(Integer id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setCompany(Company company) {
        this.company = new SimpleObjectProperty<Company>(company);
    }

    public void setCategory(Category category) {
        this.category = new SimpleObjectProperty<Category>(category);
    }
}


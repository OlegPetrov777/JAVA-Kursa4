package sample.models.Model;

import com.google.gson.Gson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sample.models.APIModel;

import java.util.HashMap;
import java.util.Map;

public class ModelTable implements APIModel {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty category;
    private StringProperty company;


    public ModelTable() {
        this(null, null, null, null);
    }

    public ModelTable(String name, String category, String company) {
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.company = new SimpleStringProperty(company);
    }

    public ModelTable(Integer id, String name, String category, String company) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.company = new SimpleStringProperty(company);
    }


    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name.get());
        map.put("category", category.get());
        map.put("company", company.get());

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

    public String getName() {
        return name.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getCompany() {
        return company.get();
    }


    /* Setters */
    public void setId(Integer id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
}


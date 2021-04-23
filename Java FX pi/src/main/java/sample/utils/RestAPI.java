package sample.utils;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import sample.models.Model;
import sample.models.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RestAPI {
    private static final String ServerURL = "http://localhost:8090/api/";



    public void CreateProduct(Product product) {
        HttpClass.PostRequest(ServerURL + "product", product.toJson());
    }

    public List<Product> GetProduct() {
        List<Product> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "product");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();
        System.out.println(jsonResult);

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentPerson = jsonResult.get(i).getAsJsonObject();

            Integer id = currentPerson.get("id").getAsInt();
            String model = currentPerson.get("model").getAsJsonObject().get("name").getAsString();
            String price = currentPerson.get("price").getAsString();
            String color = currentPerson.get("color").getAsString();
            String count = currentPerson.get("count").getAsString();

            Product product = new Product(id, model, price, color, count);
            result.add(product);
        }
        return result;
    }


    public List<Model> GetModel() {
        List<Model> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "model");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();
        System.out.println(jsonResult);
        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentPerson = jsonResult.get(i).getAsJsonObject();

            Integer id = currentPerson.get("id").getAsInt();
            String name = currentPerson.get("name").getAsString();
            String category = currentPerson.get("category").getAsJsonObject().get("name").getAsString();
            String company = currentPerson.get("company").getAsJsonObject().get("name").getAsString();

            Model model = new Model(id, name, category, company);
            result.add(model);
        }
        return result;
    }

    public List<Model> GetCategory() {
        List<Model> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "category");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();
        System.out.println(jsonResult);
        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentPerson = jsonResult.get(i).getAsJsonObject();

            Integer id = currentPerson.get("id").getAsInt();
            String name = currentPerson.get("name").getAsString();
            String category = currentPerson.get("category").getAsJsonObject().get("name").getAsString();
            String company = currentPerson.get("company").getAsJsonObject().get("name").getAsString();

            Model model = new Model(id, name, category, company);
            result.add(model);
        }
        return result;
    }

    public List<Model> GetCompany() {
        List<Model> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "company");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();
        System.out.println(jsonResult);
        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentPerson = jsonResult.get(i).getAsJsonObject();

            Integer id = currentPerson.get("id").getAsInt();
            String name = currentPerson.get("name").getAsString();
            String category = currentPerson.get("category").getAsJsonObject().get("name").getAsString();
            String company = currentPerson.get("company").getAsJsonObject().get("name").getAsString();

            Model model = new Model(id, name, category, company);
            result.add(model);
        }
        return result;
    }
}

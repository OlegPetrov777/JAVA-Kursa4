package sample.utils;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import sample.models.Category;
import sample.models.Company;
import sample.models.Model;
import sample.models.Product;

import java.util.ArrayList;
import java.util.List;


public class RestAPI {
    private static final String ServerURL = "http://localhost:8090/api";


    public List<Product> GetProduct() {
        List<Product> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/product");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

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

    public void CreateProduct(Product product) {
        HttpClass.PostRequest(ServerURL + "/product", product.toJson());
    }


    public void UpdateProduct(Product product) {
        Integer id = product.getId();
        String jsonString = product.toJson();
        HttpClass.PutRequest(ServerURL + "/product/" + id, jsonString);
    }

    public boolean DeleteProduct(Product product) {
        Integer id = product.getId();
        return HttpClass.DeleteRequest(ServerURL + "/product/" + id);
    }



    public List<Model> GetModel() {
        List<Model> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/model");
        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

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




    public List<Category> GetCategory() {
        List<Category> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/category");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentPerson = jsonResult.get(i).getAsJsonObject();

            Integer id = currentPerson.get("id").getAsInt();
            String name = currentPerson.get("name").getAsString();

            Category category = new Category(id, name);
            result.add(category);
        }
        return result;
    }

    public void CreateCategory(Category category) {
        HttpClass.PostRequest(ServerURL + "/category", category.toJson());
    }

    public void UpdateCategory(Category category) {
        Integer id = category.getId();
        String jsonString = category.toJsonPUT();

        HttpClass.PutRequest(ServerURL + "/category/" + id, jsonString);
    }

    public boolean DeleteCategory(Category category) {
        Integer id = category.getId();
        return HttpClass.DeleteRequest(ServerURL + "/category/" + id);
    }



    public List<Company> GetCompany() {
        List<Company> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/company");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentPerson = jsonResult.get(i).getAsJsonObject();

            Integer id = currentPerson.get("id").getAsInt();
            String name = currentPerson.get("name").getAsString();

            Company company = new Company(id, name);
            result.add(company);
        }
        return result;
    }

    public void CreateCompany(Company company) {
        HttpClass.PostRequest(ServerURL + "/company", company.toJson());
    }

    public void UpdateCompany(Company company) {
        Integer id = company.getId();
        String jsonString = company.toJsonPUT();
        HttpClass.PutRequest(ServerURL + "/company/" + id, jsonString);
    }

    public boolean DeleteCompany(Company company) {
        Integer id = company.getId();
        return HttpClass.DeleteRequest(ServerURL + "/company/" + id);
    }
}

package sample.utils;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import sample.models.*;

import java.util.ArrayList;
import java.util.List;


public class RestAPI {
    private static final String ServerURL = "http://localhost:8090/api";


    public List<Product> GetProduct() {
        List<Product> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/product");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentProduct = jsonResult.get(i).getAsJsonObject();

            Integer id = currentProduct.get("id").getAsInt();
            String model = currentProduct.get("model").getAsJsonObject().get("name").getAsString();
            String price = currentProduct.get("price").getAsString();
            String color = currentProduct.get("color").getAsString();
            String count = currentProduct.get("count").getAsString();

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



    public List<ModelTable> GetModel() {
        List<ModelTable> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/model");
        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentModel = jsonResult.get(i).getAsJsonObject();

            Integer id = currentModel.get("id").getAsInt();
            String name = currentModel.get("name").getAsString();
            String category = currentModel.get("category").getAsJsonObject().get("id").getAsString();
            String company = currentModel.get("company").getAsJsonObject().get("id").getAsString();

            ModelTable modelTable = new ModelTable(id, name, category, company);
            result.add(modelTable);
        }
        return result;
    }

    public void CreateModel(ModelCreate modelCreate) {
        HttpClass.PostRequest(ServerURL + "/model", modelCreate.toJson());
    }

    public void UpdateModel(ModelTable modelTable) {
        Integer id = modelTable.getId();
        String jsonString = modelTable.toJsonPUT();

        HttpClass.PutRequest(ServerURL + "/model/" + id, jsonString);
    }

    public boolean DeleteModel(ModelTable modelTable) {
        Integer id = modelTable.getId();
        return HttpClass.DeleteRequest(ServerURL + "/model/" + id);
    }




    public List<Category> GetCategory() {
        List<Category> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/category");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentCategory = jsonResult.get(i).getAsJsonObject();

            Integer id = currentCategory.get("id").getAsInt();
            String name = currentCategory.get("name").getAsString();

            Category category = new Category(id, name);
            result.add(category);
        }
        return result;
    }

    public Category GetCategoryById(Integer id) {
        String buffer = HttpClass.GetRequest(ServerURL + "/category/" + id);

        JsonObject currentCategory = JsonParser.parseString(buffer).getAsJsonObject();

        if (currentCategory != null){
            String name = currentCategory.get("name").getAsString();

            return new Category(id, name);
        } else
            return null;
    }

    public List<Category> GetCategoryByName(String name) {
        List<Category> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/category/name_" + name);

        if (buffer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(buffer).getAsJsonArray();

            for (int i = 0; i < jsonAnswer.size(); i++) {
                JsonObject currentCategory = jsonAnswer.get(i).getAsJsonObject();

                Integer id = currentCategory.get("id").getAsInt();

                Category category = new Category(id, name);

                result.add(category);
            }
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
            JsonObject currentCompany = jsonResult.get(i).getAsJsonObject();

            Integer id = currentCompany.get("id").getAsInt();
            String name = currentCompany.get("name").getAsString();

            Company company = new Company(id, name);
            result.add(company);
        }
        return result;
    }

    public Company GetCompaniesById(Integer id) {
        String buffer = HttpClass.GetRequest(ServerURL + "/company/" + id);

        JsonObject currentCompany = JsonParser.parseString(buffer).getAsJsonObject();

        if (currentCompany != null){

            String name = currentCompany.get("name").getAsString();

            return new Company(id, name);
        } else
            return null;
    }

    public List<Company> GetCompaniesByName(String name) {
        List<Company> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/company/name_" + name);

        if (buffer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(buffer).getAsJsonArray();

            for (int i = 0; i < jsonAnswer.size(); i++) {
                JsonObject currentCompany = jsonAnswer.get(i).getAsJsonObject();

                Integer id = currentCompany.get("id").getAsInt();

                Company company = new Company(id, name);

                result.add(company);
            }
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

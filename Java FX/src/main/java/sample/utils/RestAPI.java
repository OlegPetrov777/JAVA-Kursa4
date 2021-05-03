package sample.utils;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import sample.models.*;
import sample.models.Model.ModelCreate;
import sample.models.Model.ModelTable;
import sample.models.Product.ProductCreate;
import sample.models.Product.ProductTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, обрабатывающий запросы
 * Свзывает Фронт и Бэк
 */
public class RestAPI {
    private static final String ServerURL = "http://localhost:8090/api";

    /**
     * GET запрос
     * Получаю список всех Заказов (Order)
     * @return
     */
    public List<Order> GetOrder() {
        List<Order> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/order");

        if (buffer != null) {  // проверка, чтобы код не начал парсить null
            JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();
            for (int i = 0; i < jsonResult.size(); i++) {
                JsonObject currentOrder = jsonResult.get(i).getAsJsonObject();

                Integer id = currentOrder.get("id").getAsInt();
                LocalDate dateOfCreate = DateUtil.parse(currentOrder.get("date_of_create").getAsString());
                LocalDate dateOfReady = DateUtil.parse(currentOrder.get("date_of_ready").getAsString());
                Integer productId = currentOrder.get("product_id").getAsInt();
                Integer amount = currentOrder.get("amount").getAsInt();

                Order order = new Order(id, dateOfCreate, dateOfReady, productId, amount);
                result.add(order);
            }
        }
        return result;
    }

    /**
     * POST запрос
     * Создание заказа
     * @param order
     */
    public void CreateOrder(Order order) {
        HttpClass.PostRequest(ServerURL + "/order", order.toJson());
    }

    /**
     * PUT запрос
     * Изменение заказа
     * @param order
     */
    public void UpdateOrder(Order order) {
        Integer id = order.getId();
        String jsonString = order.toJsonPUT();
        HttpClass.PutRequest(ServerURL + "/order/" + id, jsonString);
    }

    /**
     * DELETE запрос
     * Удаление заказа
     * @param order
     * @return
     */
    public boolean DeleteOrder(Order order) {
        Integer id = order.getId();
        return HttpClass.DeleteRequest(ServerURL + "/order/" + id);
    }



    /**
     * GET запрос
     * Получаю список всех Продуктов (Product)
     * @return
     */
    public List<ProductTable> GetProduct() {
        List<ProductTable> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/product");

        if (buffer != null) {
            JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();
            for (int i = 0; i < jsonResult.size(); i++) {
                JsonObject currentProduct = jsonResult.get(i).getAsJsonObject();

                Integer id = currentProduct.get("id").getAsInt();
                String model = currentProduct.get("model").getAsJsonObject().get("name").getAsString();
                String price = currentProduct.get("price").getAsString();
                String color = currentProduct.get("color").getAsString();
                String count = currentProduct.get("count").getAsString();

                ProductTable productTable = new ProductTable(id, model, price, color, count);
                result.add(productTable);
            }
        }
        return result;
    }

    /**
     * GET запрос
     * Нахожу объект-Продукт по ID
     * @return
     */
    public ProductTable GetProductById(Integer id) {
        String buffer = HttpClass.GetRequest(ServerURL + "/product/" + id);
        JsonObject currentProduct = JsonParser.parseString(buffer).getAsJsonObject();

        if (currentProduct != null) {
            String model = currentProduct.get("model").getAsJsonObject().get("name").getAsString();
            String price = currentProduct.get("price").getAsString();
            String color = currentProduct.get("color").getAsString();
            String count = currentProduct.get("count").getAsString();

            return new ProductTable(id, model, price, color, count);
        } else
            return null;
    }

    /**
     * POST запрос
     * Создание Продукта
     * @param productCreate
     */
    public void CreateProduct(ProductCreate productCreate) {
        HttpClass.PostRequest(ServerURL + "/product", productCreate.toJson());
    }

    /**
     * PUT запрос
     * Изменение Продукта
     * @param productCreate
     */
    public void UpdateProduct(ProductCreate productCreate) {
        Integer id = productCreate.getId();
        String jsonString = productCreate.toJsonPUT();
        HttpClass.PutRequest(ServerURL + "/product/" + id, jsonString);
    }

    /**
     * DELETE запрос
     * Удаление Продута
     * @param productTable
     * @return
     */
    public boolean DeleteProduct(ProductTable productTable) {
        Integer id = productTable.getId();
        return HttpClass.DeleteRequest(ServerURL + "/product/" + id);
    }



    /**
     * GET запрос
     * Получаю список всех Моделей (Model)
     * @return
     */
    public List<ModelTable> GetModel() {
        List<ModelTable> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/model");

        if (buffer != null) {
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
        }
        return result;
    }

    /**
     * GET запрос
     * Нахожу список Модеоей по имени (NAME)
     * @param name
     * @return
     */
    public List<ModelCreate> GetModelsByName(String name) {
        List<ModelCreate> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/model/name_" + name);

        if (buffer != null) {
            JsonArray jsonAnswer = JsonParser.parseString(buffer).getAsJsonArray();

            for (int i = 0; i < jsonAnswer.size(); i++) {
                JsonObject currentModel = jsonAnswer.get(i).getAsJsonObject();

                Integer id = currentModel.get("id").getAsInt();

                /* CATEGORY */
                Integer categoryId = currentModel.get("category").getAsJsonObject().get("id").getAsInt();
                String categoryName = currentModel.get("category").getAsJsonObject().get("name").getAsString();
                Category category = new Category(categoryId, categoryName);

                /* COMPANY */
                Integer companyId = currentModel.get("company").getAsJsonObject().get("id").getAsInt();
                String companyName = currentModel.get("company").getAsJsonObject().get("name").getAsString();
                Company company = new Company(companyId, companyName);

                /* MODEL */
                ModelCreate model = new ModelCreate(id, name, category, company);
                result.add(model);
            }
        }
        return result;
    }

    /**
     * POST запрос
     * Создание Модели
     * @param modelCreate
     */
    public void CreateModel(ModelCreate modelCreate) {
        HttpClass.PostRequest(ServerURL + "/model", modelCreate.toJson());
    }

    /**
     * PUT запрос
     * Изменение Модели
     * @param modelCreate
     */
    public void UpdateModel(ModelCreate modelCreate) {
        Integer id = modelCreate.getId();
        String jsonString = modelCreate.toJsonPUT();
        HttpClass.PutRequest(ServerURL + "/model/" + id, jsonString);
    }

    /**
     * DELETE запрос
     * Удаление Модели
     * @param modelTable
     * @return
     */
    public boolean DeleteModel(ModelTable modelTable) {
        Integer id = modelTable.getId();
        return HttpClass.DeleteRequest(ServerURL + "/model/" + id);
    }



    /**
     * GET запрос
     * Получаю список всех Категорий (Category)
     * @return
     */
    public List<Category> GetCategory() {
        List<Category> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/category");

        if (buffer != null) {
            JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();
            for (int i = 0; i < jsonResult.size(); i++) {
                JsonObject currentCategory = jsonResult.get(i).getAsJsonObject();

                Integer id = currentCategory.get("id").getAsInt();
                String name = currentCategory.get("name").getAsString();

                Category category = new Category(id, name);
                result.add(category);
            }
        }
        return result;
    }

    /**
     * GET запрос
     * Нахожу список Категорий по имени (NAME)
     * @param name
     * @return
     */
    public List<Category> GetCategoriesByName(String name) {
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

    /**
     * POST запрос
     * Создание Категории
     * @param category
     */
    public void CreateCategory(Category category) {
        HttpClass.PostRequest(ServerURL + "/category", category.toJson());
    }

    /**
     * PUT запрос
     * Изменение Категории
     * @param category
     */
    public void UpdateCategory(Category category) {
        Integer id = category.getId();
        String jsonString = category.toJsonPUT();
        HttpClass.PutRequest(ServerURL + "/category/" + id, jsonString);
    }

    /**
     * DELETE запрос
     * Удаление Категории
     * @param category
     * @return
     */
    public boolean DeleteCategory(Category category) {
        Integer id = category.getId();
        return HttpClass.DeleteRequest(ServerURL + "/category/" + id);
    }



    /**
     * GET запрос
     * Получаю список всех Компаний (Company)
     * @return
     */
    public List<Company> GetCompany() {
        List<Company> result = new ArrayList<>();
        String buffer = HttpClass.GetRequest(ServerURL + "/company");

        if (buffer != null) {
            JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();
            for (int i = 0; i < jsonResult.size(); i++) {
                JsonObject currentCompany = jsonResult.get(i).getAsJsonObject();

                Integer id = currentCompany.get("id").getAsInt();
                String name = currentCompany.get("name").getAsString();

                Company company = new Company(id, name);
                result.add(company);
            }
        }
        return result;
    }

    /**
     * GET запрос
     * Нахожу список Компаний по имени (NAME)
     * @param name
     * @return
     */
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

    /**
     * POST запрос
     * Создание Компании
     * @param company
     */
    public void CreateCompany(Company company) {
        HttpClass.PostRequest(ServerURL + "/company", company.toJson());
    }

    /**
     * PUT запрос
     * Изменение Компания
     * @param company
     */
    public void UpdateCompany(Company company) {
        Integer id = company.getId();
        String jsonString = company.toJsonPUT();
        HttpClass.PutRequest(ServerURL + "/company/" + id, jsonString);
    }

    /**
     * DELETE запрос
     * Удаление Компания
     * @param company
     * @return
     */
    public boolean DeleteCompany(Company company) {
        Integer id = company.getId();
        return HttpClass.DeleteRequest(ServerURL + "/company/" + id);
    }
}

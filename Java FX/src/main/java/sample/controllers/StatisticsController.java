package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import sample.Main;
import sample.models.Product.ProductTable;

import java.util.List;

/**
 * Класс для создания Диаграммы
 */
public class StatisticsController {

    @FXML
    private PieChart pieChart;

    /* ПЕРЕМЕННЫЕ */
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();


    /**
     * Инициализация
     */
    @FXML
    void initialize() {
        productStat();
    }

    /**
     * Создание и зполнения круговой Диаграммы данными о продуктах
     */
    public void productStat() {
        List<ProductTable> productList = Main.session.GetProduct(); // Получаю List со всеми продуктами

        /* Циклом из каждого продукта достаю имя Модели и их кол-во на складе */
        for (int i=0; i<productList.size(); i++) {
            String countStr = productList.get(i).getCount();
            Integer count = Integer.parseInt(countStr.trim()); // Конвертация string в int

            String nameModel = productList.get(i).getModel() + " (" + countStr + "pcs)";

            pieChartData.add(new PieChart.Data(nameModel, count)); // добавляю в круговую диаграмму
        }
        pieChart.setData(pieChartData);
        pieChart.setTitle("         Products Statistics\n(Number of products in stock)");
    }
}

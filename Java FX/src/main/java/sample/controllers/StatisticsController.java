package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import sample.Main;
import sample.models.Product.ProductTable;

import java.util.List;

public class StatisticsController {

    @FXML
    private PieChart pieChart;


    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        productStat();
    }

    public void productStat() {
        List<ProductTable> productList = Main.session.GetProduct();

        for (int i=0; i<productList.size(); i++) {
            String countStr = productList.get(i).getCount();
            Integer count = Integer.parseInt(countStr.trim()); // Конвертация string в int

            String nameModel = productList.get(i).getModel() + " (" + countStr + "pcs)";

            pieChartData.add(new PieChart.Data(nameModel, count));
        }
        pieChart.setData(pieChartData);
        pieChart.setTitle("         Products Statistics\n(Number of products in stock)");
    }
}

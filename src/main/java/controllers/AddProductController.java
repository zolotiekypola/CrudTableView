package controllers;

import dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Category;
import model.Product;
import model.Warehouse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.CategoryDaoImpl;
import service.ProductDaoImpl;
import service.WarehouseDaoImpl;

public class AddProductController {

    private final ObservableList<Warehouse> warehouseObservableList = FXCollections.observableArrayList();

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonAdd;

    @FXML
    private TextField txtTitle;

    @FXML
    private ComboBox<Warehouse> comboBoxWarehouse;

    public void initialize(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Product,Integer> productDaoImpl = new ProductDaoImpl(factory);
        Dao<Warehouse,Integer> warehouseDaoImpl = new WarehouseDaoImpl(factory);
        warehouseObservableList.addAll(warehouseDaoImpl.readByAll());
        comboBoxWarehouse.setItems(warehouseObservableList);

        buttonCancel.setOnAction(actionEvent -> {
            buttonCancel.getScene().getWindow().hide();
        });
        buttonAdd.setOnAction(actionEvent -> {
            Product product = new Product(txtTitle.getText(),comboBoxWarehouse.getValue());

            productDaoImpl.create(product);

            buttonAdd.getScene().getWindow().hide();
        });
    }

}

package controllers;

import dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Product;
import model.Warehouse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.ProductDaoImpl;
import service.WarehouseDaoImpl;

public class UpdateProductController {

    private final ObservableList<Warehouse> warehouseObservableList = FXCollections.observableArrayList();

    @FXML
    private TextField txtTitle;

    @FXML
    private ComboBox<Warehouse> comboBoxWarehouse;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonUpdate;

    public void initialize(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Warehouse,Integer> warehouseIntegerDao = new WarehouseDaoImpl(factory);
        Dao<Product,Integer> productIntegerDao = new ProductDaoImpl(factory);
        warehouseObservableList.addAll(warehouseIntegerDao.readByAll());
        comboBoxWarehouse.setItems(warehouseObservableList);

        buttonCancel.setOnAction(actionEvent -> {
            buttonCancel.getScene().getWindow().hide();
        });
        buttonUpdate.setOnAction(actionEvent -> {
            MainController.product.setTitle(txtTitle.getText());
            MainController.product.setWarehouse(comboBoxWarehouse.getValue());
            productIntegerDao.update(MainController.product);
            buttonUpdate.getScene().getWindow().hide();
        });

    }
}

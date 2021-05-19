package controllers;

import dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Category;
import model.Warehouse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.CategoryDaoImpl;
import service.WarehouseDaoImpl;

public class UpdateCategoryController {

    private final ObservableList<Warehouse> warehouseObservableList = FXCollections.observableArrayList();

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonUpdate;

    @FXML
    private ComboBox<Warehouse> comboBoxWarehouse;

    @FXML
    private TextField txtName;

    public void initialize(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Category,Integer> categoryDaoImpl = new CategoryDaoImpl(factory);
        Dao<Warehouse,Integer> warehouseDaoImpl = new WarehouseDaoImpl(factory);

        warehouseObservableList.addAll(warehouseDaoImpl.readByAll());
        comboBoxWarehouse.setItems(warehouseObservableList);

        buttonCancel.setOnAction(actionEvent -> {
            buttonCancel.getScene().getWindow().hide();
        });
        buttonUpdate.setOnAction(actionEvent -> {
            MainController.category.setName(txtName.getText());
            MainController.category.setWarehouse(comboBoxWarehouse.getValue());
            categoryDaoImpl.update(MainController.category);
            buttonUpdate.getScene().getWindow().hide();
        });
    }
}

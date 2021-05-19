package controllers;

import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Warehouse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.WarehouseDaoImpl;

public class UpdateWarehouseController {
    @FXML
    private TextField txtAdres;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonUpdate;

    public void initialize(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Warehouse,Integer> warehouseIntegerDao = new WarehouseDaoImpl(factory);
        buttonCancel.setOnAction(actionEvent -> {
            buttonCancel.getScene().getWindow().hide();
        });
        buttonUpdate.setOnAction(actionEvent -> {
            MainController.warehouse.setAdres(txtAdres.getText());
            warehouseIntegerDao.update(MainController.warehouse);

            buttonUpdate.getScene().getWindow().hide();
        });
    }
}

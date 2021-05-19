package controllers;

import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Warehouse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.WarehouseDaoImpl;

public class AddWarehouseController {
    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonAdd;

    @FXML
    private TextField txtAdres;

    public void initialize(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Warehouse,Integer> warehouseDaoImpl = new WarehouseDaoImpl(factory);

        buttonCancel.setOnAction(actionEvent -> {
            buttonCancel.getScene().getWindow().hide();
        });
        buttonAdd.setOnAction(actionEvent -> {
            Warehouse warehouse = new Warehouse(txtAdres.getText());

            warehouseDaoImpl.create(warehouse);

            buttonAdd.getScene().getWindow().hide();
        });
    }

}

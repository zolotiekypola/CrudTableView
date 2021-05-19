package controllers;

import dao.Dao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Category;
import model.Product;
import model.Warehouse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.CategoryDaoImpl;
import service.ProductDaoImpl;
import service.WarehouseDaoImpl;

import java.io.IOException;

public class MainController {

    private final ObservableList<Warehouse> warehouseObservableList = FXCollections.observableArrayList();
    private final ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    private final ObservableList<Category> categoryObservableList = FXCollections.observableArrayList();

    public static Category category;
    public static Product product;
    public static Warehouse warehouse;


    @FXML
    private Button buttonWarehouse;

    @FXML
    private Button buttonProduct;

    @FXML
    private Button buttonCategory;

    @FXML
    private TableView<Warehouse> tableViewWarehouse;

    @FXML
    private TableColumn<Warehouse, Integer> columnIdWarehouse;

    @FXML
    private TableColumn<Warehouse, String> columnAdresWarehouse;

    @FXML
    private TableView<Category> tableViewCategory;

    @FXML
    private TableColumn<Category, Integer> columnIdCategory;

    @FXML
    private TableColumn<Category, String> columnNameCategory;

    @FXML
    private TableColumn<Category, String> columnWarehouseCategory;

    @FXML
    private TableView<Product> tableViewProduct;

    @FXML
    private TableColumn<Product, Integer> columnIdProduct;

    @FXML
    private TableColumn<Product, String> columnTitleProduct;

    @FXML
    private TableColumn<Product,String> columnWarehouseProduct;

    @FXML
    private Button buttonAddProduct;

    @FXML
    private Button buttonUpdateProduct;

    @FXML
    private Button buttonDeleteProduct;

    @FXML
    private Button buttonAddWarehouse;

    @FXML
    private Button buttonUpdateWarehouse;

    @FXML
    private Button buttonDeleteWarehouse;

    @FXML
    private Button buttonAddCategory;

    @FXML
    private Button buttonUpdateCategory;

    @FXML
    private Button buttonDeleteCategory;

    @FXML
    private TextField txtSearchProduct;

    @FXML
    private TextField txtSearchCategory;

    @FXML
    private TextField txtSearchWarehouse;

    public void initialize(){
        buttonAddCategory.setOnAction(actionEvent -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/view/addCategory.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Добавление категории");
            stage.setScene(new Scene(root));
            stage.show();
        });
        buttonAddWarehouse.setOnAction(actionEvent -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/view/addWarehouse.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Добавление склада");
            stage.setScene(new Scene(root));
            stage.show();
        });
        buttonAddProduct.setOnAction(actionEvent -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/view/addProduct.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Добавление продукта");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    @FXML
    void onButtonCategory(ActionEvent event) {
        categoryObservableList.clear();
        tableViewCategory.refresh();

        tableViewCategory.setVisible(true);
        tableViewProduct.setVisible(false);
        tableViewWarehouse.setVisible(false);
        buttonAddCategory.setVisible(true);
        buttonUpdateCategory.setVisible(true);
        buttonDeleteCategory.setVisible(true);
        buttonAddProduct.setVisible(false);
        buttonDeleteProduct.setVisible(false);
        buttonUpdateProduct.setVisible(false);
        buttonAddWarehouse.setVisible(false);
        buttonUpdateWarehouse.setVisible(false);
        buttonDeleteWarehouse.setVisible(false);
        txtSearchProduct.setVisible(false);
        txtSearchCategory.setVisible(true);
        txtSearchWarehouse.setVisible(false);

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Category,Integer> categoryDaoImpl = new CategoryDaoImpl(factory);
        categoryObservableList.addAll(categoryDaoImpl.readByAll());

        columnIdCategory.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getId()));
        columnNameCategory.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getName()));
        columnWarehouseCategory.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getWarehouse().getAdres()));

        tableViewCategory.setItems(categoryObservableList);

        tableViewCategory.getSelectionModel().selectedItemProperty().addListener((obj,oldValue,newValue) -> {
            buttonUpdateCategory.setOnAction(actionEvent -> {
                category = newValue;

                Parent root = null;

                try {
                    root = FXMLLoader.load(getClass().getResource("/view/updateCategory.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Редактирование категории");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                categoryObservableList.setAll(categoryDaoImpl.readByAll());
            });
            buttonDeleteCategory.setOnAction(actionEvent -> {
                categoryDaoImpl.delete(newValue);
                categoryObservableList.remove(newValue);
            });
        });

    }

    @FXML
    void onButtonProduct(ActionEvent event) {
        productObservableList.clear();

        tableViewCategory.setVisible(false);
        tableViewProduct.setVisible(true);
        tableViewWarehouse.setVisible(false);
        buttonAddCategory.setVisible(false);
        buttonUpdateCategory.setVisible(false);
        buttonDeleteCategory.setVisible(false);
        buttonAddProduct.setVisible(true);
        buttonDeleteProduct.setVisible(true);
        buttonUpdateProduct.setVisible(true);
        buttonAddWarehouse.setVisible(false);
        buttonUpdateWarehouse.setVisible(false);
        buttonDeleteWarehouse.setVisible(false);
        txtSearchProduct.setVisible(true);
        txtSearchCategory.setVisible(false);
        txtSearchWarehouse.setVisible(false);

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Product,Integer> productDaoImpl = new ProductDaoImpl(factory);
        productObservableList.addAll(productDaoImpl.readByAll());

        columnIdProduct.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getId()));
        columnTitleProduct.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTitle()));
        columnWarehouseProduct.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getWarehouse().getAdres()));

        tableViewProduct.setItems(productObservableList);

        tableViewProduct.getSelectionModel().selectedItemProperty().addListener((obj,oldValue,newValue) -> {
            product = newValue;

            buttonUpdateProduct.setOnAction(actionEvent -> {
                Parent root = null;

                try {
                    root = FXMLLoader.load(getClass().getResource("/view/updateProduct.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Редактирование продуктов");
                stage.setScene(new Scene(root));
                stage.show();
            });
            buttonDeleteProduct.setOnAction(actionEvent -> {
                productDaoImpl.delete(newValue);
                productObservableList.remove(newValue);
            });
        });

    }

    @FXML
    void onButtonWarehouse(ActionEvent event) {
        warehouseObservableList.clear();

        tableViewCategory.setVisible(false);
        tableViewProduct.setVisible(false);
        tableViewWarehouse.setVisible(true);
        buttonAddCategory.setVisible(false);
        buttonUpdateCategory.setVisible(false);
        buttonDeleteCategory.setVisible(false);
        buttonAddProduct.setVisible(false);
        buttonDeleteProduct.setVisible(false);
        buttonUpdateProduct.setVisible(false);
        buttonAddWarehouse.setVisible(true);
        buttonUpdateWarehouse.setVisible(true);
        buttonDeleteWarehouse.setVisible(true);
        txtSearchProduct.setVisible(false);
        txtSearchCategory.setVisible(false);
        txtSearchWarehouse.setVisible(true);

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Warehouse,Integer> warehouseDaoImpl = new WarehouseDaoImpl(factory);
        warehouseObservableList.addAll(warehouseDaoImpl.readByAll());

        columnIdWarehouse.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getId()));
        columnAdresWarehouse.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getAdres()));

        tableViewWarehouse.setItems(warehouseObservableList);

        tableViewWarehouse.getSelectionModel().selectedItemProperty().addListener((obj,oldValue,newValue) -> {

            warehouse = newValue;

            buttonUpdateWarehouse.setOnAction(actionEvent -> {

                Parent root = null;

                try {
                    root = FXMLLoader.load(getClass().getResource("/view/updateWarehouse.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Редактирование склада");
                stage.setScene(new Scene(root));
                stage.show();

            });
            buttonDeleteWarehouse.setOnAction(actionEvent -> {
                warehouseDaoImpl.delete(newValue);
                warehouseObservableList.remove(newValue);
            });
        });

    }

    @FXML
    public void searchProduct(){
        FilteredList<Product> filteredList = new FilteredList<>(productObservableList,c -> true);

        txtSearchProduct.textProperty().addListener((c,oldValue,newValue) -> {
            filteredList.setPredicate(product1 -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String toLowerCase = newValue.toLowerCase();
                if (product1.getTitle().toLowerCase().contains(toLowerCase)) {
                    return true;
                } else if (product1.getWarehouse().getAdres().toLowerCase().contains(toLowerCase)) {
                    return true;
                } else if (String.valueOf(product1.getId()).contains(toLowerCase))
                    return true;
                else return false;
            });
        });

        SortedList<Product> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(tableViewProduct.comparatorProperty());

        tableViewProduct.setItems(sortedList);
    }
    @FXML
    public void searchCategory(){
        FilteredList<Category> filteredList = new FilteredList<>(categoryObservableList,c -> true);

        txtSearchCategory.textProperty().addListener((c,oldValue,newValue) -> {
            filteredList.setPredicate(category1 -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (category1.getName().contains(lowerCaseFilter))
                    return true;
                else if (String.valueOf(category1.getId()).contains(lowerCaseFilter))
                    return true;
                else if (category1.getWarehouse().getAdres().contains(lowerCaseFilter))
                    return true;
                else return false;
            });
        });
        SortedList<Category> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(tableViewCategory.comparatorProperty());

        tableViewCategory.setItems(sortedList);
    }
    @FXML
    public void searchWarehouse(){
        FilteredList<Warehouse> filteredList = new FilteredList<>(warehouseObservableList,c -> true);

        txtSearchWarehouse.textProperty().addListener((c,oldValue,newValue) -> {
            filteredList.setPredicate(warehouse1 -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(warehouse1.getId()).contains(lowerCaseFilter))
                    return true;
                else if (warehouse1.getAdres().contains(lowerCaseFilter))
                    return true;
                else return false;
            });
        });
        SortedList<Warehouse> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableViewWarehouse.comparatorProperty());

        tableViewWarehouse.setItems(sortedList);
    }
}

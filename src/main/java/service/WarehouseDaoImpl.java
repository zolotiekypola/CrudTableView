package service;

import dao.Dao;
import model.Category;
import model.Warehouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class WarehouseDaoImpl implements Dao<Warehouse,Integer> {
    private final SessionFactory factory;

    public WarehouseDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Warehouse warehouse) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(warehouse);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Warehouse warehouse) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(warehouse);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Warehouse warehouse) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(warehouse);
            session.getTransaction().commit();
        }
    }

    @Override
    public Warehouse readById(Integer id) {
        try (Session session = factory.openSession()){
            Warehouse warehouse = session.get(Warehouse.class,id);
            return warehouse;
        }
    }

    @Override
    public List<Warehouse> readByAll() {
        try (Session session = factory.openSession()){
            Query<Warehouse> query = session.createQuery("from Warehouse");
            return query.list();
        }
    }
}

package service;

import dao.Dao;
import model.Category;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDaoImpl implements Dao<Category,Integer> {
    private final SessionFactory factory;

    public CategoryDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Category category) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Category category) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Category category) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
        }
    }

    @Override
    public Category readById(Integer id) {
        try (Session session = factory.openSession()){
            Category category = session.get(Category.class,id);
            return category;
        }
    }

    @Override
    public List<Category> readByAll() {
        try (Session session = factory.openSession()){
            Query<Category> query = session.createQuery("from Category");
            return query.list();
        }
    }
}

package dao;

import java.util.List;

public interface Dao <Entity,Key>{
    void create(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
    Entity readById(Key id);
    List<Entity> readByAll();
}

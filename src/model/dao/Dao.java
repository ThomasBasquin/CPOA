package model.dao;

import java.util.List;

public interface Dao<T> {
    T getById(int id);
    public List<T> findAll();
    boolean create(T objet);
    boolean update(T objet);
    boolean delete(T objet);
}


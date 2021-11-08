package model.dao;

public interface Dao<T> {
    T getById(int id);

    boolean create(T objet);
    boolean update(T objet);
    boolean delete(T objet);
}


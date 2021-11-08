package model.dao.revue;

import model.dao.Dao;

import java.util.List;

public interface DaoRevue<Revue> extends Dao<Revue> {
    List<Revue> getByTitre(String titre);
    List<Revue> getByDescription(String description);
}

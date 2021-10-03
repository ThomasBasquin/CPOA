package Dao.Revue;

import Dao.Dao;

import java.util.List;

public interface DaoRevue<Revue> extends Dao<Revue> {
    List<Revue> getByTitre(String titre);
    List<Revue> getByDescription(String description);
}

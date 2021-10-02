package Dao.Revue;

import Dao.Dao;

public interface DaoRevue<Revue> extends Dao<Revue> {
    Revue getByRevue(Revue revue);
    String getByTitre(String titre);
}

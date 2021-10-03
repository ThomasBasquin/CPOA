package Dao.Periodicite;

import Dao.Dao;

import java.util.List;

public interface DaoPeriodicite<Periodicite> extends Dao<Periodicite> {
    List<Periodicite> getBylibelle(String libelle);
}

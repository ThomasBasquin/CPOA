package model.dao.periodicite;

import model.dao.Dao;

import java.util.List;

public interface DaoPeriodicite<Periodicite> extends Dao<Periodicite> {
    List<Periodicite> getBylibelle(String libelle);
}
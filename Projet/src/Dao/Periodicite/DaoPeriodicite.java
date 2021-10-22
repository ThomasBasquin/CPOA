package Dao.Periodicite;

import Dao.Dao;
import Metier.Periodicite;

import java.util.ArrayList;
import java.util.List;

public interface DaoPeriodicite<Periodicite> extends Dao<Periodicite> {
    List<Periodicite> getBylibelle(String libelle);
}
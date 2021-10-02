package Dao.Periodicite.listememoire;

import Dao.Periodicite.DaoPeriodicite;
import Metier.Periodicite;

import java.util.List;

public class ListeMemoireDaoPeriodicite implements DaoPeriodicite<Periodicite> {

    private static ListeMemoireDaoPeriodicite instance;

    private ListeMemoireDaoPeriodicite() {}

    public static ListeMemoireDaoPeriodicite getInstance() {
        if (instance == null) {
            instance = new ListeMemoireDaoPeriodicite();
        }
        return instance;
    }
    @Override
    public Periodicite getById(int id) {

        return null;
    }

    @Override
    public boolean create(Periodicite objet) {
        return false;
    }

    @Override
    public boolean update(Periodicite objet) {
        return false;
    }

    @Override
    public boolean delete(Periodicite objet) {
        return false;
    }

    @Override
    public List<Periodicite> getBylibelle(String libelle) {
        return null;
    }
}

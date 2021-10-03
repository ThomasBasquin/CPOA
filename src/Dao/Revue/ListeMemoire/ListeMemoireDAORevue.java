package Dao.Revue.ListeMemoire;

import Dao.Periodicite.ListeMemoire.ListeMemoireDAOPeriodicite;
import Dao.Revue.DaoRevue;
import Metier.Periodicite;
import Metier.Revue;

import java.util.ArrayList;
import java.util.List;

public class ListeMemoireDAORevue implements DaoRevue<Revue> {

    private static ListeMemoireDAORevue instance;

    private List<Revue> donnees;

    public static ListeMemoireDAORevue getInstance() {

        if (instance == null) {
            instance = new ListeMemoireDAORevue();
        }

        return instance;
    }

    private ListeMemoireDAORevue() {

        this.donnees = new ArrayList<Revue>();
    }

    @Override
    public Revue getById(int id) {
        int idx = this.donnees.indexOf(new Revue(id));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public List<Revue> getByTitre(String titre) {
        return null;
    }

    @Override
    public List<Revue> getByDescription(String description) {
        return null;
    }

    @Override
    public boolean create(Revue objet) {
        return false;
    }

    @Override
    public boolean update(Revue objet) {
        return false;
    }

    @Override
    public boolean delete(Revue objet) {
        return false;
    }


}

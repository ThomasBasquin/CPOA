package Dao.Periodicite.ListeMemoire;

import Dao.Periodicite.DaoPeriodicite;
import Metier.Periodicite;

import java.util.ArrayList;
import java.util.List;

public class ListeMemoireDAOPeriodicite implements DaoPeriodicite<Periodicite>{

    private static ListeMemoireDAOPeriodicite instance;

    private List<Periodicite> donnees;

    public static ListeMemoireDAOPeriodicite getInstance() {

        if (instance == null) {
            instance = new ListeMemoireDAOPeriodicite();
        }

        return instance;
    }

    private ListeMemoireDAOPeriodicite() {

        this.donnees = new ArrayList<Periodicite>();

        this.donnees.add(new Periodicite(1, "Mensuel"));
        this.donnees.add(new Periodicite(2, "Quotidien"));
    }

    @Override
    public Periodicite getById(int id) {
        int idx = this.donnees.indexOf(new Periodicite(id, "test"));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public boolean create(Periodicite  objet) {
        objet.setId(3);

        while (this.donnees.contains(objet)) {

            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Periodicite  objet) {

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Periodicite  objet) {
        Periodicite supprime;

        // Ne fonctionne que si l'objet métier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public List<Periodicite> getBylibelle(String libelle) {
        List<Periodicite> periodList = new ArrayList<>();
        for (Periodicite pe: donnees) {
            if (pe.getLibelle().equalsIgnoreCase(libelle)){
                periodList.add(pe);
            }
        }
        return periodList;
        }
    }

    //@Override
    public ArrayList<Periodicite> findAll() {
        return (ArrayList<Periodicite>) this.donnees;
    }

}

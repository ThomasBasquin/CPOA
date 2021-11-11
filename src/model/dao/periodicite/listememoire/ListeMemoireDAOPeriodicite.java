package model.dao.periodicite.listememoire;

import model.dao.periodicite.DaoPeriodicite;
import model.metier.Periodicite;

import java.util.ArrayList;
import java.util.List;

public class ListeMemoireDAOPeriodicite implements DaoPeriodicite<Periodicite>{

    private static ListeMemoireDAOPeriodicite instance;

    private ArrayList<Periodicite> donnees;

    public static ListeMemoireDAOPeriodicite getInstance() {

        if (instance == null) {
            instance = new ListeMemoireDAOPeriodicite();
        }

        return instance;
    }

    private ListeMemoireDAOPeriodicite() {

        this.donnees = new ArrayList<Periodicite>();
        this.donnees.add(new Periodicite(1, "Quotidien"));
        this.donnees.add(new Periodicite(2, "Hebdomadaire"));

    }

    @Override
    public boolean create(Periodicite  objet) {
        objet.setId(objet.getId());

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
        ArrayList<Periodicite> listePeriod = new ArrayList<Periodicite>();
		int i = 0;
		
		while (i < donnees.size())
		{
			if (donnees.get(i).getLibelle().equalsIgnoreCase(libelle))
				listePeriod.add(donnees.get(i));
			i++;
		}
		return (listePeriod);
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
    public List<Periodicite> findAll() {
        return this.donnees;
    }



}

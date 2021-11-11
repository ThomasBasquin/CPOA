package model.dao.revue.listememoire;

import model.dao.revue.DaoRevue;
import model.metier.Client;
import model.metier.Revue;

import java.util.ArrayList;
import java.util.Date;
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

        this.donnees.add(new Revue(1,1,"Livre pour enfant",6,"Ane trotro","enfant"));
        this.donnees.add(new Revue(2,2,"Livre de l'espace",23,"L'univers","Terre"));
    }

    public List<Revue> findAll() {
        return this.donnees;
    }

    @Override
    public boolean create(Revue objet) {
        objet.setId_revue(objet.getId_revue());

        while (this.donnees.contains(objet)) {
            objet.setId_revue(objet.getId_revue() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Revue objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } else {
            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Revue objet) {
        Revue supprime;

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }
        return objet.equals(supprime);
    }

    @Override
    public Revue getById(int id) {
        int idx = this.donnees.indexOf(new Revue(id, 1,"Revue10",14,"ScienceEtVie","Animaux"));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public List<Revue> getByTitre(String titre) {
        List<Revue> listeTitre = new ArrayList<>();
        for (Revue pe: donnees) {
            if (pe.getTitre().equalsIgnoreCase(titre)){
                listeTitre.add(pe);
            }
        }
        return listeTitre;
    }

    @Override
    public List<Revue> getByDescription(String descrption) {
        List<Revue> listeDescription = new ArrayList<>();
        for (Revue pe: donnees) {
            if (pe.getDescription().equalsIgnoreCase(descrption)){
                listeDescription.add(pe);
            }
        }
        return listeDescription;
    }
}

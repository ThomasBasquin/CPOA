package model.dao.abonnement.listememoire;

import model.dao.abonnement.DaoAbonnement;
import model.metier.Abonnement;
import model.metier.Client;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListeMemoireDAOAbonnement implements DaoAbonnement<Abonnement> {

    private static ListeMemoireDAOAbonnement instance;

    private List<Abonnement> donnees;

    public static ListeMemoireDAOAbonnement getInstance() {

        if (instance == null) {
            instance = new ListeMemoireDAOAbonnement();
        }

        return instance;
    }

    private ListeMemoireDAOAbonnement() {

        this.donnees = new ArrayList<>();

    }

    @Override
    public List<Abonnement> findAll() {
        return this.donnees;
    }

    @Override
    public boolean create(Abonnement objet) {
        objet.setId_abonnement(objet.getId_abonnement());
        while (this.donnees.contains(objet)) {

            objet.setId_abonnement(objet.getId_abonnement() + 1);
        }

        return this.donnees.add(objet);
    }

    @Override
    public boolean delete(Abonnement objet) {

        Abonnement supprime;

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public boolean update(Abonnement objet) {

        int idx = -1;
        for (Abonnement abonnement : this.donnees) {
            if (abonnement.getId_abonnement() == objet.getId_abonnement())
            {
                idx = this.donnees.indexOf(abonnement);
            }
        }
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        }
        else {
            this.donnees.set(idx, objet);
            return true;
        }
    }

    @Override
    public Abonnement getById(int id) {

        int idx = this.donnees.indexOf(new Abonnement(id,null,null,1,2));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public List<Abonnement> getByDateDeb(Date date_deb) {
        return null;
    }

    @Override
    public List<Abonnement> getByDateFin(Date date_fin) {
        return null;
    }
}

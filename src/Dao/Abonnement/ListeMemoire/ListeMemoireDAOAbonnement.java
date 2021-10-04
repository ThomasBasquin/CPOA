package Dao.Abonnement.ListeMemoire;

import Dao.Abonnement.ListeMemoire.ListeMemoireDAOAbonnement;
import Metier.Abonnement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListeMemoireDAOAbonnement {

    private static ListeMemoireDAOAbonnement instance;

    private List<Abonnement> donnees;

    public static ListeMemoireDAOAbonnement getInstance() {

        if (instance == null) {
            instance = new ListeMemoireDAOAbonnement();
        }

        return instance;
    }

    private ListeMemoireDAOAbonnement() {

        this.donnees = new ArrayList<Abonnement>();

        Date date = new Date("2013-03-08");
        Date date1 = new Date("2014-05-06");
        this.donnees.add(new Abonnement(1, date ,date1,1,1));
        this.donnees.add(new Abonnement(2, date,date1,1,1));
    }

   /* @Override
    public boolean create(Abonnement  objet) {
        //TODO
    }

    @Override
    public boolean update(Abonnement  objet) {

        //TODO
    }

    @Override
    public boolean delete(Abonnement  objet) {

        //TODO
    }

    @Override
    public List<Abonnement> getBylibelle(String libelle) {

        //TODO
    }

    @Override
    public Abonnement getById(int id) {
        //TODO
    } */
}

package model.dao.factory;


import model.dao.Dao;
import model.dao.abonnement.DaoAbonnement;
import model.dao.client.DaoClient;
import model.dao.periodicite.DaoPeriodicite;
import model.dao.revue.DaoRevue;

import javax.management.openmbean.CompositeData;

public abstract class DaoFactory {

    public static DaoFactory getDAOFactory(Persistance cible){

        DaoFactory daoF = switch (cible) {
            case MYSQL -> new MySqlDaoFactory();
            case LISTE_MEMOIRE -> new ListeMemoireDaoFactory();
        };

        return daoF;
    }
    public abstract DaoAbonnement getAbonnementDAO();

    public abstract DaoClient getClientDAO();

    public abstract DaoPeriodicite getPeriodiciteDAO();

    public abstract DaoRevue getRevueDAO();

}


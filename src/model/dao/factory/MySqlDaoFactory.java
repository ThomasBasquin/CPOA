package model.dao.factory;

import model.dao.abonnement.DaoAbonnement;
import model.dao.abonnement.mysql.MySqlDaoAbonnement;
import model.dao.client.DaoClient;
import model.dao.client.mysql.MySqlDaoClient;
import model.dao.periodicite.DaoPeriodicite;
import model.dao.periodicite.mysql.MySqlDaoPeriodicite;
import model.dao.revue.DaoRevue;
import model.dao.revue.mysql.MySqlDaoRevue;

import javax.management.openmbean.CompositeData;

public class MySqlDaoFactory extends DaoFactory{

    @Override
    public DaoAbonnement getAbonnementDAO() {
        return MySqlDaoAbonnement.getInstance();
    }

    @Override
    public DaoClient getClientDAO() {
        return MySqlDaoClient.getInstance();
    }

    @Override
    public DaoPeriodicite getPeriodiciteDAO() {
        return MySqlDaoPeriodicite.getInstance();
    }

    @Override
    public DaoRevue getRevueDAO() {
        return MySqlDaoRevue.getInstance();
    }

}

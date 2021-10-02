package Dao.Factory;

import Dao.Abonnement.DaoAbonnement;
import Dao.Client.DaoClient;
import Dao.Periodicite.DaoPeriodicite;
import Dao.Periodicite.mysql.MySqlDaoPeriodicite;
import Dao.Revue.DaoRevue;

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
    public DaoPeriodicite getPeriodicteDAO() {
        return MySqlDaoPeriodicite.getInstance();
    }

    @Override
    public DaoRevue getRevueDAO() {
        return MySqlDaoRevue.getInstance();
    }
}

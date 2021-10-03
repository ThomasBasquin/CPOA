package Dao.Factory;

import Dao.Abonnement.DaoAbonnement;
import Dao.Abonnement.mysql.MySqlDaoAbonnement;
import Dao.Client.DaoClient;
import Dao.Client.mysql.MySqlDaoClient;
import Dao.Periodicite.DaoPeriodicite;
import Dao.Periodicite.mysql.MySqlDaoPeriodicite;
import Dao.Revue.DaoRevue;
import Dao.Revue.mysql.MySqlDaoRevue;

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

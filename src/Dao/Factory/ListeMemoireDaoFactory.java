package Dao.Factory;

import Dao.Abonnement.DaoAbonnement;
import Dao.Client.DaoClient;
import Dao.Periodicite.DaoPeriodicite;
import Dao.Periodicite.listememoire.ListeMemoireDaoPeriodicite;
import Dao.Revue.DaoRevue;

public class ListeMemoireDaoFactory extends DaoFactory{

    @Override
    public DaoAbonnement getAbonnementDAO() {
        return ListeMemoireDaoAbonnement.getInstance();
    }

    @Override
    public DaoClient getClientDAO() {
        return ListeMemoireDaoClient.getInstance();
    }

    @Override
    public DaoPeriodicite getPeriodicteDAO() {
        return ListeMemoireDaoPeriodicite.getInstance();
    }

    @Override
    public DaoRevue getRevueDAO() {
        return ListeMemoireDaoRevue.getInstance();
    }
}

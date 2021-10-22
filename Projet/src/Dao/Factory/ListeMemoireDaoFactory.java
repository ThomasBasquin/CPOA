package Dao.Factory;

import Dao.Abonnement.DaoAbonnement;
import Dao.Abonnement.ListeMemoire.ListeMemoireDAOAbonnement;
import Dao.Client.DaoClient;
import Dao.Client.ListeMemoire.ListeMemoireDAOClient;
import Dao.Periodicite.DaoPeriodicite;
import Dao.Periodicite.ListeMemoire.ListeMemoireDAOPeriodicite;
import Dao.Revue.DaoRevue;
import Dao.Revue.ListeMemoire.ListeMemoireDAORevue;

public class ListeMemoireDaoFactory extends DaoFactory{

    @Override
    public DaoAbonnement getAbonnementDAO() {
        return ListeMemoireDAOAbonnement.getInstance();
    }

    @Override
    public DaoClient getClientDAO() { return ListeMemoireDAOClient.getInstance(); }

    @Override
    public DaoPeriodicite getPeriodicteDAO() {
        return ListeMemoireDAOPeriodicite.getInstance();
    }

    @Override
    public DaoRevue getRevueDAO() { return ListeMemoireDAORevue.getInstance(); }

}

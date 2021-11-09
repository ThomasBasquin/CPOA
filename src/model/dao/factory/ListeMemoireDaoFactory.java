package model.dao.factory;

import model.dao.abonnement.DaoAbonnement;
import model.dao.abonnement.listememoire.ListeMemoireDAOAbonnement;
import model.dao.client.DaoClient;
import model.dao.client.listememoire.ListeMemoireDAOClient;
import model.dao.periodicite.DaoPeriodicite;
import model.dao.periodicite.listememoire.ListeMemoireDAOPeriodicite;
import model.dao.revue.DaoRevue;
import model.dao.revue.listememoire.ListeMemoireDAORevue;

import javax.management.openmbean.CompositeData;

public class ListeMemoireDaoFactory extends DaoFactory{

    @Override
    public DaoAbonnement getAbonnementDAO() {
        return (DaoAbonnement) ListeMemoireDAOAbonnement.getInstance();
    }

    @Override
    public DaoClient getClientDAO() { return ListeMemoireDAOClient.getInstance(); }

    @Override
    public DaoPeriodicite getPeriodiciteDAO() {
        return ListeMemoireDAOPeriodicite.getInstance();
    }

    @Override
    public DaoRevue getRevueDAO() { return ListeMemoireDAORevue.getInstance(); }


}

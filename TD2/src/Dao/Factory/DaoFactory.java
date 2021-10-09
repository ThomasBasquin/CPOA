package Dao.Factory;


import Dao.Abonnement.DaoAbonnement;
import Dao.Client.DaoClient;
import Dao.Periodicite.DaoPeriodicite;
import Dao.Revue.DaoRevue;

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
    public abstract DaoPeriodicite getPeriodicteDAO();
    public abstract DaoRevue getRevueDAO();

}


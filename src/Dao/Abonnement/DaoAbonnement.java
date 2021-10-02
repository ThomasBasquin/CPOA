package Dao.Abonnement;

import Dao.Dao;

import java.util.Date;

public interface DaoAbonnement<Abonnement> extends Dao<Abonnement> {
    Abonnement getByAbonnement(Abonnement abonnement);
    Date getByDateDeb(Date date_deb);
    Date getByDateFin(Date date_fin);
}

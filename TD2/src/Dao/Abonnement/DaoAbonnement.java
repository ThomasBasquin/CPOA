package Dao.Abonnement;

import Dao.Dao;
import java.util.Date;
import java.util.List;

public interface DaoAbonnement<Abonnement> extends Dao<Abonnement> {
    List<Abonnement> getByDateDeb(Date date_deb);
    List<Abonnement> getByDateFin(Date date_fin);
}

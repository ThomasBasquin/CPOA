package model.dao.abonnement.mysql;

import model.connexion.Connexion;
import model.dao.abonnement.DaoAbonnement;
import model.metier.Abonnement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlDaoAbonnement implements DaoAbonnement<Abonnement> {

    private static MySqlDaoAbonnement instance;
    private Connexion maConnexion;

    public static MySqlDaoAbonnement getInstance() {
        if (instance == null) {
            instance = new MySqlDaoAbonnement();
        }
        System.out.println("Je suis en SQL");
        return instance;
    }

    private MySqlDaoAbonnement() {
        maConnexion = new Connexion();
    }

    @Override
    public boolean create(Abonnement objet) {
        try {;
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("insert into Abonnement (date_debut , date_fin , id_revue , id_client) values (?,?,?,?)");
            requete.setDate(1, (java.sql.Date) objet.getDate_deb());
            requete.setDate(2, (java.sql.Date) objet.getDate_fin());
            requete.setInt(3,objet.getId_revue());
            requete.setInt(4, objet.getId_abonnement());
            requete.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("pb dans le select" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Abonnement objet) {
        try{
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Abonnement where id_abonnement = ?");
            requete.setInt(1,objet.getId_client());
            requete.executeUpdate();
            return true;
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Abonnement objet) {
        try {
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Abonnement SET date_debut = ? , date_fin = ? , id_revue = ? , id_client = ? WHERE id_abonnement = ?");

            requete.setDate(1, (java.sql.Date) objet.getDate_deb());
            requete.setDate(2,(java.sql.Date) objet.getDate_fin());
            requete.setInt(3,objet.getId_revue());
            requete.setInt(4,objet.getId_client());
            requete.setInt(5,objet.getId_abonnement());
            requete.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("pb dans le select" + e.getMessage());
            return false;
        }
    }

    @Override
    public Abonnement getById(int id) {
        int id_revue = 0;
        int id_client;
        Date date_deb;
        Date date_fin;

        try{
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement requete= laConnexion.prepareStatement("SELECT id_abonnement , id_client,id_revue,date_deb,date_fin, WHERE id_abonnement = ? FROM Abonnement");
            requete.setInt(1,id);
            ResultSet res = requete.executeQuery();

            while  (res.next())
                id_revue = res.getInt("id_revue");
                id_client =res.getInt("id_client");
                date_deb = res.getDate("date_deb");
                date_fin = res.getDate("date_fin");

            return new Abonnement(id,date_deb,date_fin,id_client,id_revue);

        }
        catch (SQLException sql){
            System.out.println("pb dans le select" + sql.getMessage());
        }
        return null;
    }

    @Override
    public List<Abonnement> findAll() {
        return null;
    }

    @Override
    public List<Abonnement> getByDateDeb(Date date_deb) {
        List<Abonnement> dateDebRes = new ArrayList<>();

        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT id_abonnement , id_client , id_revue , date_deb , date_fin , WHERE date_deb = ? FROM Abonnement");
            requete.setDate(1, (java.sql.Date) date_deb);
            ResultSet res = requete.executeQuery();
            while (res.next()){
                int id_revue = res.getInt("id_revue");
                int id_client = res.getInt("id_client");
                int id_abonnement = res.getInt("id_abonnement");
                Date date_fin = res.getDate("date_fin");

                Abonnement abonnement = new Abonnement(id_abonnement , date_deb , date_fin , id_client , id_revue);
                dateDebRes.add(abonnement);
            }

            return dateDebRes;
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return null;
        }

    }

    @Override
    public List<Abonnement> getByDateFin(Date date_fin) {
        List<Abonnement> dateFinRes = new ArrayList<>();

        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT id_abonnement , id_client , id_revue , date_deb , date_deb , WHERE date_fin = ? FROM Abonnement");
            requete.setDate(1, (java.sql.Date) date_fin);
            ResultSet res = requete.executeQuery();
            while (res.next()){
                int id_revue = res.getInt("id_revue");
                int id_client = res.getInt("id_client");
                int id_abonnement = res.getInt("id_abonnement");
                Date date_deb = res.getDate("date_deb");

                Abonnement abonnement = new Abonnement(id_abonnement , date_deb , date_fin , id_client , id_revue);
                dateFinRes.add(abonnement);
            }

            return dateFinRes;
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return null;
        }
    }

}

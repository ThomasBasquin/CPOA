package Metier;

import Connexion.Connexion;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Abonnement {
    private Connexion connexion;
    private Connection laConnexion;

    public Abonnement(){
        connexion = new Connexion();
        laConnexion = connexion.creeConnexion();
    }

    public void insert(int id_client, int id_revue) { // mÃ©thode ajouter
        try{
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("insert into Métier.Abonnement(date_debut,date_fin, id_client,id_revue) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            req.setDate(1,dateValid());
            req.setDate(2,dateValid());
            req.setInt(3, id_client);
            req.setInt(4, id_revue);

            int nbLignes = req.executeUpdate();

            ResultSet res = req.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }

            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();


        } catch (SQLException sqle) {
            System.out.println("Pb dans select" + sqle.getMessage());
        }
    }

    public void delete(int id_abonnement) {
        try {
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("delete from Métier.Abonnement where id_abonnement=?", Statement.RETURN_GENERATED_KEYS);

            req.setInt(1, id_abonnement);

            int nbLignes = req.executeUpdate();

            ResultSet res = req.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }

            if (res != null) {
                res.close();
            } else if (requete != null) {
                requete.close();
            } else if (laConnexion != null) {
                laConnexion.close();
            }
        } catch (SQLException sqle) {
            System.out.println("Pb dans select" + sqle.getMessage());
        }
    }

    public void update(int id_abonnement, int id_client, int id_revue) {
        try{
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("UPDATE Métier.Abonnement SET date_debut = ?, date_fin = ?, id_client = ?, id_revue = ? WHERE id_abonnement = ?", Statement.RETURN_GENERATED_KEYS);

            req.setDate(1, dateValid());
            req.setDate(2, dateValid());
            req.setInt(3, id_client);
            req.setInt(4, id_revue);
            req.setInt(5, id_abonnement);

            int nbLignes = req.executeUpdate();

            ResultSet res = req.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }

            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();


        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }

    public boolean verifFormatDate(String date){

        String formatDate = "yyyy/MM/dd";
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatDate);
            format.setLenient(false);
            format.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public java.sql.Date dateValid(){
        String date1;
        do {
            System.out.println("Veuillez saisir une date de la forme yyyy/mm/dd");
            Scanner sc = new Scanner(System.in);
            date1 = sc.nextLine();
        }while (!verifFormatDate(date1));
        java.util.Date myDate = new java.util.Date(date1);
        return new Date(myDate.getTime());
    }



}
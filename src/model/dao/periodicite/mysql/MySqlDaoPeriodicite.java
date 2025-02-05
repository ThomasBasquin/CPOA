package model.dao.periodicite.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.connexion.Connexion;
import model.metier.*;
import model.dao.periodicite.DaoPeriodicite;

public class MySqlDaoPeriodicite implements DaoPeriodicite<Periodicite> {

    private static MySqlDaoPeriodicite instance;
    private Connexion maConnexion;
    private int id_periodicite;
    private String libelle;

    public static MySqlDaoPeriodicite getInstance() {
        if (instance == null) {
            instance = new MySqlDaoPeriodicite();
        }
        return instance;
    }

    private MySqlDaoPeriodicite() {
        maConnexion = new Connexion();
    }

    @Override
    public boolean create(Periodicite objet) {
        try {
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("Insert into Periodicite (libelle) value (?)");
            req.setString(1, objet.getLibelle());
            req.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Periodicite objet) {
        try {
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("delete from Periodicite where id_periodicite= ?");
            req.setInt(1,objet.getId());
            req.executeUpdate();

            PreparedStatement requeteAI = laConnexion.prepareStatement("ALTER TABLE Periodicite AUTO_INCREMENT = 0");
            requeteAI.executeUpdate();

            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Periodicite objet) {
        try {
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("update Periodicite SET libelle = ? WHERE id_periodicite = ?");
            req.setString(1, objet.getLibelle());
            req.setInt(2,objet.getId());
            req.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public List getBylibelle(String libelle) {
        List<Periodicite> result = new ArrayList<>();
        int id = 0;
        try{
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_periodicite FROM Periodicite WHERE libelle = ? ");
            req.setString(1,libelle);
            ResultSet res = req.executeQuery();
            while (res.next()){
                id =res.getInt("id_periodicite");
                Periodicite p = new Periodicite(id , libelle);
                result.add(p);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public Periodicite getById(int id) {
        String libelle = null;
        try {
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT libelle FROM Periodicite WHERE id_periodicite = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();
            while (res.next()){
                libelle =res.getString("libelle");
            }
            return new Periodicite(id,libelle);
        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Periodicite> findAll() {
        try {
            Connection laConnexion = maConnexion.creeConnexion();

            List<Periodicite> listePeriodicite = new ArrayList<>();
            PreparedStatement req = laConnexion.prepareStatement("SELECT * FROM Periodicite");
            ResultSet res = req.executeQuery();

            while (res.next()){
                id_periodicite = res.getInt(1);
                libelle = res.getString(2);
                Periodicite periodicite = new Periodicite(id_periodicite,libelle);
                listePeriodicite.add(periodicite);
            }
            return listePeriodicite;

        }catch (SQLException sqlException){
            System.out.println("Problème select : "+sqlException.getMessage());
            return null;
        }
    }

}

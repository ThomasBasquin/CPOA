package model.dao.revue.mysql;

import model.connexion.Connexion;
import model.dao.revue.DaoRevue;
import model.metier.Revue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDaoRevue implements DaoRevue<Revue> {

    private static MySqlDaoRevue instance;
    private Connexion maConnexion;

    public static MySqlDaoRevue getInstance() {
        if (instance == null) {
            instance = new MySqlDaoRevue();
        }
        return instance;
    }

    private MySqlDaoRevue() {
        maConnexion = new Connexion();
    }

    @Override
    public boolean create(Revue objet) {
        try {;
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("insert into Revue (id_revue , id_periodicite , description , tarif-numeros , titre , visuel ) values (?,?,?,?,?,?)");
            requete.setInt(1, objet.getId_revue());
            requete.setInt(2, objet.getId_periodicite());
            requete.setString(3, objet.getDescription());
            requete.setFloat(4, objet.getTarif_numeros());
            requete.setString(5, objet.getTitre());
            requete.setString(6, objet.getVisuel());
            requete.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("pb dans le select" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Revue objet) {
        try {
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Revue where id_revue=?");
            requete.setInt(1,objet.getId_revue());
            requete.executeUpdate();
            return true;
        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Revue objet) {
        try {
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Revue SET description = ? , tarif_numero = ?, titre = ? , visuel = ? , id_periodicite = ? WHERE id_revue = ?");
            requete.setString(1, objet.getDescription());
            requete.setFloat(2, objet.getTarif_numeros());
            requete.setString(3, objet.getTitre());
            requete.setString(4, objet.getVisuel());
            requete.setInt(5,objet.getId_periodicite());
            requete.setInt(6, objet.getId_revue());
            requete.executeUpdate();

            return true;
        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }

    }

    @Override
    public Revue getById(int id) {

        Revue revue = null;
        int id_periodicite;
        String titre;
        String description;
        Float tarif_numeros;
        String visuel;

        try {
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE id_revue = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();
            while (res.next()){
                id_periodicite =res.getInt("id_periodicite");
                titre = res.getString("titre");
                description = res.getString("description");
                tarif_numeros = res.getFloat("tarif_numero");
                visuel = res.getString("visuel");

                revue = new Revue(id,id_periodicite,description,tarif_numeros,titre,visuel);
            }

            return revue;

        }catch (SQLException sqle){
            System.out.println("Pb dans le select"+sqle.getMessage());
            return null;
        }

    }

    @Override
    public List<Revue> findAll() {
        return null;
    }

    @Override
    public List<Revue> getByTitre(String titre) {
        List<Revue> titreRes = new ArrayList<>();
        int id = 0;
        try{
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE description = ? ");
            req.setString(1,titre);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id_revue = res.getInt("id_revue");
                String description = res.getString("description");
                Float tarif_numero = res.getFloat("tarif_numero");
                int id_periodicite = res.getInt("id_periodicite");
                String visuel = res.getString("visuel");

                Revue revue = new Revue(id_revue,id_periodicite,description,tarif_numero,titre,visuel);
                titreRes.add(revue);
            }
            return titreRes;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Revue> getByDescription(String description) {
        List<Revue> descriptionRes = new ArrayList<>();
        int id = 0;
        try{
            Connection laConnexion = maConnexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE description = ? ");
            req.setString(1,description);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id_revue = res.getInt("id_revue");
                String titre = res.getString("titre");
                Float tarif_numero = res.getFloat("tarif_numero");
                int id_periodicite = res.getInt("id_periodicite");
                String visuel = res.getString("visuel");

                Revue revue = new Revue(id_revue,id_periodicite,description,tarif_numero,titre,visuel);
                descriptionRes.add(revue);
            }
            return descriptionRes;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

}

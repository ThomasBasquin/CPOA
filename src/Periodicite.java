import java.sql.*;

public class Periodicite {
    private Connexion connexion;
    private Connection laConnexion;

    public Periodicite() {
        connexion = new Connexion();
        laConnexion = connexion.creeConnexion();
    }

    public void insert(String libelle) { // méthode ajouter
        try {
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("insert into Periodicite(libelle) values(?)", Statement.RETURN_GENERATED_KEYS);

            req.setString(1, libelle);
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

    public void delete(int id_periodicite) { // méthode supprimer
        try{
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("delete from Periodicite where id_periodicite=?", Statement.RETURN_GENERATED_KEYS);

            req.setInt(1, id_periodicite);
            int nbLignes =  req.executeUpdate();

            ResultSet res = req.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }

            if(res != null) {
                res.close();
            }
            else if (requete != null) {
                requete.close();
            }
            else if (laConnexion != null){
                laConnexion.close();
            }

        } catch (SQLException sqle){
            System.out.println("Pb dans select" + sqle.getMessage());
        }
    }

    public void update(int id, String libelle){  // méthode modifier
        try {
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("UPDATE Periodicite SET libelle = ? WHERE id_periodicite = ?", Statement.RETURN_GENERATED_KEYS);
            req.setString(1,libelle);
            req.setInt(2,id);
            req.executeUpdate();

            ResultSet res = req.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }

            if(res != null) {
                res.close();
            }
            else if (requete != null) {
                requete.close();
            }
            else if (laConnexion != null){
                laConnexion.close();
            }

        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
}

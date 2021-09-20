import java.sql.*;

public class Revue {
    private Connexion connexion;
    private Connection laConnexion;

    public Revue(){
        connexion = new Connexion();
        laConnexion = connexion.creeConnexion();
    }

    public void insert(String titre, String desc, float tarif, String visuel, int id_periodicite) { // méthode ajouter
        try {
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("insert into Revue(titre,description, tarif_numero,visuel,id_periodicite) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            req.setString(1, titre);
            req.setString(2, desc);
            req.setFloat(3, tarif);
            req.setString(4, visuel);
            req.setInt(5, id_periodicite);

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

    public void delete(int id_revue) { // méthode supprimer
        try {
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("delete from Revue where id_revue=?", Statement.RETURN_GENERATED_KEYS);

            req.setInt(1, id_revue);

            int nbLignes = req.executeUpdate();

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

        } catch (SQLException sqle) {
            System.out.println("Pb dans select" + sqle.getMessage());
        }
    }

    public void update(int id, String titre, String desc, float tarif, String visuel, int id_periodicite) {
        try {
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("UPDATE Revue SET titre = ?, description = ?, tarif_numero = ?, visuel = ?, id_periodicite = ? WHERE id_revue = ?", Statement.RETURN_GENERATED_KEYS);

            req.setString(1, titre);
            req.setString(2, desc);
            req.setFloat(3, tarif);
            req.setString(4, visuel);
            req.setInt(5, id_periodicite);
            req.setInt(6, id);

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

        } catch ( SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }

}

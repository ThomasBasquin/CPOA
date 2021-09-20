import java.sql.*;

public class Abonnement {
    private Connexion connexion;
    private Connection laConnexion;

    public Abonnement(){
        connexion = new Connexion();
        laConnexion = connexion.creeConnexion();
    }

    public void insert(Date date_debut, Date date_fin , int id_client , int id_revue) {
        try {
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("insert into Abonnement(date_debut , date_fin , id_client , id_revue) values(? , ? , ? , ?)", Statement.RETURN_GENERATED_KEYS);

            req.setDate(1 , date_debut);
            req.setDate(2 , date_fin);
            req.setInt(3 , id_client);
            req.setInt(4 , id_revue);
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

}

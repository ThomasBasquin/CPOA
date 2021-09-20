import java.sql.*;

public class Client {
    private Connexion connexion;
    private Connection laConnexion;

    public Client(){
        connexion = new Connexion();
        laConnexion = connexion.creeConnexion();
    }

    public void insert(String nom,String prenom,String no_rue,String voie,String code_postal,String ville,String pays){
        try{
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("insert into Client(nom,prenom,no_rue,voie,code_postal,ville,pays) values(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            req.setString(1,nom);
            req.setString(2,prenom);
            req.setString(3,no_rue);
            req.setString(4,voie);
            req.setString(5,code_postal);
            req.setString(6,ville);
            req.setString(7,pays);
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

    public void delete(int id){
        try {
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("delete from Client where id_client = ?", Statement.RETURN_GENERATED_KEYS);

            req.setInt(1, id);
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

    public void update(int id,String nom , String prenom , String no_rue , String voie , String code_postal , String ville , String pays){
        try{
            Statement requete = laConnexion.createStatement();
            PreparedStatement req = laConnexion.prepareStatement("update Client set nom = ? , prenom = ? , no_rue = ? , voie = ? , code_postal = ? , ville = ? , pays = ? where id_client = ?", Statement.RETURN_GENERATED_KEYS);

            req.setString(1 , nom);
            req.setString(2 , prenom);
            req.setString(3 , no_rue);
            req.setString(4 , voie);
            req.setString(5 , code_postal);
            req.setString(6 , ville);
            req.setString(7 , pays);
            req.setInt(8 , id);
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

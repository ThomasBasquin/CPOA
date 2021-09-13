import java.sql.*;
public class Connexion {
    
public Connection creeConnexion() {
    String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/kuntzw48u_cpoatd1";
    String login = "kuntz48u_appli";
    String pwd = "";
    Connection maConnexion = null;

    try {
        maConnexion = DriverManager.getConnection(url, login, pwd);
    } catch (SQLException sqle) {
        System.out.println("Erreur connexion" + sqle.getMessage());
    } finally {
        System.out.println("PaS cOnNeCtE");
    }
    return maConnexion;
}

}

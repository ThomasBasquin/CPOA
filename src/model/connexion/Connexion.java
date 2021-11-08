package model.connexion;

import java.sql.*;

public class Connexion {
    public Connection creeConnexion() {
        String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/basquin6u_cpoa";
        String login = "basquin6u_appli";
        String pwd = "mpeg-f73";
        Connection maConnexion = null;

        try {
            maConnexion = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException sqle) {
            System.out.println("Erreur connexion" + sqle.getMessage());
        }
        return maConnexion;
    }

}

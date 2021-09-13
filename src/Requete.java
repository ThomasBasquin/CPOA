public class Requete {

    public void uneRequete() {
        try {
            Connection laConnexion = creeConnexion();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select nom, prenom from client");
        } catch (SQLException sqle) {
            System.out.println("Pb select"
        }
    }
}

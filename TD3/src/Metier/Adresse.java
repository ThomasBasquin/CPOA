package Metier;

public class Adresse{

    private String pays;
    private String ville;
    private int zip;
    private int numRue;
    private String rue;

    public Adresse(String pays, String ville, int zip, int numRue, String rue) {
        this.pays = pays;
        this.ville = ville;
        this.zip = zip;
        this.numRue = numRue;
        this.rue = rue;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "pays='" + pays + '\'' +
                ", ville='" + ville + '\'' +
                ", zip=" + zip +
                ", numRue=" + numRue +
                ", rue='" + rue + '\'' +
                '}';
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getNumRue() {
        return numRue;
    }

    public void setNumRue(int numRue) {
        this.numRue = numRue;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }
}

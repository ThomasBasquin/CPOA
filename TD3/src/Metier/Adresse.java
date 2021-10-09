package Metier;

public class Adresse{

    private String pays;
    private String ville;
    private String zip;
    private String numRue;
    private String rue;

    public Adresse(String pays, String ville, String zip, String numRue, String rue) {
        this.pays = pays;
        this.ville = ville;
        this.zip = zip;
        this.numRue = numRue;
        this.rue = rue;
    }

    @Override
    public String toString() {
        return "Adresse : " + numRue + " " + rue + " " + zip + " " + ville + ", " + pays;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getNumRue() {
        return numRue;
    }

    public void setNumRue(String numRue) {
        this.numRue = numRue;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }
}

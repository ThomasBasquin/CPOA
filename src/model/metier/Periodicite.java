package model.metier;

public class Periodicite {

    private int id_periodicite;
    private String libelle;

    public Periodicite(int id, String libelle) {
        this.id_periodicite = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id_periodicite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setId(int id) {
        this.id_periodicite = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Periodicite other = (Periodicite) obj;
        if (id_periodicite != other.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id_periodicite + " - " + libelle;
    }
}
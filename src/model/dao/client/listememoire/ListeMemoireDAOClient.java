package model.dao.client.listememoire;

import model.dao.client.DaoClient;
import model.metier.Client;
import model.metier.Periodicite;

import java.util.ArrayList;
import java.util.List;

public class ListeMemoireDAOClient implements DaoClient<Client> {

    private static ListeMemoireDAOClient instance;

    private List<Client> donnees;

    public static ListeMemoireDAOClient getInstance() {
        if (instance == null) {
            instance = new ListeMemoireDAOClient();
        }
        return instance;
    }


    private ListeMemoireDAOClient(){
        this.donnees = new ArrayList<Client>();

        this.donnees.add(new Client("KUNTZ", "Alban", "76", "Metz", "France", "rue Aux Arenes", "57000", 1));
        this.donnees.add(new Client("BASQUIN", "Thomas", "19", "Metz", "France", "Place du Saulcy", "57000", 2));
    }

    public List<Client> findAll() {
        return this.donnees;
    }

    @Override
    public boolean create(Client objet) {
        objet.setId_client(objet.getId_client());
        while (this.donnees.contains(objet)) {

            objet.setId_client(objet.getId_client() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Client objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Client objet) {
        Client supprime;

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }
        return objet.equals(supprime);
    }

    @Override
    public Client getById(int id) {
        int idx = this.donnees.indexOf(new Client("madne", "toto", "28", "Metz", "France", "rue Belle-Isle", "57000", id));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public List<Client> getByCodePostal(String code_postal) {
        List<Client> listeZip = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getCode_postal().equalsIgnoreCase(code_postal)){
                listeZip.add(pe);
            }
        }
        return listeZip;
    }

    @Override
    public List<Client> getByNom(String nom) {
        List<Client> listeNom = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getNom().equalsIgnoreCase(nom)){
                listeNom.add(pe);
            }
        }
        return listeNom;
    }

    @Override
    public List<Client> getByNoRue(String no_rue) {
        List<Client> listeRue = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getNoRue().equalsIgnoreCase(no_rue)){
                listeRue.add(pe);
            }
        }
        return listeRue;
    }

    @Override
    public List<Client> getByPrenom(String prenom) {
        List<Client> listePrenom = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getPrenom().equalsIgnoreCase(prenom)){
                listePrenom.add(pe);
            }
        }
        return listePrenom;
    }

    @Override
    public List<Client> getByVille(String ville) {
        List<Client> listeVille = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getVille().equalsIgnoreCase(ville)){
                listeVille.add(pe);
            }
        }
        return listeVille;
    }

    @Override
    public List<Client> getByVoie(String voie) {
        List<Client> listeVoie = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getVoie().equalsIgnoreCase(voie)){
                listeVoie.add(pe);
            }
        }
        return listeVoie;
    }

    @Override
    public List<Client> getByPays(String pays) {
        List<Client> listePays = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getPays().equalsIgnoreCase(pays)){
                listePays.add(pe);
            }
        }
        return listePays;
    }
}

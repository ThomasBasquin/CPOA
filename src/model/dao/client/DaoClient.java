package model.dao.client;


import model.dao.Dao;

import java.util.List;

public interface DaoClient<Client> extends Dao<Client> {
    List<Client> getByCodePostal(String code_postal);
    List<Client> getByNom(String nom);
    List<Client> getByNoRue(String no_rue);
    List<Client> getByPrenom(String prenom);
    List<Client> getByVille(String ville);
    List<Client> getByVoie(String voie);
    List<Client> getByPays(String pays);
}

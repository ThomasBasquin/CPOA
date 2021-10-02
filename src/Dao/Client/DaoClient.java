package Dao.Client;

import Dao.Dao;

public interface DaoClient<Client> extends Dao<Client> {
    Client getByRevue(Client client);
    String getByNom(String nom);
    String getByPrenom(String prenom);

}

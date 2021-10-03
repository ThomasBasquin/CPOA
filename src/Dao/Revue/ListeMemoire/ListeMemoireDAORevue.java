package Dao.Revue.ListeMemoire;

import Metier.Revue;

import java.util.List;

public class ListeMemoireDAORevue {

    private static ListeMemoireDAORevue instance;
    private List<Revue> donnees;


    public static ListeMemoireDAORevue getInstance() {
        if (instance==null) {
            instance = new ListeMemoireDAORevue();
        }

        return instance;
    }

}

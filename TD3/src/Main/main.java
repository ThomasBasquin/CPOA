package Main;


import Metier.*;
import ProcessAdresse.ProcessAdresse;

public class main {

    public static void main (String[] args) {

        Adresse test = new Adresse("belgium", "St moulis les metz", "5700", "28", "av. aux Arènes");
        ProcessAdresse poulet = new ProcessAdresse();

        poulet.normalizePays(test);
        poulet.normalizeVille(test);
        poulet.normalizeVoie(test);
        poulet.normalizeZip(test);
        poulet.normalizeNumero(test);
        System.out.println(test.toString());


    }
}

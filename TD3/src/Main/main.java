package Main;


import Metier.*;
import ProcessAdresse.ProcessAdresse;

public class main {

    public static void main (String[] args) {

        Adresse test = new Adresse("belgium", "st moulins les metz", "5700", "28", "av. aux Arènes");
        ProcessAdresse procAd = new ProcessAdresse();

        procAd.normalizePays(test);
        procAd.normalizeVille(test);
        procAd.normalizeVoie(test);
        procAd.normalizeZip(test);
        procAd.normalizeNumero(test);
        procAd.normalizeAbreviation(test);
        System.out.println(test.toString());



    }
}

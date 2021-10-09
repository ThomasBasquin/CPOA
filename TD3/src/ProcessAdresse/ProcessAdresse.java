package ProcessAdresse;

import Metier.Adresse;

import java.util.Locale;

public class ProcessAdresse {

    public void normalizePays(Adresse adresse) {
        String pays = adresse.getPays();
        if(pays.equalsIgnoreCase("letzebuerg")) {
            adresse.setPays("Luxembourg");
        } else if (pays.equalsIgnoreCase("belgium")) {
            adresse.setPays("Belgique");
        } else if (pays.equalsIgnoreCase("switzerland") || pays.equalsIgnoreCase("schweiz")) {
            adresse.setPays("Suisse");
        }
    }

    public void normalizeVille(Adresse adresse) {
        String ville = adresse.getVille();
        String firstVille = ville.substring(0, 1).toUpperCase(); //take the first letter + set to UpperCase
        String preVille = ville.substring(1, ville.length()).trim().replace(" ", "-"); //replace the space to "-"

        adresse.setVille(firstVille + preVille);

        if (ville.toLowerCase().contains("st")) {
            String Saint = ville.replace("st", "Saint");
            adresse.setVille(Saint);
        } else if (ville.toLowerCase().contains("ste")) {
            String Sainte = ville.replace("ste", "Sainte");
            adresse.setVille(Sainte);
        }

    }

}


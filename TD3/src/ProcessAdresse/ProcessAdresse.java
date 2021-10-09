package ProcessAdresse;

import Metier.Adresse;

import java.util.Locale;

public class ProcessAdresse {

    public void normalizePays(Adresse adresse) {  // translate country
        String pays = adresse.getPays();
        if(pays.equalsIgnoreCase("letzebuerg")) {
            adresse.setPays("Luxembourg");
        } else if (pays.equalsIgnoreCase("belgium")) {
            adresse.setPays("Belgique");
        } else if (pays.equalsIgnoreCase("switzerland") || pays.equalsIgnoreCase("schweiz")) {
            adresse.setPays("Suisse");
        }
    }

    public void normalizeVille(Adresse adresse) { // add "-" between words & replace St/Ste by Saint/Sainte
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

    public void normalizeZip(Adresse adresse){
        String zip = adresse.getZip();

        if(zip.substring(0,1).matches("[^\\d.]")) { //if zip start with letter, delete it
            zip = zip.substring(2, zip.length());
        }
        if(zip.length() < 5) { //if zip = 4digit, concat 0 at the beginning
            zip = "0" + zip;
        }
        adresse.setZip(zip);
    }



}


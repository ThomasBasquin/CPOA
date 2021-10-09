package ProcessAdresse;

import Metier.Adresse;

import java.util.Locale;

public class ProcessAdresse {


    public Adresse normalize(Adresse adresse) {
        // Normalyse pays
        if (adresse.getPays() != null) {
            normalizePays(adresse);
        }
        else if(adresse.getVille() != null) {
            normalizeVille(adresse);
        } else if (adresse.getZip() != null) {
            normalizeZip(adresse);

        }else if (adresse.getRue() != null) {
            normalizeVoie(adresse);

        } else if (adresse.getNumRue() != null) {
            normalizeNumero(adresse);
        }
        return adresse;
    }

    public void normalizePays(Adresse adresse) {  // translate country
        String pays = adresse.getPays();
        if (pays.equalsIgnoreCase("letzebuerg")) {
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

        if (ville.contains("st")) {
            String Saint = ville.replace("st", "Saint");
            adresse.setVille(Saint);
        } else if (ville.toLowerCase().contains("ste")) {
            String Sainte = ville.replace("ste", "Sainte");
            adresse.setVille(Sainte);
        } else if (ville.contains("St")) {
            String Saint = ville.replace("St", "Saint");
            adresse.setVille(Saint);
        } else if (ville.contains("Ste")) {
            String Saint = ville.replace("Ste", "Sainte");
            adresse.setVille(Saint);
        }
    }

    public void normalizeZip(Adresse adresse) {
        String zip = adresse.getZip();

        if (zip.substring(0, 1).matches("[^\\d.]")) { //if zip start with letter, delete it
            zip = zip.substring(2, zip.length());
        }
        if (zip.length() < 5) { //if zip = 4digit, concat 0 at the beginning
            zip = "0" + zip;
        }
        adresse.setZip(zip);
    }


    public void normalizeVoie(Adresse adresse) {
        String voie = adresse.getRue();

        if (voie.toLowerCase().contains("boul.")) {
            String boulevard = voie.replace("boul.", "boulevard");
            adresse.setRue(boulevard);
        } else if (voie.toLowerCase().contains("boul")) {
            String boulevard = voie.replace("boul", "boulevard");
            adresse.setRue(boulevard);
        } else if (voie.toLowerCase().contains("bd")) {
            String boulevard = voie.replace("bd", "boulevard");
            adresse.setRue(boulevard);
        } else if (voie.toLowerCase().contains("av.")) {
            String avenue = voie.replace("av.", "avenue");
            adresse.setRue(avenue);
        } else if (voie.toLowerCase().contains("faub.")) {
            String faubourg = voie.replace("faub.", "faubourg");
            adresse.setRue(faubourg);
        } else if (voie.toLowerCase().contains("fg")) {
            String faubourg = voie.replace("fg", "faubourg");
            adresse.setRue(faubourg);
        } else if (voie.toLowerCase().contains("pl")) {
            String place = voie.replace("pl", "place");
            adresse.setRue(place);
        }
    }

    public void normalizeNumero(Adresse adresse) {
        String num = adresse.getNumRue();
        String numero = num + ",";
        adresse.setNumRue(numero);
    }
}


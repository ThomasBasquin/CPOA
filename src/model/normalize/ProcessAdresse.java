package model.normalize;

import model.metier.Client;

public class ProcessAdresse {
    public static Client normalize(Client adresse) {
        if (adresse.getPays() != null) {
            normalizePays(adresse);
        }
        if(adresse.getVille() != null) {
            normalizeVille(adresse);
            normalizeAbreviation(adresse);
        }
        if (adresse.getCode_postal() != null) {
            normalizeZip(adresse);
        }
        if (adresse.getVoie() != null) {
            normalizeRue(adresse);
        }
        if (adresse.getNoRue() != null) {
            normalizeNumero(adresse);
        }
        return adresse;
    }


    public static void normalizePays(Client adresse) {  // translate country
        String pays = adresse.getPays();
        if (pays.equalsIgnoreCase("letzebuerg")) {
            adresse.setPays("Luxembourg");
        } else if (pays.equalsIgnoreCase("belgium")) {
            adresse.setPays("Belgique");
        } else if (pays.equalsIgnoreCase("switzerland") || pays.equalsIgnoreCase("schweiz")) {
            adresse.setPays("Suisse");
        }
    }

    public static void normalizeVille(Client adresse) { // add "-" between words & replace St/Ste by Saint/Sainte
        String ville = adresse.getVille();
        String firstVille = ville.substring(0, 1).toUpperCase(); //take the first letter + set to UpperCase
        String preVille = ville.substring(1, ville.length()).trim().replace(" ", "-"); //replace the space to "-"

        adresse.setVille(firstVille + preVille);
    }
    public static void normalizeAbreviation(Client adresse) {
        String ville = adresse.getVille();

        if (ville.contains("ste")) {
            String Saint = ville.replace("ste", "Sainte");
            adresse.setVille(Saint);
        } else if (ville.toLowerCase().contains("Ste")) {
            String Sainte = ville.replace("Ste", "Sainte");
            adresse.setVille(Sainte);
        } else if (ville.contains("st")) {
            String Saint = ville.replace("st", "Saint");
            adresse.setVille(Saint);
        } else if (ville.contains("St")) {
            String Saint = ville.replace("St", "Saint");
            adresse.setVille(Saint);
        }
    }

    public static void normalizeZip(Client adresse) {
        String zip = adresse.getCode_postal();

        if (zip.substring(0, 1).matches("[^\\d.]")) { //if zip start with letter, delete it
            zip = zip.substring(2, zip.length());
        }
        if (zip.length() < 5) { //if zip = 4digit, concat 0 at the beginning
            zip = "0" + zip;
        }
        adresse.setCode_postal(zip);
    }


    public static void normalizeRue(Client adresse) {
        String voie = adresse.getVoie();

        if (voie.toLowerCase().contains("boul.")) {
            String boulevard = voie.replace("boul.", "boulevard");
            adresse.setVoie(boulevard);
        } else if (voie.toLowerCase().contains("boul")) {
            String boulevard = voie.replace("boul", "boulevard");
            adresse.setVoie(boulevard);
        } else if (voie.toLowerCase().contains("bd")) {
            String boulevard = voie.replace("bd", "boulevard");
            adresse.setVoie(boulevard);
        } else if (voie.toLowerCase().contains("av.")) {
            String avenue = voie.replace("av.", "avenue");
            adresse.setVoie(avenue);
        } else if (voie.toLowerCase().contains("faub.")) {
            String faubourg = voie.replace("faub.", "faubourg");
            adresse.setVoie(faubourg);
        } else if (voie.toLowerCase().contains("fg")) {
            String faubourg = voie.replace("fg", "faubourg");
            adresse.setVoie(faubourg);
        } else if (voie.toLowerCase().contains("pl")) {
            String place = voie.replace("pl", "place");
            adresse.setVoie(place);
        }
    }

    public static void normalizeNumero(Client adresse) {
        String num = adresse.getNoRue();
        String numero = num + ",";
        adresse.setNoRue(numero);
    }
}

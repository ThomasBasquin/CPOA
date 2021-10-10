package Test;

import Metier.Adresse;
import ProcessAdresse.*;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class testAdresse {

    private Adresse ad = new Adresse("belgium", "moulins st les metz", "L-5700","3","av. aux Arènes");
    ProcessAdresse procAd = new ProcessAdresse();

    @BeforeEach
    public void setUp() {
        this.ad = new Adresse("belgium", "Moulins st les metz", "L-5700","3","av. aux Arènes");
    }

    // ======================
    //      TEST PAYS
    // ======================

    @Test
    public void testNormalizeLuxembourg() {
        ad.setPays("letzebuerg");
        assertEquals("Luxembourg" , procAd.normalize(ad).getPays());
    }

    @Test
    public void testNormalizeBelgique() {
        assertEquals("Belgique" , procAd.normalize(ad).getPays());
    }

    @Test
    public void testNormalizeSuisse() {
        ad.setPays("switzerland");
        assertEquals("Suisse" , procAd.normalize(ad).getPays());
        ad.setPays("schweiz");
        assertEquals("Suisse" , procAd.normalize(ad).getPays());
    }

    // ======================
    //      TEST VILLE
    // ======================

    @Test
    public void testNormalizeUpCase() {
        ad.setVille("rennes");
        assertEquals("Rennes" , procAd.normalize(ad).getVille());
    }

    @Test
    public void testNormalizeSpace() {
        ad.setVille("Saint Brieuc");
        assertEquals("Saint-Brieuc" , procAd.normalize(ad).getVille());
    }

    @Test
    public void testNormalizeSaint() {
        ad.setVille("st-Marc");
        assertEquals("Saint-Marc" , procAd.normalize(ad).getVille());
        ad.setVille("St-Marc");
        assertEquals("Saint-Marc" , procAd.normalize(ad).getVille());
        ad.setVille("ste-Marie");
        assertEquals("Sainte-Marie" , procAd.normalize(ad).getVille());
        ad.setVille("Ste-Marie");
        assertEquals("Sainte-Marie" , procAd.normalize(ad).getVille());
    }

    // ======================
    //      TEST ZIP
    // ======================

    @Test
    public void testNormalizeZip() {
        //TODO
    }

    // ======================
    //      TEST RUE
    // ======================

    @Test
    public void testNormalizeRue() {
        ad.setRue("boul. Saint-Joseph");
        assertEquals("boulevard Saint-Joseph" , procAd.normalize(ad).getRue());
        ad.setRue("boul Saint-Joseph");
        assertEquals("boulevard Saint-Joseph" , procAd.normalize(ad).getRue());
        ad.setRue("bd Saint-Joseph");
        assertEquals("boulevard Saint-Joseph" , procAd.normalize(ad).getRue());
        ad.setRue("av. des fougères");
        assertEquals("avenue des fougères" , procAd.normalize(ad).getRue());
        ad.setRue("faub. du Temple");
        assertEquals("faubourg du Temple" , procAd.normalize(ad).getRue());
        ad.setRue("fg du Temple");
        assertEquals("faubourg du Temple" , procAd.normalize(ad).getRue());
        ad.setRue("pl de la Fontaine");
        assertEquals("place de la Fontaine" , procAd.normalize(ad).getRue());
    }

    // ======================
    //      TEST NUMERO
    // ======================

    @Test
    public void testNormalizeNumero() {
        ad.setNumRue("23");
        assertEquals("23," , procAd.normalize(ad).getNumRue());
    }
}

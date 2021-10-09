package Test;

import Metier.*;
import ProcessAdresse.ProcessAdresse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class testAdresse {

    private Adresse ad = new Adresse();
    ProcessAdresse procAd = new ProcessAdresse();

    @BeforeEach
    public void setUp() {
        this.ad = new Adresse();
    }


    // ======================
    //      TEST PAYS
    // ======================

    @Test
    public void testNormalizeLuxembourg() {
        ad.setVille("letzebuerg");
        assertEquals("Luxembourg", procAd.normalize(ad).getPays());
    }

    @Test
    public void testNormalizeBelgique() {
        assertEquals("Belgique", procAd.normalize(ad).getPays());
    }

    @Test
    public void testNormalizeSuisse() {
        ad.setVille("switzerland");
        assertEquals("Suisse", procAd.normalize(ad).getPays());
        ad.setVille("schweiz");
        assertEquals("Suisse", procAd.normalize(ad).getPays());
    }

}

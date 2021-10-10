package Test;

import Metier.Adresse;
import ProcessAdresse.*;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class testAdresse {

    private Adresse ad = new Adresse("belgium", "Moulins st les metz", "L-5700","3","av. aux Arènes");
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
        assertEquals("Luxembourg", procAd.normalize(ad).getPays());
    }

    @Test
    public void testNormalizeBelgique() {
        assertEquals("Belgique", procAd.normalize(ad).getPays());
    }

    @Test
    public void testNormalizeSuisse() {
        ad.setPays("switzerland");
        assertEquals("Suisse", procAd.normalize(ad).getPays());
    }
}

package Test;

import Metier.Adresse;
import org.junit.jupiter.api.BeforeEach;

public class testAdresse {

    private Adresse ad = new Adresse("belgium", "Moulins st les metz", "L-5700","3","av. aux Arènes");

    @BeforeEach
    public void setUp() {
        this.ad = new Adresse("belgium", "Moulins st les metz", "L-5700","3","av. aux Arènes");
    }

}

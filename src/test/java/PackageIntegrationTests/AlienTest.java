package PackageIntegrationTests;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class AlienTest {

    @Test
    public void testBombInitialization() {
        Logger log = Logger.getLogger("AlienBombInitializationTest");

        // Inicialización del alienígena en coordenadas específicas
        int posicionX = 100;
        int posicionY = 100;
        Alien alienInstance = new Alien(posicionX, posicionY);

        // Verificamos que la bomba esté correctamente asignada
        Alien.Bomb alienBomb = alienInstance.getBomb();
        assertNotNull(alienBomb, "La bomba debería inicializarse correctamente.");

        // Validar que la bomba está en la misma posición que el alienígena
        boolean posicionValida = (alienBomb.getX() == posicionX && alienBomb.getY() == posicionY);
        log.log(Level.INFO, "Validación de posición de la bomba: " + posicionValida);
        assertEquals(posicionX, alienBomb.getX(), "La posición X de la bomba no coincide con la del alienígena.");
        assertEquals(posicionY, alienBomb.getY(), "La posición Y de la bomba no coincide con la del alienígena.");
    }
}

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class bombTestCB {

    @ParameterizedTest
    @CsvSource({
            // Coordenadas válidas dentro de los límites del tablero
            "348, 340, 348, 340, Camino 1: Dentro del tablero",
            // Coordenadas fuera de los límites del tablero
            "368, 360, 358, 350, Camino 2:Fuera del tablero"
    })
    void testInitBomb(int x, int y, int expectedX, int expectedY, String description) {
        // Crear un objeto Bomb como clase interna de Alien
        Alien alien = new Alien(0, 0); // Crear el objeto Alien necesario
        Alien.Bomb bomb = new Alien.Bomb(x, y); // Crear la bomba a través del Alien

        // Verificar las coordenadas de la bomba
        assertEquals(expectedX, bomb.getX(), description + " - Coordenada X incorrecta");
        assertEquals(expectedY, bomb.getY(), description + " - Coordenada Y incorrecta");
    }
}

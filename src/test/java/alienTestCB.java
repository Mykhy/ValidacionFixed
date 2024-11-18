import main.Commons;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertEquals;

class alienTestCB {

    @ParameterizedTest
    @CsvSource({
            "900, 700, 800, 600", // Camino 1: x y y fuera del límite superior
            "-50, -50, 0, 0",    // Camino 2: x y y fuera del límite inferior
            "100, 50, 100, 50",  // Camino 3: x y y dentro de los límites
            "900, -50, 800, 0",  // Camino 4: x fuera superior, y fuera inferior
            "-50, 700, 0, 600"   // Camino 5: x fuera inferior, y fuera superior
    })
    void testInitAlien(int inputX, int inputY, int expectedX, int expectedY) throws Exception {
        Alien alien = new Alien(0, 0);

        alien.initAlien(inputX,inputY);

        // Verificar que las coordenadas resultantes coinciden con las esperadas
        assertEquals(expectedX, alien.getX());
        assertEquals(expectedY, alien.getY());
    }
    @Test
    void testAct() {

        int xInicial = 100;
        int yInicial = 50;
        int direction = 20; // Dirección para mover el Alien

        Alien alien = new Alien(xInicial, yInicial); // Posición inicial en (100, 50)
        int alienWidth = Commons.ALIEN_WIDTH; // Ancho del Alien (suponiendo que es una constante)

        // Ejecutar el método
        alien.act(direction);

        // Verificar que la posición x se haya actualizado correctamente
        assertEquals(xInicial + direction + alienWidth, alien.getX());
        assertEquals(yInicial, alien.getY()); // La posición y no debería cambiar
    }

}


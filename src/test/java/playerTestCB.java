import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import space_invaders.sprites.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class playerTestCB {

    private Player player;
    private JPanel panel; // Componente ficticio para generar KeyEvent

    @BeforeEach
    public void setUp() {
        player = new Player();
        panel = new JPanel();
    }

    @Test
    void testInitPlayer() {
        // Crear una instancia de Player
        Player player = new Player();

        // Verificar las coordenadas iniciales del jugador
        assertEquals(179, player.getX(), "La coordenada X inicial del jugador debe ser 270.");
        assertEquals(175, player.getY(), "La coordenada Y inicial del jugador debe ser 280.");

        // Verificar que la imagen del jugador se cargó correctamente
        assertNotNull(player.getImage(), "La imagen del jugador debe estar cargada.");
    }
    @ParameterizedTest
    @CsvSource({
            // KeyEvent.VK_LEFT = 37
            "0, 0, 37, Camino 1: Movimiento a la izquierda, restringido por BORDER_LEFT",
            // KeyEvent.VK_RIGHT = 39
            "358, 358, 39, Camino 2: Movimiento a la derecha, restringido por BORDER_RIGHT",
            // KeyEvent.VK_RIGHT = 39
            "50, 52, 39, Camino 3: Movimiento dentro de los bordes"
    })
    void testPlayerAct(int startX, int expectedX, int keyCode, String message) {
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, ' ');
        player.setX(startX);
        player.keyPressed(keyEvent);
        player.act();

        assertEquals(expectedX, player.getX(), message);
    }

    @ParameterizedTest
    @CsvSource({
            "37, -2, Camino 1: VK_LEFT reduce dx a -2", // KeyEvent.VK_LEFT = 37
            "39, 2, Camino 2: VK_RIGHT aumenta dx a 2", // KeyEvent.VK_RIGHT = 39
            "65, 0, Camino 4: Otra tecla (A) no afecta dx" // Letra 'A' = 65
    })
    void testKeyPressed(int keyCode, int expectedDx, String message) {
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, ' ');
        player.keyPressed(keyEvent);

        assertEquals(expectedDx, player.dx, message);
    }

    @ParameterizedTest
    @CsvSource({
            "37, 0, Camino 1: Liberar VK_LEFT establece dx en 0", // KeyEvent.VK_LEFT = 37
            "39, 0, Camino 2: Liberar VK_RIGHT establece dx en 0", // KeyEvent.VK_RIGHT = 39
            "65, 0, Camino 4: Liberar tecla no relacionada (A) no afecta dx" // Letra 'A' = 65
    })
    void testKeyReleased(int keyCode, int expectedDx, String message) {
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, keyCode, ' ');
        player.keyPressed(keyEvent); // Asignar un valor inicial a dx
        player.keyReleased(keyEvent);
        assertEquals(expectedDx, player.dx, message); // getDx() asume que dx no es público
    }
}

import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import javax.swing.Timer;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class boardTestCB {
    private Board board;

    @BeforeEach
    public void setUp() {
       board = new Board();
    }
    @Test
    public void testGameInit() throws Exception {
        board.gameInit();


        // Verificar que la lista de alienígenas tiene el tamaño correcto
        assertNotNull(board.getAliens(), "La lista de alienígenas no debería ser nula");
        assertEquals(24, board.getAliens().size(), "Deberían haberse creado 24 alienígenas");

        // Verificar las posiciones de los alienígenas generados
        List<Alien> aliens = board.getAliens();
        int expectedX = Commons.ALIEN_INIT_X;
        int expectedY = Commons.ALIEN_INIT_Y;

        for (int i = 0; i < 4; i++) { // Bucle externo
            for (int j = 0; j < 6; j++) { // Bucle interno
                int index = i * 6 + j;
                Alien alien = aliens.get(index);
                assertEquals(expectedX + 18 * j, alien.getX(),
                        "La coordenada X del alien no es correcta para i=" + i + ", j=" + j);
                assertEquals(expectedY + 18 * i, alien.getY(),
                        "La coordenada Y del alien no es correcta para i=" + i + ", j=" + j);
            }
        }
        assertNotNull(board.getPlayer(), "El jugador debería estar inicializado");
        assertNotNull(board.getShot(), "El disparo debería estar inicializado");

    }

    @Test
    public void testUpdateVictoria() throws Exception{
        // Caso: deaths == Commons.CHANCE
        board.setDeaths(Commons.CHANCE); // Simular que todos los alienígenas han sido destruidos

        board.update();


        assertFalse(board.isInGame(), "El juego debería haber terminado (inGame == false)");
        assertEquals("Game won!", board.getMessage(), "El mensaje debería ser 'Game won!'");
        assertFalse(board.getTimer().isRunning());
    }
    @Test
    public void testUpdateNoVictoria() throws Exception{
        // Caso: deaths != Commons.CHANCE
        board.setDeaths(Commons.CHANCE - 1); // Simular que aún quedan alienígenas

        board.update();

        // Verificar que el juego continúa
        assertTrue(board.isInGame(), "El juego debería continuar (inGame == true)");
        assertTrue(board.getTimer().isRunning());
    }

    @ParameterizedTest
    @CsvSource({
            //shotVisible,shotX,shotY,alienVisible,alienX,alienY, alienExpectedVisibility,shotExpectedVisibility,expectedDeaths,Message
            // Camino 1: El disparo está visible y colisiona con un alienígena
            "true, 10, 10, true, 10, 10, false, false, 1, Camino 1: Disparo colisiona con alienígena",
            // Camino 2: El disparo está visible pero no colisiona
            "true, 0, 0, true, 100, 100, true, true, 0, Camino 2: Disparo no colisiona con alienígena",
            // Camino 3: El disparo supera los límites del tablero
            "true, 100, -1, true, 100, 100, true, false, 0, Camino 3: Disparo fuera del tablero",
            // Camino 4: El disparo no está visible desde el principio
            "false, 100, 100, true, 100, 100, true, false, 0, Camino 4: Disparo no visible",
            // Camino 5: No hay alienígenas en el tablero
            "true, 10, 10, false, 0, 0, true, true, 0, Camino 5: No hay alienígenas en el tablero"

    })
    void testUpdateShots(boolean shotVisible, int shotX, int shotY, boolean alienVisible, int alienX, int alienY, boolean alienExpectedVisibility, boolean shotExpectedVisibility, int expectedDeaths, String message) {
        board.gameInit(); // Inicializar el tablero
        // Configurar disparo
        board.getShot().setVisible(shotVisible);
        board.getShot().setX(shotX);
        board.getShot().setY(shotY);
        // Configurar alienígena si existe
        if (!board.getAliens().isEmpty()) {
            Alien targetAlien = board.getAliens().get(0);
            targetAlien.setVisible(alienVisible);
            targetAlien.setX(alienX);
            targetAlien.setY(alienY);
        }
        board.update_shots();
        // Verificar alienígenas
        if (!board.getAliens().isEmpty()) {
            Alien targetAlien = board.getAliens().get(0);
            assertEquals(alienExpectedVisibility, targetAlien.isVisible(), message + " - Alien visibility");
        }
        // Verificar disparo
        assertEquals(shotExpectedVisibility, board.getShot().isVisible(), message + " - Shot visibility");
        // Verificar contador de muertes
        assertEquals(expectedDeaths, board.getDeaths(), message + " - Death count");
    }

    @Test
    void updateAliensCamino1() {
        board.getAliens().clear(); // No hay alienígenas en el tablero

        board.update_aliens();

        assertTrue(board.isInGame(), "El juego debería continuar porque no hay alienígenas en el tablero.");
        assertEquals("", board.getMessage(), "No debería haber un mensaje porque el juego continúa.");
    }
    @Test
    void updateAliensCamino2() {
        board.gameInit(); // Inicializar alienígenas

        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT);
        aliens.get(0).setY(5);

        int initialY = aliens.get(0).getY();

        board.setDirection(-1); // Dirección inicial izquierda
        board.update_aliens();

        assertEquals(Commons.GO_DOWN + initialY, aliens.get(0).getY(), "El alienígena debería haber bajado.");
        assertEquals(1, board.direction, "La dirección debería haber cambiado a derecha.");
    }
    @Test
    void updateAliensCamino3() {
        board.gameInit();

        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(Commons.BORDER_LEFT);
        aliens.get(0).setY(10);

        int initialY = aliens.get(0).getY();

        board.setDirection(1); // Dirección inicial derecha
        board.update_aliens();

        assertEquals(Commons.GO_DOWN + initialY, aliens.get(0).getY(), "El alienígena debería haber bajado.");
        assertEquals(-1, board.direction, "La dirección debería haber cambiado a izquierda.");
    }
    @Test
    void updateAliensCamino4() {
        board.gameInit();

        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(150);
        aliens.get(0).setY(50);

        int initialX = aliens.get(0).getX();
        int initialY = aliens.get(0).getY();

        board.setDirection(1); // Dirección inicial derecha
        board.update_aliens();

        assertTrue(aliens.get(0).getX() > initialX, "El alienígena debería haberse movido a la derecha.");
        assertEquals(initialY, aliens.get(0).getY(), "La coordenada Y del alienígena no debería haber cambiado.");
    }
    @Test
    void updateAliensCamino5() {
        board.gameInit();

        List<Alien> aliens = board.getAliens();
        aliens.get(0).setY(Commons.GROUND - Commons.ALIEN_HEIGHT + 1); // Alienígena en el borde del suelo

        board.update_aliens();

        assertFalse(board.isInGame(), "El juego debería terminar porque el alienígena alcanzó el suelo.");
        assertEquals("Invasion!", board.message, "El mensaje debería indicar 'Invasion!'.");
    }
    @Test
    void updateAliensCamino6() {
        board.gameInit();

        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(Commons.BORDER_LEFT);
        aliens.get(0).setY(50);
        aliens.get(1).setX(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT);
        aliens.get(1).setY(50);

        int initialYAlien1 = aliens.get(0).getY();
        int initialYAlien2 = aliens.get(1).getY();

        board.setDirection(1); // Dirección inicial derecha
        board.update_aliens();

        assertEquals(Commons.GO_DOWN + initialYAlien1, aliens.get(0).getY(), "Alien 1 debería haber bajado.");
        assertEquals(Commons.GO_DOWN + initialYAlien2, aliens.get(1).getY(), "Alien 2 debería haber bajado.");
        assertEquals(-1, board.direction, "La dirección debería haber cambiado a izquierda.");
    }
    @Test
    void updateAliensCamino7() {
        board.gameInit();

        List<Alien> aliens = board.getAliens();
        aliens.get(0).setY(Commons.GROUND - Commons.ALIEN_HEIGHT + 1); // Alienígena en el borde del suelo
        aliens.get(1).setY(50);

        board.update_aliens();

        assertFalse(board.isInGame(), "El juego debería terminar porque un alienígena alcanzó el suelo.");
        assertEquals("Invasion!", board.message, "El mensaje debería indicar 'Invasion!'.");
    }


    @Test
    void testBomb_Update() {
        //Los 5 caminos
    testBombHitsGround();
    testNoBombDropped();
    testBombHitsPlayer();
    testMultipleBombsDropped();
    testBombDoesNotHitPlayerOrGround();

    }

    void testBombHitsPlayer() {
        // Configuración: Simular una bomba de un alienígena que golpea al jugador
        Alien alien = board.getAliens().get(0); // Obtener el primer alienígena
        Player player = board.getPlayer();

        // Posicionar la bomba para que golpee al jugador
        alien.getBomb().setX(player.getX());
        alien.getBomb().setY(player.getY());
        alien.getBomb().setDestroyed(true);

        // Acción
        board.update_bomb();

        // Verificar que el jugador fue alcanzado y la bomba se destruyó
        assertFalse(player.isDying(), "El jugador debería desaparecer tras ser alcanzado por la bomba.");
        assertTrue(alien.getBomb().isDestroyed(), "La bomba debería destruirse tras golpear al jugador.");
    }


    void testBombHitsGround() {
        // Configuración: Simular una bomba que llega al suelo
        Alien alien = board.getAliens().get(0); // Obtener el primer alienígena
        alien.getBomb().setX(100);
        alien.getBomb().setY(Commons.GROUND - Commons.BOMB_HEIGHT);
        alien.getBomb().setDestroyed(false);

        // Acción
        board.update_bomb();

        // Verificar que la bomba se destruyó al llegar al suelo
        assertTrue(alien.getBomb().isDestroyed(), "La bomba debería destruirse tras llegar al suelo.");
    }


    void testBombDoesNotHitPlayerOrGround() {
        // Configuración: Simular una bomba en el aire
        Alien alien = board.getAliens().get(0); // Obtener el primer alienígena
        alien.getBomb().setX(100);
        alien.getBomb().setY(100);
        alien.getBomb().setDestroyed(false);

        // Acción
        board.update_bomb();

        // Verificar que la bomba continúa moviéndose hacia abajo
        assertEquals(101, alien.getBomb().getY(), "La bomba debería moverse hacia abajo en 1 unidad.");
        assertFalse(alien.getBomb().isDestroyed(), "La bomba no debería destruirse mientras esté en el aire.");
    }


    void testNoBombDropped() {
        // Configuración: Simular que no se lanza ninguna bomba
        Alien alien = board.getAliens().get(0); // Obtener el primer alienígena
        alien.getBomb().setDestroyed(true); // La bomba está destruida (no lanzada)

        // Acción
        board.update_bomb();

        // Verificar que no pasa nada
        assertTrue(alien.getBomb().isDestroyed(), "No debería crearse ni actualizarse ninguna bomba.");
    }


    void testMultipleBombsDropped() {
        // Configuración: Simular que varios alienígenas lanzan bombas
        List<Alien> aliens = board.getAliens();
        for (Alien alien : aliens) {
            alien.getBomb().setX(alien.getX());
            alien.getBomb().setY(alien.getY());
            alien.getBomb().setDestroyed(false);
        }

        // Acción
        board.update_bomb();

        // Verificar que todas las bombas se movieron hacia abajo
        for (Alien alien : aliens) {
            assertEquals(alien.getY() + 1, alien.getBomb().getY(), "Cada bomba debería moverse hacia abajo en 1 unidad.");
        }
    }
    }



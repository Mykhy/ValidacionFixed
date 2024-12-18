package PackageIntegrationTests;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Logger log;
    private Board boardInstance;
    private Alien testAlien;
    private Shot testShot;
    private Player testPlayer;

    @Test
    public void testGameInitialization() {
        log = Logger.getLogger("TestGameInitialization");

        // Inicialización de variables a null
        testAlien = null;
        testPlayer = null;
        testShot = null;

        // Creación de la instancia del tablero
        boardInstance = new Board();

        // Recuperación de los objetos inicializados
        testAlien = boardInstance.getAliens().get(0);
        testPlayer = boardInstance.getPlayer();
        testShot = boardInstance.getShot();
        boolean allObjectsCreated = testAlien != null && testPlayer != null && testShot != null;
        log.log(Level.INFO, "Objetos creados correctamente: " + allObjectsCreated);
        assertTrue(allObjectsCreated);

        Alien.Bomb bombInstance = testAlien.getBomb();
        log.log(Level.INFO, "Bomba inicializada: " + (bombInstance != null));
        assertNotNull(bombInstance);
    }

    @Test
    public void testUpdateBoardState() {
        setupUpdateTests("TestUpdateBoardState");

        testPlayer = new Player();
        testShot = new Shot();
        testAlien = boardInstance.getAliens().get(0);
        int initialAlienX = testAlien.getX();

        Alien.Bomb bombInstance = testAlien.getBomb();

        // Configuración inicial del estado tal que muera el disparo y el personaje se mueva
        testShot.setX(testAlien.getX());
        testShot.setY(0);
        bombInstance.setX(testPlayer.getX() + Commons.PLAYER_WIDTH / 2);
        bombInstance.setY(testPlayer.getY() + Commons.PLAYER_HEIGHT / 2);
        testShot.setVisible(true);
        testAlien.setVisible(true);
        testPlayer.setDx(2);
        bombInstance.setDestroyed(false);

        int initialPlayerX = testPlayer.getX();

        boardInstance.setPlayer(testPlayer);
        boardInstance.setShot(testShot);

        logStateBeforeUpdate(initialPlayerX, initialAlienX, testShot, bombInstance);
        waitForGameCycle();
        logStateAfterUpdate(testPlayer, testAlien, testShot, bombInstance);

        assertTrue(testPlayer.getX() != initialPlayerX,"Player X");
        assertTrue(testAlien.getX() != initialAlienX,"Alien X");
        assertFalse(testShot.isVisible(), "show visible ");
        assertTrue(bombInstance.isDestroyed(),"bomb destroyed");
    }

    @Test
    public void testUpdateShotVisibility() {
        setupUpdateTests("TestUpdateShotVisibility");

        testAlien = boardInstance.getAliens().get(0);
        testShot = new Shot();

        testShot.setX(testAlien.getX());
        testShot.setY(testAlien.getY());
        testShot.setVisible(true);

        boardInstance.setShot(testShot);
        log.log(Level.INFO, "Estado inicial del disparo (visible): " + testShot.isVisible());

        waitForGameCycle();

        log.log(Level.INFO, "Estado final del disparo (visible): " + testShot.isVisible());
        assertTrue(!testShot.isVisible());
    }

    @Test
    public void testAlienMovementUpdate() {
        setupUpdateTests("TestAlienMovementUpdate");

        testAlien = boardInstance.getAliens().get(0);
        int initialAlienX = testAlien.getX();

        log.log(Level.INFO, "Posición inicial del alien: " + initialAlienX);

        waitForGameCycle();

        log.log(Level.INFO, "Posición final del alien: " + testAlien.getX());
        assertTrue(testAlien.getX() != initialAlienX);
    }

    private void setupUpdateTests(String testName) {
        boardInstance = new Board();
        log = Logger.getLogger(testName);
    }

    private void waitForGameCycle() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error en la espera del ciclo del juego", e);
        }
    }

    private void logStateBeforeUpdate(int playerX, int alienX, Shot shot, Alien.Bomb bomb) {
        log.log(Level.INFO, "Estado inicial:\n\tPlayer X: " + playerX +
                "\n\tShot Visible: " + shot.isVisible() +
                "\n\tAlien X: " + alienX +
                "\n\tBomb Destruida: " + bomb.isDestroyed());
    }

    private void logStateAfterUpdate(Player player, Alien alien, Shot shot, Alien.Bomb bomb) {
        log.log(Level.INFO, "Estado final:\n\tPlayer X: " + player.getX() +
                "\n\tShot Visible: " + shot.isVisible() +
                "\n\tAlien X: " + alien.getX() +
                "\n\tBomb Destruida: " + bomb.isDestroyed());
    }
}

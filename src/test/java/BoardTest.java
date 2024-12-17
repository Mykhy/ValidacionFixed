import main.Board;
import main.Commons;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @org.junit.jupiter.api.Test
    void gameInitTest() {
        board.gameInit();
        assertEquals(Commons.NUMBER_OF_ALIENS_TO_DESTROY, board.getAliens().size());
        assertNotNull(board.getPlayer());
        assertNotNull(board.getShot());
        List<Alien> aliens = board.getAliens();
        int expectedX = Commons.ALIEN_INIT_X;
        int expectedY = Commons.ALIEN_INIT_Y;
        int y = 0;
        int suma = 0;
        for(int i = 0; i < Commons.NUMBER_OF_ALIENS_TO_DESTROY;i++){
            Alien alien = aliens.get(i);
            if(alien.getY() == expectedY + 18){
                expectedY += 18;
                y = 1;
                expectedX = Commons.ALIEN_INIT_X;
            }else {
                suma = 18 * y;
                expectedX = Commons.ALIEN_INIT_X + suma;
                y++;
            }
            assertEquals(expectedX, alien.getX());
            assertEquals(expectedY, alien.getY());
        }

    }
    //TEST UPDATE
    @org.junit.jupiter.api.Test
    void update1(){
        board.gameInit();
        board.setInGame(true);
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        board.update();
        assertFalse(board.isInGame());
    }
    @org.junit.jupiter.api.Test
    void update2(){
        board.gameInit();
        board.setDeaths(0);
        board.setInGame(true);
        board.update();
        assertTrue(board.isInGame());
    }
    //TEST UPDATE_SHOT
    @org.junit.jupiter.api.Test
    void updateShot1(){
        board.gameInit();
        Alien alien = board.getAliens().get(0);
        board.setDeaths(0);
        Shot shot = new Shot();
        shot.initShot(alien.getX() + 6, alien.getY() + 1);
        board.setShot(shot);
        board.update_shots();
        assertTrue(alien.isDying());
        assertEquals(1, board.getDeaths());
    }
    @org.junit.jupiter.api.Test
    void updateShot2(){
        board.gameInit();
        Alien alien = board.getAliens().get(0);
        board.setDeaths(0);
        Shot shot = new Shot();
        shot.initShot(alien.getX(), alien.getY() + 7);
        board.setShot(shot);
        board.update_shots();
        assertTrue(alien.isDying());
        assertEquals(1, board.getDeaths());
    }
    //FUERA DEL TABLERO Y DEL ALIEN
    @org.junit.jupiter.api.Test
    void updateShot3(){
        board.gameInit();
        board.setDeaths(0);
        Shot shot = new Shot();
        shot.initShot(-10, 0);
        board.setShot(shot);
        board.update_shots();
        assertEquals(0, board.getDeaths());
    }

    //TEST UPDATE_ALIENS
    @org.junit.jupiter.api.Test
    void updateAliens1(){
        board.gameInit();
        board.setDirection(-1);
        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(150);
        aliens.get(0).setY(5);
        int initialX = aliens.get(0).getX();
        int initialY = aliens.get(0).getY();
        board.update_aliens();
        assertTrue(aliens.get(0).getX() < initialX);
        assertEquals(initialY, aliens.get(0).getY());
    }
    @org.junit.jupiter.api.Test
    void updateAliens2(){
        board.gameInit();
        board.setDirection(1);
        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(150);
        aliens.get(0).setY(5);
        int initialX = aliens.get(0).getX();
        int initialY = aliens.get(0).getY();
        board.update_aliens();
        assertTrue(aliens.get(0).getX() > initialX);
        assertEquals(initialY, aliens.get(0).getY());
    }
    @org.junit.jupiter.api.Test
    void updateAliens3(){
        board.gameInit();
        board.setDirection(-1);
        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(5);
        aliens.get(0).setY(5);
        int initialX = aliens.get(0).getX();
        int initialY = aliens.get(0).getY();
        board.update_aliens();
        assertEquals(1, board.getDirection());
        assertTrue(aliens.get(0).getX() >= initialX);
        assertTrue(initialY > aliens.get(0).getY());
    }
    @org.junit.jupiter.api.Test
    void updateAliens4(){
        board.gameInit();
        board.setDirection(1);
        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(328);
        aliens.get(0).setY(5);
        int initialX = aliens.get(0).getX();
        int initialY = aliens.get(0).getY();
        board.update_aliens();
        assertEquals(-1, board.getDirection());
        assertTrue(aliens.get(0).getX() <= initialX);
        assertTrue(initialY > aliens.get(0).getY());
    }
    @org.junit.jupiter.api.Test
    void updateAliens5(){
        board.gameInit();
        board.setDirection(1);
        List<Alien> aliens = board.getAliens();
        aliens.get(0).setX(328);
        aliens.get(0).setY(275);
        board.update_aliens();
        assertEquals("Invasion!", board.getMessage());
        assertFalse(board.isInGame());
    }
    //TEST UPDATE_BOMB
    //Generacion Bomba
    @org.junit.jupiter.api.Test
    void updateBomb1(){
        board.gameInit();
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);
        board.update_bomb();
        assertFalse(bomb.isDestroyed());
        assertEquals(alien.getX(), bomb.getX());
        assertEquals(alien.getY(), bomb.getY());
    }
    //Destruccion bomba llega suelo
    @org.junit.jupiter.api.Test
    void updateBomb2(){
        board.gameInit();
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setY(284);
        board.update_bomb();
        assertEquals(285,bomb.getY());
        assertTrue(bomb.isDestroyed());
    }
    //Colision con el jugador
    @org.junit.jupiter.api.Test
    void updateBomb3(){
        board.gameInit();
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(board.getPlayer().getX() + 6);
        bomb.setY(board.getPlayer().getY());
        assertTrue(board.getPlayer().isVisible());
        board.update_bomb();
        assertTrue(board.getPlayer().isDying());
        assertTrue(bomb.isDestroyed());
    }
    //La bomba baja una posicion en el aire
    @org.junit.jupiter.api.Test
    void updateBomb4(){
        board.gameInit();
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(board.getPlayer().getX() - 30);
        bomb.setY(board.getPlayer().getY() - 30);
        int initialY = bomb.getY();
        int initialX = bomb.getX();
        board.update_bomb();
        assertEquals(initialX, bomb.getX());
        assertEquals(initialY + 1, bomb.getY());
        assertFalse(bomb.isDestroyed());
        assertFalse(board.getPlayer().isDying());
    }

}
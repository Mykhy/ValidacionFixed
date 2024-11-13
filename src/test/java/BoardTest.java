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
        for(int i = 0; i < Commons.NUMBER_OF_ALIENS_TO_DESTROY;i++){
            Alien alien = aliens.get(i);
            if(alien.getY() == expectedY + 18){
                expectedY += 18;
                y = 0;
                expectedX = Commons.ALIEN_INIT_X;
            }else {
                expectedX = Commons.ALIEN_INIT_X + 18 * y;
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
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        board.update();
        assertEquals(false, board.isInGame());
    }
    @org.junit.jupiter.api.Test
    void update2(){
        board.gameInit();
        assertEquals(true, board.isInGame());
        board.update();
        assertEquals(true, board.isInGame());
    }
    //TEST UPDATE_SHOT
    @org.junit.jupiter.api.Test
    void updateShot1(){
        board.gameInit();
        Alien alien = board.getAliens().get(0);
        board.setDeaths(0);
        Shot shot = new Shot();
        shot.initShot(alien.getX() - 6, alien.getY() + 1);
        board.setShot(shot);
        board.update_shots();
        assertEquals(true,alien.isDying());
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
        assertEquals(true,alien.isDying());
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
}
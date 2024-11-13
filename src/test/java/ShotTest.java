import org.junit.jupiter.api.BeforeEach;
import main.Commons;
import space_invaders.sprites.Shot;

import static org.junit.jupiter.api.Assertions.*;

public class ShotTest {

    Shot shot;
    @BeforeEach
    void setUp(){
        this.shot = new Shot();
    }

    //TEST CONSTRUCTOR
    @org.junit.jupiter.api.Test
    void shot(){
        shot = new Shot();
        assertNotNull(shot);
    }

    //TEST INITSHOT
    @org.junit.jupiter.api.Test
    void initShot1(){
        shot.initShot(0,175);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(6, shot.getX());
        assertEquals(174, shot.getY());
    }
    @org.junit.jupiter.api.Test
    void initShot2(){
        shot.initShot(1, 175);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(7, shot.getX());
        assertEquals(174, shot.getY());
    }
    @org.junit.jupiter.api.Test
    void initShot3(){
        shot.initShot(179, 350);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(185, shot.getX());
        assertEquals(349, shot.getY());
    }
    @org.junit.jupiter.api.Test
    void initShot4(){
        shot.initShot(179, 349);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(185, shot.getX());
        assertEquals(348, shot.getY());
    }
    @org.junit.jupiter.api.Test
    void initShot5(){
        shot.initShot(179, 175);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(185, shot.getX());
        assertEquals(174, shot.getY());
    }
    @org.junit.jupiter.api.Test
    void initShot6(){
        shot.initShot(179, 1);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(185, shot.getX());
        assertEquals(0, shot.getY());
    }
    @org.junit.jupiter.api.Test
    void initShot7(){
        shot.initShot(179, 0);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(185, shot.getX());
        assertEquals(-1, shot.getY());
    }
    @org.junit.jupiter.api.Test
    void initShot8(){
        shot.initShot(357, 175);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(363, shot.getX());
        assertEquals(174, shot.getY());
    }
    @org.junit.jupiter.api.Test
    void initShot9(){
        shot.initShot(358, 175);
        assertNotNull(shot);
        assertNotNull(shot.getImage());
        assertEquals(364, shot.getX());
        assertEquals(174, shot.getY());
    }
}

import org.junit.jupiter.api.BeforeEach;
import space_invaders.sprites.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    Player player;

    @BeforeEach
    void setup() {
        this.player = new Player();

    }
    //TEST CONTRUCTOR
    @org.junit.jupiter.api.Test
    void player1(){
        player = new Player();
        assertNotNull(player);
    }

    //TEST INITPLAYER
    @org.junit.jupiter.api.Test
    void initPlayer1(){
        player.initPlayer();
        assertNotNull(player);
        assertNotNull(player.getImage());
        int expectedX = 179;
        int expectedY = 175;
        assertEquals(expectedX, player.getX());
        assertEquals(expectedY, player.getY());
    }

    //TEST ACT
    @org.junit.jupiter.api.Test
    void actPlayer1(){
        player.initPlayer();
        player.setX(1);
        KeyEvent left = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(left);
        player.act();
        assertEquals(0, player.getX());
    }
    @org.junit.jupiter.api.Test
    void actPlayer2(){
        player.initPlayer();
        player.setX(357);
        KeyEvent right = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(right);
        player.act();
        assertEquals(328, player.getX());
    }
    @org.junit.jupiter.api.Test
    void actPlayer3(){
        player.initPlayer();
        player.setX(0);
        KeyEvent left = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(left);
        player.act();
        assertEquals(0, player.getX());
    }
    @org.junit.jupiter.api.Test
    void actPlayer4(){
        player.initPlayer();
        player.setX(358);
        KeyEvent right = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(right);
        player.act();
        assertEquals(328, player.getX());
    }
    @org.junit.jupiter.api.Test
    void actPlayer5(){
        player.initPlayer();
        player.act();
        assertEquals(179, player.getX());
    }

    // keyPressed
    @org.junit.jupiter.api.Test
    void testKeyPressedRight() {

        JPanel panel = new JPanel();
        KeyEvent event = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        player.keyPressed(event);
        assertEquals(2,player.dx);
    }
    @org.junit.jupiter.api.Test
    void testKeyPressedLeft() {
        JPanel panel = new JPanel();
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        player.keyPressed(keyEvent);
        assertEquals(-2,player.dx);
    }
    @org.junit.jupiter.api.Test
    void testKeyPressedRandom() {
        JPanel panel = new JPanel();
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, ' ');
        player.keyPressed(keyEvent);
        assertEquals(0, player.dx);
    }
    //keyReleased
    @org.junit.jupiter.api.Test
    void testKeyReleasedLeft() {
        player.dx=-2;
        JPanel panel = new JPanel();
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        player.keyReleased(keyEvent);
        assertEquals(0,player.dx);
    }
    @org.junit.jupiter.api.Test
    void tesKeyReleasedRight() {
        player.dx=2;
        JPanel panel = new JPanel();
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        player.keyReleased(keyEvent);
        assertEquals(0,player.dx);
    }
    @org.junit.jupiter.api.Test
    void testKeyReleasedRandom(){
        player.dx=0;
        JPanel panel = new JPanel();
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, ' ');
        player.keyReleased(keyEvent);
        assertEquals(0,player.dx);
    }

}

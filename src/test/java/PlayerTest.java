import org.junit.jupiter.api.BeforeEach;
import main.Commons;
import space_invaders.sprites.Player;
import space_invaders.sprites.Sprite;

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
        assertEquals(280, player.getY());
    }
    @org.junit.jupiter.api.Test
    void actPlayer2(){
        player.initPlayer();
        player.setX(357);
        KeyEvent right = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(right);
        player.act();
        assertEquals(358, player.getX());
        assertEquals(280, player.getY());
    }
    @org.junit.jupiter.api.Test
    void actPlayer3(){
        player.initPlayer();
        player.setX(0);
        KeyEvent left = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(left);
        player.act();
        assertEquals(0, player.getX());
        assertEquals(280, player.getY());
    }
    @org.junit.jupiter.api.Test
    void actPlayer4(){
        player.initPlayer();
        player.setX(358);
        KeyEvent right = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(right);
        player.act();
        assertEquals(358, player.getX());
        assertEquals(280, player.getY());
    }
    @org.junit.jupiter.api.Test
    void actPlayer5(){
        player.initPlayer();
        player.act();
        assertEquals(270, player.getX());
        assertEquals(280, player.getY());
    }
}

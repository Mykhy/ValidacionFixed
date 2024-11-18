import org.junit.jupiter.api.BeforeEach;
import space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.*;
public class BombTest {

    Alien.Bomb bomb;

    @BeforeEach
    void setup(){
        Alien alien = new Alien(0,0);
        bomb = alien.getBomb();
    }

    @org.junit.jupiter.api.Test
    void constructor1(){
        bomb = new Alien.Bomb(0,190);
        assertEquals(0,bomb.getX());
        assertEquals(190,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void constructor2(){
        bomb = new Alien.Bomb(1,190);
        assertEquals(1,bomb.getX());
        assertEquals(190,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void constructor3(){
        bomb = new Alien.Bomb(190,190);
        assertEquals(190,bomb.getX());
        assertEquals(190,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void constructor4(){
        bomb = new Alien.Bomb(190,0);
        assertEquals(190,bomb.getX());
        assertEquals(0,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void constructor5(){
        bomb = new Alien.Bomb(190,1);
        assertEquals(190,bomb.getX());
        assertEquals(1,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void constructor6(){
        bomb = new Alien.Bomb(358,190);
        assertEquals(358,bomb.getX());
        assertEquals(190,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void constructor7(){
        bomb = new Alien.Bomb(357,190);
        assertEquals(357,bomb.getX());
        assertEquals(190,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void constructor8(){
        bomb = new Alien.Bomb(190,350);
        assertEquals(190,bomb.getX());
        assertEquals(350,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void constructor9(){
        bomb = new Alien.Bomb(190,349);
        assertEquals(190,bomb.getX());
        assertEquals(349,bomb.getY());
    }

    @org.junit.jupiter.api.Test
    void initbomb1(){
        bomb.initBomb(-1,200);
        assertEquals(0,bomb.getX());
        assertEquals(200,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb2(){
        bomb.initBomb(0,200);
        assertEquals(0,bomb.getX());
        assertEquals(200,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb3(){
        bomb.initBomb(1,200);
        assertEquals(1,bomb.getX());
        assertEquals(200,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb4(){
        bomb.initBomb(200,-1);
        assertEquals(200,bomb.getX());
        assertEquals(0,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb5(){
        bomb.initBomb(200,0);
        assertEquals(200,bomb.getX());
        assertEquals(0,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb6(){
        bomb.initBomb(200,1);
        assertEquals(200,bomb.getX());
        assertEquals(1,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb7(){
        bomb.initBomb(200,200);
        assertEquals(200,bomb.getX());
        assertEquals(200,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb8(){
        bomb.initBomb(358,200);
        assertEquals(358,bomb.getX());
        assertEquals(200,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb9(){
        bomb.initBomb(357,200);
        assertEquals(357,bomb.getX());
        assertEquals(200,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb10(){
        bomb.initBomb(359,200);
        assertEquals(358,bomb.getX());
        assertEquals(200,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb11(){
        bomb.initBomb(200,350);
        assertEquals(200,bomb.getX());
        assertEquals(350,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb12(){
        bomb.initBomb(200,349);
        assertEquals(200,bomb.getX());
        assertEquals(349,bomb.getY());
    }
    @org.junit.jupiter.api.Test
    void initbomb13(){
        bomb.initBomb(200,351);
        assertEquals(200,bomb.getX());
        assertEquals(350,bomb.getY());
    }

}

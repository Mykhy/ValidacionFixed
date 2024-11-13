import space_invaders.sprites.Sprite;
import static org.junit.jupiter.api.Assertions.*;

public class SpriteTest {
    Sprite sprite;
    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        this.sprite = new Sprite();
    }
    //TEST CONSTRUCTOR
    @org.junit.jupiter.api.Test
    void sprite1(){
        sprite = new Sprite();
        assertNotNull(sprite);
    }
    //TEST DIE
    @org.junit.jupiter.api.Test
    void die1(){
        sprite.die();
        assertEquals(false, sprite.isVisible());
    }
    //TEST ISVISIBLE Y SETVISIBLE
    @org.junit.jupiter.api.Test
    void isVisible1(){
        sprite.setVisible(true);
        assertEquals(true, sprite.isVisible());
    }
    @org.junit.jupiter.api.Test
    void isVisible2(){
        sprite.setVisible(false);
        assertEquals(false, sprite.isVisible());
    }

}

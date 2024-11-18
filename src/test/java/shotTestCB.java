import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Shot;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class shotTestCB {

    @Test
    public void testInitShot() {
        // Valores iniciales para las coordenadas
        int x = 100;
        int y = 200;

        // Crear un objeto Shot
        Shot shot = new Shot();

        shot.initShot(x,y);

        assertEquals(x + 6, shot.getX(), "La coordenada x debería ser ajustada con H_SPACE");
        assertEquals(y - 1, shot.getY(), "La coordenada y debería ser ajustada con V_SPACE");

        // Verificar que la imagen se cargó correctamente
        assertNotNull(shot.getImage(), "La imagen del disparo debería haberse cargado");
    }
}

package space_invaders.sprites;

import main.Commons;

import javax.swing.ImageIcon;

public class Alien extends Sprite {

    private Bomb bomb;

    /**
     * Inicializa un nuevo alien
     * @param x coordenada X de la posición del nuevo alien
     * @param y coordenada Y de la posición del nuevo alien
     * */
    public Alien(int x, int y) {

        initAlien(x, y);
    }
    /**
     * Inicializa un nuevo alien y le asigna la imagen correspondiente en la interfaz
     * @param x coordenada X de la posición del nuevo alien
     * @param y coordenada Y de la posición del nuevo alien
     * Si alguna de las coordenadas indicadas supera los márgenes de la pantalla, se ubicará en el máximo permitido.
     * Por ejemplo, si la coordenada X indicada supera el margen de la pantalla, se asignará a X el valor máximo posible, es decir, el ancho de la pantalla.
     * Si se introduce alguna coordenada negativa, se reemplazará por 0.
     * */
    public void initAlien(int x, int y) {

        if (x> Commons.BOARD_WIDTH){    //CAMBIO EN LOS CONDICIONALES ANIDADOS
            this.x = Commons.BOARD_WIDTH;
        }else if (x<0){
            this.x = 0;
        } else {
            this.x = x;
        }
        if (y> Commons.BOARD_HEIGHT){   //CAMBIO EN LOS CONDICIONALES
            this.y = Commons.BOARD_HEIGHT;
        }else if (y<0){
            this.y=0;
        } else {
            this.y = y;
        }

        bomb = new Bomb(x, y);

        var alienImg = "src/main/resources/images/alien.png";
        var ii = new ImageIcon(alienImg);

        setImage(ii.getImage());
    }

    /**
     * Mueve horizontalmente el alien en la dirección indicada.
     * @param direction posición hacia la izquierda o derecha hacia la que se mueve el alien
     * */
    public void act(int direction) {
        if(this.x + direction > Commons.BOARD_WIDTH){   //CAMBIO EN EL CONDICIONAL
            this.x = Commons.BOARD_WIDTH;
        } else if (this.x + direction < 0) {    //CAMBIO EN EL CONDICIONAL
            this.x = 0;
        } else {
            this.x = direction + this.x;
        }
    }

    /**
     * Devuelve el objeto explosión asociado al alien
     * @return bomb
     * */
    public Bomb getBomb() {

        return bomb;
    }

    public static class Bomb extends Sprite {

        private boolean destroyed;

        /**
         * Crea una nueva bomba en la posición indicada
         * @param x coordenada X de la posición de la nueva explosión
         * @param y coordenada Y de la posición de la nueva explosión
         * */

        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        /**
         * Inicializa el nuevo objeto bomba y le asigna las coordenadas indicadas y la imagen correspondiente en la interfaz
         * @param x coordenada X de la posición de la nueva bomba
         * @param y coordenada Y de la posición de la nueva bomba
         * Si el valor X o Y indicados superan el margen de la pantalla, se les asignará el valor máximo permitido.
         * Si se introduce algún valor negativo, será reemplazado por 0.
         * */
        public void initBomb(int x, int y) {

            setDestroyed(true);

            if (x > Commons.BOARD_WIDTH){   //CAMBIO EN LOS CONDICIONALES
                this.x = Commons.BOARD_WIDTH;
            } else if (x < 0) {
                this.x = 0;
            } else {
                this.x = x;
            }
            if (y > Commons.BOARD_HEIGHT) { // CAMBIO EN LOS CONDICIONALES
                this.y = Commons.BOARD_HEIGHT;
            } else if (y < 0){
                this.y = 0;
            }else {
                this.y = y;
            }

            var bombImg = "src/main/resources/images/bomb.png";
            var ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
        }

        /**
         * Cambia el valor de "destruido" al valor asignado
         * @param destroyed
         * */
        public void setDestroyed(boolean destroyed) {

            this.destroyed = destroyed;
        }
        /**
         * Comprueba si la bomba ha sido destruida
         * @return destroyed
         * */
        public boolean isDestroyed() {

            return destroyed;
        }
    }
}

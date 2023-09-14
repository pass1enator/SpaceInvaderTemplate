/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.space;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import java.awt.Toolkit;

/**
 *
 * @author Pedro
 */
public class Ship {

    private Point2D position;
    private TextColor color;
    private TextColor backgroundcolor;
  
    private static int bullets_size = 2;
    private Bullet[] bullets;
    //por la frecuencia
    private static int max_paint_counter = 35;
    private int paint_counter = 0;
    private static char bullet_symbol=com.googlecode.lanterna.Symbols.ARROW_UP;
    private String cartoon[] = {
        "⢀⣀⣾⣿⣷⣀⡀",
        "⣿⣿⣿⣿⣿⣿⣿"
    };
    /**
     * constructor por defecto
     */
    public Ship() {
        this.position = new Point2D();
        this.init();
    }
    /**
     * constructor sobrecargado
     * @param p posición del la nave
     */
    public Ship(Point2D p) {
        this.position = p;
        this.init();
    }
    /**
     * construtor sobrecargado con la posición de nave
     * @param x
     * @param y 
     */
    public Ship(int x, int y) {
        this.position = new Point2D(x, y);
        this.init();
    }

    private void init() {
        this.color = TextColor.ANSI.GREEN;
        this.backgroundcolor = Game.BACKGROUND; //TextColor.ANSI.BLACK;
        this.bullets=(new Bullet[Ship.bullets_size]);
     }
    /**
     * devuelve las balas de la nave
     * @return 
     */
    public Bullet[] getBullets() {
        return bullets;
    }

    
    /**
     * muevel a nave incrementado intx
     * @param intx incremento, positivo o negativo
     * @param min_x menor valor posible de x
     * @param max_x mayor valor posible de x 
     */
    public void moveHorizontal(int intx, int min_x, int max_x) {
        if (this.position.getX() + intx - this.cartoon[0].length() / 2 >= min_x && this.position.getX() + intx + this.cartoon[0].length() / 2 <= max_x) {
            this.position.addX(intx);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    /**
     * Coloca la nave en la posicoin x (centrado)
     * @param x 
     */
    public void setXPosition(int x){
        this.position.setX(x-this.cartoon.length/2);
    }
    public void moveBullets(int min_y, int max_y) {
        this.paint_counter++;
        //para que se pueda ver el disparo
        if (this.paint_counter >= Ship.max_paint_counter) {
            this.paint_counter = 0;

            for (int i = 0; i < this.getBullets().length; i++) {
                if (this.getBullets()[i] != null) {
                    this.getBullets()[i].moveVertical(-1, min_y, max_y);
                    //en caso de llegar a la parte superior se elimina
                    if (this.getBullets()[i].getPosition().getY() <= min_y) {
                        this.getBullets()[i] = null;
                    }
                }
            }
        }
    }
    /**
     * pinta la nave y las balas asociadas
     * @param s 
     */
    public void paint(Screen s) {
        //se pinta la matriz
        for (int i = 0; i < this.cartoon.length; i++) {
            for (int j = -this.cartoon[i].length() / 2; j < this.cartoon[i].length() - this.cartoon[i].length() / 2; j++) {
                s.setCharacter(this.position.getX() + j,
                        this.position.getY() + i,
                        new TextCharacter(this.cartoon[i].charAt(j + this.cartoon[i].length() / 2),
                                color, this.backgroundcolor));
            }
        }
        //se pintan las balas
        for (int i = 0; i < this.getBullets().length; i++) {
            if (this.getBullets()[i] != null) {
                this.getBullets()[i].paint(s);
            }
        }
    }
    /**
     * dispara una bala si tiene disponible
     */
    public void shoot() {
        Bullet tempo;
        boolean shooted = false;
        //solo  dispara si tiene un disparo libre
        for (int i = 0; i < this.getBullets().length && !shooted; i++) {
            if (this.getBullets()[i] == null) {
                tempo = new Bullet(this.position.getX(), this.position.getY());
                tempo.setCharacter(Ship.bullet_symbol);
                this.getBullets()[i] = tempo;
                shooted = true;
            }
        }
    }
    /**
     * detecta colisiones con una bala,
     * @param b bala a evaluar
     * @return cierto si colisiona, falso sino.
     */
     public boolean colision(Bullet b) {
        int x, y;
        if (b != null) {
            //se encuentra en el eje x
            if (this.position.getX() - this.cartoon[0].length() / 2 <= b.getPosition().getX()
                    && 
                    this.position.getX() + this.cartoon[0].length() / 2 >= b.getPosition().getX()) {
                //se encuentra en el eje y
                if (this.position.getY() - this.cartoon[0].length() / 2 < b.getPosition().getY() && 
                        this.position.getY() + this.cartoon[0].length() / 2 > b.getPosition().getY()) {
                    x = b.getPosition().getX() - (this.position.getX() - this.cartoon[0].length() / 2);
                    y = this.position.getY() - b.getPosition().getY();
                    return true;

                }
            }
        }
        return false;
    }
    
    
}

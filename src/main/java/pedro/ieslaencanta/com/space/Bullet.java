/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.space;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import java.awt.Toolkit;

/**
 *
 * @author Pedro
 */
public class Bullet {

    private Point2D position;

    private TextColor color;
    private TextColor backgroundcolor;
    private int width = 1;
    private int height = 1;
   
    private TextCharacter bulletsymbol;
    /**
     * constructor por defecto
     */
    public Bullet() {
        this.position = new Point2D();
        this.init();
    }
    /**
     * constructor sobrecargado
     * @param p 
     * @see Point2D
     */
    public Bullet(Point2D p) {
        this.position = p;
        this.init();
    }
    /**
     * constructor sobrecargado
     * @param x
     * @param y 
     */
    public Bullet(int x, int y) {
        this.position = new Point2D(x, y);
        this.init();
    }
    /**
     * Obtener la posicion de la bala
     * @return 
     */
    public Point2D getPosition() {
        return position;
    }
    /**
     * coloca la bala en una posición
     * @param position 
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    private void init() {
        this.color = TextColor.ANSI.GREEN;
        this.backgroundcolor = Game.BACKGROUND; //TextColor.ANSI.GREEN;
         }
    /**
     * se asigna el caracter con el que se representa la bala
     * @param c 
     */
    public void setCharacter(char c) {
        this.bulletsymbol = TextCharacter.fromCharacter(c
        )[0].withForegroundColor(this.color).withBackgroundColor(Game.BACKGROUND);

    }
    /**
     * Mueve la bala hacia arriba o hacia abajo en función de incy
     * @param incy
     * @param min_y valor mínimo de y
     * @param max_y valor mínimo de y
     */
    public void moveVertical(int incy, int min_y, int max_y) {
        if (this.getPosition().getY() + incy >= min_y && this.getPosition().getY() + incy <= max_y) {
            this.getPosition().addY(incy);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    /**
     * pinta la bala
     * @param s 
     */
    public void paint(Screen s) {

        s.setCharacter(this.getPosition().getX(), this.getPosition().getY(), this.bulletsymbol);

        //s.setCharacter(this.getPosition().getX(), this.getPosition().getY(),this.bulletsymobol);
    }
}

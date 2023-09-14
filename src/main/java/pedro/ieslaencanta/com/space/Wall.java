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
 * @author Administrador
 */
public class Wall {

    private Point2D position;
    private TextColor color;
    private TextColor backgroundcolor;
    private int hardness;

    //por la frecuencia
    private static int max_paint_counter = 35;
    private int paint_counter = 0;
    private StringBuilder cartoon[];
    /**
     * constructor
     */
    public Wall() {
        this.position = new Point2D();
        this.init();
    }
    /**
     * construtor sobrecargado con la coordenada
     * @param p 
     */
    public Wall(Point2D p) {
        this.position = p;
        this.init();
    }
    /**
     * constructor sobrecargado
     * @param x
     * @param y 
     */
    public Wall(int x, int y) {
        this.position = new Point2D(x, y);
        this.init();
    }

    private void init() {
        this.color = TextColor.ANSI.MAGENTA;
        this.backgroundcolor = Game.BACKGROUND; //TextColor.ANSI.BLACK;
        this.cartoon = new StringBuilder[2];
        this.cartoon[0] = new StringBuilder("⣿⣿⣿⣿⣿⣿⣿");
        this.cartoon[1] = new StringBuilder("⣿⣿⣿⣿⣿⣿⣿");
        this.hardness = this.cartoon.length * 2;

    }
    /**
     * detecta colision con las balas, en caso de detectarse, se destruye 
     * el lugar en el que colisiona
     * @param b bala a evaluar
     * @return 
     */
    public boolean colision(Bullet b) {
        int x, y;
        if (b != null) {
            //se encuentra en el eje x
            if (this.position.getX() - this.cartoon[0].length() / 2 <= b.getPosition().getX()
                    && this.position.getX() + this.cartoon[0].length() / 2 >= b.getPosition().getX()) {
                //se encuentra en el eje y
                if (this.position.getY() <= b.getPosition().getY() && this.position.getY() + this.cartoon.length > b.getPosition().getY()) {
                    //ahora se tiene que obtener la posicion
                    x = b.getPosition().getX() - (this.position.getX() - this.cartoon[0].length() / 2);
                    y = this.position.getY() - b.getPosition().getY();
                    if (y == 0 && this.cartoon[0].charAt(x) != ' ') {
                        this.cartoon[0].setCharAt(x, ' ');
                        return true;
                    } else if (this.cartoon[1].charAt(x) != ' ') {
                        this.cartoon[1].setCharAt(x, ' ');
                        return true;
                    }

                }
            }
        }
        return false;
    }
    /**
     * pinta el muro
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
            //   this.backgroundcolor=TextColor.ANSI.BLUE;
        }

    }

}

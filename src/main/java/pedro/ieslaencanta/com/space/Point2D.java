/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.space;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

/**
 *
 * @author Pedro
 */
public class Point2D {
    private int x;
    private int y;
    /**
     * constructor
     */
    public Point2D(){
        this.x=-1;
        this.y=-1;
    }
    /**
     * constructor sobrecargado
     * @param x
     * @param y 
     */
    public Point2D(int x,int y){
        this.x=x;
        this.y=y;
    }
    /**
     * obtener posicion x
     * @return 
     */
    public int getX() {
        return x;
    }
    /**
     * asignar posicion x.
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * devolver posicion y
     * @return 
     */
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    /**
     * incrementa o decrementa la posicion x
     * @param incx 
     */
    public void addX(int incx){
        this.x+=incx;
    }
    /**
     * incrementa o decrementa la posicion y
     * @param incy 
     */
      public void addY(int incy){
        this.y+=incy;
    }
          public void paint(Screen s) {
        //se pinta la posicion
           s.setCharacter(this.x, this.y, new TextCharacter('⣿'));

    }
}

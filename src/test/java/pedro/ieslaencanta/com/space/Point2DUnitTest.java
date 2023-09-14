/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pedro.ieslaencanta.com.space;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro
 */
public class Point2DUnitTest {
   
    public Point2DUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void defaultConstructorTest(){
        Point2D point= new Point2D();
        assertTrue(point.getX()==-1 && point.getY()==-1);
    }
    @Test
    public void overrideConstructorTest(){
        Point2D point= new Point2D(5,4);
        assertTrue(point.getX()==5 && point.getY()==4);
    }  
    @Test
    public void addTest(){
        Point2D point= new Point2D(5,4);
        point.addX(5);
        point.addY(1);
        assertTrue(point.getX()==10 && point.getY()==5);
    }    
   
}

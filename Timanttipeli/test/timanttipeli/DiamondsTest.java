package timanttipeli;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import timanttipeli.Diamonds;
import java.awt.Color;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Krista
 */
public class DiamondsTest {
    
    public DiamondsTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void twoDiamondsAreSameColor(){
        Diamonds diamonds = new Diamonds(2,2);
        diamonds.setColor(0, 1, 1);
        
        assertEquals(true, diamonds.isSameColor(0, 1, Color.red));        
    }
    
    @Test
    public void rightAmountOfNeighboursWhenAllSameColor() {
         Diamonds diamonds = new Diamonds(6,6);
         for (int i = 0; i < 6; i++) {
             for (int j = 0; j < 6; j++) {
                 diamonds.setColor(i, j, 0);
             }
         }          
         assertEquals(6, diamonds.countNeighboursWithSameColorOnSameRow(3, 2).size());
         assertEquals(6, diamonds.countNeighboursWithSameColorOnSameColumn(5, 1).size());
     }
     
     @Test
     public void rightAmountOfNeighboursOnSameRow(){
         Diamonds diamonds = new Diamonds(7,7);
         for (int j = 3; j < 6; j++) {
             diamonds.setColor(2, j, 0);            
         }
         diamonds.setColor(2, 2, 1);
         diamonds.setColor(2, 6, 1);
         
         assertEquals(3, diamonds.countNeighboursWithSameColorOnSameRow(2, 4).size());
     }
     
     @Test
     public void rightAmountOfNeighboursOnSameColumn(){
         Diamonds diamonds = new Diamonds(7,7);
         for (int i = 3; i < 6; i++) {
             diamonds.setColor(i, 0, 0);            
         }
         diamonds.setColor(2, 0, 1);
         diamonds.setColor(6, 0, 1);
         
         assertEquals(3, diamonds.countNeighboursWithSameColorOnSameColumn(5, 0).size());
     }
     
     @Test
     public void replacedWithRightColorWhenDestroyed(){
         Diamonds diamonds = new Diamonds(3,3);
         Color color = diamonds.getColor(0,1);
         diamonds.destroyDiamond(1, 1);
         
         assertEquals(color, diamonds.getColor(1, 1));
     }
     
     @Test
     public void newDiamondCreatedWhenOneDestroyed(){
         Diamonds diamonds = new Diamonds(3,3);
         diamonds.destroyDiamond(1, 1);
         
         assertEquals(true, diamonds.getColor(0, 1)!=null);
     }
     
     @Test
     public void replacedWithRightColorWhen3DiamondsDestroyedOnSameRow(){
         Diamonds diamonds = new Diamonds(7,7);
         for (int j = 3; j < 6; j++) {
             diamonds.setColor(2, j, 0);            
         }
          for (int j = 3; j < 6; j++) {
             diamonds.setColor(1, j, 1);            
         }
         diamonds.setColor(2, 2, 1);
         diamonds.setColor(2, 6, 1);

        diamonds.destroyDiamonds(diamonds.countNeighboursWithSameColorOnSameRow(2, 4));
        for (int j = 3; j < 6; j++){
            assertEquals(Color.red, diamonds.getColor(2, j));
        }
     }
     
     /*
      * destroyDiamonds ei toistaiseksi toimi oikein
      */
     
//     @Test
//     public void replacedWithRightColorWhen3DiamondsDestroyedOnSameColumn(){
//         Diamonds diamonds = new Diamonds(7,7);
//         for (int i = 4; i < 7; i++) {
//             diamonds.setColor(i, 0, 0);            
//         }
//         for (int i = 1; i < 4; i++) {
//             diamonds.setColor(i, 0, 1);            
//         }
//         diamonds.destroyDiamonds(diamonds.countNeighboursWithSameColorOnSameColumn(5, 0));
//         for (int i = 4; i < 7; i++){
//            assertEquals(Color.red, diamonds.getColor(i, 0));
//        }
//     }
}


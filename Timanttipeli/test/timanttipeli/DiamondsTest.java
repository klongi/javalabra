package timanttipeli;

import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Krista
 */
public class DiamondsTest {
    
    Diamonds diamonds;
    
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
        diamonds = new Diamonds(7,7);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void twoDiamondsAreSameColor(){
        diamonds.setColor(0, 1, 1);
        assertEquals(true, diamonds.isSameColor(0, 1, Color.red));        
    }
    
    @Test
    public void switchesCorrectly() {
        Color color = diamonds.getColor(1, 1);
        diamonds.switchPlaces(new Coordinate(1,1), new Coordinate(1,2));
        assertEquals(color, diamonds.getColor(1, 2));
    }
    
    @Test
    public void doesntSwitchWhenNotNextToEachOther() {
        Color color = diamonds.getColor(0, 0);
        diamonds.switchPlaces(new Coordinate(0,0), new Coordinate(2,2));
        assertEquals(color, diamonds.getColor(0, 0));
    }
    
    @Test
    public void rightAmountOfNeighboursWhenAllSameColor() {
         diamonds = new Diamonds(6,6);
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
         for (int j = 3; j < 6; j++) {
             diamonds.setColor(2, j, 0);            
         }
         diamonds.setColor(2, 2, 1);
         diamonds.setColor(2, 6, 1);
         
         assertEquals(3, diamonds.countNeighboursWithSameColorOnSameRow(2, 4).size());
     }
     
     @Test
     public void rightAmountOfNeighboursOnSameColumn(){
         for (int i = 3; i < 6; i++) {
             diamonds.setColor(i, 0, 0);            
         }
         diamonds.setColor(2, 0, 1);
         diamonds.setColor(6, 0, 1);
         
         assertEquals(3, diamonds.countNeighboursWithSameColorOnSameColumn(5, 0).size());
     }
     
     @Test
     public void replacedWithRightColorWhenDestroyed(){
         Color color = diamonds.getColor(0,1);
         diamonds.destroyDiamond(1, 1);
         
         assertEquals(color, diamonds.getColor(1, 1));
     }
     
     @Test
     public void newDiamondCreatedWhenOneDestroyed(){
         diamonds.destroyDiamond(1, 1);
         
         assertEquals(true, diamonds.getColor(0, 1)!=null);
     }
     
     @Test
     public void replacedWithRightColorWhen3DiamondsDestroyedOnSameRow(){
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
                                                                                           

     @Test
     public void replacedWithRightColorWhen3DiamondsDestroyedOnSameColumn(){
         for (int i = 4; i < 7; i++) {
             diamonds.setColor(i, 0, 0);            
         }
         for (int i = 1; i < 4; i++) {
             diamonds.setColor(i, 0, 1);            
         }
         diamonds.destroyDiamonds(diamonds.countNeighboursWithSameColorOnSameColumn(4, 0));
         for (int i = 4; i < 7; i++){
            assertEquals(Color.red, diamonds.getColor(i, 0));
        }
     }
}


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
    public void noSetsWhenInitialised() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Coordinate c = new Coordinate(i,j);
                assertTrue(diamonds.countNeighboursWithSameColorOnSameColumn(c).size() < 3);
                assertTrue(diamonds.countNeighboursWithSameColorOnSameRow(c).size() < 3);
            }        
        }
    }
    @Test
    public void doesntSwitchWhenNotNextToEachOther() {
        Color color = diamonds.getColor(0, 0);
        diamonds.switchPlaces(new Coordinate(0,0), new Coordinate(2,2));
        assertEquals(color, diamonds.getColor(0, 0));
    }
    @Test
    public void twoDiamondsAreNextToEachOther(){
        assertEquals(true, diamonds.areNextToEachOther(new Coordinate(1,1), new Coordinate(0,1)));
        assertEquals(true, diamonds.areNextToEachOther(new Coordinate(1,1), new Coordinate(1,0)));
        assertEquals(true, diamonds.areNextToEachOther(new Coordinate(1,1), new Coordinate(2,1)));
        assertEquals(true, diamonds.areNextToEachOther(new Coordinate(1,1), new Coordinate(1,2)));
    }
    @Test
    public void switchesCorrectly(){
        Coordinate c1 = new Coordinate(1,1);
        Coordinate c2 = new Coordinate(1,2);
        Color color1 = diamonds.getColor(c1);
        Color color2 = diamonds.getColor(c2);
        diamonds.doSwitch(c1, c2);
        assertEquals(color1, diamonds.getColor(c2));
        assertEquals(color2, diamonds.getColor(c1));
    }
    
    @Test
    public void switchValidWhenAllSameColor(){
        for (int i = 0; i < 7; i++) {
             for (int j = 0; j < 7; j++) {
                 diamonds.setColor(i, j, 0);
             }
         } 
        assertTrue(diamonds.switchValid(new Coordinate(1,1), new Coordinate(1,2)));
    }
    @Test
    public void twoDiamondsAreSameColor(){
        diamonds.setColor(0, 1, 1);
        assertTrue(diamonds.isSameColor(0, 1, Color.red));        
    }
    
    @Test
    public void switchNotValidWhenNotEnoughNeighbours(){
        diamonds = new Diamonds(2,2);
        assertEquals(false, diamonds.switchValid(new Coordinate(0,0), new Coordinate(0,1)));
        
    }
    
    @Test
    public void twoDiamondsNotNextToEachOther(){
        assertEquals(false, diamonds.areNextToEachOther(new Coordinate(1,1), new Coordinate(2,2)));
    }
    
    @Test
    public void rightAmountOfNeighboursWhenAllSameColor() {
         for (int i = 0; i < 7; i++) {
             for (int j = 0; j < 7; j++) {
                 diamonds.setColor(i, j, 0);
             }
         }          
         assertEquals(7, diamonds.countNeighboursWithSameColorOnSameRow(new Coordinate(2,3)).size());
         assertEquals(7, diamonds.countNeighboursWithSameColorOnSameColumn(new Coordinate(5,1)).size());
     }
     
     @Test
     public void rightAmountOfNeighboursOnSameRow(){
         for (int j = 3; j < 6; j++) {
             diamonds.setColor(2, j, 0);            
         }
         diamonds.setColor(2, 2, 1);
         diamonds.setColor(2, 6, 1);
         
         assertEquals(3, diamonds.countNeighboursWithSameColorOnSameRow(new Coordinate(2,4)).size());
     }
     
     @Test
     public void rightAmountOfNeighboursOnSameColumn(){
         for (int i = 3; i < 6; i++) {
             diamonds.setColor(i, 0, 0);            
         }
         diamonds.setColor(2, 0, 1);
         diamonds.setColor(6, 0, 1);
         
         assertEquals(3, diamonds.countNeighboursWithSameColorOnSameColumn(new Coordinate(5,0)).size());
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
    public void replacedWithRightColorWhen3DiamondsDestroyedOnSameRow() {
        for (int j = 3; j < 6; j++) {
            diamonds.setColor(2, j, 0);
        }
        for (int j = 3; j < 6; j++) {
            diamonds.setColor(1, j, 1);
        }
        diamonds.setColor(2, 2, 1);
        diamonds.setColor(2, 6, 1);

        diamonds.destroyDiamonds(diamonds.countNeighboursWithSameColorOnSameRow(new Coordinate(2, 3)));
        for (int j = 3; j < 6; j++) {
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
         diamonds.destroyDiamonds(diamonds.countNeighboursWithSameColorOnSameColumn(new Coordinate (4,0)));
         for (int i = 4; i < 7; i++){
            assertEquals(Color.red, diamonds.getColor(i, 0));
        }
     }
     
     @Test
     public void noSetsLeftWhenSomethingDestroyed() {
          for (int j = 3; j < 6; j++) {
             diamonds.setColor(2, j, 0);            
         }
          diamonds.startDeleting(new Coordinate(2,3));
          
          for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Coordinate c = new Coordinate(i,j);
                assertTrue(diamonds.countNeighboursWithSameColorOnSameColumn(c).size() < 3);
                assertTrue(diamonds.countNeighboursWithSameColorOnSameRow(c).size() < 3);
            }        
        }
     }
}
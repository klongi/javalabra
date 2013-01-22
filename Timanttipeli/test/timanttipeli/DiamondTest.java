package timanttipeli;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import timanttipeli.Diamond;
import java.awt.Color;
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
public class DiamondTest {
    
    public DiamondTest() {
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
//     TODO add test methods here.
//     The methods must be annotated with annotation @Test. For example:
    
     @Test
     public void diamondInitialisedWithRightColor() {
         Diamond diamond = new Diamond(0);
         assertEquals(Color.blue, diamond.getColor());         
     }
     
     @Test
     public void diamondChangesColorCorrectly(){
         Diamond diamond = new Diamond(0);
         diamond.setColor(1);
         assertEquals(Color.red, diamond.getColor());
     }
     
     @Test
     public void diamondDoesNotChangeColor(){
         Diamond diamond = new Diamond(0);
         diamond.setColor(-8);
         assertEquals(Color.blue, diamond.getColor());
     }
     
     
}

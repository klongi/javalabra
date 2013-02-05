/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli;

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
public class PlayerTest {
    
    Player uusi;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        uusi = new Player("Pekka Pelaaja");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createPlayer() {
        assertEquals(0, uusi.getPoints());
        assertEquals("Pekka Pelaaja, 0 points", uusi.toString());
    }
    
    @Test
    public void addPoints() {
        uusi.addPoints(100);
        assertEquals(100, uusi.getPoints());
    }
}

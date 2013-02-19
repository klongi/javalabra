/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli;

import java.util.ArrayList;
import java.util.Collections;
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
public class CoordinateTest {
    
    public CoordinateTest() {
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
    public void coordinateListSortedCorrectly() {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(4, 2));
        coordinates.add(new Coordinate(1, 4));
        coordinates.add(new Coordinate(3, 5));
        coordinates.add(new Coordinate(4, 3));

        Collections.sort(coordinates);

        assertEquals(1, coordinates.get(0).getRowNumber());
        assertEquals(2, coordinates.get(2).getColumnNumber());
    }
    
    @Test
    public void coordinatesAreTheSame() {
        Coordinate c = new Coordinate(2,2);
        assertTrue(c.equals(new Coordinate(2,2)));
    }
    @Test
    public void coordinatesAreNotSame() {
        Coordinate c = new Coordinate(2,2);
        assertTrue(!c.equals(new Coordinate(2,3)));
    }
}

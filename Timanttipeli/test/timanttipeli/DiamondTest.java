package timanttipeli;

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

    Diamond diamond;

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
        diamond = new Diamond(0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void diamondInitialisedWithRightColor() {
        assertEquals(Color.blue, diamond.getColor());
    }

    @Test
    public void diamondChangesColorCorrectly() {
        diamond.setColor(1);
        assertEquals(Color.red, diamond.getColor());
    }

    @Test
    public void diamondDoesNotChangeColor() {
        diamond.setColor(-8);
        assertEquals(Color.blue, diamond.getColor());
    }

    @Test
    public void diamondChangesColorWhenGivenColor() {
        diamond.setColor(Color.red);
        assertEquals(Color.red, diamond.getColor());
    }
}    

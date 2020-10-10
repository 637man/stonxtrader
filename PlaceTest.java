package stonxtrader;


import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Tests only getAdjacentPlace method. Other than that, Place is effectively
 * a bean class, and there's no point in testing plain getters and setters.
 *
 * @author    637man
 */
public class PlaceTest {
    
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Provided as a sample test.
     */
    @Test
    public void testCanPass() {
        Place place1 = new Place("hala", "vstupní hala budovy VŠE na Jižním městě");
        Place place2 = new Place("bufet", "bufet, kam si můžete zajít na svačinku");
        place1.addExit(place2);
        place2.addExit(place1);
        assertEquals(place2, place1.getAdjacentPlace("bufet"));
        assertEquals(null, place2.getAdjacentPlace("pokoj"));
    }
    
    /**
     * Tests correct handling of aliases.
     */
    @Test
    public void testAliases() {
        Place place1 = new Place("Aprimaryname", Arrays.asList("Aalias1", "Aalias2", "Aalias3"), "A's Long Fancy Descriptive Name");
        Place place2 = new Place("Bprimaryname", Arrays.asList("Balias1", "Balias2", "Balias3"), "B's Long Fancy Descriptive Name");
        place1.addExit(place2);
        place2.addExit(place1);
        assertEquals(place2, place1.getAdjacentPlace("Bprimaryname"));
        assertEquals(place2, place1.getAdjacentPlace("Balias1"));
        assertEquals(place2, place1.getAdjacentPlace("Balias2"));
        assertEquals(place2, place1.getAdjacentPlace("Balias3"));
        assertEquals(place1, place2.getAdjacentPlace("Aprimaryname"));
        assertEquals(place1, place2.getAdjacentPlace("Aalias1"));
        assertEquals(place1, place2.getAdjacentPlace("Aalias2"));
        assertEquals(place1, place2.getAdjacentPlace("Aalias3"));
    }

}








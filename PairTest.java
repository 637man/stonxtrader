/*
 * Copyleft (ɔ) Jan Getmancuk 2020. All wrongs reserved.
 */

package stonxtrader;

import static org.junit.Assert.*;
import stonxtrader.Pair;;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Brief test of a LISP-inspired Pair class.
 * @author 637man
 */


public class PairTest {
    Pair<Integer,Integer> a;
    Pair<Integer,Integer> b;
    
    public PairTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        a = new Pair<Integer,Integer>(7,23);
        b = a.cons(42, 69); // can't be static, so it's like this
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCar method, of class Pair.
     */
    @Test
    public void testGetCar() {
        System.out.println("getCar");
        assertEquals((Integer) 7, a.getCar()); 
        assertEquals((Integer) 42, b.getCar());
        // Either NetBeans or Java is legit confused, unsure whether
        // to convert int,Integer -> long, or int -> Integer
    }

    /**
     * Test of setCar method, of class Pair.
     */
    @Test
    public void testSetCar() {
        System.out.println("setCar");
        a.setCar(1);
        assertEquals((Integer) 1, a.getCar());
        b.setCar(2);
        assertEquals((Integer) 2, b.getCar());
    }

    /**
     * Test of getCdr method, of class Pair.
     */
    @Test
    public void testGetCdr() {
        System.out.println("getCdr");
        assertEquals((Integer) 23, a.getCdr());
        assertEquals((Integer) 69, b.getCdr());
    }

    /**
     * Test of setCdr method, of class Pair.
     */
    @Test
    public void testSetCdr() {
        System.out.println("setCdr");
        a.setCdr(3);
        assertEquals((Integer) 3, a.getCdr());
        b.setCdr(4);
        assertEquals((Integer) 4, b.getCdr());
    }

    /**
     * Test of cons method, of class Pair, also getCddr and getCdar.
     */
    @Test
    public void testCons() {
        System.out.println("cons");
        Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> res = new Pair<>(null, null);
        res = res.cons(a, b);
        //assertEquals((Integer) 7, res.getCaar()); // not implemented
        //assertEquals((Integer) 23, res.getCadr()); // not implemented
        assertEquals((Integer) 42, res.getCdar());
        assertEquals((Integer) 69, res.getCddr());
    }
    
}









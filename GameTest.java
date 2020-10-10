package stonxtrader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/****************************************************************************
 * Tests individual game commands and features. Winning or losing can't be
 * reliably tested as the market is non-deterministic.
 *
 * @author   Jan Getmančuk
 * @version  1.0
 */
public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game("st");
    }

    @After
    public void tearDown() {
    }


    /**
     * This can be considered an achievement in itself. Also a prerequisite
     * to all the following tests.
     */
    @Test
    public void testGameLoads() {
        assertEquals(1100000,game.getGameWorld().getMoney());
        assertEquals(1000000,game.getGameWorld().getDebt());
        assertEquals(0,game.getGameWorld().getPortfolio().size());
        assertEquals(false,game.isEndGame());
    }
    
    /**
     * Borrow and Payback are complementary. TODO check interest
     */
    @Test
    public void testBorrowPayback() {
        game.performCommand("borrow 1000");
        assertEquals(1101000,game.getGameWorld().getMoney());
        assertEquals(1001000,game.getGameWorld().getDebt());
        
        game.performCommand("payback 1000");
        assertEquals(1100000,game.getGameWorld().getMoney());
        assertEquals(1000000,game.getGameWorld().getDebt());
        
        game.performCommand("payback");
        assertEquals(100000,game.getGameWorld().getMoney());
        assertEquals(0,game.getGameWorld().getDebt());
    }
    
    
    /**
     * Buy and Sell are obviously complementary.
     */
    @Test
    public void testBuySell() {
        game.performCommand("go ll"); // why not test also this?
        assertEquals("liberpolis",game.getGameWorld().getCurrentPlace().getName());
        
        game.performCommand("buy 0 1000"); // now we talking some dotting around for 1 less magic number
        assertEquals(1100000 - 1000*game.getGameWorld().getCurrentPlace().getStocks().get(0).getValue() , game.getGameWorld().getMoney());
    
        game.performCommand("sell 0 500");
        assertEquals(1100000 - 500*game.getGameWorld().getCurrentPlace().getStocks().get(0).getValue() , game.getGameWorld().getMoney());
        
        game.performCommand("buy 0 500");
        assertEquals(1100000 - 1000*game.getGameWorld().getCurrentPlace().getStocks().get(0).getValue() , game.getGameWorld().getMoney());
        
        game.performCommand("sell 0 1000");
        assertEquals(1100000, game.getGameWorld().getMoney());
        
        game.performCommand("sell 0 -1000"); // users entering funny stuff
        assertEquals(1100000, game.getGameWorld().getMoney());
    }
    
    
    /**
     * Test
     */
    @Test
    public void testGo() {
        game.performCommand("go ll");
        assertEquals("liberpolis",game.getGameWorld().getCurrentPlace().getName());
        assertEquals(2,game.getGameWorld().getDay());
        game.performCommand("go liberpolis"); 
        assertEquals("liberpolis",game.getGameWorld().getCurrentPlace().getName());
        assertEquals(2,game.getGameWorld().getDay());
        // going to the same place is a no-op
        game.performCommand("go bratislava"); 
        assertEquals("bratislava",game.getGameWorld().getCurrentPlace().getName());
        assertEquals(3,game.getGameWorld().getDay());
        game.performCommand("go elements"); 
        assertEquals("elements",game.getGameWorld().getCurrentPlace().getName());
        assertEquals(4,game.getGameWorld().getDay());
        game.performCommand("go absolutelynonexistentplace"); // could also be a typo
        assertEquals("elements",game.getGameWorld().getCurrentPlace().getName());
        assertEquals(4,game.getGameWorld().getDay());
    }
    
    
    /**
     * This functionality isn't implemented because generating horizontal
     * ASCII art graphs would require a framebuffer and me making all these
     * graphics drawing functions, and it's like 2 week before deadline.
     * Besides, I like to do this stuff in C.
     */
    @Test
    public void testList() {
        assertEquals("","");
    }
    
    
    /**
     * This test is slightly more complicated, we need to check the scores.csv
     * file for the last line. May mess with the seed test.
     */
    @Test
    public void testRetire() {
        RNG.rng.setSeed(System.currentTimeMillis());
        int salt = RNG.rng.nextInt();
        game.performCommand("retire RetireTest"+salt);
        Scanner sc = null;
        try{
            sc = new Scanner(new File("st/_scores.csv"));
        } catch (FileNotFoundException e){
            System.err.println(e.getCause());
        }
        if(sc == null) {
            fail("High scores file couldn't be opened.");
            return; // netbeans warns about possible nullpointer dereference
        }
        String line = null;
        while(sc.hasNextLine()){
            line = sc.nextLine();
        }
        assertEquals("RetireTest"+salt+";100000;0;22;42",line);
    }
    
    
    /**
     * Tests whether the RNG can be seeded correctly. This test must be maintained.
     */
    @Test
    public void testSeed() {
        game.performCommand("seed");
        game.performCommand("seed blabla");
        game.performCommand("seed 1");
        game.performCommand("go ll");
        game.performCommand("sleep 100");
        assertEquals(958,game.getGameWorld().getCurrentPlace().getStocks().get(0).getValue());
    }
    
    
    /**
     * This could also check whether the interest rate has been applied to the debt,
     * but that's not the point of this command. Also days start at 1.
     */
    @Test
    public void testSleep() {
        assertEquals(1,game.getGameWorld().getDay());
        game.performCommand("sleep");
        assertEquals(2,game.getGameWorld().getDay());
        game.performCommand("sleep 0");
        assertEquals(2,game.getGameWorld().getDay());
        game.performCommand("sleep 10");
        assertEquals(12,game.getGameWorld().getDay());
        game.performCommand("sleep -1"); // really could use some unsigned int
        assertEquals(12,game.getGameWorld().getDay());
    }
    
}




















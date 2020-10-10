package stonxtrader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2016/2017
 */
public class CommandsTest
{
    private Game game;
    private CommandQuit cmdQuit;
    private CommandGo cmdGo;
    
    @Before
    public void setUp() {
        game = new Game("st");
        cmdQuit = new CommandQuit(game);
        cmdGo = new CommandGo(game.getGameWorld());
    }

    @Test
    public void testInsertSelect() {
        Commands cmds = new Commands();
        cmds.insertCommand(cmdQuit);
        cmds.insertCommand(cmdGo);
        assertEquals(cmdQuit, cmds.getCommand("quit"));
        assertEquals(cmdGo, cmds.getCommand("go"));
        assertEquals(null, cmds.getCommand("help"));
    }
    @Test
    public void testIsValidCommand() {
        Commands cmds = new Commands();
        cmds.insertCommand(cmdQuit);
        cmds.insertCommand(cmdGo);
        assertEquals(true, cmds.isValidCommand("quit"));
        assertEquals(true, cmds.isValidCommand("go"));
        assertEquals(false, cmds.isValidCommand("help"));
        assertEquals(false, cmds.isValidCommand("Quit"));
    }
    
    @Test
    public void testCommandNames() {
        Commands cmds = new Commands();
        cmds.insertCommand(cmdQuit);
        cmds.insertCommand(cmdGo);
        String names = cmds.getCommandNames();
        assertEquals(true, names.contains("quit"));
        assertEquals(true, names.contains("go"));
        assertEquals(false, names.contains("help"));
        assertEquals(false, names.contains("Quit"));
    }
    
}


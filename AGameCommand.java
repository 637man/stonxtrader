/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;

/**
 * Except Help, Quit, and Seed, all the commands share these properties.
 * Most importantly, they all operate on the game world itself.
 * 
 * @author 637man
 */
public abstract class AGameCommand implements ICommand {
    private final String NAME; // can't be static
    protected GameWorld world; // needed for access from subclasses
    
    /**
     * This is intended to work the following way:
     * A concrete subclass has a constructor that calls
     * <code> super("cmd_name", world); </code>, which is processed here.
     * In that call's arguments, there may be an i18n table lookup yielding
     * an i14d String instead of the literal. This makes getName() useless.
     * 
     * @param name the command name
     * @param world the world to be operated on
     */
    public AGameCommand(String name, GameWorld world) {
        this.NAME = name;
        this.world = world;
    }
    
    /**
     * A common thing to do with many of these commands is to advance the game
     * time, updating all the prices. It needs to be reflected immediately in
     * the stock table, which also contains information about how many are
     * available to buy or how many the player has, therefore an access to the
     * world, where the portfolio is stored, is needed.
     * 
     * @return a formatted stock table
     */
    public String generateTable(){
        return "Fancier stock table NYI.\n";
    }
    
    @Override
    public abstract String processCommand(String... parameters);

    @Override
    public String getName() {
        return NAME;
    }
    
}

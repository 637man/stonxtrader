package stonxtrader;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class Game implements IGame {
    private Commands validCommands;    // obsahuje seznam přípustných příkazů
    private GameWorld gameWorld;
    private boolean endGame = false;
    
    /**
     * Loads a game, be it data-driven or internal, and initializes commands.
     * Not the best way of creating commands, too much copy and paste.
     * Maybe create the commands collection somewhere else in advance?
     * @param args Command line arguments from Main.main()
     */
    public Game(String gamename){
        gameWorld = new GameWorld(gamename);
        
        validCommands = new Commands();
        validCommands.insertCommand(new CommandHelp(validCommands));
        validCommands.insertCommand(new CommandQuit(this));
        validCommands.insertCommand(new CommandGo(gameWorld));
        validCommands.insertCommand(new CommandBuy(gameWorld));
        validCommands.insertCommand(new CommandSell(gameWorld));
        validCommands.insertCommand(new CommandList(gameWorld));
        validCommands.insertCommand(new CommandSleep(gameWorld));
        validCommands.insertCommand(new CommandSeed(gameWorld));
        validCommands.insertCommand(new CommandBorrow(gameWorld));
        validCommands.insertCommand(new CommandPayback(gameWorld));
        validCommands.insertCommand(new CommandRetire(gameWorld, this));
        validCommands.insertCommand(new CommandStats(gameWorld));
        
        //validCommands.insertCommand(new XCommandTake(gameWorld));
    }
    
    /**
     *  Return a welcome message for the player.
     */
    public String prolog() {
        return "You are a slightly naive stock trading newbie and you\n" +
                "want to get as rich as possible as quickly as possible,\n" +
                "so you can retire as early as possible.\n" +
                "Type \"help\" for a list of available commands.\n\n" +
                gameWorld.getCurrentPlace().longDescription() +
                performCommand("list *");
    }
    
    /**
     *  Returns a good-bye message.
     */
    public String epilog() {
        return "Thanks for playing STONXTRADER.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean isEndGame() {
        return endGame;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String performCommand(String line) {
        String[] words = line.split("[ \t]+");
        String commandWord = words[0];
        String[] parameters = new String[words.length-1];
        for(int i=0 ;i<parameters.length;i++){
           	parameters[i]= words[i+1];
        }
        String textToPrint=" .... ";
        if (validCommands.isValidCommand(commandWord)) {
            ICommand cmd = validCommands.getCommand(commandWord);
            textToPrint = cmd.processCommand(parameters);
        }
        else {
            textToPrint="This command isn't supported.";
        }
        return textToPrint;
    }
    
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
    
    public GameWorld getGameWorld(){
        return gameWorld;
    }
    
}


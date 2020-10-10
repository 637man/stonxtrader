package stonxtrader;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 *  
 */
class CommandHelp implements ICommand {
    
    private static final String NAME = "help";
    private Commands validCommands;
    
    
    /**
     *  Konstruktor třídy
     *  
     *  @param platnePrikazy seznam příkazů, které je možné ve hře použít,                   
     *                       aby je nápověda mohla zobrazit uživateli. 
     */    
    public CommandHelp(Commands validCommands) {
        this.validCommands = validCommands;
    }
    
    /**
     *  Returns a very brief list of available commands.
     *  No syntax guide yet. Just RTFM for now. May be replaced with menus.
     *  @return the help text to print
     */
    @Override
    public String processCommand(String... parameters) {
        return "STONXTRADER by 637man 2020. An oversimplified stock trading game.\n"
        + "Valid commands: "
        + validCommands.getCommandNames();
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
      public String getName() {
        return NAME;
     }

}

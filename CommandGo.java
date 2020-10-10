package stonxtrader;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
class CommandGo extends AGameCommand implements ICommand {
    private CommandList commandList;
    
    public CommandGo(GameWorld world) {
        super("go", world);
        commandList = new CommandList(world);
    }

    /**
     * Tries to change the current place to the one specified in the parameter.
     * If successful, returns a long description of that place. If the place 
     * isn't adjacent, returns an error message instead.
     */ 
    @Override
    public String processCommand(String... parameters) {
        if (parameters.length == 0) { // print possible arguments as a help
            String ret = "Exchanges and markets to go to: \n";
            for (Place pl : world.getCurrentPlace().getExits()) {
                ret += pl.getName() + " ---> ";
                for (String al: pl.getAliases()) {
                    ret += al + " ";
                }
                ret += "\n";
            }
            return ret;
        }
        
        Place adj = world.getCurrentPlace().getAdjacentPlace(parameters[0]);
        if (adj == null) {
            return "This exchange or market doesn't exist in this game.";
        }else if(adj.equals(world.getCurrentPlace())){
            return "It happens to be that you are already there.";
        }else{
            world.update();
            world.setCurrentPlace(adj);
            // generate the list here as there is a reference to entire world
            return adj.longDescription() + commandList.processCommand("*"); 
        }
    }
}

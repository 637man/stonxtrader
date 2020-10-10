/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;


public class CommandSeed implements ICommand {
    private final String NAME;
    
    public CommandSeed(GameWorld world) {
        this.NAME = "seed";
    }
    
    /**
     * This command is to be used only by demos, tests, and speedrunners.
     * The rng in the RNG class is protected and static. 
     */
    @Override
    public String processCommand(String... parameters) {
        if(parameters.length > 0){
            long seed;
            try{
                seed = Long.parseLong(parameters[0]);
            }catch(NumberFormatException e){
                return "Couldn't read the seed number for the RNG.\n";
            }
            RNG.rng.setSeed(seed);
            return "The RNG seed has been set to " + seed +".\n";
        }else{
            RNG.rng.setSeed(System.currentTimeMillis());
            return "The RNG seed has been set according to the current time.\n";
        }

    }

    @Override
    public String getName() {
        return NAME;
    }
}

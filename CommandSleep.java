/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;


public class CommandSleep extends AGameCommand implements ICommand {
    CommandList cl;
    
    public CommandSleep(GameWorld world) {
        super("sleep", world);
        cl = new CommandList(world);
    }
    
    @Override
    public String processCommand(String... parameters) {
        if(parameters.length > 0){
            int i;
            try {
                i = Integer.parseInt(parameters[0]);
            }catch (NumberFormatException e){ 
                i = 1; // A user can be very creative
            }
            for (; i > 0; --i) {
                world.update();
            }
        }else{
            world.update();
        }
        // return generateTable(); // construct a better table in there (NYI)
        return "The day is now " + world.getDay() + ".\n\n" + world.getCurrentPlace().longDescription() + cl.processCommand("*"); 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;


public class XCommandTake extends AGameCommand implements ICommand {
        
    public XCommandTake(GameWorld world) {
        super("take", world);
    }
    
    @Override
    public String processCommand(String... parameters) {
        if(parameters.length > 0){
            String thingName = parameters[0];
            if(!world.getCurrentPlace().containsThing(thingName)){
                return "There's no " + thingName + " in this room.";
            }else if(!world.getCurrentPlace().getThing(thingName).isCarriable()){                
                return "This " + thingName + " thing cannot be carried away.";
            }else{
                return "You'd have taken " + thingName + " if only the inventory was implemented.";
            }
            
        }else{
            return "No thing name provided.";
        }
    }
}

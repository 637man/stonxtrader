/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;


public class CommandPayback extends AGameCommand implements ICommand {
        
    public CommandPayback(GameWorld world) {
        super("payback", world);
    }
    
    @Override
    public String processCommand(String... parameters) {
        long amount;
        if(parameters.length > 0){
            try{
                amount = Long.parseLong(parameters[0]);
            }catch(NumberFormatException e){
                return "Couldn't read the amount to pay back.";
            }
        
        }else{
            amount = world.getDebt();
        }
        
        if(amount > world.getMoney()){
            amount = world.getMoney();
        }
        
        world.setDebt(world.getDebt()-amount);
        world.setMoney(world.getMoney()-amount);
        
        return "Paid back " + amount + ", so you have " + world.getMoney() + " and owe " + world.getDebt() + ".\n";
    }
}

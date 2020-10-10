/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;


public class CommandBorrow extends AGameCommand implements ICommand {
        
    public CommandBorrow(GameWorld world) {
        super("borrow", world);
    }
    
    @Override
    public String processCommand(String... parameters) {
        long amount;
        if(parameters.length > 0){
            try{
                amount = Long.parseLong(parameters[0]);
            }catch(NumberFormatException e){
                return "Couldn't read the amount to borrow.";
            }
        
        }else{
            return "The amount to borrow is mandatory to specify.";
        }
        
        world.setDebt(world.getDebt()+amount);
        world.setMoney(world.getMoney()+amount);
        
        return "Borrowed " + amount + ", so you have " + world.getMoney() + " and owe " + world.getDebt() + ".\n";
    }
}

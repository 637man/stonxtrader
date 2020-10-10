/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;


public class CommandSell extends AGameCommand implements ICommand {
   
    public CommandSell(GameWorld world) {
        super("sell", world);
    }
    /**
     * Mostly copied from CommandBuy.
     * Possibly merge into 1 and use negative quantity numbers to sell.
     * 
     * @param parameters the words typed after the command name
     * @return the message for the player
     */
    @Override
    public String processCommand(String... parameters) {
        if(parameters.length > 0){
            // * * *   Checking arguments (annoying)  * * *
            int which = -1;
            int howMany = 1;
            try{
                which = Integer.parseInt(parameters[0]);
            }catch(NumberFormatException e){
                return "Bad arguments, provide numbers - the stock ID and optionally quantity.\n";
            }
            try{
                howMany = Integer.parseInt(parameters[1]);
            }catch(Exception e){ // Both AIouBE and NFE
                // howMany stays at 1
            }
            if(howMany < 0){
                return "Can't sell negative quantities.\n";
            }
            Stock stock = world.getCurrentPlace().getStocks().get(which);
            if(stock == null){
                return "Invalid stock number.\n";
            }
            
            // * * *   Finding already owned pieces in portfolio   * * *
            boolean playerHasSome = false;
            int foundAtIndex = -1;
            for(Pair<Stock, Integer> i : world.getPortfolio()){
                if(i.car.equals(stock)){
                    playerHasSome = true;
                    foundAtIndex = world.getPortfolio().indexOf(i);
                }
            }
            
            // * * *   Taking the stocks away from the player   * * *
            if(playerHasSome){
                int playerHasAmount = world.getPortfolio().get(foundAtIndex).cdr;
                if(playerHasAmount <= howMany){ // allows typing 9999... to sell all
                    howMany = playerHasAmount;
                    world.getPortfolio().remove(foundAtIndex);
                }else{
                    Pair<Stock, Integer> listPair = world.getPortfolio().get(foundAtIndex);
                    listPair.setCdr(listPair.getCdr() - howMany);
                }
            }else{
                return "You can't sell something you don't have.";
            }
            
            // * * *   Giving the player the money in return   * * *
            long cost = stock.getValue() * howMany;
            world.setMoney(world.getMoney() + cost);
            return "Sold " + howMany + " shares of " + stock.getName() + " for " + cost + ", leaving you with $" + world.getMoney() + ".\n";
        }
        
        return "No arguments, provide the stock number and optionally quantity.\n";    
    }

}

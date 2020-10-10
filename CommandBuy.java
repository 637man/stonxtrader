/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;


public class CommandBuy extends AGameCommand implements ICommand {
        
    public CommandBuy(GameWorld world) {
        super("buy", world);
    }
    /**
     * There may be a better and more concise way to implement this.
     * 
     * @param parameters words typed after the command name
     * @return the message for the player
     */
    @Override
    public String processCommand(String... parameters) {
        if(parameters.length > 0){
            // * * *   Checking arguments (annoying)   * * *
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
                return "Can't buy negative quantities.\n";
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
            
            // * * *   Transfering the stocks to the player   * * *
            long cost = stock.getValue() * howMany;
            if(world.getMoney() < cost){ // only buy what the player can afford
                howMany = (int)(world.getMoney() / stock.getValue());
            }
            if(playerHasSome){
                Pair<Stock, Integer> listPair = world.getPortfolio().get(foundAtIndex);
                int leftStocks = world.getCurrentPlace().getStocks().get(which).getSupply() - listPair.getCdr();
                if(howMany > leftStocks){ // allows to type 9999... to puchase all, better than dealing with "*"
                    howMany = leftStocks;
                }
                listPair.setCdr(listPair.getCdr() + howMany);
            }else{
                int stockSupply = world.getCurrentPlace().getStocks().get(which).getSupply();
                if(howMany > stockSupply){ // ditto
                    howMany = stockSupply;
                }
                world.getPortfolio().add(new Pair<>(stock, howMany));
            }
            
            // * * *   Taking away money from the player   * * *
            world.setMoney(world.getMoney() - cost);
            return "Bought " + howMany + " shares of " + stock.getName() + " for " + cost + ", leaving you with $" + world.getMoney() + ".\n";
        }
        
        return "No arguments, provide the stock number and optionally quantity.\n";
    }
    
}

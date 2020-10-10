/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;


public class CommandStats extends AGameCommand implements ICommand {
        
    public CommandStats(GameWorld world) {
        super("stats", world);
    }
    
    @Override
    public String processCommand(String... parameters) {
        String ret = "";
        ret += "Day: " + world.getDay();
        ret += "\nAge: " + (world.getDay()/360 + 20);
        
        ret += "\nMoney: " + world.getMoney();
        ret += "\nDebt: " + world.getDebt();
        int portval = 0;
        for(Pair<Stock,Integer> li : world.getPortfolio()){
            portval += li.cdr * li.car.getValue();
        }
        ret += "\nPortfolio value: " + portval;
        ret += "\nNet worth: " + (world.getMoney() + portval - world.getDebt());
        
        //ret += "\nGame data: " + world.getGameName();
        //ret += "\nCurrent place: " + world.getCurrentPlace().getName() + "\n";
        return ret;
    }
}

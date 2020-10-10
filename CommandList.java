/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;

import java.util.ArrayList;
import java.util.List;


public class CommandList extends AGameCommand implements ICommand {
        
    public CommandList(GameWorld world) {
        super("list", world);
    }
    
    /**
     * This is reused by some other commands for the player's convenience
     * @param parameters
     * @return 
     */
    @Override
    public String processCommand(String... parameters) {
        //            00000000011111111112222222222333333333344444444445555555555666666666677777777778
        //            12345678901234567890123456789012345678901234567890123456789012345678901234567890
        String ret = "ID Name                         Volat   UnitVal      Owned   TotalVal     Supply\n";
        ret +=       "--------------------------------------------------------------------------------\n";
        List<Stock> st = world.getCurrentPlace().getStocks();
        if(parameters.length > 0){
            if(parameters[0].equals("*")){
                for (int i = 0; i < st.size(); ++i) {
                    Stock cur = st.get(i);
                    Pair<Stock,Integer> li = null;
                    for(Pair<Stock,Integer> p : world.getPortfolio()){
                        if(p.car.equals(cur)){
                            li = p;
                        }
                    } // TODO STRING.FORMAT FIX TABS
                    ret += String.format("%2d %-30s%3d %10d %10d %10d %10d\n", i, cur.getName(), cur.getVolatility(), cur.getValue(), (li == null ? 0 : li.cdr), (li == null ? 0 : li.cdr*cur.getValue()), cur.getSupply());
                    //ret += i + " " + cur.getName() + "\t\t" + cur.getVolatility() + "\t" + cur.getValue() + "\t" + (li == null ? 0 : li.cdr) + "\t" + (li == null ? 0 : li.cdr*cur.getValue()) + "\t" + cur.getSupply() + "\n";
                }
            }else{
                ret = "Remote listing not implemented.";
            }
        }else{
            for(Pair<Stock, Integer> i : world.getPortfolio()){
                int id = st.contains(i.getCar()) ? st.indexOf(i.getCar()) : -1;
                //ret += (id == -1 ? "--" : id) + " " + i.car.getName() + "\t\t" + i.car.getVolatility() + "\t" + i.car.getValue() + "\t" + i.cdr + "\t" + i.car.getValue()*i.cdr + "\t" + i.car.getSupply() + "\n";
                ret += String.format("%2s %-30s%3d %10d %10d %10d %10d\n", (id == -1 ? "--" : id), i.car.getName(), i.car.getVolatility(), i.car.getValue(), i.cdr, i.car.getValue()*i.cdr, i.car.getSupply()); 
            }
        }
        return ret;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents an individual stock in a stock exchange or the player's portfolio.
 * This class is also used for other securities which are not the main focus of the game.
 * It's not named Security due to possible confusion with other meanings.
 * 
 * @author 637man
 */
public class Stock{
    private String name;
    private long value; // in VALUESCALEths of a game currency unit
    public static final int VALUESCALE = 1 << 16; // -> max malue 32767
    private int volatility; // per cent of random jump sans inflation
    private List<Long> pastValues; // used for graphing
    private int pastValuesOffset; // keep track of cleared history amount
    private final int supply; // Total amount available in game, 1000 for per mille
    private static final float INFLATION = 1.02f; // this is legacy fallback
    
    public Stock(String name, int volatility, long value, int supply){
        this.name = name;
        this.value = value * VALUESCALE;
        this.volatility = volatility;
        this.pastValues = new ArrayList<>(1024);
        this.pastValuesOffset = 0;
        this.supply = supply;
    }
    
    /**
     * Calculates and updates the value for the next day.
     * The volatility determines the random step size in per mille.
     * The price is also adjusted for daily inflation slightly.
     * This is numerically very wonky, the value is a scaled integer.
     * Also needs an overflow check, especially with volatile (in)securities.
     * @param infl annual inflation rate as loaded in GameWorld()
     */
    public void update(float infl){
        pastValues.add(value);
        if (value != 0){
            value *= (1 + ((double)infl - 1)/360) * (((RNG.rng.nextDouble() - 0.5) * volatility * 2/100) + 1);
        }
    }

    /**
     * For rare cases in the late game when you are running out of memory
     * and don't mind slightly more difficult decision making.
     */
    public void forget(){
        pastValuesOffset += pastValues.size();
        pastValues.clear();
    }
    
    public String getName(){
        return this.name;
    }
    
    public long getValue(){ // fianlly a sensible use for a getter
        return this.value / VALUESCALE;
    }
    
    public List<Long> getPastValues(){
        return this.pastValues;
    }
    
    public int getVolatility(){
        return this.volatility;
    }
    
    public int getPastValuesOffset(){
        return this.pastValuesOffset;
    }
    
    public int getSupply(){
        return this.supply;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stock other = (Stock)obj;
        return Objects.equals(this.name, other.getName());
    }

}























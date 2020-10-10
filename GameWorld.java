package stonxtrader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
//import javafx.util.Pair;

/**
 * This class holds the game state and layout.
 * This also includes all data relating to the player.
 * Multiplayer not implemented.
 * 
 * @author     637man
 * @version    1.0
 */
public class GameWorld {
    
    private Place currentPlace;
    private List<Pair<Stock,Integer>> portfolio;
    private long money; // Can easily exceed 2 milliard
    private long debt;
    private float INTEREST; // no write past construction
    private float INFLATION;
    private int day; // 2 milliard days is over 5 million years
    private String gameName = "st";
    
    public GameWorld(String gamename){
        createGamePlaces(gamename);
        money = -1;
        debt = -1;
        portfolio = null;
        day = -1;
        INTEREST = Float.NaN;
        INFLATION = Float.NaN;
        String startplace = "";
        
        try{
            Scanner sc = new Scanner(new File("st/_player.txt"));
            String line;
            int field = 1; 
            while(sc.hasNext()){
                line = sc.nextLine();
                if(line.startsWith("#")){
                    continue; // don't increment field
                }
                switch(field){ // this is lame
                    case 1: // Initial Money
                        try{
                            money = Integer.parseInt(line);
                        }catch (NumberFormatException e) {
                            money = 1100000;
                        }
                    break;
                    case 2: // Initial Loan
                        try{
                            debt = Integer.parseInt(line);
                        }catch (NumberFormatException e) {
                            debt = 1000000;
                        }
                    break;
                    case 3: // Startning Place
                            startplace = line;
                    break;
                    case 4: // Interest Rate
                        try{
                            INTEREST = Float.parseFloat(line);
                        }catch (NumberFormatException e) {
                            INTEREST = 1.05f;
                        }
                    break;
                    case 5: // Inflation
                        try{
                            INFLATION = Float.parseFloat(line);
                        }catch (NumberFormatException e) {
                            INFLATION = 1.02f;
                        }
                    break;
                    default:
                }
                ++field;
            }
        }catch(FileNotFoundException e){ 
            System.err.println("Couldn't load _player.txt, using defaults.");
        }
        // use the old hardcoded defaults
        if(money == -1) money = 1100000;
        if(debt == -1) debt = 1000000; // a small loan of million dolars
        if(portfolio == null) portfolio = new ArrayList<Pair<Stock, Integer>>(10);
        if(day == -1) day = 1;
        if(INTEREST == Float.NaN) INTEREST = 1.05f;
        if(INFLATION == Float.NaN) INFLATION = 1.02f;
        if(startplace.equals("")) startplace = "bratislava";
        
        // there should be a proper probe, but the places form a complete graph
        Place pl = currentPlace.getAdjacentPlace(startplace); 
        if (pl == null) {
            System.err.println("Starting place of " + startplace + "doesn't exist.\n");
        }else{
            currentPlace = pl;
        }
        
    }
    
    /**
     * Applies interest to the debt, advances day, pays dividends each year,
     * and finally, updates all the prices.
     */
    public void update(){
        debt *= 1 + (INTEREST - 1)/360;
        ++day;
        if (day % 360 == 0){
            money += portfolio.size() * 1000; // TODO calculate dividends
        }
        
        // maybe too hacky, have a list of all places or recursively traverse
        for (Place pl: currentPlace.getExits()) { 
            for(Stock st: pl.getStocks()){
                st.update(INFLATION);
            }
        }
    }
    
    /**
     * Creates the places, puts various things in them and joins them by 
     * setting their exits. After that the starting place is set.
     */
    private void createGamePlaces(String gamename) {
        // * * *   LOAD GAME DATA FILES   * * *
        this.gameName = gamename;
        ArrayList<Place> places = new ArrayList<>(16);
        try{
            File gamedir = new File("."+File.separator+gamename+File.separator);
            String[] ls = gamedir.list((File f, String name) -> !name.startsWith("_"));

            for (String placeName: ls) {
                //if(placeName.startsWith("_")) continue;
                Scanner stockSc = new Scanner(new File(gamename+File.separator+placeName),"UTF8");
                ArrayList<Stock> stocks = new ArrayList<>(16);
                while(stockSc.hasNext()){
                    String stockLine = stockSc.nextLine();
                    if(stockLine.startsWith("#")) continue;
                    String[] stockCells = stockLine.split(";");
                    stocks.add(new Stock(stockCells[0], Integer.parseInt(stockCells[1]), Long.parseLong(stockCells[2]), Integer.parseInt(stockCells[3])));
                }
                Scanner placeListSc = new Scanner(new File(gamename+File.separator+"_places.csv"),"UTF8");
                while(placeListSc.hasNext()){
                    String placeLine = placeListSc.nextLine();
                    if(placeLine.startsWith("#")) continue;
                    String[] placeCells = placeLine.split(";");
                    if(placeName.equals(placeCells[0]+".csv")){
                        List<String> placeAliases = Arrays.asList(placeCells[2],placeCells[3],placeCells[4]);
                        places.add(new Place(placeCells[0],placeAliases,placeCells[1],stocks));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        // * * *   LINK THE PLACES   * * *
        // there are no access limitations, so each to each (for cycle)
        
        for (Place i: places) {
            for (Place j: places) {
                i.addExit(j);
            }
        }
        currentPlace = places.get(0);
        
        // Home - A special place with no stocks of its own, but player can sell any of their stock or buy more
        // would require some searching around
        
    }
    
    public Place getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(Place place) {
       currentPlace = place;
    }
    
    public long getMoney(){
        return money;
    }
    
    public void setMoney(long money){
        this.money = money;
    }
    
    public long getDebt(){
        return debt;
    }
    
    public void setDebt(long val){
        this.debt = val;
    }
    
    public int getDay(){
        return day;
    }
    
    public String getGameName(){
        return this.gameName;
    }
    
    protected List<Pair<Stock, Integer>> getPortfolio(){

        return portfolio;
    }

}

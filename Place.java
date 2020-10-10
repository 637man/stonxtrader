package stonxtrader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a security exchange. Each instance holds the current state
 * of all its shares or commodities that are traded on it.
 *
 * @author Jan Getmancuk
 * @version 1.0
 */
public class Place {
    private String name;
    private List<String> aliases; // maybe external map
    private String description;
    private Set<Place> exits;   // use global stock exchange trade list maybe
    /**
     * The actual available quantity is calculated by subtracting the amount
     * owned by the player from the supply specified in the specific security.
     * The Stocks are called by numbers in-game for convenience, therefore a List is used.
     */
    private List<Stock> stocks; 
    
    // Excercise only
    private Map<String, XThing> xthings; // "Koste" -> XThing{"Koste", "kostatko asi nejake carodejnice", true}
    
    // TODO maybe generalize with null checking
    public Place(String name, String description) {
        this.name = name;
        this.aliases = new ArrayList<>(4);
        this.description = description;
        exits = new HashSet<>(10);
        stocks = new ArrayList<>(10);
        
        this.xthings = new HashMap<String,XThing>(16);
    }
    public Place(String name, List<String> aliases, String description) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
        exits = new HashSet<>(10);
        stocks = new ArrayList<>(10);
        
        this.xthings = new HashMap<String,XThing>(16);
    }
    /**
     * Due to cross-dependencies, exits are to be added with 
     * <code> addExit(Place) </code> after all places have been constructed.
     * 
     * @param name the primary internal name of this place (legacy)
     * @param aliases alternative names to be typed with the "go" command
     * @param description full name
     * @param stocks the securities to be traded in this place
     */
    public Place(String name, List<String> aliases, String description, List<Stock> stocks) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
        this.exits = new HashSet<>(10);
        this.stocks = stocks;
        
        this.xthings = new HashMap<String,XThing>(16);
                
    }

    public void addExit(Place adjacent) {
        exits.add(adjacent);
    }
    public void addAlias(String alias) {
        aliases.add(alias);
    }
    public void addStock(Stock stock){
        stocks.add(stock);
    }
    
    public void addThing(XThing thing){
        xthings.put(thing.getName(), thing);
    }
    public boolean containsThing(String thingName){
        return xthings.containsKey(thingName);
    }
    public XThing getThing(String thingName){
        return xthings.get(thingName);
    }
    public XThing removeThing(String thingName){
        return xthings.remove(thingName);
    }

      
    public String getName() {
        return name;
    }

    public String longDescription() {
        
        String ret = " ~~~ " + description.toUpperCase() + " ~~~" + "\n\n";
        /* // Legacy code
        for (int i = 0; i < stocks.size(); ++i) {
            Stock cur = stocks.get(i);
            ret += "#" + i + ": " + cur.getName() + " ($" + cur.getValue() + "); ";
            if(i%4==3) ret += "\b\n";
        }
        ret += "\b\b\n";
        */
        /* // More legacy code
        ret += "\nThings lying around: ";
        for(String thingName: xthings.keySet()){
            ret += thingName + " ";
        }
        */
        return ret;
    }

    private String exitDescription() { // not needed, use plain "go"
        String ret = "Exits:";
        if(exits != null){
            for (Place adjacent : exits) {
                ret += " " + adjacent.getName();
            }
        }else{
            ret += " none";
        }
        return ret;
    }

    public Place getAdjacentPlace(String adjacentName) {
        for (Place exit: exits) {
            if(exit.getName().equals(adjacentName) || exit.getAliases().contains(adjacentName)){
                return exit;
            }
        }
        
        /*List<Place> placesLookedFor = exits.stream()
            	.filter(adjacent -> adjacent.getName().equals(adjacentName))
            	.collect(Collectors.toList());*/
        
        return null;
        
    }

    public Collection<Place> getExits() {
        return Collections.unmodifiableCollection(exits);
    }
    public Collection<String> getAliases() {
        return Collections.unmodifiableCollection(aliases);
    }
    public List<Stock> getStocks(){
        return stocks;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Place)) {
            return false;
        }
        Place other = (Place) o;
        return (java.util.Objects.equals(this.name, other.name));
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(this.name);
    }
}

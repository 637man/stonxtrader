/*
 * Copyleft (ɔ) Jan Getmancuk 2020. All wrongs reserved.
 */

package stonxtrader;

/**
 * Only excercise
 * @author 637man
 */
public class XThing {
    private final String name;
    private final String description;
    private boolean carriable;

    public XThing(String name, String description, boolean carriable){
        this.name = name;
        this.description = description;
        this.carriable = carriable;
    }
    
    public void toggleCarriable(){
        carriable = !carriable;
    }
    
    public void setCarriable(boolean val){
        carriable = val;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCarriable() {
        return carriable;
    }
    
    
}

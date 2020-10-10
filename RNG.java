
/*
 * Copyleft (ɔ) Jan Getmancuk 2020. All wrongs reserved.
 */

package stonxtrader;

import java.util.Random;

/**
 * Holds the central random number generator. Needed for tests and demos
 * to work deterministically. Technically used as a singleton.
 * @author 637man
 */
public class RNG {
    protected static Random rng = new Random();
    
    public RNG(long seed){
        rng.setSeed(seed);
    }

    RNG() {
    }
    
    public void setSeed(long seed){
        rng.setSeed(seed);
    }
    
}

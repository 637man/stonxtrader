/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package stonxtrader;

/*******************************************************************************
 * Main class that merely holds the main method used to decode command line
 * arguments and initialize the game.
 *
 * @author    Jan Getmancuk
 * @version   1.0
 */
public class Main{
    /***************************************************************************
     * Main method that initializes and starts the game.
     * Argument syntax TODO: java -jar stonxtrader.jar -g st -s 42069 -i demo.txt -o out.txt
     * --game-data --random-seed --input-file --output-file
     * @param args Command line arguments
     */
    public static void main(String[] args){
        String input = null;
        String output = null;
        long seed = System.currentTimeMillis();
        String gamename = "st";
        
        // Argument syntax error handling is very unforgiving
        for (int i = 0; i < args.length; i+=2) {
            switch(args[i]){
                case "-g":
                    if(args.length < i+2){ 
                        System.err.println("Missing game data directory argument to -g");
                        return;
                    }
                    gamename = args[i+1];
                break;
                case "-s":
                    try{
                        seed = Long.parseLong(args[i+1]);
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.err.println("Missing seed argument for -s");
                        return;
                    }catch(NumberFormatException e){
                        System.err.println("Couldn't parse the seed value as long");
                        return;
                    }
                break;
                case "-i":
                    if(args.length < i+2){
                        System.err.println("Missing input file argument for -i");
                        return;
                    }
                    input = args[i+1];
                break;
                case "-o":
                    if(args.length < i+2){
                        System.err.println("Missing output file argument for -o");
                        return;
                    }
                    output = args[i+1];
                break;
                default:
                    System.err.println("Unrecognized parameter " + args[i]);
                    return;
            }
        }
        
        RNG.rng.setSeed(seed);
        
        IGame game = new Game(gamename);
        CLI cli = new CLI(game);
        if(input == null){
            cli.play();
        }else{
            cli.playFromFile(input);
        }
        
    }
    
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class CommandRetire implements ICommand {
    private GameWorld world;
    private Game game;
    private final String NAME = "retire";
    
    public CommandRetire(GameWorld world, Game game) {
        this.world = world;
        this.game = game;
    }
    
    @Override
    public String processCommand(String... parameters) {
        String scoreLine = "";
        
        if(parameters.length == 0){ // no other command prompts for missing arguments
            System.out.print("Enter your name: ");
            Scanner sc = new Scanner(System.in);
            scoreLine += sc.next();
        }else{ 
            scoreLine += parameters[0];
        }
        
        int tradedFor = world.getDay() / 360;
        long retiredFor = world.getMoney() / 50000; // this needs to account for inflation, or maybe is just buddhist
        long livedUntil = tradedFor + retiredFor + 20;
        
        scoreLine += ";" + (world.getMoney()-world.getDebt()) + ";" + tradedFor + ";" + retiredFor + ";" + livedUntil + "\n";
        
        try {
            Files.write(Paths.get(world.getGameName() + File.separator + "_scores.csv"), scoreLine.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.err.println("Couldn't write to file _scores.csv.");
        }
        
        game.setEndGame(true);
        
        return "You made " + (world.getMoney()-world.getDebt()) + " over " + tradedFor + " years, which was enough for " + retiredFor + " years,\nthus can live happily until " + livedUntil + " years.\n";
    }
    
    @Override
    public String getName(){
        return this.NAME;
    }
}

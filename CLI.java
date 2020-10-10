package stonxtrader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class CLI {
    private IGame game;

    /**
     *  Vytváří hru.
     */
    public CLI(IGame game) {
        this.game = game;
    }

    CLI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void play() {
        System.out.println(game.prolog());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!game.isEndGame()) {
            String line = readString();
            System.out.println(game.performCommand(line));
        }

        System.out.println(game.epilog());
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     * @return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String readString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }
    
    // This would be more convenient to do with piping, violates DRY principle
    public void playFromFile(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename)); PrintWriter pw = new PrintWriter(new FileWriter("out.txt"))){        
            pw.println(game.prolog());

            String line = br.readLine();
            pw.println("> " + line); // for visual
            while (!game.isEndGame() && line != null) {
                String message = game.performCommand(line);
                pw.println(message);
                line = br.readLine();
                pw.println("> " + line);
            }

        //System.out.println(game.epilog());
        pw.println(game.epilog());
            
        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
            System.err.println(new File(filename).getAbsolutePath());
        }catch(IOException e){
            System.err.println(e.getMessage());
        }finally{
            
        }
    }

}

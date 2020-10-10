package stonxtrader;

import java.util.HashMap;
import java.util.Map;

/**
 *  Class SeznamPrikazu - eviduje seznam přípustných příkazů adventury.
 *  Používá se pro rozpoznávání příkazů
 *  a vrácení odkazu na třídu implementující konkrétní příkaz.
 *  Každý nový příkaz (instance implementující rozhraní Prikaz) se
 *  musí do seznamu přidat metodou vlozPrikaz.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
class Commands {
    // mapa pro uložení přípustných příkazů
    private  Map<String,ICommand> commandMap;
    
   
    
    /**
     * Konstruktor
     */
    public Commands() {
        commandMap = new HashMap<>();
    }
    
    
    /**
     * Vkládá nový příkaz.
     *
     *@param  prikaz  Instance třídy implementující rozhraní IPrikaz
     */
    public void insertCommand(ICommand command) {
        commandMap.put(command.getName(),command);
    }
    
    /**
     * Vrací odkaz na instanci třídy implementující rozhraní IPrikaz,
     * která provádí příkaz uvedený jako parametr.
     *
     *@param  retezec  klíčové slovo příkazu, který chce hráč zavolat
     *@return          instance třídy, která provede požadovaný příkaz
     */
    public ICommand getCommand(String string) {
        if (commandMap.containsKey(string)) {
            return commandMap.get(string);
        }
        else {
            return null;
        }
    }

    /**
     * Kontroluje, zda zadaný řetězec je přípustný příkaz.
     *
     *@param  retezec  Řetězec, který se má otestovat, zda je přípustný příkaz
     *@return          Vrací hodnotu true, pokud je zadaný
     *                     řetězec přípustný příkaz
     */
    public boolean isValidCommand(String string) {
        return commandMap.containsKey(string);
    }

    /**
     *  Vrací seznam přípustných příkazů, jednotlivé příkazy jsou odděleny mezerou.
     *  
     *  @return     Řetězec, který obsahuje seznam přípustných příkazů
     */
    public String getCommandNames() {
        String list = "\n";
        int col = 1;
        for (String commandWord : commandMap.keySet()){
            list += commandWord + "\t\t";
            if(col % 4 == 0) {
                list += "\n";
            }
            ++col;
        }
        return list;
    }
    
}


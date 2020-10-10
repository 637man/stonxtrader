/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonxtrader;

/**
 * Apparently only in JavaFX, and not enough LISP in there.
 * Has both direct access and getters, and even setters.
 * Currently only used for individual portfolio items.
 * @author 637man
 * @param <CAR> contents of the address part of register (on IBM 704)
 * @param <CDR> contents of the decrement part of register (pronounce cadre)
 */
public class Pair <CAR, CDR>{
    public CAR car;
    public CDR cdr;
    
    public Pair(CAR car, CDR cdr){
        this.car = car;
        this.cdr = cdr;
    }
    
    public boolean isNull(){
        return car == null && cdr == null;
    }
    
    public CAR getCar(){
        return car;
    }
    public void setCar(CAR car){ // Very unfunctional, use cons
        this.car = car;
    }
    public CDR getCdr(){
        return cdr;
    }
    public void setCdr(CDR cdr){ // Very unfunctional, use cons
        this.cdr = cdr;
    }
    
    // cannot be static, c++ is better for this metaprogramming
    public Pair<CAR,CDR> cons(CAR car, CDR cdr){
        return new Pair<CAR,CDR>(car,cdr);
    }
    
    // Following 4 methods are very type-wonky
    @SuppressWarnings("unchecked")
    public CAR getCdar(){
        if(this.cdr != null && this.cdr instanceof Pair){
            Pair cdrcast = (Pair) this.cdr;
            return (CAR) cdrcast.car;
        }else{
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public void setCdar(CAR car){
        if(this.cdr != null && this.cdr instanceof Pair){
            Pair cdrcast = (Pair) this.cdr;
            cdrcast.setCar(car);
        }
    }
    
    @SuppressWarnings("unchecked")
    public CDR getCddr(){
        if(this.cdr != null && this.cdr instanceof Pair){
            Pair cdrcast = (Pair) this.cdr;
            return (CDR) cdrcast.cdr;
        }else{
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public void setCddr(CDR cdr){
        if(this.cdr != null && this.cdr instanceof Pair){
            Pair cdrcast = (Pair) this.cdr;
            cdrcast.setCdr(cdr);
        }
    }

}


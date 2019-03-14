/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygamenew;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayesh
 */
public abstract class Worrior extends Thread {
    private final String name;
    private GridLocation gridlocation;
    private boolean alive;                  //if worrior is alive true, else false
    private boolean fins;                   //if worrior has fins true, else false
    private boolean immortal;               //if worrior is immortal true, else false
    private boolean canSwim;
    public static volatile boolean gameOver = false;
    public static int num_of_worriors;
    
    public Worrior(String name, GridLocation gridLocation){
        this.name = name;
        this.gridlocation = gridLocation;
        alive = true;
        fins = true;
        immortal = false;
        canSwim = true;
        num_of_worriors++;
    }
    
    protected boolean getIsAlive(){
        return alive;
    }
    
    protected void setAlive(boolean alive){
        this.alive = alive;
    }
    
    protected void setCanSwim(boolean can){
        this.canSwim = can;
    }
    
    protected boolean getCanSwim(){
        return canSwim;
    }
    
    protected boolean hasFins() {
        return fins;
    }

    protected void setFins(boolean fins) {
        this.fins = fins;
    }

    protected boolean isImmortal() {
        return immortal;
    }

    protected void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }

    protected String getWorriorName() {
        return name;
    }

    protected GridLocation getGridlocation() {
        return gridlocation;
    }

    protected void setGridlocation(GridLocation gridlocation) {
        this.gridlocation = gridlocation;
    }
    
    public void die(){
        Worrior.num_of_worriors--;
        this.alive = false;
        this.canSwim = false;
    }

    @Override
    public String toString(){
        return this.getName();
    }
    
    public void getNotified(){
        this.canSwim = false;
        
    }
    
    @Override
    public void run(){
        while(this.getCanSwim()){
            try{
                this.sleep(1);
                if(!Worrior.gameOver){
                    swim(this.gridlocation);
                }
                this.sleep(1000);
            } catch (InterruptedException ex) {
            Logger.getLogger(Worrior.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public abstract void eat();
    public abstract void swim(GridLocation currentLocation);
    public abstract void Sleep();
    
}

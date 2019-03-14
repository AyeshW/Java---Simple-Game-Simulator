/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygamenew;

/**
 *
 * @author Ayesh
 */
public class KillerFish extends Fish implements SpiritDecision{
    
    public KillerFish(GridLocation gridLocation,String name) {
        super(gridLocation, name);
    
    }
    
    public void kill(Worrior worrior){
        System.out.println("\n***** " + worrior + " was died *****\n");
        worrior.getGridlocation().removeFromGrid(worrior);
        worrior.die();
    }
    
    @Override
    public String toString(){
        return this.name ;
    }
}

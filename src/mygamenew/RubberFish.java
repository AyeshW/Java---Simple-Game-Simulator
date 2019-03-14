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
public class RubberFish extends Fish {
    
    public RubberFish(GridLocation gridLocation,String name) {
        super(gridLocation,name);

    }
    
    public void eatFins(Worrior worrior){
        worrior.setFins(false);
        worrior.setCanSwim(false);
        System.out.println("\n***** Rubber eating fish ate fins; " + worrior + " cannot swim *****\n****************" + worrior + " is floating on " + worrior.getGridlocation() + "****************\n");
    }
    
    
    @Override
    public String toString(){
        return this.name;
    }
}

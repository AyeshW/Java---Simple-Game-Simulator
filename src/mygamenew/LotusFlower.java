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
public class LotusFlower implements SpiritDecision {
    
    private final GridLocation gridLocation;
    private int numOfPetals;
    
    public LotusFlower(GridLocation gridLocation) {
        this.gridLocation = gridLocation;
        numOfPetals = 100;
    }
    
    public void makeImmoral(Worrior worrior){
        worrior.setImmortal(true);
        numOfPetals--;
        System.out.println("\n***** " + worrior + " is immortalized *****\n");
    }
    
    public int getNumOfPetals(){
        return this.numOfPetals;
    }
    
    @Override
    public String toString(){
        return "Lotus Flower";
    }
}

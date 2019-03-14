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

//This class was made to avoid adding Killer Fishes and Rubber Fishes to the InnocentFishGrid
public class InnocentFish extends Fish{

    public InnocentFish(GridLocation gridLocation,String name) {
        super(gridLocation, name);
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}

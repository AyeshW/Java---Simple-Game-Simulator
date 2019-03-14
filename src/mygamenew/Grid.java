/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygamenew;
import java.util.HashMap;
/**
 *
 * @author Ayesh
 */
public class Grid {

    //HashMap to store grid locations of worriors
    public static HashMap<Worrior, GridLocation> worriorsGrid = new HashMap<>();
    
    //HashMap to store grid locations of killer fishes' and lotus flowers' 
    public static HashMap<GridLocation, SpiritDecision> flowerAndKillerFishGrid = new HashMap<>();
    
    //HashMap to store grid locations of rubber eating fishes
    public static HashMap<GridLocation, RubberFish> rubberFishGrid = new HashMap<>();
    
    //HashMap to store grid locations of innocent fishes
    public static HashMap<GridLocation, InnocentFish> InnocentFishGrid = new HashMap<>();

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygamenew;
import java.util.ArrayList;
/**
 *
 * @author Ayesh
 */
public class NormalWorrior extends Worrior {
    
    public NormalWorrior(String name, GridLocation gridLocation) {
        super(name, gridLocation);
    }

    @Override
    public void eat() {
        System.out.println("Eating slowly");
    }

    @Override
    public void swim(GridLocation currentLocation) {
    
            ArrayList availablePoints = Controller.availableLocations(currentLocation);
            GridLocation nextLocation = Controller.bestPoint(currentLocation, availablePoints);
            if (this.getIsAlive()){
            nextLocation.putToGrid(this);
            System.out.println(this + " swam from " + currentLocation + " to " + nextLocation);
            this.setGridlocation(nextLocation);
            Controller.checkStatus(this, nextLocation);
             
            }else{
                nextLocation.removeFromGrid(this);
            }
    }

    @Override
    public void Sleep() {
        System.out.println("Sleeping deeply");
    }
    
}

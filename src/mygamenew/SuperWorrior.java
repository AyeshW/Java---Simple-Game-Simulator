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
public class SuperWorrior extends Worrior{
    private final Binocular binocular;

    public SuperWorrior(String name, GridLocation gridLocation) {
        super(name, gridLocation);
        binocular = new Binocular();
    }
    
    private GridLocation lookWithBinocular(ArrayList availablePoints){
        GridLocation nextPoint;
        ArrayList<GridLocation> flowerPoints = new ArrayList<>();
        for (Object point: availablePoints){
            GridLocation pointA = (GridLocation)point;
            if (Grid.flowerAndKillerFishGrid.containsKey(pointA)){
               flowerPoints.add(pointA);
            }
        }
        if (flowerPoints.isEmpty()){
            nextPoint = Controller.bestPoint(this.getGridlocation(), availablePoints);
        }
        else {
            nextPoint = Controller.bestPoint(this.getGridlocation(), flowerPoints);
        }
        return nextPoint;
    }
            
    @Override
    public void eat() {
        System.out.println("Eating quickly");
    }

    @Override
    public void swim(GridLocation currentLocation) {
        
            GridLocation nextLocation;
            ArrayList availablePoints = Controller.availableLocations(currentLocation);
            if (!this.isImmortal()){
                nextLocation = this.lookWithBinocular(availablePoints);
            } else {
                nextLocation = Controller.bestPoint(currentLocation, availablePoints);
            }
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
        System.out.println("Sleeping");
    }
    
}

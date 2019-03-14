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
public class TreasureChest {
    private final GridLocation gridLocation;
    public static ArrayList<Worrior> worriors;
    public static Worrior winner;
    
    public TreasureChest(){
        gridLocation = new GridLocation(5,5);
        worriors = new ArrayList<>();
        winner = null;
    }

    public void setTarget(Worrior wo){
        worriors.add(wo);
    }
    
    public void removeTarget(Worrior wo){
        worriors.remove(wo);
    }
    
    public void notifyWorriors(){
        for(Worrior w : worriors){
            w.getNotified();
        }
    }
    
    public GridLocation getGridLocation() {
        return gridLocation;
    }

}

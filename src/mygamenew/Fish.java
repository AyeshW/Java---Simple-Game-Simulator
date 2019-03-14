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

public abstract class Fish {
    private final GridLocation gridLocation;
    protected final String name;

    public Fish(GridLocation gridLocation, String name) {
        this.gridLocation = gridLocation;
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
}

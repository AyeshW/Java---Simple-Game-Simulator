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
public class GridLocation {
    private int x;
    private int y;
    private boolean isOccupied;
    
    public GridLocation(int x, int y){
        this.x = x;
        this.y = y;
        this.isOccupied = false;
    }

    public int getX() {
        return x;
    }
    
    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public synchronized void putToGrid(Worrior w){
        while(this.isOccupied){
            try{
                wait();
            }catch(InterruptedException ex){};
        }
        this.isOccupied = true;
        Grid.worriorsGrid.put(w, this);
        notifyAll();
    }
    
    public synchronized void removeFromGrid(Worrior w){
        while(!this.isOccupied){
            try{
                wait();
            }catch(InterruptedException ex){};
        }
        this.isOccupied = false;
        Grid.worriorsGrid.remove(w);
        notifyAll();
    }
    @Override
    public String toString(){
        return "(" + x + "," + y + ")" ;
    }
    
    @Override
    public boolean equals(Object location){
        boolean equality;
        if (location instanceof GridLocation){
            GridLocation gl = (GridLocation)location;
            equality = this.x == gl.x && this.y == gl.y;
        }else{
            equality = false;
        }
        return equality;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.x;
        hash = 11 * hash + this.y;
        return hash;
    }
}

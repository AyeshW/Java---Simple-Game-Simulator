/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygamenew;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Ayesh
 */
public class Game {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        
        controller.creatWorriors();
        controller.creatLotusFlowers();
        controller.creatFish();
        
        System.out.println("\nNames of Inhabitants: ");
        for(String inhab : Controller.InhabitantsNames){
            System.out.print(inhab + ",");
        }
        
        System.out.print("\n");
        System.out.println("\nInitial Locations of worriors = " + Grid.worriorsGrid);
        System.out.println("Initial Locations of lotus flowers and killer fishes = " + Grid.flowerAndKillerFishGrid);
        System.out.println("Initial Locations of rubber fishes = " + Grid.rubberFishGrid + "\n");
        
        long StartTime = System.nanoTime();
        
        ArrayList<Worrior> worriors= new ArrayList<>(Grid.worriorsGrid.keySet());
        for(Worrior wo: worriors){
            wo.start();
        }
        
        worriors.forEach((wo) ->{
            try {
                wo.join();
            } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            });

        double TimeTaken = (System.nanoTime() - StartTime)/1000000000.0;
        double Score = (1/TimeTaken)*100;

        BufferedWriter record = new BufferedWriter(new FileWriter("LakeNozama.txt"));
        record.write("*********Game Record***********\n");
        record.newLine();
        if(TreasureChest.winner == null){
            System.out.println("There is no Winner; Everybody was died");
            record.write("All worriors have died; No Winner.");
            record.newLine();
        }else{
            System.out.println("\n***** " + TreasureChest.winner + " found the Treasure Quest ***** \nNumber of worriors still alive: " + Worrior.num_of_worriors + "\n**********GAME OVER**********");
            record.write(TreasureChest.winner + " is the winner!");
            record.newLine();
            record.write("NEW SCORE : " + (int)Score);
            record.newLine();
        }
        record.write("Time Taken : " + TimeTaken +" seconds");
        record.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygamenew;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Ayesh
 */
public class Controller {
    
    private static Grid grid;
    private static TreasureChest chest;
    public static ArrayList<String> InhabitantsNames = new ArrayList<>();

    public Controller() {
        grid = new Grid();
        chest = new TreasureChest();
    }
    
    public static GridLocation newWorriorLocation(){
        GridLocation gl;
        Random rand = new Random();
        int x = rand.nextInt(12);
        int y = rand.nextInt(12);
        
        if (x == 0){
            gl = new GridLocation(x,y);
        }
        else if(y == 0){
            gl = new GridLocation(x,y);
        }
        else if (x == 11){
            gl = new GridLocation(x,y);
        }
        else if (y == 11){
            gl = new GridLocation(x,y);
        }
        else{
            gl = newWorriorLocation();
        }    
        return gl;
    }
    
    public void creatWorriors(){

        String[] worriorNames = {"Shaggy","Scooby","Fred","Welma"};
        int i = 0;
        
        while(i <= 3){
            GridLocation initialPoint = newWorriorLocation();
            if (!Grid.worriorsGrid.containsValue(initialPoint)){
                if (i <= 1){
                SuperWorrior superW = new SuperWorrior(worriorNames[i],initialPoint);
                InhabitantsNames.add(superW.getWorriorName());
                superW.setName(superW.getWorriorName());
                subscribeTreasure(superW);
                Grid.worriorsGrid.put(superW, superW.getGridlocation());
                System.out.println(superW.getName() + " is a Super Worrior");
                i++;
                }
                
                else if (i <= 3){
                    NormalWorrior normalW = new NormalWorrior(worriorNames[i],initialPoint);
                    InhabitantsNames.add(normalW.getWorriorName());
                    normalW.setName(normalW.getWorriorName());
                    subscribeTreasure(normalW);
                    Grid.worriorsGrid.put(normalW, normalW.getGridlocation());
                    System.out.println(normalW.getName() + " is a Normal Worrior");
                    i++;
                }
            }
        }
    }
    
    public void creatLotusFlowers(){
        Random gen = new Random();
        int j = 1;
        while (j <= 5){
            
            int x = gen.nextInt(10) +1;
            int y = gen.nextInt(10) +1;
            GridLocation fixedLocation = new GridLocation(x,y);
            LotusFlower flower = new LotusFlower(fixedLocation);
            Grid.flowerAndKillerFishGrid.put(fixedLocation, flower);
            j++;
        }
        
    }
    
    public void creatFish(){
        Random gen = new Random();
        int k = 1;
        while(k <= 6){
            int x = gen.nextInt(10) + 1;
            int y = gen.nextInt(10) + 1;
            GridLocation fishLocation = new GridLocation(x, y);
            
            if (k <=2){
                if (Grid.InnocentFishGrid.get(fishLocation) == null){
                    InnocentFish innoF = new InnocentFish(fishLocation,"Innocent Fish"+k);
                    InhabitantsNames.add(innoF.getName());
                    Grid.InnocentFishGrid.put(fishLocation, innoF);
                    k++;
                }
            }
            else if (k <= 4){
                if (Grid.rubberFishGrid.get(fishLocation) == null){                
                    RubberFish rubF = new RubberFish(fishLocation,"Rubber Fish"+k);
                    InhabitantsNames.add(rubF.getName());
                    Grid.rubberFishGrid.put(fishLocation, rubF);
                    k++;
                }   
            } else {
                if (!Grid.flowerAndKillerFishGrid.containsKey(fishLocation)){ //this is to avoid the confusion of having Lotus Flower & Killer Fish in a same location
                    KillerFish killF = new KillerFish(fishLocation,"Killer Fish"+k);
                    InhabitantsNames.add(killF.getName());
                    Grid.flowerAndKillerFishGrid.put(fishLocation, killF);
                    k++;
                }
                
            }
        }
    }
    
    public static ArrayList availableLocations(GridLocation currentLocation){
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();
       
        GridLocation upLoc = new GridLocation(currentX, currentY+1);
        GridLocation downLoc = new GridLocation(currentX, currentY-1);
        GridLocation leftLoc = new GridLocation(currentX-1, currentY);
        GridLocation rightLoc = new GridLocation(currentX+1, currentY);
        
        GridLocation[] requiredPoints = {upLoc, downLoc,leftLoc, rightLoc};
        ArrayList<GridLocation> availablePoints = new ArrayList<>();
        
        for (GridLocation loc: requiredPoints){
            if ((0 <= loc.getX()) && (loc.getX() <= 11) && (0 <= loc.getY()) && (loc.getY() <= 11)){
                if (!Grid.worriorsGrid.containsValue(loc)){
                    availablePoints.add(loc);
                }
            }
        }
        return availablePoints;
    }
    
    public static void checkStatus(Worrior worrior, GridLocation currentLocation){
        if(Grid.worriorsGrid.isEmpty()){
            Worrior.gameOver = true;
        }
        else if (currentLocation.equals(chest.getGridLocation())){
            TreasureChest.winner = worrior;
            Worrior.gameOver = true;
            chest.notifyWorriors();
        }
        else if (Grid.flowerAndKillerFishGrid.containsKey(currentLocation)){
            SpiritDecision spirit = Grid.flowerAndKillerFishGrid.get(currentLocation);
            if (spirit instanceof LotusFlower){
                LotusFlower flower = (LotusFlower)spirit;
                if (flower.getNumOfPetals()> 0){
                    flower.makeImmoral(worrior);
                }
                if (Grid.rubberFishGrid.containsKey(currentLocation)){
                    RubberFish rubF = Grid.rubberFishGrid.get(currentLocation);
                    rubF.eatFins(worrior);
                }   
            }
            else if (spirit instanceof KillerFish){
                if (!worrior.isImmortal()){
                    KillerFish killer = (KillerFish)spirit;
                    killer.kill(worrior);
                    unsubscribeTreasure(worrior);
                }
            }
        }
        else if (Grid.rubberFishGrid.containsKey(currentLocation)){
            RubberFish rubF = Grid.rubberFishGrid.get(currentLocation);
            rubF.eatFins(worrior);
        }
    }
    
    public static GridLocation bestPoint(GridLocation currentPoint, ArrayList availablePoints){
        GridLocation suitablePoint = null;
        int currentX = currentPoint.getX();
        int currentY = currentPoint.getY();
        
        for(Object point: availablePoints){
            GridLocation availablePoint = (GridLocation)point;
            int newX = availablePoint.getX();
            int newY = availablePoint.getY();
            
            if (currentY < chest.getGridLocation().getY()){
                if (newY > currentY){
                    suitablePoint = availablePoint;
                    break;
                }      
            }
            else if (currentY > chest.getGridLocation().getY()){
                if (newY < currentY){
                    suitablePoint = availablePoint;
                    break;
                }    
            }
            else if (currentX > chest.getGridLocation().getX()){
                if (newX < currentX){
                    suitablePoint = availablePoint;
                    break;
                }      
            }
            else if (currentX < chest.getGridLocation().getX()){
                if (newX > currentX){
                    suitablePoint = availablePoint;
                    break;
                }
            }
            else if (currentX == chest.getGridLocation().getX()){
                if (currentY > chest.getGridLocation().getY()){
                    if (newY < currentY){
                        suitablePoint = availablePoint;
                        break;
                    }                       
                }
                else if(currentY < chest.getGridLocation().getY()){
                    if (newY > currentY){
                        suitablePoint = availablePoint;
                        break;
                    }
                }
            }
            else if (currentY == chest.getGridLocation().getY()){
                if (currentX > chest.getGridLocation().getX()){
                    if (newX < currentX){
                        suitablePoint = availablePoint;
                        break;
                    }                       
                }
                else if(currentX < chest.getGridLocation().getX()){
                    if (newX > currentX){
                        suitablePoint = availablePoint;
                        break;
                    }
                }               
            }
        }
        if (suitablePoint == null){
            Random gen = new Random();
            int index = gen.nextInt(availablePoints.size());
            suitablePoint = (GridLocation)availablePoints.get(index);
        }
        return suitablePoint;        
    }
    
    public static void subscribeTreasure(Worrior wo){
        chest.setTarget(wo);
    }
    
    public static void unsubscribeTreasure(Worrior wo){
        chest.removeTarget(wo);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryswapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Acer
 */
public class InventorySwapper {

    /**
     * @param args the command line arguments
     */
    private final Mill millArt;
    private final Mill millOweng;
    private final Mill millStan;
    
    public InventorySwapper(){
        millArt = MillCreator.createMillArt();
        millOweng = MillCreator.createMillOweng();
        millStan = MillCreator.createMillStan();
        ArrayList<Mill> swapMill = new ArrayList<>();
        swapMill.add(millOweng);
        swapMill.add(millStan);
//        checkSwapping(millArt, "A", swapInventory);
        checkSwapping(millArt, swapMill, "A","A", 2477);
    }
    
    public ArrayList<SugarClass> checkSwapping(Mill sourceMill, ArrayList<Mill> swapMills, String sugarSourceClass,String sugarSwapClass, int swapAmount){
        ArrayList<Sugar> sourceSugar;
        ArrayList<Sugar> swapSugar;
        ArrayList<SugarClass> foundCombinations = new ArrayList<>();
        sourceSugar = getSugarFromMill(sourceMill, sugarSourceClass);
        swapSugar = getSwapSugars(swapMills, sugarSwapClass);
        
        System.out.println("Source Inventory has " + sourceSugar.size()+ " items");
//        for(int ctr = 0; ctr < sugarList.size(); ctr++){
//            System.out.println(sugarList.get(ctr).getMill() + " " + sugarList.get(ctr).getPriority() + " " + sugarList.get(ctr).getBags());
//        }
////        Sort by Bags
//        Collections.sort(sugarList, new Comparator<Sugar>(){
//            @Override
//            public int compare(Sugar s1, Sugar s2){
//                return Double.compare(s1.getBags(), s2.getBags());
//            }
//        });
//        Collections.reverse(sugarList);
//        //Sort by Priority
        Collections.sort(sourceSugar, new Comparator<Sugar>(){
            @Override
            public int compare(Sugar s1, Sugar s2){
                //return s1.getPriority() - s2.getPriority();
                return s1.getPriority().compareTo(s2.getPriority());
            }
        });
        
//        System.out.println("Sorted by Priority");
//        for(int ctr = 0; ctr < sugarList.size(); ctr++){
//            
//            System.out.println(sugarList.get(ctr).getMill() + " " + sugarList.get(ctr).getPriority() + " " + sugarList.get(ctr).getBags());
//        }
        
  //      PrioritySplitter sortSource = new PrioritySplitter(sugarList);
  //      sortSource.split();
  
        
        CombinationFinder finder = new CombinationFinder(swapAmount,sourceSugar); //Amount to Find
        foundCombinations.add(finder.findCombination());
        
        Collections.sort(swapSugar, new Comparator<Sugar>(){
            @Override
            public int compare(Sugar s1, Sugar s2){
                return s1.getPriority().compareTo(s2.getPriority());
            }
        });
        System.out.println("");
        System.out.println("Swapping Inventory has " + swapSugar.size()+ " items");
//        System.out.println("Sorted by Priority");
//        for(int ctr = 0; ctr < swapList.size(); ctr++){
//            System.out.println(swapList.get(ctr).getMill() + " " + swapList.get(ctr).getPriority() + " " + swapList.get(ctr).getBags());
//        }
        CombinationFinder swapFinder = new CombinationFinder(swapAmount,swapSugar);
        foundCombinations.add(swapFinder.findCombination());
        return foundCombinations;
    }
        /**
     * @return the millArt
     */
    public Mill getMillArt() {
        return millArt;
    }

    /**
     * @return the millOweng
     */
    public Mill getMillOweng() {
        return millOweng;
    }

    /**
     * @return the millStan
     */
    public Mill getMillStan() {
        return millStan;
    }

    private ArrayList<Sugar> getSugarFromMill(Mill sourceMill, String sugarSourceClass) {
        ArrayList<Sugar> sugarList = new ArrayList<>();
        for(SugarClass sugarClass: sourceMill.getMillInventory()){
            if(sugarClass.getSugarClass().equals(sugarSourceClass)){
                sugarList = sugarClass.getSugarList();
                break;
            }
        }
        return sugarList;
    }

    private ArrayList<Sugar> getSwapSugars(ArrayList<Mill> swapMills, String sugarSwapClass) {
        ArrayList<Sugar> sugarList = new ArrayList<>();
        for(Mill mill: swapMills){
            for(SugarClass sugarClass: mill.getMillInventory()){
                if(sugarClass.getSugarClass().equals(sugarSwapClass)){
                    sugarList.addAll(sugarClass.getSugarList());
                    break;
                }
            }
        }
        return sugarList;
    }
}

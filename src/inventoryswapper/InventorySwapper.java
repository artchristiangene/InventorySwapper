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
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Mill millArt;
        Mill millOweng;
        Mill millStan;
        millArt = MillCreator.createMillArt();
        millOweng = MillCreator.createMillOweng();
        millStan = MillCreator.createMillStan();
        ArrayList<Sugar> swapInventory = new ArrayList<>();
        swapInventory.addAll(millOweng.getMillInventory().get(0).getSugarList());
        swapInventory.addAll(millStan.getMillInventory().get(0).getSugarList());
        checkSwapping("A",millArt.getMillInventory(), swapInventory);
    }
    
    private static void checkSwapping(String sugarType,ArrayList<SugarType> sugars, ArrayList<Sugar> swapList){
        ArrayList<Sugar> sugarList = new ArrayList<>();
        for(int ctr = 0; ctr < sugars.size(); ctr++)
        {
            if(sugars.get(ctr).getSugarName().equals(sugarType)){
                sugarList = sugars.get(ctr).getSugarList();
                break;
            }
        }
        System.out.println("Source Inventory has " + sugarList.size()+ " items");
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
        Collections.sort(sugarList, new Comparator<Sugar>(){
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
        int swapAmount = 2477;
        CombinationFinder finder = new CombinationFinder(swapAmount,sugarList); //Amount to Find
        finder.findCombination();
        
        Collections.sort(swapList, new Comparator<Sugar>(){
            @Override
            public int compare(Sugar s1, Sugar s2){
                return s1.getPriority().compareTo(s2.getPriority());
            }
        });
        System.out.println("");
        System.out.println("Swapping Inventory has " + swapList.size()+ " items");
//        System.out.println("Sorted by Priority");
//        for(int ctr = 0; ctr < swapList.size(); ctr++){
//            System.out.println(swapList.get(ctr).getMill() + " " + swapList.get(ctr).getPriority() + " " + swapList.get(ctr).getBags());
//        }
        CombinationFinder swapFinder = new CombinationFinder(swapAmount,swapList);
        swapFinder.findCombination();
    }
}

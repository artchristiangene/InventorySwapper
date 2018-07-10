/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryswapper;

import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class Mill {
    final String millName;
    private ArrayList<SugarType> millInventory = new ArrayList<>();
    
    public Mill(String name){
        this.millName = name;
    }
    
    public String getName(){
        return this.millName;
    }
    
    public void addMillInventory(SugarType sugarType){
        this.millInventory.add(sugarType);
    }

    /**
     * @return the millInventory
     */
    public ArrayList<SugarType> getMillInventory() {
        return millInventory;
    }

    /**
     * @param millInventory the millInventory to set
     */
    public void setMillInventory(ArrayList<SugarType> millInventory) {
        this.millInventory = millInventory;
    }
}

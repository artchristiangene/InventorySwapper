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
    private ArrayList<SugarClass> millInventory = new ArrayList<>();
    
    public Mill(String name){
        this.millName = name;
    }
    
    public String getName(){
        return this.millName;
    }
    
    public void addMillInventory(SugarClass sugarType){
        this.millInventory.add(sugarType);
    }

    /**
     * @return the millInventory
     */
    public ArrayList<SugarClass> getMillInventory() {
        return millInventory;
    }

    /**
     * @param millInventory the millInventory to set
     */
    public void setMillInventory(ArrayList<SugarClass> millInventory) {
        this.millInventory = millInventory;
    }
    
    @Override
    public String toString(){
        return this.millName;
    }
}

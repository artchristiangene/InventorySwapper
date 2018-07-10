/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryswapper;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class PrioritySplitter {
    private final ArrayList<Sugar> sugarList;
    private final ArrayList<SugarClass> sugarTypeSorted;
    
    public PrioritySplitter(ArrayList<Sugar> sugars){
        this.sugarList = sugars;
        sugarTypeSorted = new ArrayList<>();
        splitListing();
    }
    
    public void splitListing(){
        Date currentPriority = sugarList.get(0).getPriority();
        ArrayList<Sugar> sugarListPerPriority = new ArrayList<>();
        for (Sugar sugar : sugarList) {            
            if(sugar.getPriority() == currentPriority){
                sugarListPerPriority.add(sugar);
            }
            else{
                this.sugarTypeSorted.add(new SugarClass(sugarListPerPriority, currentPriority));
                sugarListPerPriority = new ArrayList<>();
                sugarListPerPriority.add(sugar);
            }
            currentPriority = sugar.getPriority();            
        }
        this.sugarTypeSorted.add(new SugarClass(sugarListPerPriority, currentPriority));
    }

    /**
     * @return the sugarTypeSorted
     */
    public ArrayList<SugarClass> getSugarTypeSorted() {
        return sugarTypeSorted;
    }
}

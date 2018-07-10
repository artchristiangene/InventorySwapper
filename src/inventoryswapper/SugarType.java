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
public class SugarType {
    private String sugarName;
    private final ArrayList<Sugar> sugarList;
    private Date priority;
    
    public SugarType(){
        this.sugarList = new ArrayList<>();
    }
    
    public SugarType(String name){
        this.sugarName = name;
        this.sugarList = new ArrayList<>();
    }
    
    public SugarType(ArrayList<Sugar> sugars, Date priority){
        this.sugarList = sugars;
        this.priority = priority;
    }
    
    public String getSugarName(){
        return this.sugarName;
    }

    /**
     * @return the sugarList
     */
    public ArrayList<Sugar> getSugarList() {
        return sugarList;
    }
    
    public void addSugar(Sugar sugar){
        this.sugarList.add(sugar);
//        computePriorityList();
    }
//    
//    public ArrayList<Integer> getPriorityList(){
//        return this.priorityList;
//    }
//    
//    private void computePriorityList(){
//        this.priorityList.clear();
//        for(int ctr = 0; ctr < sugarList.size();ctr++){
//            this.priorityList.add(this.sugarList.get(ctr).getPriority());
//        }
//    }

    /**
     * @return the priority
     */
    public Date getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Date priority) {
        this.priority = priority;
    }
    
   
}

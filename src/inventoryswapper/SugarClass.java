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
public class SugarClass {

    /**
     * @param sugarClass the sugarClass to set
     */
    public void setSugarClass(String sugarClass) {
        this.sugarClass = sugarClass;
    }
    private String sugarClass = "";
    private ArrayList<Sugar> sugarList;
    private Date priority;
    
    public SugarClass(){
        this.sugarList = new ArrayList<>();
    }
    
    public SugarClass(String name){
        this.sugarClass = name;
        this.sugarList = new ArrayList<>();
    }
    
    public SugarClass(ArrayList<Sugar> sugars, Date priority){
        this.sugarList = sugars;
        this.priority = priority;
    }
    
    public String getSugarClass(){
        return this.sugarClass;
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

    /**
     * @param sugarList the sugarList to set
     */
    public void setSugarList(ArrayList<Sugar> sugarList) {
        this.sugarList = sugarList;
    }
    
   
}

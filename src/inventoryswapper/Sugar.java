/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryswapper;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class Sugar {
    private Date priority;
    private int bags = 0;
    private String mill = "";
    private String sugarClass = "";
    
    public Sugar(Date priority, int bags, String millName, String sugarClass){
        this.priority = priority;
        this.bags = bags;
        this.mill = millName;
        this.sugarClass = sugarClass;
    }

    /**
     * @return the priority
     */
    public Date getPriority() {
        return priority;
    }

    /**
     * @return the bags
     */
    public int getBags() {
        return bags;
    }

    /**
     * @return the mill
     */
    public String getMill() {
        return mill;
    }

    /**
     * @param mill the mill to set
     */
    public void setMill(String mill) {
        this.mill = mill;
    }

    /**
     * @return the sugarClass
     */
    public String getSugarClass() {
        return sugarClass;
    }

    /**
     * @param sugarClass the sugarClass to set
     */
    public void setSugarClass(String sugarClass) {
        this.sugarClass = sugarClass;
    }
}

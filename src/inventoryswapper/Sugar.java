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
    
    public Sugar(Date priority, int bags, String millName){
        this.priority = priority;
        this.bags = bags;
        this.mill = millName;
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
}

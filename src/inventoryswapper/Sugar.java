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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Date priority) {
        this.priority = priority;
    }

    /**
     * @param bags the bags to set
     */
    public void setBags(int bags) {
        this.bags = bags;
    }
    private Date priority;
    private int bags = 0;
    private String mill = "";
    private String sugarClass = "";
    private int id;
    private String serialNumber;
    
    public Sugar(Date priority, int bags, String millName, String sugarClass){
        this.priority = priority;
        this.bags = bags;
        this.mill = millName;
        this.sugarClass = sugarClass;
    }
    
    public Sugar(){
        
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

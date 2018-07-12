/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryswapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class MillCreator {
    
    private String source;
    private ArrayList<Mill> millList;
    
    public MillCreator(String source){
        this.source = source;
        millList = new ArrayList<>();
        createMills();
    }
    
    private ArrayList<Mill> createMills(){    
        ArrayList<Sugar> allSugars = new ArrayList<>();
        try {
            allSugars = initializeExcelReader(source);
        } catch (IOException ex) {
            Logger.getLogger(MillCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return breakIntoMills(allSugars);   
    }
    
    private ArrayList<Sugar> initializeExcelReader(String source) throws FileNotFoundException, IOException{
        String excelFilePath = source;
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        ArrayList<Sugar> sugarList = new ArrayList<>();
        Sugar sugar;
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        int ctr = 0;
        if(iterator.hasNext()){
            iterator.next();//skip header row
        }
        int elementCtr = 0;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            sugar = new Sugar();
            ctr = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (ctr) {
                    case 0:
                        sugar.setId((int) cell.getNumericCellValue());
                        break;
                    case 1:
                        sugar.setMill(cell.getStringCellValue());
                        break;
                    case 2:
                        sugar.setSugarClass(cell.getStringCellValue());
                        break;
                    case 3:
                        sugar.setPriority(new Date(cell.getStringCellValue()));
                        break;
                    case 4:
                        BigDecimal bags = new BigDecimal(cell.getNumericCellValue());
                        bags = bags.setScale(2, RoundingMode.HALF_DOWN);
                        sugar.setBags(bags.multiply(new BigDecimal(100)).intValue());
                        break;
                    default:
                        sugar.setSerialNumber(cell.getStringCellValue());
                        break;
                }
                ctr++;  
            }
            sugarList.add(sugar);
            elementCtr++;
        }
         
        workbook.close();
        inputStream.close();
        return sugarList;
    }

    private ArrayList<Mill> breakIntoMills(ArrayList<Sugar> allSugars) {
        
        //Sort per Priority
        Collections.sort(allSugars, new Comparator<Sugar>(){
            @Override
            public int compare(Sugar s1, Sugar s2){
                //return s1.getPriority() - s2.getPriority();
                return s1.getPriority().compareTo(s2.getPriority());
            }
        });
        
        //Sort per SugarClass
        Collections.sort(allSugars, new Comparator<Sugar>(){
            @Override
            public int compare(Sugar s1, Sugar s2){
                //return s1.getPriority() - s2.getPriority();
                return s1.getSugarClass().compareTo(s2.getSugarClass());
            }
        });
        
        //Sort per Mill
        Collections.sort(allSugars, new Comparator<Sugar>(){
            @Override
            public int compare(Sugar s1, Sugar s2){
                //return s1.getPriority() - s2.getPriority();
                return s1.getMill().compareTo(s2.getMill());
            }
        });
        Mill mill = new Mill();
        SugarClass sugarClass = new SugarClass();
        ArrayList<Sugar> allSugarInMill = new ArrayList<>();
        for(Sugar sugar: allSugars){
            if(mill.getMillName().equals("")){
                mill.setMillName(sugar.getMill());
            }
            if(mill.getMillName().equals(sugar.getMill())){
                allSugarInMill.add(sugar);
            }
            else {
                mill.setMillInventory(splitIntoClasses(allSugarInMill));
                millList.add(mill);
                mill = new Mill(sugar.getMill());
                allSugarInMill = new ArrayList<>();
                allSugarInMill.add(sugar);
            }
        }
        mill.setMillInventory(splitIntoClasses(allSugarInMill));
        millList.add(mill);
        
        
        return this.millList;
    }
    
    
    private ArrayList<SugarClass> splitIntoClasses(ArrayList<Sugar> sugarList){
        ArrayList<SugarClass> splitted = new ArrayList<>();
        SugarClass sugarClass = new SugarClass();
        for(Sugar sugar: sugarList){
            if(sugarClass.getSugarClass().equals("")){
                sugarClass.setSugarClass(sugar.getSugarClass());
            }
            if(sugarClass.getSugarClass().equals(sugar.getSugarClass())){
                sugarClass.setSugarClass(sugar.getSugarClass());
                sugarClass.addSugar(sugar);
            }
            else{
                splitted.add(sugarClass);
                sugarClass = new SugarClass(sugar.getSugarClass());
                sugarClass.addSugar(sugar);
            }
        }
        splitted.add(sugarClass);
        return splitted;
    }

    /**
     * @return the millList
     */
    public ArrayList<Mill> getMillList() {
        return millList;
    }
}
    
//    Mill mill = new Mill("Art");
//        SugarClass sugarTypeA = new SugarClass("A");
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),25,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),20,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),35,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),30,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),45,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),40,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),25,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),70,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),20,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),35,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),30,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),45,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),40,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),10,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 2, 2018"),2,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 3, 2018"),5,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 3, 2018"),200,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 4, 2018"),1000,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 5, 2018"),800,mill.getName(),"A"));
//        
// 
//        mill.addMillInventory(sugarTypeA);
//        return mill;
//    }
//    
//    public static Mill createMillOweng(){
//        Mill mill = new Mill("Oweng");
//        SugarClass sugarTypeA = new SugarClass("B");
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),25,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),20,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),35,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),30,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),45,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),40,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),25,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),70,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),20,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),35,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),30,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),45,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),40,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),10,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 2, 2018"),2,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 3, 2018"),5,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 3, 2018"),200,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 4, 2018"),1000,mill.getName(),"B"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 5, 2018"),800,mill.getName(),"B"));
//        
// 
//        mill.addMillInventory(sugarTypeA);
//        return mill;
//    }
//    
//    public static Mill createMillStan(){
//        Mill mill = new Mill("Stan");
//        SugarClass sugarTypeA = new SugarClass("A");
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),25,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),20,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),35,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),30,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),45,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),40,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),25,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),70,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),20,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),35,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),30,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),45,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),40,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 1, 2018"),10,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 2, 2018"),2,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 3, 2018"),5,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 3, 2018"),200,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 4, 2018"),1000,mill.getName(),"A"));
//        sugarTypeA.addSugar(new Sugar (new Date("July 5, 2018"),800,mill.getName(),"A"));
//        
// 
//        mill.addMillInventory(sugarTypeA);
//        return mill;

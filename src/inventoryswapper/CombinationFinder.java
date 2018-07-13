/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryswapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class CombinationFinder {
    
    private final int swapAmount;
    public static final int SUGGESTIONRANGE = 200;
    //private final ArrayList<Sugar> foundCombination;
    private ArrayList<Sugar> sugars;
    private boolean[][] proglist;
    
    public CombinationFinder(int forSwapping, ArrayList<Sugar> sugars){
        this.swapAmount = forSwapping;
        this.sugars = sugars;
       // this.foundCombination = new ArrayList<>();
    }
    
    public SugarClass findCombination(){
        //int arr[] = {2,2,5,5,10,10,20,20,20,20,25,25,25,25,30,30,30,30,35,35,35,35,40,40,40,40,45,45,45,45,70,70,200,200,800,800,1000,};
        int n = sugars.size();
        proglist = findAllSubsets(sugars, n, swapAmount + SUGGESTIONRANGE);//find including suggestion range to avoid reconstruction of progList table
        SugarClass foundSugar = new SugarClass("Found");
        foundSugar.setSugarList(getOneSubset(n, sugars, swapAmount));
//        printSugarlist(foundSugar.getSugarList());
        return foundSugar;
//        printFirstCombination();
    }
    
    public SugarClass findLeewayCombination(int swapAmount){
        //int arr[] = {2,2,5,5,10,10,20,20,20,20,25,25,25,25,30,30,30,30,35,35,35,35,40,40,40,40,45,45,45,45,70,70,200,200,800,800,1000,};
        SugarClass foundSugar = new SugarClass("FoundLeeway");
        foundSugar.setSugarList(getOneSubset(sugars.size(), sugars, swapAmount));
//        printSugarlist(foundSugar.getSugarList());
        return foundSugar;
//        printFirstCombination();
    }
    
   // Prints all subsets of arr[0..n-1] with sum 0.
    private boolean[][] findAllSubsets(ArrayList<Sugar> sugarList, int n, int sum)
    {
        boolean [][]dp = new boolean[n][sum + 1];
        if (n == 0 || sum < 0)
           return dp;
      
        // Sum 0 can always be achieved with 0 elements
        
        for (int i=0; i<n; ++i)
        {
            dp[i][0] = true;  
        }
      
        // Sum arr[0] can be achieved with single element
        if (sugarList.get(0).getBags() <= sum)
           dp[0][sugarList.get(0).getBags()] = true;
      
        // Fill rest of the entries in dp[][]
        for (int i = 1; i < n; ++i)
            for (int j = 0; j < sum + 1; ++j)
                dp[i][j] = (sugarList.get(i).getBags() <= j) ? (dp[i-1][j] ||
                                           dp[i-1][j-sugarList.get(i).getBags()])
                                         : dp[i - 1][j];
        if (dp[n-1][sum] == false)
        {
//            System.out.println("There are no combinations found.");
            return dp;         
        }
      
        // Now recursively traverse dp[][] to find all
        // paths from dp[n-1][sum]
        ArrayList<Sugar> p = new ArrayList<>();
//        findSubsetRecord(sugarList, n-1, sum, p);
        return dp;
    }
    
//    // A recursive function to print all subsets with the
//    // help of dp[][]. Vector p[] stores current subset.
//    private void findSubsetRecord(ArrayList<Sugar> sugarList, int i, int sum, 
//                                         ArrayList<Sugar> p)
//    {
//        // If we reached end and sum is non-zero. We print
//        // p[] only if arr[0] is equal to sun OR dp[0][sum]
//        // is true.
//        if (i == 0 && sum != 0 && dp[0][sum])
//        {
//            p.add(sugarList.get(i));
//            addFoundCombination(p);
//            p.clear();
//            return;
//        }
//      
//        // If sum becomes 0
//        if (i == 0 && sum == 0)
//        {
//            addFoundCombination(p);
//            p.clear();
//            return;
//        }
//      
//        // If given sum can be achieved after ignoring
//        // current element.
//        if (dp[i-1][sum])
//        {
//            // Create a new vector to store path
//            ArrayList<Sugar> b = new ArrayList<>();
//            b.addAll(p);
//            findSubsetRecord(sugarList, i-1, sum, b);
//        }
//      
//        // If given sum can be achieved after considering
//        // current element.
//        if (sum >= sugarList.get(i).getBags() && dp[i-1][sum-sugarList.get(i).getBags()])
//        {
//            p.add(sugarList.get(i));
//            findSubsetRecord(sugarList, i-1, sum-sugarList.get(i).getBags(), p);
//        }
//    }
    

    
//    private void printFirstCombination(){
//        if(!foundCombinations.isEmpty()){
//            System.out.println("Found Combination");
//            for(Sugar sugar: foundCombinations.get(0).getSugarList()){
//                System.out.println("Priority " + sugar.getPriority()+ " Bags " + sugar.getBags());
//            }
//            
//        }    
//    }
    
    private ArrayList<Sugar> getOneSubset(int elements, ArrayList<Sugar> arr, int sum){
    int element = sum;
    ArrayList<Sugar> subset = new ArrayList<>();
    if(sum!=0){
        for(int check = 0; check < elements; check++){
            if(this.proglist[check][sum]){
                if(arr.get(check).getBags() == sum){
                    subset.add(arr.get(check));
                    break;
                }
                else {
                    for(int ctr = check; ctr >= 0; ctr--){
                        if(ctr == 0){
                            subset.add(arr.get(ctr));
                        }
                        else{
                            if(!this.proglist[ctr-1][element]){
                                subset.add(arr.get(ctr));
                                element = element - arr.get(ctr).getBags();
                                if(element == 0){
                                    break;
                                }
                            }
                        }

                    }
                }
                break;
            }
        }
    }

        return subset;
    }
    
//    public BigDecimal getUpperSuggestion(){
//        BigDecimal upperSuggestion = new BigDecimal(0);
//        for(int ctr1 = 0; ctr1 < sugars.size(); ctr1++){
//            for(int ctr2 = swapAmount; ctr2 <= swapAmount + SUGGESTIONRANGE; ctr2++){
//                if(this.proglist[ctr1][ctr2]){
//                    upperSuggestion = new BigDecimal(ctr2);
//                    break;
//                }
//            }
//            if(!upperSuggestion.equals(new BigDecimal(BigInteger.ZERO))){
//                break;
//            }
//        }
//        return upperSuggestion;
//    }
//    
//    public BigDecimal getLowerSuggestion(){
//        BigDecimal lowerSuggestion = new BigDecimal(0);
//        for(int ctr1 = 0; ctr1 < sugars.size(); ctr1++){
//            for(int ctr2 = swapAmount; ctr2 >= swapAmount - SUGGESTIONRANGE; ctr2--){
//                if(this.proglist[ctr1][ctr2]){
//                    lowerSuggestion = new BigDecimal(ctr2);
//                    break;
//                }
//            }
//            if(!lowerSuggestion.equals(new BigDecimal(BigInteger.ZERO))){
//                break;
//            }
//        }
//        return lowerSuggestion;
//    }
    
    public BigDecimal getUpperSuggestion(){
        BigDecimal upperSuggestion = new BigDecimal(0);
        for(int ctr2 = swapAmount; ctr2 <= swapAmount + SUGGESTIONRANGE; ctr2++){
            for(int ctr1 = 0; ctr1 < sugars.size(); ctr1++){
                if(this.proglist[ctr1][ctr2]){
                    upperSuggestion = new BigDecimal(ctr2);
                    break;
                }
            }
            if(!upperSuggestion.equals(new BigDecimal(BigInteger.ZERO))){
                break;
            }
        }
        return upperSuggestion;
    }
    
    public BigDecimal getLowerSuggestion(){
        BigDecimal lowerSuggestion = new BigDecimal(0);
        for(int ctr2 = swapAmount; ctr2 >= swapAmount - SUGGESTIONRANGE; ctr2--){
            for(int ctr1 = 0; ctr1 < sugars.size(); ctr1++){
                if(this.proglist[ctr1][ctr2]){
                    lowerSuggestion = new BigDecimal(ctr2);
                    break;
                }
            }
            if(!lowerSuggestion.equals(new BigDecimal(BigInteger.ZERO))){
                break;
            }
        }
        return lowerSuggestion;
    }

//    private void printSugarlist(ArrayList<Sugar> sugarList) {
//        Collections.reverse(sugarList);
//        DateFormat df = new SimpleDateFormat("MM/dd/yy");
//            if(!sugarList.isEmpty()){
//                System.out.println("Found Combination");
//                System.out.println("MILL\t WEEK ENDING\t TYPE\tPCS");
//                for(Sugar sugar: sugarList){
//                System.out.println(sugar.getMill() + " \t" + df.format(sugar.getPriority()) + "\t" +"B\t"+ sugar.getBags());
//            }
//        }
//        
//    }

    /**
     * @return the proglist
     */
    public boolean[][] getProglist() {
        return proglist;
    }
}

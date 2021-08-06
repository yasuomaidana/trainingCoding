import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

class Result {

    /*
     * Complete the 'maximumToys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY prices
     *  2. INTEGER k
     */
    int maximumToys = 0;
    private int maximumPrice;
    private HashMap<Integer,ArrayList<ArrayList<Integer>>> ToysList = new HashMap<>();
    private HashSet<Integer> UsedToys = new HashSet<>();
    private ArrayList<Integer> Reminders = new ArrayList<>();
    private HashSet<Integer> UsedReminders = new HashSet<>();
    Result(int maximumPrice){
        this.maximumPrice = maximumPrice;
    }
    private void newList(int toy,int reminder){
        ArrayList<ArrayList<Integer>> currentList = new ArrayList<>();
        ArrayList<Integer> returnList =new ArrayList<>();
        returnList.add(toy);
        if(ToysList.containsKey(reminder)) 
        currentList = ToysList.get(reminder);
        currentList.add(returnList);
        ToysList.put(reminder, currentList);
    }
    private ArrayList<ArrayList<Integer>> copyLists(int Key){
        ArrayList<ArrayList<Integer>> rArrayList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> baseList = ToysList.get(Key);
        for(int i=0;i<baseList.size();i++){
            ArrayList<Integer> Line = new ArrayList<>();
            ArrayList<Integer> baseLine = baseList.get(i);
            for(int j=0;j<baseLine.size();j++){
                Line.add(baseLine.get(j));
            }
            rArrayList.add(Line);
        }
        return rArrayList;
    }
    interface searchStrategy{
        int strategy(int toEvaluate,int element);
    }
    private void addReminder(int reminder){
        if(Reminders.isEmpty()){ Reminders.add(reminder);UsedReminders.add(reminder); return;}
        searchStrategy strategy =(int price,int element)->{
            if (price == element) return 0;
            if(price>element) return 1;
            return -1;
        };
        if(UsedReminders.contains(reminder)) return;
        int index = findIndex(reminder, strategy)[1];
        Reminders.add(index, reminder);
        UsedReminders.add(reminder);
    }
    private int[] findIndex(int toEvaluate,searchStrategy strategy){
        int[] minmaxIndex = new int[2];
        int min = 0;
        int max = Reminders.size()-1;
        if (strategy.strategy(toEvaluate, Reminders.get(min))==-1) return minmaxIndex;
        if (strategy.strategy(toEvaluate, Reminders.get(max))==1||
        Reminders.size()==2 && strategy.strategy(toEvaluate, Reminders.get(max))==-1){ 
            minmaxIndex[0]=min;minmaxIndex[1]=max;
            return minmaxIndex;
        }
        if(strategy.strategy(toEvaluate, Reminders.get(min))==0){
            minmaxIndex[0]=min; minmaxIndex[1]=min; return minmaxIndex;
        }
        if(strategy.strategy(toEvaluate, Reminders.get(max))==0){
            minmaxIndex[0]=max; minmaxIndex[1]=max; return minmaxIndex;
        }
        while(min!=max-1){
            int middle = (min+max)/2;
            switch (strategy.strategy(toEvaluate, Reminders.get(middle))){
                case 1:
                    min = middle;
                    break;
                case -1:
                    max = middle;
                    break;
                default:
                    minmaxIndex[0]=middle;minmaxIndex[1]=middle;
                    return minmaxIndex;
            }
        }
        minmaxIndex[0]=min;minmaxIndex[1]=max;return minmaxIndex;
    }
    private void calculateMaximumToys(int priceToy){
        searchStrategy strategy =(int price,int element)->{
            int reminder = element-price;
            if (reminder==0) return 0;
            if(reminder>0) return -1;
            return 1;
        };
        if(priceToy> Reminders.get(Reminders.size()-1)) return;
        int[] index = findIndex(priceToy, strategy);
        int start;
        if(strategy.strategy(priceToy,Reminders.get(index[0]))==1) start = index[1];
        else start = index[0];
        ArrayList<Integer> newReminders = new ArrayList<>();
        int RemSize = Reminders.size();
        //For each reminder
        for(int i = start;i<RemSize;i++){
            int reminder = Reminders.get(i);
            int destReminder = reminder-priceToy;
            ArrayList<ArrayList<Integer>> newLists = copyLists(reminder);
            //For each array of that reminder
            newLists.forEach(list->{
                list.add(priceToy);
                if(list.size()>maximumToys) maximumToys=list.size();
            });
            if(ToysList.containsKey(destReminder)){
                ArrayList<ArrayList<Integer>> rep = ToysList.get(destReminder);
                rep.addAll(newLists);
                ToysList.put(destReminder,rep);
            }else ToysList.put(destReminder,newLists);
            if(!UsedReminders.contains(destReminder)) newReminders.add(destReminder);
        }
        newReminders.forEach(nreminder->addReminder(nreminder));
    }
    public void addToy(int priceToy){
        int reminder = maximumPrice - priceToy;
        if(reminder<0||UsedToys.contains(priceToy)) return;
        if(ToysList.isEmpty()) maximumToys=1;
        if(Reminders.size()>0) calculateMaximumToys(priceToy);
        addReminder(reminder);
        newList(priceToy,reminder);
        UsedToys.add(priceToy);
    }
    public static int maximumToys(List<Integer> prices, int k) {
        Result workingObject = new Result(k);
        for(int price : prices){
            workingObject.addToy(price);
            
        }
        return workingObject.maximumToys;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
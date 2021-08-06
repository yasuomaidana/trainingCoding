import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    private ArrayList<Integer> Toys = new ArrayList<>();
    Result(int maximumPrice){
        this.maximumPrice = maximumPrice;
    }
    interface searchStrategy{
        int strategy(int toEvaluate,int element);
    }
    private void addToy(int toy){
        if(Toys.isEmpty()){ Toys.add(toy);return;}
        searchStrategy strategy =(int price,int element)->{
            if (price == element) return 0;
            if(price>element) return 1;
            return -1;
        };
        int index = findIndex(toy, strategy)[1];
        if(strategy.strategy(toy, Toys.get(Toys.size()-1))==1) Toys.add(toy);
        else Toys.add(index, toy);
    }
    private int[] findIndex(int toEvaluate,searchStrategy strategy){
        int[] minmaxIndex = new int[2];
        int min = 0;
        int max = Toys.size()-1;
        if (strategy.strategy(toEvaluate, Toys.get(min))==-1) return minmaxIndex;
        if (strategy.strategy(toEvaluate, Toys.get(max))==1||
        Toys.size()==2 && strategy.strategy(toEvaluate, Toys.get(max))==-1){ 
            minmaxIndex[0]=min;minmaxIndex[1]=max;
            return minmaxIndex;
        }
        if(strategy.strategy(toEvaluate, Toys.get(min))==0){
            minmaxIndex[0]=min; minmaxIndex[1]=min; return minmaxIndex;
        }
        if(strategy.strategy(toEvaluate, Toys.get(max))==0){
            minmaxIndex[0]=max; minmaxIndex[1]=max; return minmaxIndex;
        }
        while(min!=max-1){
            int middle = (min+max)/2;
            switch (strategy.strategy(toEvaluate, Toys.get(middle))){
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
    public void calculateMaximum(){
        while(maximumPrice>0){
            maximumPrice-=Toys.get(maximumToys);
            maximumToys++;
        }
        maximumToys--;
    }
    public static int maximumToys(List<Integer> prices, int k) {
        Result workingObject = new Result(k);
        for(int price : prices){
            workingObject.addToy(price);
        }
        workingObject.calculateMaximum();
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
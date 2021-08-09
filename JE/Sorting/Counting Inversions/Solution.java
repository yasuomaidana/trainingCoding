import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {
    /*
     * Complete the 'countInversions' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    private int[] temp;
    long moves = 0;
    Result(int size){
        temp = new int[size];
    }
    private void copyToTemp(List<Integer> src,int srcP,int desP,int q){
        if(q<=0) return;
        for(int i=0;i<q;i++){
            temp[desP] = src.get(srcP);
            desP++;
            srcP++;
        }
    }
    private void copyToArr(List<Integer> src,int srcP,int q){
        for(int i=0;i<q;i++){
            src.set(srcP, temp[srcP]);
            srcP++;
        }
    }
    private void merge(List<Integer> arr,int left,int right){
        int middle = (left+right)/2;
        int leftInd = left;
        int rightInd = middle+1;
        int tempInd = left;
        while(leftInd<=middle && rightInd<=right){
            int leftValue =arr.get(leftInd);
            int righValue =arr.get(rightInd);
            if(leftValue<=righValue){
                temp[tempInd] = leftValue;
                leftInd++;
            }
            else{
                temp[tempInd] = righValue;
                moves += (middle-leftInd)+1;
                rightInd++;
            }
            tempInd++;
        }
        copyToTemp(arr, leftInd, tempInd, middle-leftInd+1);
        copyToTemp(arr, rightInd, tempInd, right-rightInd+1);
        copyToArr(arr, left, right-left+1);

    }
    private void mergesort(List<Integer> arr, int left,int right){
        if(left>=right) return;
        int middle = (left+right)/2;
        int startRight = middle+1;
        mergesort(arr,left,middle);
        mergesort(arr,startRight,right);
        merge(arr,left,right);
    }
    private void mergesort(List<Integer> arr){
        int left = 0;
        int right = arr.size()-1;
        mergesort(arr,left,right);
    }
    public static long countInversions(List<Integer> arr) {
        Result working = new Result(arr.size());
        working.mergesort(arr);
        return working.moves;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        //BufferedReader bufferedReader = new BufferedReader(new FileReader("input02.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                long result = Result.countInversions(arr);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
} 
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
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
    public static long countInversions(List<Integer> arr) {
        long moves =0;
        int index =0;
        int lastWell = 0;
        while(index<arr.size()){
            int firstNumber = arr.get(index);
            if(lastWell==firstNumber){
                index++;
                continue;
            } 
            boolean move = false;
            for(int i=index+1;i<arr.size();i++){
                int secondNumber = arr.get(i);
                move = firstNumber>secondNumber;
                if (move){ 
                    Collections.swap(arr, index, i);
                    moves++;
                    break;
                }
            }
            if(!move){ 
                index++;
                lastWell = firstNumber;
            }
        }
        return moves;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input02.txt"));
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
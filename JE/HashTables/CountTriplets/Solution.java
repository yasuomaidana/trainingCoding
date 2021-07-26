import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r,int n) {
        HashMap<Long,Integer> powers = new HashMap<>();
        for(Long num: arr){
            Integer a=powers.putIfAbsent(num, 1);
            if(a!=null){
                powers.put(num,a+1);
            }
        }
        int triplets =0;
        for(int i=0;i<n-2;i++){
            int elements=0;
            for(int j=0;j<3;j++){
                long pow = (long) Math.pow(r, i+j);
                int power =powers.getOrDefault(pow,-1);
                if(power>0){
                    if(power>elements) elements=power;
                }
                else{
                    elements = -1;
                    break;
                }
            }
            if(elements>0) triplets+=elements;
        }
        return triplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r,n);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
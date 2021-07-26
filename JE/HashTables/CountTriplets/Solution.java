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

    static HashMap<Long,Integer> toDict(List<Long> arr){
        HashMap<Long,Integer> ret = new HashMap<>();
        for(Long number: arr){
            if(ret.putIfAbsent(number,1)!=null) ret.compute(number, (k,v)->v+1);
            
        }
        return ret;
    }
    static long countTriplets(List<Long> arr, long r) {
        long triplets = 0;
        HashMap<Long,Integer> futureMap = toDict(arr);
        HashMap<Long,Integer> pastMap = new HashMap<>();
        for(Long number:arr){
            long future = number*r;
            long past = number/r;
            int modulus = (int) (number%r);
            futureMap.compute(number,(key,value)->value-1);
            if(pastMap.containsKey(past)&&futureMap.containsKey(future)&&modulus==0){
                triplets+=pastMap.get(past)*futureMap.get(future);
            }
            if(pastMap.putIfAbsent(number, 1)!=null) 
            pastMap.compute(number, (key,value)->value+1);

        }
        return triplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
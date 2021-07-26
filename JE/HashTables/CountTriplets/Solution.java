import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Solution {

    static HashMap<Long,Long> toDict(List<Long> arr){
        HashMap<Long,Long> ret = new HashMap<>();
        for(Long number: arr){
            if(ret.getOrDefault(number,(long) -1)<0){
                ret.put(number,(long) 1);
            }
            else ret.compute(number, (k,v)->v+1);
            
        }
        return ret;
    }
    static long countTriplets(List<Long> arr, long r) {
        long triplets = 0;
        HashMap<Long,Long> futureMap = toDict(arr);
        HashMap<Long,Long> pastMap = new HashMap<>();
        for(Long number:arr){
            long future = number*r;
            long past = number/r;
            int modulus = (int) (number%r);
            futureMap.compute(number,(key,value)->value-1);
            if(pastMap.containsKey(past)&&futureMap.containsKey(future)&&modulus==0){
                triplets+=pastMap.get(past)*futureMap.get(future);
            }
            if(pastMap.getOrDefault(number,(long) -1)<0){
                pastMap.put(number,(long) 1);
            }
            else pastMap.compute(number, (k,v)->v+1);

        }
        return triplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input03.txt"));
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
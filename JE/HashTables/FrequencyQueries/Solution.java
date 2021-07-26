import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
    static HashMap<Integer,Integer> add(int x, HashMap<Integer,Integer> Map){
        Map.compute(x, (k,v)-> v==null ? 1:v+1);
        return Map;
    }
    static HashMap<Integer,Integer> delete(int y, HashMap<Integer,Integer> Map){
        Map.compute(y, (k,v)-> v==null|v<=1 ? 0:v-1);
        return Map;
    }
    static int check(int z, HashMap<Integer,Integer> Map){
        if(Map.containsValue(z)){
            System.out.println("1");
            return 1;
        }
        System.out.println("0");
        return 0;
    }
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> ret = new ArrayList<>();
        HashMap<Integer,Integer> data = new HashMap<>();
        for(List<Integer> query:queries){
            int op = query.get(0);
            int num = query.get(1);
            switch (op){
                case 1:
                data = add(num,data);
                break;
                case 2:
                data = delete(num,data);
                break;
                case 3:
                ret.add(check(num, data));
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

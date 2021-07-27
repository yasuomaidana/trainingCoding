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
    static HashMap<Integer,Integer> values = new HashMap<>();
    static HashMap<Integer,Integer> data = new HashMap<>();
    static void add(int x){
        data.compute(x, (k,v)-> v==null ? 1:v+1);
        int quantity = data.get(x);
        if(quantity-1>0) values.compute(quantity-1, (k,v)-> v-1<=0 ? 0:v-1);
        values.compute(quantity, (k,v)-> v==null ? 1:v+1);
        return;
    }
    static void delete(int y){
        data.compute(y, (k,v)-> v==null||v<=1 ? 0:v-1);
        int quantity = data.getOrDefault(y,-1);
        if (quantity<=0) return;
        values.compute(quantity+1, (k,v)-> v-1<=0 ? 0:v-1);
        if(quantity>0) values.compute(quantity, (k,v)-> v+1);
        return;
    }
    static int check(int z){
        if(values.getOrDefault(z, -1)>0){
            System.out.println("1");
            return 1;
        }
        System.out.println("0");
        return 0;
    }
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> ret = new ArrayList<>();
        
        for(List<Integer> query:queries){
            int op = query.get(0);
            int num = query.get(1);
            switch (op){
                case 1:
                add(num);
                break;
                case 2:
                delete(num);
                break;
                case 3:
                ret.add(check(num));
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

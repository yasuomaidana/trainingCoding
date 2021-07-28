import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    static HashMap<Integer,Integer> values = new HashMap<>();
    static HashMap<Integer,Integer> data = new HashMap<>();
    static int i =0;
    static void add(int x){
        data.compute(x, (k,v)-> v==null ? 1:v+1);
        // Key number quantity
        int number_quantity = data.get(x);
        // Number of key number
        Integer stored_quantity = values.get(number_quantity);
        //Update count
        if(stored_quantity==null) values.put(number_quantity,1);
        else values.put(number_quantity, stored_quantity+1);
        
        //Update previous count
        int prev_number_quantity = number_quantity-1;
        if(prev_number_quantity!= 0){
            int prev_stored_quantity = values.get(prev_number_quantity)-1;
            if(prev_stored_quantity<=0) prev_stored_quantity=0;
            values.put(prev_number_quantity, prev_stored_quantity);
        }
    }
    static void delete(int y){
        Integer number_quantity = data.get(y);
        if(number_quantity==null||number_quantity==0) return;
        data.compute(y, (k,v)-> v-1<0 ? 0:v-1);
        //Update current count
        int stored_quantity = values.get(number_quantity)-1;
        if(stored_quantity<=0) stored_quantity=0;
        values.put(number_quantity, stored_quantity);

        //Update previous quantity
        int prev_number_quantity = number_quantity-1;
        if(prev_number_quantity!= 0){
            int prev_stored_quantity = values.get(prev_number_quantity)+1;
            values.put(prev_number_quantity, prev_stored_quantity);
        }
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
            i++;
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
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input10.txt"));
        BufferedReader bufferedWriter = new BufferedReader(new FileReader("output10.txt"));

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
        String line;
        System.out.println("Comparison starts ----");
        List<Integer> real_ans = new ArrayList<>();
        while((line = bufferedWriter.readLine()) != null){
            real_ans.add(Integer.parseInt(line));
        }
        for(int i =0;i<ans.size();i++){
            int real = real_ans.get(i);
            int current = ans.get(i);
            if(real!=current) 
            System.out.println("Real :"+real+" Current:"+current+" i:"+i);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */
    public static void minimumBribes(List<Integer> q) {
        int shifts =0;
        //original_Position = or_Pos
        ArrayList<Integer> or_Pos = new ArrayList<>();
        int last_big = 1;
        or_Pos.add(last_big);
        //current_Position = cur_Pos
        int seq =1;
        for(int cur_Pos:q){
            int diff = cur_Pos-seq;
            if(diff>2){
                System.out.println("Too chaotic");
                return;}
            if(diff>0){
                while(last_big<cur_Pos){
                    last_big++;
                    or_Pos.add(last_big);
                }
            }
            else{
                if(or_Pos.size()<2&&last_big!=q.size()){
                    last_big++;
                    or_Pos.add(last_big);
                }
            }
            int deals = or_Pos.indexOf(cur_Pos);
            shifts+=deals;
            or_Pos.remove(deals);
            seq++;
        }
        System.out.println(shifts);
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/nsl-intern/Documents/ACO/JE/NewYearChaos/input02.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
} 
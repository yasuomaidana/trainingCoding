import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

class Result {

    /*
     * Complete the 'countSwaps' function below.
     *
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static void countSwaps(List<Integer> a) {
        int swaps=0;
        for(int i=0;i<a.size();i++){
            for(int j=0;j<a.size()-1;j++){
                int inJ = a.get(j);
                int inJp = a.get(j+1);
                if(inJ>inJp){
                    swaps++;
                    a.set(j,inJp);
                    a.set(j+1,inJ);
                }
            }
        }
        System.out.println(String.format("Array is sorted in %d swaps.\n"+
        "First Element: %d\n"+
        "Last Element: %d", swaps,a.get(0),a.get(a.size()-1)));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.countSwaps(a);

        bufferedReader.close();
    }
}
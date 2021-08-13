import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
        int max =0;
        HashMap<Character,Integer> frequencies = new HashMap<>();
        for(char letter:s.toCharArray()){
            if(frequencies.merge(letter, 1, (old,newV)->old+newV)>max)
            max = frequencies.get(letter);
        }
        Iterator<Character> letters = frequencies.keySet().iterator();
        HashMap<Integer,Integer> counter = new HashMap<>();
        int count=0;
        while(letters.hasNext()){
            int quantity = frequencies.get(letters.next());
            counter.merge(quantity, 1, (o,n)->o+n);
            if(counter.size()==2){
                count = counter.values().stream().map(mapper->mapper>1 ? 1:0).reduce(0, Integer::sum);
                List<Integer> diff = counter.keySet().stream().collect(Collectors.toList());
                if(Math.abs(diff.get(0)-diff.get(1))>1) count = 2;
            }
            if(counter.size()>2||count>1) return "NO";
        }
        return "YES";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
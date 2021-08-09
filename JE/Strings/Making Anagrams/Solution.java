import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Result {

    /*
     * Complete the 'makeAnagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */
    private HashMap<Character,Integer> aLetters = new HashMap<>();
    private HashMap<Character,Integer> bLetters = new HashMap<>();
    private void computeA(String a){
        for(char letter:a.toCharArray()){
            aLetters.compute(letter,(k,v)->v==null ? 1:v+1);
        }
    }
    private void computeB(String b){
        for(char letter:b.toCharArray()){
            if(aLetters.containsKey(letter)){
                aLetters.compute(letter,(k,v)->v-1);
                aLetters.remove(letter, 0);
            }else{
                bLetters.compute(letter,(k,v)->v==null ? 1:v+1);
            }
        }
    }
    private int count(HashMap<Character,Integer> letters){
        int count =0;
        for(char letter:letters.keySet()){
            count+= letters.get(letter);
        }
        return count;
    }
    private int compute(String a,String b){
        computeA(a);
        computeB(b);
        return count(aLetters)+count(bLetters);
    }
    public static int makeAnagram(String a, String b) {
        Result working = new Result();
        return working.compute(a,b);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        int res = Result.makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
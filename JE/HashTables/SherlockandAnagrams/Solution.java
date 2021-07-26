import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.IntStream;

class Result {

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    public static String sortString(String toSort){
        char[] letters = toSort.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }
    public static boolean hasRepeat(String word){
        HashSet<Character> letters = new HashSet<>();
        for(Character letter:word.toCharArray()){
            if(!letters.add(letter)) return true;
        }
        return false;
    }
    public static int sherlockAndAnagrams(String s) {
        int anagrams = 0;
        HashMap<String,Integer> anagramsMaps = new HashMap<>();
        HashSet<String> helper = new HashSet<>();
        HashSet<String> helperDup = new HashSet<>();
        for(int i =1;i<s.length();i++){
            for(int j =0;j<s.length()-i+1;j++){
                String subString = sortString(s.substring(j, j+i));                
                if(anagramsMaps.getOrDefault(subString, -1)<0 && hasRepeat(subString)){
                    if(!helperDup.add(subString)) anagramsMaps.put(subString, 1);
                    continue;
                }
                if(anagramsMaps.getOrDefault(subString, -1)>=0) 
                anagramsMaps.put(subString,anagramsMaps.get(subString)+1);
                if(!helper.add(subString)&&anagramsMaps.getOrDefault(subString,-1)==-1) 
                anagramsMaps.put(subString,1);
            }
        }
        for(String key:anagramsMaps.keySet()){
            int n=anagramsMaps.get(key);
            anagrams+=n*(n+1)/2;
        }
        return anagrams;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
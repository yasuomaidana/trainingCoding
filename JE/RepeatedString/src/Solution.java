import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

    /*
     * Complete the 'repeatedString' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. LONG_INTEGER n
     */

    public static long repeatedString(String s, long n) {
        long complete,remainder,size;
        long remainder_sum = 0;
        long complete_sum = 0;
        size = s.length();
        remainder = n%size;
        complete = (n-remainder)/size;
        int ind = 0;
        char firstLetter = 'a';
        for(char letter:s.toCharArray()){
            if(letter == firstLetter){
                if (ind<remainder) remainder_sum+=1;
                complete_sum+=1;
            }
            if (ind>=n-1) break;
            ind+=1;
        }
        return complete_sum*complete+remainder_sum;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String s = bufferedReader.readLine();

        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = Result.repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
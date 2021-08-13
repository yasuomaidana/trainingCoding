import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    long quantity =0;
    private HashSet<String> good = new HashSet<>();
    private HashSet<String> bad = new HashSet<>();
    private boolean validString(String s){
        int size = s.length();
        if(size==1) return true;
        Stack<Character> toCheck = new Stack<>();
        int middle = s.length()/2;
        char[] letters = s.toCharArray();
        for(int i=0;i<middle;i++){
            toCheck.add(letters[i]);
        }
        if(size%2!=0) middle++;
        for(int i=middle;i<size;i++){
            if(letters[i]!=toCheck.pop()) return false;
        }
        return true;
    }
    public void checkString(String s){
        if(good.contains(s)){quantity+=1;return;}
        if(bad.contains(s)) return;
        if(validString(s)){good.add(s);quantity+=1;}
        else bad.add(s);
    }
    static long substrCount(int n, String s) {
        int len = s.length();
        Solution working = new Solution();
        for(int i =0;i<len;i++){
            for(int j=i;j<len;j++){
                String s2 = s.substring(i, j+1);
                working.checkString(s2);
            }
        }
        return working.quantity;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
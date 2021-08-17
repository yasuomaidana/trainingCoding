import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    private int i,size;
    private char[] letters;
    private long count;
    Solution(int n,String s){
        letters=s.toCharArray();
        size = n;
        i=0;
        count=0;
    }
    
    private void checkConsecutives(){
        int j=i+1;
        long equalLetters=1;
        while(j<size && letters[i]==letters[j]){
            j++;
            equalLetters++;
        }
        count += (equalLetters*(equalLetters+1))/2;
        i=j;
    }
    private boolean inLimits(int Upper,int Lower){
        return (Upper<size) && (Lower>=0);
    }
    private boolean equalLetters(int Upper,int Lower){
        return letters[Upper]==letters[Lower];
    }
    private boolean nonConsecutives(int other){
        return !equalLetters(i, other);
    }
    private void checkBox(){
        int middleSize = 1;
        int upperLimit = i+middleSize;
        int lowerLimit = i-middleSize;
        long equals =0;
        while(inLimits(upperLimit, lowerLimit)&&
        equalLetters(upperLimit, lowerLimit)&&
        nonConsecutives(i-1)){
            middleSize++;
            upperLimit = i+middleSize;
            lowerLimit = i-middleSize;
            equals++;
        }
        count+=equals-1;
    }
    private long countCases(){
        char l=' ';
        while(i<size){
            l = letters[i];
            checkConsecutives();
            if(i<size)
            l=letters[i];
            checkBox();
        }
        count --;
        return count;
    }
    static long substrCount(int n, String s) {    
        Solution working = new Solution(n,s);
        return working.countCases();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input02.txt")));
        //Scanner scanner = new Scanner(System.in);
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
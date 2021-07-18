import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine().trim());
        solver(N);
        bufferedReader.close();
    }
    public static void solver(int n){
        if(n%2!=0) {System.out.println("Weird");return;}
        if(n>=2 && n<=5 || n>20) System.out.println("Not Weird");
        if(n>=6 && n<=20) System.out.println("Weird");
    }
}

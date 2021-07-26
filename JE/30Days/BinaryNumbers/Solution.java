import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        int quotient =n;
        int reminder = 0;
        int consecutives = 0;
        int max_consecutives = 0;
        while(quotient>0){
            reminder = quotient%2;
            quotient/=2;
            if(reminder==1) consecutives+=1;
            else consecutives=0;
            if(consecutives>max_consecutives) max_consecutives=consecutives;
        }
        System.out.println(max_consecutives);
        bufferedReader.close();
    }
}

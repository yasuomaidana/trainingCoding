import java.io.*;

public class Solution {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(reader.readLine());
            for(int i=0;i<n;i++){
                String word = reader.readLine();
                String oddWord="";
                String pairWord="";
                for(int j=0;j<word.length();j++){
                    if(j%2==0) pairWord+=word.charAt(j);
                    else oddWord+=word.charAt(j);
                }
                System.out.println(pairWord+" "+oddWord);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
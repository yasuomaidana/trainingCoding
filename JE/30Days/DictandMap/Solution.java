import java.util.HashMap;
import java.util.Scanner;

class Solution{
    public static void main(String []argh){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        HashMap<String,Integer> Dictionary = new HashMap<>();
        for(int i = 0; i < n; i++){
            String name = in.next();
            int phone = in.nextInt();
            Dictionary.put(name,phone);
        }
        while(in.hasNext()){
            String s = in.next();
            if(Dictionary.get(s)!=null) System.out.println(s+"="+Dictionary.get(s));
            else System.out.println("Not found");
        }
        in.close();
    }
}
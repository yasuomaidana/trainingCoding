import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int movements=0;
        for (int i=0;i<arr.length-1;i++){
            int reach = i+1;
            if(reach!=arr[i]){
                movements++;
                int j=reach;
                int moved = arr[j];
                while(moved!=reach){
                    j++;
                    moved=arr[j];
                }
                arr[j] = arr[i];
                arr[i] = moved;
            }
        }
        return movements;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Result.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
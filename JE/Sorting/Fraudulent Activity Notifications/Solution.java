import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

class Data{
    int[] trailing;
    int oldestInd;
    int newestInd;
    private void add(int expenditure,int max){
        int low_ind = 0;
        int max_ind = max;
        if(trailing[max_ind]==0) {
            trailing[max_ind]=expenditure;
            return;
        }
        while(max_ind-1!=low_ind){
            int low_Val = trailing[low_ind];
            if(expenditure==low_Val) break;
            if(expenditure>low_Val){
                int new_low_ind=(low_ind+max_ind)/2;
                if(expenditure>trailing[new_low_ind]) max_ind=new_low_ind;
                else low_ind = new_low_ind;
            }
        }
        int max_Val = trailing[max_ind];
        if(expenditure>max_Val) add(max_Val,max_ind);
    }
    public void add(int expenditure){
        int max_ind = trailing.length-1;
        add(expenditure,max_ind);
    }
    Data(int d){
        trailing = new int[d];
    }
}
class Result {

    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */
    public static int activityNotifications(List<Integer> expenditure, int d) {
        Data working = new Data(d);
        working.add(20);
        working.add(30);
        working.add(10);
        return 0;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
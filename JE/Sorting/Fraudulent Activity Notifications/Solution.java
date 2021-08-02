import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;
class Result {

    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */
    ArrayList<Integer>  trailing = new ArrayList<>();;
    int fully;
    int warnings=0;
    Queue<Integer> history= new LinkedList<>();;
    Result(int d){
        fully=d-1;
    }
    private int getMedian(){
        ArrayList<Integer> working = trailing;
        int l = working.size();
        int median =0;
        int middle =l/2;
        if(l%2==0){
            median = working.get(middle)+working.get(middle+1);
        }
        else median = working.get(middle)*2;
        return median;
    }
    public void add(int expenditure){
        if(fully>-1){
            trailing.add(expenditure);
            history.add(expenditure);
            fully--;
            if(fully==-1) Collections.sort(trailing);
        }else{
            warnings+= expenditure>=getMedian() ? 1:0;
            history.add(expenditure);
            int indToRemove = trailing.indexOf(history.poll());
            trailing.remove(indToRemove);
            int last = trailing.size()-1;
            if(trailing.get(last)<expenditure){
                trailing.add(expenditure);
                return;
            }
            for(int i=0;i<last;i++)
            {
                if(trailing.get(i)>expenditure){
                    trailing.add(i, expenditure);
                }
            }
        }
        
    }
    public static int activityNotifications(List<Integer> expenditure, int d) {
        Result working = new Result(d);
        for(int ex:expenditure){
            working.add(ex);
        }
        return working.warnings;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        Integer.parseInt(firstMultipleInput[0]);

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
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Integer,Integer> ExpendituresCounter = new HashMap<>();
    private Queue<Integer> ExpendituresHistory = new LinkedList<>();
    private ArrayList<Integer> AliveExpenditures = new ArrayList<>();
    private boolean odd;
    private int ind1,ind2,trailingDays,notifications;
    Result(int size){
        odd = size%2==0;
        if(odd) ind1 = size/2;
        else ind1=size/2+1;
        ind2 = ind1+1;
        trailingDays = size;
        notifications = 0;
    }
    private int findAliveIndex(int expenditure){
        int windowMax = AliveExpenditures.size()-1;
        int windowMin = 0;
        while(windowMax!=windowMin-1){
            if(AliveExpenditures.get(windowMax)==expenditure) return windowMax;
            if(AliveExpenditures.get(windowMin)==expenditure) return windowMin;
            int inter = (windowMax+windowMin)/2;
            int interValue = AliveExpenditures.get(inter);
            if(interValue==expenditure) return inter;
            if(expenditure>interValue){
                windowMin=inter;
            }else{
                windowMax=inter;
            }
        }
        return -1;
    }
    private void MoveWindow(int expenditure){
        ExpendituresHistory.add(expenditure);
        int toRemove = ExpendituresHistory.poll();
        if(ExpendituresCounter.containsKey(expenditure)) ExpendituresCounter.compute(expenditure,(k,v)->v+1);
        else addNew(expenditure);
        ExpendituresCounter.compute(toRemove,(k,v)->v-1);
        if(ExpendituresCounter.get(toRemove)<=0){
            int indToRemove = findAliveIndex(toRemove);
            ExpendituresCounter.remove(toRemove);
            AliveExpenditures.remove(indToRemove);
        }
    }
    private void addByMiddle(int expenditure){
        int windowMax = AliveExpenditures.size()-1;
        int windowMin = 0;
        while(windowMax!=windowMin+1){
            int inter = (windowMax+windowMin)/2;
            if(expenditure>AliveExpenditures.get(inter)){
                windowMin=inter;
            }else{
                windowMax=inter;
            }
        }
        AliveExpenditures.add(windowMax, expenditure);
        ExpendituresCounter.put(expenditure,1);
    }
    private void addNew(int expenditure){
        int windowMax = AliveExpenditures.size()-1;
        int windowMin = 0;
        if(expenditure>AliveExpenditures.get(windowMax)){
            ExpendituresCounter.put(expenditure,1);
            AliveExpenditures.add(expenditure);
            return;
        }
        if(expenditure<AliveExpenditures.get(windowMin)){
            ExpendituresCounter.put(expenditure,1);
            AliveExpenditures.add(0, expenditure);
            return;
        }
        addByMiddle(expenditure);
    }
    private void firstsAdding(int expenditure){
        trailingDays--;
        ExpendituresHistory.add(expenditure);
        if(AliveExpenditures.isEmpty()){
            ExpendituresCounter.put(expenditure,1);
            AliveExpenditures.add(expenditure);
            return;
        }
        if(ExpendituresCounter.containsKey(expenditure)){
            ExpendituresCounter.compute(expenditure,(k,v)->v+1);
            return;
        }
        addNew(expenditure);
    }
    private void calculateNotification(int expenditure){
        int helper=0;
        int aliveInd=0;
        int number = AliveExpenditures.get(aliveInd);
        helper+=ExpendituresCounter.get(number);
        while(helper<ind1){
            aliveInd++;
            number = AliveExpenditures.get(aliveInd);
            helper+=ExpendituresCounter.get(number);
        }
        if(odd && ind2>helper) number+=AliveExpenditures.get(aliveInd+1);
        else number*=2;
        
        if(expenditure>=number) notifications++;
    }
    public void add(int expenditure){
        if(trailingDays>0) firstsAdding(expenditure);
        else{
            calculateNotification(expenditure);
            MoveWindow(expenditure);
        }
    }
    public int getNotifications(){
        return notifications;
    }
    public static int activityNotifications(List<Integer> expenditure, int d) {
        Result workingObject = new Result(d);
        for(int ex:expenditure){
            workingObject.add(ex);
        }
        return workingObject.notifications;
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
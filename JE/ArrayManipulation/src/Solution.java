import static java.util.stream.Collectors.toList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

    /*
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */
    static long max;
    //Lower limit
    static int ll =(int) 1e10;
    //Upper limit
    static int ul=0;
    //Limits hashmap
    static HashMap<List<Integer>,Long> sectionsSum;
    static boolean notUsed = true;

    public static List<Integer> limits(int a,int b){
        List<Integer> limitList = new ArrayList<>();
        limitList.add(a);
        limitList.add(b);
        return limitList;
    }
    public static long check_max(long s){
        if(s>max) return s;
        else return max;
    }
    public static HashMap<List<Integer>,Long> fillMap (HashMap<List<Integer>,Long> map,int ll,int ul,Long s){
        if(ll>ul) return map;
        map.put(limits(ll, ul), s);
        return map;
    }
    public static HashMap<List<Integer>,Long> fillMap (HashMap<List<Integer>,Long> map,int ll,int ul,int s){
        return fillMap(map, ll, ul, (long) s);
    }
    public static HashMap<List<Integer>,Long> fillFromLeft (HashMap<List<Integer>,Long> sectionsSum,int qll,int qul,long qs,List<Integer> key){
        int s_ll = key.get(0);
        int s_ul = key.get(1);
        long s_s = sectionsSum.get(key);
        if(s_ll>qul) return sectionsSum;
        sectionsSum.remove(key);
        if(qul+1<=s_ul){
            fillMap(sectionsSum, qul+1, s_ul, s_s);
            notUsed = false;
        }
        if(qll<=s_ll){
            fillMap(sectionsSum, qll, s_ll-1, qs);
            fillMap(sectionsSum, s_ll, qul, qs+s_s);
            max = check_max(qs+s_s);
            notUsed = false;
        }
        if(qll>s_ll){
            fillMap(sectionsSum, s_ll, qll-1, s_s);
            if(s_ul>qll) s_ul=qul;
            fillMap(sectionsSum, qll, s_ul, s_s);
            fillFromLeft(sectionsSum, qll, qul, qs, limits(qll, s_ul));
            notUsed = false;
        }
        return sectionsSum;
    }
    public static boolean inLimits(int qul, int qll,int s){
        //If range outside of lists
        if(qul<ll){
            ll=qll;
            fillMap(sectionsSum, qll, qul, s);
            max = check_max(s);
            return false;
        }
        if(ul<qll){
            ul=qul;
            fillMap(sectionsSum, qll, qul, s);
            max = check_max(s);
            return false;
        }
        return true;
    }
    public static List<Integer> edgeLimits(List<Integer> query){
        List<Integer> edgLimits = new ArrayList<>();
        edgLimits.add(query.get(0));
        edgLimits.add(query.get(1));
        //Query Lower Limit
        int qll = query.get(0);
        //Query Upper limit
        int qul = query.get(1);
        // Summand
        int s = query.get(2);
        //Initialize hashmap
        int continue_=0;
        if(sectionsSum.isEmpty()){
            ll=qll;
            ul=qul;
            fillMap(sectionsSum, qll, qul, s);
            max = s;
            continue_=-1;
        }
        if(!inLimits(qul, qll, s)) continue_=-1;
        if(qll<ll) ll=qll;
        if(qul>ul) ul = qul;
        edgLimits.add(continue_);
        return edgLimits;
    }
    public static List<List<Integer>> searchInSections(List<Integer> limits,int s){
        int qll = limits.get(0);
        int qul = limits.get(1);
        List<List<Integer>> sectionToCheck = new ArrayList<>();
        if(!inLimits(qul, qll, s)){
            return sectionToCheck;
        }
        for(Entry<List<Integer>, Long> key:sectionsSum.entrySet()){
            int s_ll = key.getKey().get(0);
            int s_ul = key.getKey().get(1);
            if(qul<=s_ul){
                fillFromLeft(sectionsSum, qll, qul, s, key.getKey());
            }
            else if(qll>s_ll && qll<=s_ul){
                fillFromLeft(sectionsSum, qll, s_ul, s, key.getKey());
                notUsed = true;
                qll = s_ul+1;
            }
            else if(qll<s_ll && qul>s_ul){
                fillFromLeft(sectionsSum, s_ll, s_ul, s, key.getKey());
                sectionToCheck.add(limits(qll, s_ll-1));
                sectionToCheck.add(limits(s_ul+1, qul));
                return sectionToCheck;
            }
        }
        if(notUsed) fillMap(sectionsSum, qll, qul, s);
        return sectionToCheck;
    }
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        sectionsSum =  new HashMap<>();
        Stack<List<Integer>> toSearch = new Stack<>();
        //HashMap<List<Integer>, Long> lookSections = sectionsSum;
        edgeLimits(queries.get(0));
        for(int i =1;i<queries.size();i++){
            List<Integer> query = queries.get(i);
            int s = query.get(2);
            query.remove(2);
            toSearch.add(query);
            //lookSections = sectionsSum;
            while(!toSearch.empty()){
                List<List<Integer>> newSections = searchInSections(toSearch.pop(), s);
                newSections.forEach((section)->toSearch.add(section));
                //lookSections = sectionsSum;
            }
        }
        return max;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
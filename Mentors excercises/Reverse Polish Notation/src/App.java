import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;
public class App {
    public static void main(String[] args) throws Exception {
        String[] data = {"2", "1", "+", "3", "*"};
        solver working = new solver(data);
        working.showEq();
        String[] data2 = {"4", "13", "5", "/", "+"};
        working.refil(data2);
        working.showEq();
        String[] data3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        working.refil(data3);
        working.showEq();
    }
}

class solver extends stack_Values{
    public solver(String[] input){
        super(input);
    }
    public int solve(){
        if(this.isNum()){
            return this.num;
        }
        String s = this.values.pop();
        int a = solve();
        int b = solve();
        switch(s){
            case "*":
                return a*b;
            case "/":
                return b/a;
            case "+":
                return a+b;
            case "-":
                return b-a;
        }
        return 0;
    }
    public Map<String,Integer> completeSolve(){
        Map<String, Integer> solution = new HashMap<String, Integer>();
        if(this.isNum()){
            solution.put(Integer.toString(this.num), this.num);
            return solution;
        }
        String s = this.values.pop();
        Map<String, Integer> a = completeSolve();
        Map<String, Integer> b = completeSolve();
        String ap= a.keySet().stream().findFirst().get();
        String bp= b.keySet().stream().findFirst().get();
        int ai = a.get(ap);
        int bi = b.get(bp);
        switch(s){
            case "*":
                solution.put("("+bp+"*"+ap+")",bi*ai);
                break;
            case "/":
                solution.put("("+bp+"/"+ap+")",bi/ai);
                break;
            case "+":
                solution.put("("+bp+"+"+ap+")",bi+ai);
                break;
            case "-":
            solution.put("("+bp+"-"+ap+")",bi-ai);
                break;
        }
        return solution;
    }
    public void showEq(){
        Map<String,Integer> res = completeSolve();
        String operation= res.keySet().stream().findFirst().get();
        System.out.println(operation+"="+res.get(operation));
    }
}
class stack_Values{
    Stack<String> values;
    int num;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    public stack_Values(String[] input){
        this.values = new Stack<String>();
        for(String val : input){this.values.push(val);}
    }
    public void refil(String[] input){
        this.values = new Stack<String>();
        for(String val : input){this.values.push(val);}
    }
    public boolean isNum(){
        String s =this.values.peek();
        boolean r =this.pattern.matcher(s).matches();
        if(r){
            this.num = Integer.parseInt(s);
            this.values.pop();
        }
        return r;
    }
}
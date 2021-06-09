import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;
public class App {
/*
// Reverse Polish ’Notation is postfix notation which in terms of mathematical notion signifies operators following operands. Let’s take a problem statement to implement RPN
// Valid operator
// +, -, *, /.
// EXAMPLES
//Input: ["2", "1", "+", "3", "*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9
//
//Input: ["4", "13", "5", "/", "+"]
//Output: 6
//Explanation: (4 + (13 / 5)) = 6
//
//Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
//Output: 22
//func rpn(_ input: [String]) -> Int {
//}*/
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
        if(s=="*"){
            return a*b;
        }
        if(s=="/"){
            return b/a;
        }
        if(s=="+"){
            return a+b;
        }
        if(s=="-"){
            return b-a;
        }
        return 0;
    }
    public Map<String,Integer> completeSolve(){
        Map<String, Integer> solution = new HashMap<String, Integer>();
        if(this.isNum()){
            solution.put("n", this.num);
            solution.put(Integer.toString(this.num), 0);
            return solution;
        }
        String s = this.values.pop();
        Map<String, Integer> a = completeSolve();
        Map<String, Integer> b = completeSolve();
        int ai = a.get("n");
        int bi = b.get("n");
        String ap= a.keySet().stream().filter(k->k!="n").findFirst().get();
        String bp= b.keySet().stream().filter(k->k!="n").findFirst().get();
        if(s=="*"){
            solution.put("n", bi*ai);
            solution.put("("+bp+"*"+ap+")",0);
            return solution;
        }
        if(s=="/"){
            solution.put("n", bi/ai);
            solution.put("("+bp+"/"+ap+")",0);
            return solution;
        }
        if(s=="+"){
            solution.put("n", bi+ai);
            solution.put("("+bp+"+"+ap+")",0);
            return solution;
        }
        if(s=="-"){
            solution.put("n", bi-ai);
            solution.put("("+bp+"-"+ap+")",0);
            return solution;
        }
        return solution;
    }
    public void showEq(){
        Map<String,Integer> res = completeSolve();
        // using for-each loop for iteration over Map.entrySet()
        String operation= res.keySet().stream().filter(k->k!="n").findFirst().get();
        System.out.println(operation+"="+res.get("n"));
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
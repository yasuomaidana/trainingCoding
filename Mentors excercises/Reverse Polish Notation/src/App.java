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
        String[] data = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};//{"2", "1", "+", "3", "*"};
        solver working = new solver(data);
        //working.solve();
        System.out.println(working.solve());
        /*String[] data2= {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        working.refil(data2);
        System.out.println(working.solve());*/
    }
}
class solver extends stack_Values{
    public solver(String[] input){
        super(input);
    }
    public int solve(){
        int sol=0;
        if(this.isNum()){
            return this.num;
        }
        String s = this.values.pop();
        if(s=="*"){
            int a = solve();
            int b = solve();
            return a*b;
        }
        if(s=="/"){
            int a =solve();
            int b =solve();
            return b/a;
        }
        if(s=="+"){
            int a =solve();
            int b = solve();
            return a+b;
        }
        if(s=="-"){
            int a =solve();
            int b = solve();
            return b-a;
        }
        return sol;
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
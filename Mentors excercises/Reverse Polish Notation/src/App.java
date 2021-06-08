import java.util.Stack;  
public class App {
/*
// Reverse Polish ’Notation is postfix notation which in terms of mathematical notion signifies operators following operands. Let’s take a problem statement to implement RPN
// Valid operator
// +, -, *, /.
// EXAMPLES
//Input: [“2”, “1", “+”, “3", “*”]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9
//
//Input: [“4", “13”, “5", “/”, “+”]
//Output: 6
//Explanation: (4 + (13 / 5)) = 6
//
//Input: [“10”, “6", “9”, “3", “+”, “-11", “*”, “/”, “*”, “17", “+”, “5", “+”]
//Output: 22
//func rpn(_ input: [String]) -> Int {
//}*/
    public static void main(String[] args) throws Exception {
        String[] data = {"2", "1", "+", "3", "*"};
        solver working = new solver(data);
        System.out.println("Hello, World!");
        System.out.print(working.values.peek());
    }
}
class solver extends stack_Values{
    public solver(String[] input){
        super(input);
    }
}
class stack_Values{
    Stack<String> values;
    public stack_Values(String[] input){
        this.values = new Stack<String>();
        for(String val : input){
            this.values.push(val);
            System.out.println(val);
        }
    }
}
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//Check if the strings are balanced
public class Balanced_Stack {
    public Balanced_Stack() {
        chainStack chainTester = new chainStack();
        System.out.println("Balanced");
        chainTester.test("{[()]}");
        chainTester.test("{}()[{}]");
        chainTester.test("[{[()]}]");
        System.out.println();
        System.out.println("Unbalanced");
        chainTester.test("[({)}]");
        chainTester.test("({[}])");
        chainTester.test("()}[]");
    }
}
class chainStack{
    //Stack used to store the chain characters
    private Stack<Character> chain = new Stack<>();
    //Symbols used to now if it is opener
    private List<Character> Opener = Arrays.asList('{','[','(');
    //Dictionary used to map the closer character
    private HashMap<Character,Character> dict = new HashMap<>();
    public chainStack(){
        this.dict.put('}', '{');
        this.dict.put(']', '[');
        this.dict.put(')', '(');
    }
    public void test(String chainToTest){
        prepareStack(chainToTest);
        String message = chainToTest+" is : ";

        char[] chars = chainToTest.toCharArray();
        this.chain.push(chars[0]);
        
        if(doTest(chars)) System.out.println(message+"balanced");
        else System.out.println(message+"not balanced");
    }
    private void prepareStack(String inputChain){
        if(!this.chain.empty()){this.chain.clear();}
        this.chain.setSize(inputChain.length());
    }
    private boolean doTest(char[] chars){
        for(int i=1;i<chars.length;i++){
            if(!addOrPop(chars[i])) return false;
        }
        return true;
    }
    private boolean addOrPop(Character s){
        if (this.Opener.indexOf(s)>=0){
            this.chain.push(s);
            return true;
        }
        else{
            return this.chain.pop()==this.dict.get(s);
        }
    }
}
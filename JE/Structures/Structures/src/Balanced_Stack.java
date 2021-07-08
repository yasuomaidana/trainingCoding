import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//Check if the strings are balanced
public class Balanced_Stack {
    private chainStack chainTester;
    public Balanced_Stack() {
        this.chainTester = new chainStack();
        System.out.println("Balanced");
        this.chainTester.test("{[()]}");
        this.chainTester.test("{}()[{}]");
        this.chainTester.test("[{[()]}]");
        System.out.println("Unbalanced");
        this.chainTester.test("[({)}]");
        this.chainTester.test("({[}])");
        this.chainTester.test("()}[]");
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
        if(!this.chain.empty()){this.chain.clear();}
        this.chain.setSize(chainToTest.length());

        char[] chars = chainToTest.toCharArray();
        this.chain.push(chars[0]);
        
        boolean success = true;
        String message = chainToTest+" is : ";
        for(int i=1;i<chars.length;i++){
            success = addOrPop(chars[i]);
            if(!success){break;}
        }
        //if(!this.chain.empty()) success=false;
        if(success){System.out.println(message+"balanced");}
        else{System.out.println(message+"not balanced");}
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
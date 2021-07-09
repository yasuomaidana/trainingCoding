package excercises;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import manual_abstracts_ds.ManualStack;

//Check if the strings are balanced
public class Balanced_Stack {
    private chainStack chainTester;
    private manual_chainStack manual_chainTester;
    public Balanced_Stack() {
        this.chainTester = new chainStack();
        this.manual_chainTester = new manual_chainStack();
    }
    public void show(){
        System.out.println("Balanced");
        this.chainTester.result("{[()]}");
        this.chainTester.result("{}()[{}]");
        this.chainTester.result("[{[()]}]");
        System.out.println();
        System.out.println("Unbalanced");
        this.chainTester.result("[({)}]");
        this.chainTester.result("({[}])");
        this.chainTester.result("()}[]");
    }
    public boolean compare(String toCompare){
        return this.manual_chainTester.doTest(toCompare) == this .chainTester.doTest(toCompare);
    }
}
abstract class chainOperations{
    //Symbols used to now if it is opener
    protected List<Character> Opener = Arrays.asList('{','[','(');
    //Dictionary used to map the closer character
    protected HashMap<Character,Character> dict = new HashMap<>();
    public chainOperations(){
        this.dict.put('}', '{');
        this.dict.put(']', '[');
        this.dict.put(')', '(');
    }
    abstract boolean addOrPop(Character s);
    abstract char[] prepareStack(String inputChain);
    abstract boolean doTest(String chainToTest);

}
class chainStack extends chainOperations{
    //Stack used to store the chain characters
    private Stack<Character> chain = new Stack<>();
    
    public void result(String chainToTest){
        String message = chainToTest+" is : ";
        if(doTest(chainToTest)) System.out.println(message+"balanced");
        else System.out.println(message+"not balanced");
    }
    
    protected char[] prepareStack(String inputChain){
        if(!this.chain.empty()){this.chain.clear();}
        this.chain.setSize(inputChain.length());
        this.chain.push(inputChain.toCharArray()[0]);
        return inputChain.toCharArray();
    }
    
    public boolean doTest(String chainToTest){
        char[] chars = prepareStack(chainToTest);
        for(int i=1;i<chars.length;i++){
            if(!addOrPop(chars[i])) return false;
        }
        return true;
    }
    protected boolean addOrPop(Character s){
        if (this.Opener.indexOf(s)>=0){
            this.chain.push(s);
            return true;
        }
        else{
            return this.chain.pop()==this.dict.get(s);
        }
    }
}

class manual_chainStack extends chainOperations{
    //Stack used to store the chain characters
    private ManualStack<Character> chain;
    public manual_chainStack(){
        this.dict.put('}', '{');
        this.dict.put(']', '[');
        this.dict.put(')', '(');
        this.chain = new ManualStack<>();
    }
    
    protected char[] prepareStack(String inputChain){
        if(!this.chain.empty()){this.chain.clear();}
        this.chain.push(inputChain.toCharArray()[0]);
        return inputChain.toCharArray();
    }
    protected boolean doTest(String chainToTest){
        char[] chars = prepareStack(chainToTest);
        for(int i=1;i<chars.length;i++){
            if(!addOrPop(chars[i])) return false;
        }
        return true;
    }
    protected boolean addOrPop(Character s){
        if (this.Opener.indexOf(s)>=0){
            this.chain.push(s);
            return true;
        }
        else{
            return this.chain.pop()==this.dict.get(s);
        }
    }
}
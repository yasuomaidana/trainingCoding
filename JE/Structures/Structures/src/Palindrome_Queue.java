import java.util.LinkedList;
import java.util.Queue;

public class Palindrome_Queue {
    public Palindrome_Queue(){
        middleWordQueue middleWord = new middleWordQueue();
        System.out.println("Palyndromes");
        middleWord.test("racecar");
        middleWord.test("tot");
        middleWord.test("madam");
        middleWord.test("t");
        middleWord.test("tt");
        System.out.println();
        System.out.println("Not palyndromes");
        middleWord.test("race");
        middleWord.test("tote");
        middleWord.test("madams");
        middleWord.test("to");
    }
}
class middleWordQueue{
    private Queue<Character> middleWord = new LinkedList<Character>();
    public void test(String word){
        fill(word);
        String message = word+" is ";
        if(doTest(word)) System.out.println(message+"palyndrome");
        else System.out.println(message+"not palyndrome");
    }
    private boolean doTest(String word){
        char[] chars = word.toCharArray();
        int length = chars.length;
        for(int i=length-1;i>=length/2;i--){
            if(middleWord.poll()!=chars[i]) return false;
        }
        return true;
    }
    private void fill(String word){
        if(!middleWord.isEmpty()) middleWord.clear();
        char[] chars = word.toCharArray();
        for(int i=0;i<=chars.length/2;i++){
            middleWord.add(chars[i]);
        }
    }
}
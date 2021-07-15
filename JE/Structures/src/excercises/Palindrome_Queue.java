package excercises;
import java.util.LinkedList;
import java.util.Queue;

import manual_abstracts_ds.ManualQueue;
public class Palindrome_Queue {
    private middleWordQueue middleWord;
    private manual_middleWordQueue manual_middleWord;
    public Palindrome_Queue(){
        middleWord = new middleWordQueue();
        manual_middleWord = new manual_middleWordQueue();
    }
    public void show(){
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
    public boolean compare(String word){
        return middleWord.doTest(word) == manual_middleWord.doTest(word);
    }
}
interface solver{
    boolean doTest(String word);
    char[] prepareQueue(String word);
}
class middleWordQueue implements solver{
    private Queue<Character> middleWord = new LinkedList<Character>();
    public void test(String word){
        String message = word+" is ";
        if(doTest(word)) System.out.println(message+"palyndrome");
        else System.out.println(message+"not palyndrome");
    }
    public boolean doTest(String word){
        char[] chars = prepareQueue(word);
        int length = chars.length;
        for(int i=length-1;i>=length/2;i--){
            if(middleWord.poll()!=chars[i]) return false;
        }
        return true;
    }
    public char[] prepareQueue(String word){
        if(!middleWord.isEmpty()) middleWord.clear();
        char[] chars = word.toCharArray();
        for(int i=0;i<=chars.length/2;i++){
            middleWord.add(chars[i]);
        }
        return chars;
    }
}
class manual_middleWordQueue implements solver{
    private ManualQueue<Character> middleWord = new ManualQueue<>();
    public boolean doTest(String word){
        char[] chars = prepareQueue(word);
        int length = chars.length;
        for(int i=length-1;i>=length/2;i--){
            if(middleWord.poll()!=chars[i]) return false;
        }
        return true;
    }
    public char[] prepareQueue(String word){
        if(!middleWord.isEmpty()) middleWord.clear();
        char[] chars = word.toCharArray();
        for(int i=0;i<=chars.length/2;i++){
            middleWord.add(chars[i]);
        }
        return chars;
    }
}
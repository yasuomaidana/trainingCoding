package tests;

import excercises.Palindrome_Queue;

public class palindrome_Test {
    public static void main(){
        Palindrome_Queue tester = new Palindrome_Queue();
        String[] toTest = {"{[()]}","{}()[{}]","[{[()]}]","[({)}]","({[}])","()}[]"};
        boolean success = true;
        for ( String str: toTest){  
            if(!tester.compare(str)){
                success = false;
                break;
            }
        }
        if(success){System.out.println("Queue manual equal");}
        else{System.out.println("Not equals");}
    }
}

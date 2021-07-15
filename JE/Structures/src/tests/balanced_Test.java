package tests;
import excercises.Balanced_Stack;
public class balanced_Test {
    public static void main(){
        Balanced_Stack tester = new Balanced_Stack();
        String[] toTest = {"{[()]}","{}()[{}]","[{[()]}]","[({)}]","({[}])","()}[]"};
        boolean success = true;
        for ( String str: toTest){  
            if(!tester.compare(str)){
                success = false;
                break;
            }
        }
        if(success){System.out.println("Stack manual equal");}
        else{System.out.println("Not equals");}
    }
}

package manual_abstracts_ds;
import java.util.LinkedList;

public class ManualStack<T> {
    private LinkedList<T> manual_Stack;
    public ManualStack(){
        this.manual_Stack = new LinkedList<>();
    }
    public boolean empty(){return this.manual_Stack.isEmpty();}
    public T peek(){return this.manual_Stack.getLast();}
    public void push(T data){this.manual_Stack.add(data);}
    public T pop(){
        if (this.manual_Stack.isEmpty()) return null;
        return this.manual_Stack.removeLast();
    }
    public void clear(){
        this.manual_Stack.clear();
    }
}

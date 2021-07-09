package manual_abstracts_ds;

public class ManualQueue<T>{
    private class Node{
        private T data;
        private Node next;
        private Node (T data){
            this.data = data;
        }
    }
    private Node head;
    private Node tail;
    public boolean isEmpty(){ return this.head == null;}
    public T peek(){
        return this.head.data;
    }
    public void add(T data){
        Node node = new Node(data);
        if(this.tail!=null){
            this.tail.next=node;
        }
        //Why this works?
        this.tail = node;
        if(this.head==null){this.head=this.tail;}
    }
    public T poll(){
        T data = this.head.data;
        this.head = this.head.next;
        if (this.head==null){
            this.tail=null;
        }
        return data;
    }
    public void clear(){
        this.head=null;
        this.tail=null;
    }
}
package manual_abstracts_ds.linked_list_family;

import java.util.ArrayList;

abstract public class Node<T> {
    private Node<T> next;
    private T data;
    ArrayList<Node<T>> dataHistory = new ArrayList<Node<T>>();
    Node(T data){
        this.data=data;
    }
    public Node<T> getNext(){
        return this.next;
    }
    public T getData(){
        return this.data;
    }
    protected void setNextNode(Node<T> next){
        this.next = next;
    }
    abstract ArrayList<Node<T>> remove();
}
class single_node<T> extends Node<T>{
    public single_node(T data){
        super(data);
    }
    public void setNext(Node<T> next){
        setNextNode(next);
    }
    public ArrayList<Node<T>> remove(){
        dataHistory.add(this.getNext());
        return dataHistory;
    }
}
class double_node<T> extends Node<T>{
    private Node<T> prev;
    public double_node(T data,Node<T> prev){
        super(data);
        this.prev = prev;
    }
    public ArrayList<Node<T>> remove(){
        dataHistory.add(prev);
        dataHistory.add(this.getNext());
        return dataHistory;
    }
    public double_node<T> createNode(T data){
        return new double_node<T>(data, this);
    }
    public Node<T> getPrev(){
        return prev;
    }
}
package manual_abstracts_ds.linked_list_family;

public interface list_interface<T> {
    //Get data
    public T getHead();
    public T getTravelerValue();
    public T getTail();
    public Node<T> getNode(int ind);
    public T getValue(int ind);
    //Add data
    public void add(T data);
    //Flags
    public boolean empty();
    public boolean travelNext();
    //Remove data
    public void removeHead();
    public void removeTail();
    public void removeItem(int ind);
}

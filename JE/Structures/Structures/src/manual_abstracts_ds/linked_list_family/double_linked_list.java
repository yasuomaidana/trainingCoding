package manual_abstracts_ds.linked_list_family;

public class double_linked_list<T> implements list_interface<T>{
    private double_node<T> headNode;
    private double_node<T> tailNode;
    private double_node<T> currentNode;
    private int index = 0;
    public int size = 0;
    private void refreshHeadTail(double_node<T> newTail){
        headNode.setPrev(newTail);
        if(size==2) headNode.setNextNode(newTail);
        if(size>0) tailNode.setNextNode(newTail);
        tailNode = newTail;
        tailNode.setNextNode(headNode);
    }
    private void initialize(T data){
        headNode = new double_node<T>(data, null);
        tailNode = headNode;
        headNode.setNextNode(tailNode);
        refreshHeadTail(tailNode);
        size = 1;
    }
    private void setEmpty(){
        headNode=null;
        tailNode=null;
    }
    private boolean validInd(int ind){
        if(ind<=0 || ind>size) {
            System.out.println("No valid index");
            return false;
        }
        return true;
    }

    public double_linked_list(T data){
        initialize(data);
    }
    @Override
    public T getHead() {
        return headNode.getData();
    }

    @Override
    public T getTravelerValue() {
        return currentNode.getData();
    }

    @Override
    public T getTail() {
        return tailNode.getData();
    }

    @Override
    public Node<T> getNode(int ind) {
        if(!validInd(ind)) return null;
        if(ind==index) return currentNode;
        boolean searchNext = index<ind;
        int index_memory = index;
        double_node<T> currentNode_memory = currentNode;
        if(searchNext){
            if(size-ind>ind-index){
                searchNext = false;
                index = size+1;
            }
        }
        else{
            if(index-ind<ind){
                searchNext = true;
                index = 0;
            }
        }
        if(searchNext) while(ind!=index) travelNext();
        else while(ind!=index) travelBack();
        index = index_memory;
        double_node<T> retNode = currentNode;
        currentNode=currentNode_memory;
        return retNode;
    }

    @Override
    public T getValue(int ind) {
        return getNode(ind).getData();
    }

    @Override
    public void add(T data) { 
        size+=1;
        double_node<T> newNode = new double_node<T>(data, tailNode);
        refreshHeadTail(newNode);
    }

    @Override
    public boolean empty() {
        return size>0;
    }

    @Override
    public boolean travelNext() {
        index+=1;
        boolean r=index<=size;
        if (r){ 
            if (index==1) currentNode = headNode;
            else
            currentNode = (double_node<T>) currentNode.getNext();
        }else index=0;
        return r;
    }
    public boolean travelBack(){
        index--;
        boolean r=index>0;
        if (r){ 
            if (index==size) currentNode = tailNode;
            else
            currentNode = (double_node<T>) currentNode.getPrev();
        }else index=size+1;
        return r;
    }
    @Override
    public void removeHead() {
        size--;
        if(size==0) {setEmpty();return;}
        headNode = (double_node<T>) headNode.getNext();
        headNode.setPrev(tailNode);
        tailNode.setNextNode(headNode);
    }

    @Override
    public void removeTail() {
        size--;
        if(size==0) {setEmpty();return;}
        tailNode = (double_node<T>) tailNode.getPrev();
        headNode.setPrev(tailNode);
        tailNode.setNextNode(headNode);        
    }

    @Override
    public void removeItem(int ind) {

    }
    
}

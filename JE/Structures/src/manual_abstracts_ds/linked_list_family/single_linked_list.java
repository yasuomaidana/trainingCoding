package manual_abstracts_ds.linked_list_family;
public class single_linked_list<T> implements list_interface<T> {
    private single_node<T> headNode;
    private single_node<T> tailNode;
    private single_node<T> currentNode;
    private int index = 0;

    public int size = 0;
    
    private void initialize(T data){
        tailNode = new single_node<T>(data);
        headNode = tailNode;
        index = 0;
    }
    private single_node<T> remover(Node<T> node){
        if(node.remove().get(0)==null || node == null) return null;
        return (single_node<T>) node.remove().get(0);
    }
    private void checkHeadTail(){
        if(tailNode==null||headNode==null){
            tailNode=null;
            headNode=null;
        }
    }
    private single_node<T> get_previous_Tail_Node(){
        single_node<T> previous = getNode(size-1);
        if (previous!=null) previous.setNext(null);
        return previous;
    }
    private boolean validInd(int ind){
        if(ind<=0 || ind>size) {
            System.out.println("No valid index");
            return false;
        }
        return true;
    }

    public single_linked_list(T data){
        initialize(data);
        size+=1;
    }
    //Get data routines
    public T getHead(){
        return headNode.getData();
    }
    public T getTail() {
        return tailNode.getData();
    }
    public T getTravelerValue(){
        return currentNode.getData();
    }
    @Override
    public T getValue(int ind) {
        return getNode(ind).getData();
    }
    @Override
    public single_node<T> getNode(int ind) {
        if(!validInd(ind)) return null;
        single_node<T> retNode = null;
        if(index>=size||index>ind){index = 0; currentNode = null;}
        int index_memory = index;
        single_node<T> currentNode_memory = currentNode;
        while(index<ind){
            travelNext();
        }
        retNode = currentNode;
        index = index_memory;
        currentNode = currentNode_memory;
        return retNode;
    }
    //Adding data routine
    public void add(T data){
        single_node<T> node = new single_node<T>(data);
        if(headNode==null){ initialize(data);}
        else{
            tailNode.setNext(node);
            tailNode = node;
        }
        size+=1;
    }
    //Flags routines
    public boolean empty(){
        return size<=0;
    }
    public boolean travelNext(){
        if (size<=0) return false;
        if (index > size || (index == 0 && size>0)){
            currentNode=headNode;
            index = 1;
            return true;
        }
        if(index<size){
            currentNode = (single_node<T>) currentNode.getNext();
        }
        index++;
        return index<=size;
    }
    //Remove routines
    public void removeHead(){
        if(!empty()){
            headNode = remover(headNode);
            size--;
        }
        checkHeadTail();
    }
    public void removeTail(){
        if(!empty()){
            tailNode = get_previous_Tail_Node();
            size--;
        }
        checkHeadTail();
    }
    @Override
    public void removeItem(int ind) {
        if(!validInd(ind)) return;
        if(ind == 1) removeHead();
        else if(ind == size) removeTail();
        else {getNode(ind-1).setNext(getNode(ind).getNext());size--;}
    }
    
    
}

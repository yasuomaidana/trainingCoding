package excercises;

import manual_abstracts_ds.linked_list_family.double_linked_list;

public class Cycle_in_list {
    public static void main(){
    double_linked_list<Integer> doubleList = new double_linked_list<Integer>(1);
    doubleList.add(2);
    doubleList.add(3);
    doubleList.add(4);
    System.out.println("Added to 4");
    show(doubleList);
    doubleList.removeHead();
    System.out.println("Removing head");
    show(doubleList);
    doubleList.removeTail();
    System.out.println("Showing back");
    doubleList.add(4);
    showBack(doubleList);
    int val = doubleList.getValue(2);
    System.out.println("Node 2 value:"+val);
    doubleList.add(5);
    doubleList.add(6);
    System.out.println("Showing back");
    show(doubleList);
    show(doubleList);
    val = doubleList.getValue(4);
    System.out.println("Node 4 value:"+val);
    }
    private static void show(double_linked_list<Integer> list){
        while(list.travelNext()){
            System.out.println(list.getTravelerValue());
        }
    }
    private static void showBack(double_linked_list<Integer> list){
        list.travelBack();
        while(list.travelBack()){
            System.out.println(list.getTravelerValue());
        }
    }
}
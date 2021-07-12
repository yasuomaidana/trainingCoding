package excercises;

import manual_abstracts_ds.linked_list_family.single_linked_list;

public class Cycle_in_list {
    public static void main(){
        single_linked_list<Integer> easyList = new single_linked_list<Integer>(1);
        easyList.add(2);
        easyList.add(3);
        easyList.add(4);
        show(easyList);
        System.out.println("Removing head");
        easyList.removeHead();
        show(easyList);
        System.out.println("Removing tail");
        easyList.removeTail();
        show(easyList);
        System.out.println("Removing tail to empty "+easyList.size);
        easyList.removeTail();
        easyList.removeTail();
        show(easyList);
        System.out.println("Filling again");
        easyList.add(1);
        easyList.add(2);
        show(easyList);
        System.out.println("Removing head to empty "+easyList.size);
        easyList.removeHead();
        easyList.removeHead();
        show(easyList);
        System.out.println("Filling again");
        easyList.add(1);
        easyList.add(2);
        easyList.add(3);
        easyList.add(4);
        System.out.println("Showing 3 element "+ easyList.getValue(3));
        System.out.println("Removing 3 element ");
        easyList.removeItem(3);
        show(easyList);
    }
    public static void show(single_linked_list<Integer> List){
        while(List.travelNext()){
            System.out.println(List.getTravelerValue());
        }
    }
}
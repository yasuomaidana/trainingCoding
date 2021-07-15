package excercises;

import manual_abstracts_ds.linked_list_family.single_linked_list;

public class Simple_list {
    public static void main(){
        int proof=0;
        single_linked_list<Integer> easyList = new single_linked_list<Integer>(1);
        easyList.add(2);
        easyList.add(3);
        easyList.add(4);
        proof+=easyList.size-4;
        easyList.removeHead();
        proof+=easyList.size-3;
        easyList.removeTail();
        proof+=easyList.size-2;
        easyList.removeTail();
        easyList.removeTail();
        proof+=easyList.size;
        easyList.add(1);
        easyList.add(2);
        proof+=easyList.size-2;
        easyList.removeHead();
        easyList.removeHead();
        proof+=easyList.size;
        easyList.add(1);
        easyList.add(2);
        easyList.add(3);
        easyList.add(4);
        proof+=easyList.getValue(3)-3;
        easyList.removeItem(3);
        proof+=easyList.size-3;
        if(proof!=0) System.out.println("Something go wrong");
        else System.out.println("Proof done");
    }
}

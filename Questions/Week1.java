package Questions;
// Java Code for Merging two unsorted
// arrays in sorted order
import Questions.SingelyLinkedList.Node;
public class Week1 {

    SingelyLinkedList[] arr;

    Week1(SingelyLinkedList[] arr) {
        this.arr = arr;
    }

    SingelyLinkedList MergeAll() {
        SingelyLinkedList FinalOutputList = arr[0];
        SingelyLinkedList mergedList;
        for (int i = 1; i < arr.length; i++) {
            mergedList = MergeTwo(FinalOutputList, arr[i]);
            FinalOutputList = mergedList;
        }
        return FinalOutputList;
    }

    SingelyLinkedList MergeTwo(SingelyLinkedList a, SingelyLinkedList b) {

        SingelyLinkedList result = new SingelyLinkedList();
        Node first_itterator = a.head;
        Node second_itterator = b.head;
        while (first_itterator != null) {
            result.addNode(first_itterator.data);
            first_itterator = first_itterator.next;
        }
        while (second_itterator != null) {
            result.addNode(second_itterator.data);
            second_itterator = second_itterator.next;
        }
        return sortLinkedList(result);
    }

    SingelyLinkedList sortLinkedList(SingelyLinkedList list) {
        Node current = list.head, index = null;
        int temp;

        if (list.head == null) {
            return null;
        } else {
            while (current != null) {
                index = current.next;

                while (index != null) {
                    if (current.data > index.data) {
                        temp = current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
        return list;
    }

    void Median() {
        int median = MergeAll().getSize() / 2;
        System.out.println("Median: " + MergeAll().getDataAtAnyPos(median));
    }

    public static void main(String[] args) {
        SingelyLinkedList[] arr = new SingelyLinkedList[3];
        SingelyLinkedList L1 = new SingelyLinkedList();
        SingelyLinkedList L2 = new SingelyLinkedList();
        SingelyLinkedList L3 = new SingelyLinkedList();

        L1.addNode(2);
        L1.addNode(4);
        L1.addNode(7);
        L1.addNode(5);
        L1.addNode(10);

        L2.addNode(3);
        L2.addNode(2);
        L2.addNode(7);
        L2.addNode(9);

        L3.addNode(12);
        L3.addNode(5);
        L3.addNode(6);
        L3.addNode(9);

        arr[0] = L1;
        arr[1] = L2;
        arr[2] = L3;
        Week1 w = new Week1(arr);
        w.Median();

    }
}
package Questions;
import java.util.Scanner;

    //addNode at first, print in recursion, detect and remove cycle
    class SingelyLinkedList {
        public static class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        public Node head = null;
        Node tail = null;
        public int size = 0;

        public void addNodeR() {
            size++;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a data to add in the list('-999' for quit):-");
            int data = sc.nextInt();
            sc.close();
            if (data == -999) {
                return;
            }
            Node newNode = new Node(data);
            if (head == null) {
                tail = head = newNode;
                addNodeR();
            } else {
                tail.next = newNode;
                tail = newNode;
                addNodeR();
            }
        }

        public void addNodeF(int data) {
            size++;
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
        }

        public void printList() {
            System.out.println();
            System.out.println("Elements in the List:-");
            for (Node i = head; i != null; i = i.next) {
                System.out.println(i.data);
            }
        }

        public void printListR() {
            System.out.println();
            System.out.println("Elements in the List:-");
            System.out.println(head.data);
            if (head == null) {
                return;
            }
            head = head.next;
            printListR();
        }

        public void RemoveTail() {
            size--; // removes last node
            Node current = head;
            while (current.next != tail) { // If no tail given current.next.next!=null
                current = current.next;
            }
            current.next = null;
            tail = current;
        }

        public void RemoveHead() {
            size--;
            head = head.next;
        }

        public int getDataAtAnyPos(int pos) {
            Node current = head;
            for (int i = 2; i <= pos; i++) {
                current = current.next;
            }
            return current.data;
        }

        public int getSize() {
            return size;
        }

        public void addNode(int data) {
            size++;
            Node newNode = new Node(data);
            if (head == null) {
                tail = head = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
//                 Node current = head;
//                 while (current.next!=null){
//                 current =current.next;
//                 }
//                 current.next=newNode;
            }
        }

        public static void main(String[] args) {
            SingelyLinkedList l = new SingelyLinkedList();
            l.addNodeF(6);
            l.addNodeF(7);
            l.addNodeF(0);
            l.addNodeF(10);
            l.printList();
        }
    }



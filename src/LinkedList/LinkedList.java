package LinkedList;

import java.util.NoSuchElementException;

public class LinkedList {

    private Node head;
    private int size;


    public LinkedList() {
        head = null;
        size = 0;
    }


    public void addFirst(Node node) {
        node.setNext(head);
        head = node;
        ++size;
    }

    public int getSize() {
        return size;
    }

    public Node removeFirst() {
        Node first = head;
        // report error if list empty
        if (head == null) {
            throw new NoSuchElementException();
        }
        // otherwise remove the item
        else {
            Node temp = head;
            head = head.getNext();
            temp.setNext(null);
            size--;
        }
        return first;
    }


    public void display() {
        // node current will point to head
        Node current = head;

        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Printing all nodes of the current singly linked list: ");
        while (current != null) {
            // prints each node by traversing the list link by link
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public void add(Object item) {
        Node list1 = new Node(item, null);
        addFirst(list1);
    }

    public boolean contains(Object item) {
        boolean found = false;
        Node pointer = head;
        while (!found && pointer != null) {
            if (pointer.getData().equals(item)) {
                found = true;
            }
            pointer = pointer.getNext();
        }
        return found;
    }

    public Object remove(int i) {
        if (i > size) System.out.println("This index is not in range");
        Node pointer = head;
        Node prev = null;
        for (int j = 1; j < i; j++) {
            prev = pointer;
            pointer = pointer.getNext();
        }
        if (prev != null) {
            prev.setNext(pointer.getNext());
        } else {
            head = pointer.getNext();
        }
        size--;
        return pointer.getData();
    }

    /*Add at index, overwrites the item that was there*/
    public void addAt(Object item, int i) {
        if (i > size) System.out.println("This index is not in range");
        Node pointer = head;
        Node prev = null;
        for (int j = 1; j < i; j++) {
            prev = pointer;
            pointer = pointer.getNext();
        }

        Node a = new Node(item, pointer);

        if (prev == null) {
            head = a;
        } else {
            prev.setNext(a);
        }

        size++;

    }

    public LinkedList reverse() {
        LinkedList revList = new LinkedList();
        Object[] hold = new Object[size];
        Node pointer = head;
        for (int i = 1; i <= size; i++) {
            hold[i - 1] = pointer.getData();
            pointer = pointer.getNext();
        }
        for (int i = 0; i < size; i++) {
            revList.add(hold[i]);
        }
        return revList;
    }

}

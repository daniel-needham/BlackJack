package LinkedList;

public class QueueLinkedList {

    private int size;
    private Node head;

    public QueueLinkedList() {
        size = 0;
        head = new Node(null, null);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void enqueue(Object obj){
     Node pointer = head;
     while ( pointer.getNext() != null) {
         pointer = pointer.getNext();
     }
     pointer.setNext(new Node(obj, null));
     size++;
    }

    public Object dequeue() {
        Node top = head.getNext();
        head.setNext(top.getNext());
        size--;
        return top.getData();
    }

    public void display() {
        if(size == 0) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Printing all items of the queue:");
        System.out.println();
        System.out.print("FRONT ");
        Node pointer = head.getNext();
        while ( pointer != null) {

            System.out.print(pointer.getData() + " ");
            pointer = pointer.getNext();
        }
        System.out.print(" REAR");
        System.out.println();
    }

}
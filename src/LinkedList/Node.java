package LinkedList;

public class Node {

    private Object data;
    private Node next;

    Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public void setNext(Node nextNode) {
        next = nextNode;
    }

    public Node getNext(){
        return next;
    }

    public void setData (Object enterData){
        data = enterData;
    }

    public Object getData(){
        return data;
    }
}

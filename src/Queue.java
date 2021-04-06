public class Queue<E>{
private Node head;
private Node tail;
private int added = 0;
private int removed = 0;

public Node getHead(){
    return head;
}

public Node getTail(){
    return tail;

}
public int getAdded(){
    return added;
}

public int getRemoved(){
    return removed;
}

public int getCurrentSize(){
   return (added - removed);
}

public void showQueueStats(){
    System.out.println("Queue added= " + added + " Remove= " + removed + " CURR SIZE= " + getCurrentSize());
}

public void add(E value){ // adds a value to the queue.
    if(head == null){
        head = new Node(value);
        tail = head;
    }
    else{
        tail.next = new Node(value);
        tail = tail.next;
    }
    added++;
}

public E pop(){ // takes the first node off the queue and returns its value.
    E value = head.value;
    head = head.next;
    removed++;
    return value;
}

public boolean search(E value){ // searches the queue for a given value. (not very efficient).
    Node current = head;
    while(current != null){
        if(current.value.equals(value)){
            return true;
        }
        current = current.next;
    }
    return false;
}

    public class Node{
        public E value;
        public Node next;
        public Node(E value){
            this.value = value;
        }
    }
}
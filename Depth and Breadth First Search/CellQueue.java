
/*
 * filename: CellQueue.java
 * author: Vivian Nguyen
 * date : October 23
 * Project 5
 */



public class CellQueue{

    private class Node{

       private Node next;
       private Cell cell;
       private Node prev;


       //Node constructor
        public Node(Cell c){
            this.next = null;
            this.prev = null;
            this.cell = c;
        }


        //sets the previous node pointer
        public void setPrev(Node n){
            prev =n;

        }


        //gets the next node
        public Node getNext(){
            return next;
        }
    }

   public Node head;
   public Node tail;
   public int size;


    //constructor that initializes the fields so it is an empty queue.
    public CellQueue(){
        head = null;
        tail = null;
        size = 0;
    }


    //inserts the cell at the end of the queue
    public void offer( Cell cell){
        Node newNode = new Node(cell);

        if (size == 0){
            head = newNode;
            tail = newNode;
        }
        else{

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
    
        }
        size++;
    }


    //returns but does not remove the front cell
    public Cell peek(){
        if (head == null){
            return null;
        }
        return head.cell;
    }


    //returns and removes the Cell at the front of the queue
    public Cell poll(){

        Cell remove = null;

        if ( size == 0){
            System.out.println("Empty Queue");
        }else if(size == 1){
            remove = head.cell;
            this.tail = null;
            this.head = null;

        }else{
            remove = head.cell;
            this.head = head.getNext();
            this.head.setPrev(null);
        }
        size--;
        return remove;
    }


    //returns number of cells in the queue
    public int size(){
        return this.size;
    }    
}
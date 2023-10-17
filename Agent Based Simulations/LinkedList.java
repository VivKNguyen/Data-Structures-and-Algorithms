/* 
 * filename: LinkedList.java
 * author: Vivian Nguyen
 * date: 09/26
 * Lab Section B w/ Bender
 * Lab 3 CS231
 */

import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
import java.util.Collections; // contains a shuffle function

public class LinkedList<T>implements Iterable<T>{

     // Return a new LLIterator pointing to the head of the list
     public Iterator<T> iterator() {
        return new LLIterator( this.head );
        }


    // private container class called Node
    private class Node<T> {
        public Node<T> next;
        public T data;



        //a constructor that initializes next to null and the container field to item.
        public Node(T item){
            this.next = null;
            this.data = item;
        }


        //returns the value of the container field.
        public T getData(){
            T result = this.data;
            return result;
        }


        //sets next to the given node.
        public void setNext(Node<T> n){
            this.next = n;
        }


        //returns the next field.
        public Node<T> getNext(){
            Node<T> result = next;
            return result;
        }
    }


    //creating fields for LinkedList class
    private Node<T> head;
    public int size;


    // constructor that initializes the fields so it is an empty list.
    public LinkedList(){
        this.head = null;
        this.size = 0;
    }


    //returns the size of the list
    public int size(){
        return this.size;
    }


    //returns true if the list is empty, otherwise this method returns false.
    public boolean isEmpty(){
        if (this.size == 0){
            return true;
        }
        else{
            return false;
        }
    }


    //empties the list (resets the fields so it is an empty list).
    public void clear(){
        this.head = null;
        this.size = 0;
    }


    //inserts the item at the beginning of the list.
    public void add(T item){
        Node<T> newNode = new Node<T>( item);
        Node<T> temp = this.head;
        newNode.setNext(temp);

        this.head = newNode;
        this.head.setNext(temp);
        this.size++;
    }


    //removes the first item of the list and returns it.
    public T remove(){
        T result = null;
        if (this.head != null){
            Node<T> temp = head;
            this.head = head.next;
            result = temp.data;
            this.size--;
        }
        return result;
    }

    //reverses the list  
    public Node reverse (){
        Node<T> prev = null;
        Node<T> currNode = this.head;


        Node<T> newNode = currNode.getNext();
        while (currNode != null) {
            
            currNode.setNext(prev);
            prev = currNode;
            currNode = newNode;
        }

        return newNode;
    }


    //returns the item specified by the given index
    public T get(int index){

        if(index == 0){
            return this.head.data;
        }

        Node<T> curNode = this.head;

        for ( int i = 0; i < index; i++){
            curNode = curNode.next;
        }        
        return curNode.data;
    }


    //inserts the item at the specified position in the list.
    public void add(int index, T item){
        if (index == 0){
            add(item);
            return;
        }
        Node<T> curNode = this.head;

        for ( int i = 0; i < index -1; i++){
            curNode = curNode.next;
        }
        //new node with given data
        Node<T> newNode = new Node(item);
        //The node following the newNode is what is currently in index
        newNode.setNext(curNode.next);
        //Make the node at position index-1 have the new Node as its next
        curNode.setNext(newNode);
        
        this.size++;
    }


    //removes item at specific index
    public T remove (int index){
        Node<T> curr = this.head;
        Node<T> prev = null;

        
        if (index == 0){
            this.remove();
        }

        for ( int i = 0; i<index; i++){
            prev = curr;
            curr = curr.next;

           }
            
        if ( curr == null){
            return null;
        }

        prev.next = curr.next;
        this.size--;
        return curr.data;

        }
    
    
    
    //returns true if o is present in this list, otherwise this method returns false.
    public boolean contains(Object o){
        
        Node temp = this.head;

        while (temp!= null){
            if (temp.data.equals(o)){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    //returns true if o is a LinkedList that also contains the same items in the same order.
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }

        if (this.size() != ((LinkedList)o).size()){
            return false;
        }

        for (int i = 0; i < this.size; i++){
            if ( !(((LinkedList)o).get(i)).equals(this.get(i))){
                return false;
            }
        }
         return true;
   
    }


    private class LLIterator implements Iterator<T>{

        private Node<T> current;
        
        public LLIterator(Node head){
            this.current = head;
        }

        public boolean hasNext(){
            if (current != null){
                return true;
            }
            else {
                return false;
            }
        }


        //returns next node
        public T next(){
            T data = current.data;
			this.current = this.current.next;
			return data;
        }
    }


    //turns a linkedlist to an arraylist
    public ArrayList<T> toArrayList(){
        ArrayList<T> result = new ArrayList<T>(); 
        int counter = 0; 
        for (Node<T> i = head; counter < size; counter++){
			result.add(i.getData()); 
            i = i.getNext(); 
		}
        return result; 
    }


}
    






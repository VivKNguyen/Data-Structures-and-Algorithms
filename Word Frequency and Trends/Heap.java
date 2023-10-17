import java.util.Comparator;

/* 
 * filename: Heap.java
 * author: Vivian Nguyen
 * date: Nov 14, 2022
 * Project 8
 * CS231 Section B
 */

public class Heap<T> {

    private T[] heap;
    private int size;
    private Comparator<T> comparator;

    // constructor for the heap
    public Heap(Comparator<T> comparator) {

        size = 0;
        this.comparator = comparator;
        heap = (T[]) new Object[16];
    }

    // returns the number of elements in heap
    public int size() {
        return this.size ;
    }

    // returns the number of items the heap can currently hold
    public int capacity() {
        return heap.length;
    }

    // changes the capacity of the array, note: made public for testing
    public void resize(int newSize) {

        T[] newHeap = (T[]) new Object[newSize];
        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];

        }
        heap = newHeap;

    }

    // helper method to get the left index
    private int left(int index) {
        return index * 2;
    }

    // helper method to get the right index
    private int right(int index) {
        return index * 2 + 1;
    }

    // helper method to get the parent of an index
    private int parent(int index) {
        return index / 2;
    }

    // swaps the values of two indexes
    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    // a value is bubbled up until the maxheap rules are followed
    private void bubbleUp(int curIndex) {
        if (curIndex == 1) {
            return;
        }

        T myself = heap[curIndex];
        T parent = heap[parent(curIndex)];
        // if the current value is greater than the parent
        if (comparator.compare(myself, parent) > 0) {
            // swap the values of the current and parent index
            swap(curIndex, parent(curIndex));
            // bring up the value of the current index until the rules of maxheap are
            // followed
            bubbleUp(parent(curIndex));

        }
    }

    // adds a value to the heap and increments the size
    public void offer(T item) {
        if (size + 1 == heap.length) {
            resize(2 * heap.length);
        }

        heap[size + 1] = item;
        size++;
        bubbleUp(size);

    }

    // returns the highest priority element from the heap (node)
    public T peek() {
        return heap[1];

    }

    private void bubbleDown(int index) {

      
        if (index >= this.size) {
            return;
        }
        // using helper methods to get indices of left and right childs
        int left = left(index);
        int right = right(index);
        int max = index;
        // if the left child is greater than the parent, set the max child index to
        // left child
        if (left < this.size && this.comparator.compare(this.heap[left], this.heap[max]) > 0) {
            max = left;
        }
        // if the right child is greater than the max child, set the max child
        // index to right child
        if (right < this.size && this.comparator.compare(this.heap[right], this.heap[max]) > 0) {
            max = right;
        }
        // if the max child is not the parent, swap and bubble down the max
        
        if (max != index) {
            T temp = this.heap[index];
            this.heap[index] = this.heap[max];
            this.heap[max] = temp;
            // bubble down the max child
            this.bubbleDown(max);
        }
    }

    // removes and returns the highest priority element
    public T poll() {

        if (this.size == 0) {
            return null;
        }

        if (size == this.capacity() / 4) {
            resize(heap.length / 2);
        }
        //highest priority 
        T temp = this.heap[1];
       
        //gets rid of the root
        this.heap[1] = this.heap[--size];
      
        // bubble down the root
        this.bubbleDown(1);

        return temp;

    }

    //creates a string representation of the heap
    public String toString(){
        String result = "";
        //shows the different levels of the heap
        for (int i = 0; i < size ; i++) {
            result += "Parent: " + this.heap[i] + "\n";
                              
            result += "Left Child: " + this.heap[left(i)] + "\n";

            result += "Right Child: " + this.heap[right(i)] + "\n";
 
           result += "\n";

        }
        return result;

    }

    public static void main(String[] args) {

        Heap<Integer> test = new Heap<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;

            }

        });

        
        test.offer(9);
        test.offer(10);
        test.offer(19);
        test.offer(2);
        test.offer(90);
        System.out.println("Size " + test.size());
        System.out.println("Capacity " + test.capacity());
        System.out.println("Peek " + test.peek() + "\n");

        System.out.println(test);
       
        System.out.println("Poll " + test.poll());
        System.out.println("Size " + test.size());
        System.out.println("Poll " + test.poll());

    }

}
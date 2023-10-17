/*
 * filename: CellStack.java
 * author: Vivian Nguyen
 * date: Oct 3, 2022
 * Project 4, used for Project 5 too
 */


public class CellStack {

    
    private int capacity;
    private int top;
    private Cell[] stack; 

    
    

    //initialize the stack's fields.
    public CellStack(){
        
        this.capacity = 4;
        this.stack = new Cell[this.capacity];
        this.top = -1;

    }

    //push c onto the stack.
    public void push (Cell c){

        if (this.top == this.capacity -1){
            Cell[] temp = new Cell[this.capacity * 2];
            for (int i = 0; i < this.capacity; i++){
                temp[i] = this.stack[i];
            }

            this.stack = temp;
            this.capacity *=2;
        }
        this.top++;
        this.stack[this.top] = c;
    }
   


    //return the top Cell on the stack
    public Cell peek(){
        Cell result = this.stack[this.top];
        return result;
    }
    
    
    //remove and return the top element from the stack; return null if the stack is empty
    public Cell pop(){
        if (this.top > -1){
            return this.stack[this.top--];
        }
        return new Cell();
    }


    //return the number of elements in the stack.
    public int size(){
        return this.top +1;
    }


    //return true if the stack is empty.
    public boolean empty(){
        if ((this.top + 1) == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        //testing out size and empty methods
        CellStack s=new CellStack();
        System.out.println("Size: " + s.size());
        System.out.println("Empty: " + s.empty());
    
        //testing push
        System.out.println("Pushing");
        // s.push(new Cell(0,0,5));
        System.out.println("Size: " + s.size());
        System.out.println("Empty: " + s.empty());
    
        //testing pop
        System.out.println("Popping");
        s.pop();
        System.out.println("Size: " + s.size());
        System.out.println("Empty: " + s.empty());
    }
}

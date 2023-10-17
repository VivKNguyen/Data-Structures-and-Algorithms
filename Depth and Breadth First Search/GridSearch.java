
/*
 * filename: GridSearch.java
 * author: Vivian Nguyen
 * date : October 23
 * Project 5
 */

import java.util.ArrayList;

public class GridSearch {

    public Landscape scape;
    public LandscapeDisplay ld;
    //EXTENSION
    public int Dcount;
    public int Bcount;
    
    
    //constructs the grid search
    //creates new landscape and landscape display
    public GridSearch(){
        scape = new Landscape(30, 30, 0.25);
        ld = new LandscapeDisplay(scape, 20);
    }


    //this searching algorithm traverses the grid using backtracking
    public boolean depthFirstSearch(int delay) throws InterruptedException {
        Dcount =0;
        //create an empty stack of cells
        CellStack stack = new CellStack();

        //mark the start as visited
        this.scape.start.visitFrom(null);
        
        //push the start onto the stack
        stack.push(this.scape.start);

        //while the stack isn't empty
        while (!stack.empty()){
            if (delay > 0){
                Thread.sleep(delay);
                ld.repaint();
                
            }
            //pop a cell off the stack
            Cell cur = stack.pop();
            //get neighbors of popped cell
            ArrayList<Cell> neighbors = this.scape.getNeighbors(cur);
            
            // for each cell in the neighbors list
            for ( Cell n : neighbors){
                //if the cell is not an obstacle and hasn't been visited
                if (!(n.getType() == Cell.Type.OBSTACLE) && !(n.visited()) ){
                    //the cell gets a vist from cur
                    n.visitFrom(cur);
                    //if the cell is the target cell, return true
                    if (n.getType() == Cell.Type.TARGET){
                        return true;
                    }
                    //push cell on to stack
                    stack.push(n);
                }
            }
            Dcount++;
            
        }
        
        return false;
    }


    //this algorithm explores the grid breadthwise
    public boolean breadthFirstSearch(int delay) throws InterruptedException {
        Bcount = 0;
        //create an empty queue
        CellQueue queue = new CellQueue();

        //mark the start as visited
        this.scape.start.visitFrom(null);

        //offers the start into the queue
        queue.offer(this.scape.start);

        //while the queue isn't empty
        while (queue.size() != 0){
            if ( delay > 0){
                Thread.sleep(delay);
                ld.repaint();
            }

            //poll a cell off the queue, call it cur

            Cell cur = queue.poll();
            //gets neighbors of polled cell
            ArrayList<Cell> neighbors = this.scape.getNeighbors(cur);
            //for cells in the neighbors list
            for ( Cell n : neighbors){
                //if the cell is not an obstacle and hasn't been visited
                if (n.getType() != Cell.Type.OBSTACLE && n.visited() == false){
                    //cell gets a visit from cur
                    n.visitFrom(cur);
                    //if cell is a target, return true
                    if ( n.getType() == Cell.Type.TARGET){
                        return true;
                    }
                    //offer the cell on to the queue
                    queue.offer(n);
                    
                }
            }
            Bcount++;
        }
        return false;
    }


    //prints out how many steps each algorithm takes, part of extension
    public void print(){
        System.out.println("DEPTH SEARCH: " +this.Dcount);
        System.out.println("BREADTH SEARCH: " +this.Bcount);
    }


    public static void main(String[] args) throws InterruptedException {
        GridSearch test = new GridSearch();
        //10 animations of each search algorithm
        for (int i = 0; i < 10; i++){

            test.depthFirstSearch(10);
            Thread.sleep(3000);

            test.scape.reset();

            test.breadthFirstSearch(10);
            Thread.sleep(3000);
            test.scape.reset();
            test.print();
        }

        
        

    }
    
}

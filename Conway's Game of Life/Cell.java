/*
 * filename: Cell.java
 * author: Vivian Nguyen
 * date: 09/26/2022
 * CS231 Lab Section B
 * Project 2: Conway's Game of Life
 */




import java.util.ArrayList;

public class Cell {
    /**
     * The status of the Cell.
     */
    private boolean alive;

    /**
     * Constructs a dead cell.
     */
    public Cell() {
        alive = false;

    }


    /**
     * Constructs a cell with the specified status.
     * 
     * @param status a boolean to specify if the Cell is initially alive
     */
    public Cell(boolean status) {
        this.alive = status;
    }


    /**
     * Returns whether the cell is currently alive.
     * 
     * @return whether the cell is currently alive
     */
    public boolean getAlive() {
        return this.alive;
    }


    /**
     * Sets the current status of the cell to the specified status.
     * 
     * @param status a boolean to specify if the Cell is alive or dead
     */
    public void setAlive(boolean status) {
        this.alive = status;
    }


    /**
     * Updates the state of the Cell.
     * 
     * If this Cell is alive and if there are 2 or 3 alive neighbors,
     * this Cell stays alive. Otherwise, it dies.
     * 
     * If this Cell is dead and there are 3 alive neighbors,
     * this Cell comes back to life. Otherwise, it stays dead.
     * 
     * @param neighbors An ArrayList of Cells
     */


    public void updateState(ArrayList<Cell> neighbors) {
        //counts how many alive cells are in neighbors
        int aliveCount = 0;
        for ( int r = 0; r < neighbors.size(); r++ ){
            if (neighbors.get(r).getAlive()== true){
                aliveCount +=1;
            }
        }
        System.out.println(aliveCount);

        //if the cell is alive and has 2-3 alive neighbors, lives to next gen
        if (this.getAlive() == true && aliveCount == 2 || aliveCount == 3){
            this.setAlive(true);


        }
        // if the cell is dead and has 3 living neighbors, comes back to life
        else if (this.getAlive() == false && aliveCount == 3){
            this.setAlive(true);
        }
        //the cell will remain dead in all other situations
        else{
            this.setAlive(false);
        }


    }


    /**
     * Returns a String representation of this Cell.
     * 
     * @return 1 if this Cell is alive, otherwise 0.
     */
    public String toString() {
        String result =" ";
        if (this.alive == true) {
            result +=1;}
        else{
            result +=0;}
        return result;
        }

        
    public static void main(String[] args){
        Cell cellTest = new Cell(false);
        cellTest.toString();
        System.out.println(cellTest); 

    }

        }

    

/*
 * filename: Landscape.java
 * author: Vivian Nguyen
 * date: 09/26/2022
 * CS231 Lab Section B
 * Project 2: Conway's Game of Life
 */


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Landscape {

    /**
     * The underlying grid of Cells for Conway's Game
     */
    private Cell[][] landscape;

    //creates fields to hold number of rows and columns in the grid
    private int rows;
    private int cols;

    /**
     * The original probability each individual Cell is alive
     */
    private double initialChance;

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * All Cells are initially dead.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     */


    public Landscape(int rows, int columns) {
        this.rows = rows;
        this.cols = columns;
        this.landscape = new Cell[this.rows][this.cols];
        for ( int r = 0; r< this.rows; r++){
            for( int c = 0; c < this.cols; c++){
                landscape[r][c] = new Cell();
            }
        }
        
        reset();
    }


    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     * @param chance  the probability each individual Cell is initially alive
     */


    public Landscape(int rows, int columns, double chance) {
        this.initialChance = chance;
        this.rows = rows;
        this.cols = columns;
        Random ran = new Random();
        this.landscape = new Cell[this.rows][this.cols];
        for ( int r = 0; r< this.rows; r++){
            for( int c = 0; c < this.cols; c++){
                this.initialChance = ran.nextDouble(101);
                if (this.initialChance> 49){
                    this.landscape[r][c] = new Cell(true);
                }else {
                    this.landscape[r][c] = new Cell(false);
                }
            }
        }
        reset();
    }


    /**
     * Recreates the Landscape according to the specifications given
     * in its initial construction.
     */

    public void reset() {
        Random ran = new Random();
        this.landscape = new Cell[this.rows][this.cols];
        for ( int r = 0; r< this.rows; r++){
            for( int c = 0; c < this.cols; c++){
                this.initialChance = ran.nextDouble(101);
                if (this.initialChance> 49){
                    this.landscape[r][c] = new Cell(true);
                }else {
                    this.landscape[r][c] = new Cell(false);
                } 
            }
        }
    }


    /**
     * Returns the number of rows in the Landscape.
     * 
     * @return the number of rows in the Landscape
     */
    public int getRows() {
        return this.rows;
    }


    /**
     * Returns the number of columns in the Landscape.
     * 
     * @return the number of columns in the Landscape
     */
    public int getCols() {
        return this.cols;
    }
    

    /**
     * Returns the Cell specified the given row and column.
     * 
     * @param row the row of the desired Cell
     * @param col the column of the desired Cell
     * @return the Cell specified the given row and column
     */

    public Cell getCell(int row, int col) {
        return this.landscape[row][col];
    }


    /**
     * Returns a String representation of the Landscape.
     */
    public String toString() {
        String landString = "";

        for ( int r = 0; r < this.rows; r++){
            for ( int c = 0; c < this.cols; c++){
                landString += this.landscape[r][c].toString();
            }
            landString +="\n";
        }
        return landString;
    }


    /**
     * Returns an ArrayList of the neighboring Cells to the specified location.
     * 
     * @param row the row of the specified Cell
     * @param col the column of the specified Cell
     * @return an ArrayList of the neighboring Cells to the specified location
     */
    

    public ArrayList<Cell> getNeighbors(int row, int col){
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
            //top left corner
            if(row == 0){
                if(col ==0){
                    //adding the surrounding cells
                    neighbors.add(this.landscape[0][1]);
                    neighbors.add( this.landscape[1][0]);
                    neighbors.add(this.landscape[1][1]);
                }
                //top right corner 
                else if(col == this.cols-1){
                    //add the three surrounding cells to the list
                    neighbors.add(this.landscape[0][col-1]);
                    neighbors.add(this.landscape[1][col]);
                    neighbors.add(this.landscape[1][col-1]);
                }
                //top row that isn't a corner
                else{
                    for( int i = row; i <= row+1; i++){
                        for(int j = col -1; j <= col +1; j++ ){
                            if(i == row && j == col){}
                            else{
                                //add the five surrounding cells 
                                neighbors.add(this.landscape[i][j]);
                            }
                        }
                    }	
                }
            }
        
            //bottom row
            else if(row == this.rows -1){
                //bottom left corner
                if(col ==0){
                    //add the three surrounding cells 
                    neighbors.add(this.landscape[row-1][0]);
                    neighbors.add(this.landscape[row][1]);
                    neighbors.add(this.landscape[row-1][1]);
                }
                //bottom right corner
                else if(col == this.cols-1){
                    //add the three surrounding cells 
                    neighbors.add(this.landscape[row][col-1]);
                    neighbors.add(this.landscape[row-1][col]);
                    neighbors.add(this.landscape[row-1][col-1]);	
                }
                //other cells in bottom row
                else{
                    for( int i = row-1; i <= row; i++){
                        for(int j = col -1; j <= col +1; j++ ){
                            if(i == row && j == col){}
                            else{
                                //add the five surrounding cells
                                neighbors.add(this.landscape[i][j]);
                            }
                        }
                    }	
                    
                }	
            }
        
            //left column excluding the corners
            else if(col == 0){
                for( int i = row-1; i <= row +1; i++){
                    for(int j = col; j <= col +1; j++ ){
                        if(i == row && j == col){}
                        else{
                            //add the five surrounding cells to the list
                            neighbors.add(this.landscape[i][j]);
                        }
                    }
                }	
            }
            //far right column excluding corners
            else if(col == this.cols -1){
                for( int i = row-1; i <= row +1; i++){
                    for(int j = col-1; j <= col; j++ ){
                        if(i == row && j == col){}
                        else{
                            //add the five surrounding cells to the list
                            neighbors.add(this.landscape[i][j]);
                        }
                    }
                }	
            }
            
            //all the other cells
            else{
                for( int i = row-1; i <= row +1; i++){
                    for(int j = col-1; j <= col +1; j++ ){
                        if(i == row && j == col){}
                        else{
                            ////add the eight surrounding cells to the list
                            neighbors.add(this.landscape[i][j]);
                        }
                    }
                }	
            }
            //return the list of neighbors
            return neighbors;
        }
        
    
    /**
     * Advances the current Landscape by one step. 
     */
    public void advance() {
        Landscape temp = new Landscape(this.rows,this.cols);
        for ( int r =0; r< this.getRows(); r++){
            for ( int c = 0; c < this.getCols(); c++){
                ArrayList<Cell> neighbors = this.getNeighbors(r,c);
                
				//updates the alive state of the cells 
				temp.getCell(r, c).updateState(neighbors);
            }
            this.landscape = temp.landscape;
        }
        } 
    

    /**
     * Draws the Cell to the given Graphics object at the specified scale.
     * An alive Cell is drawn with a black color; a dead Cell is drawn gray.
     * 
     * @param g     the Graphics object on which to draw
     * @param scale the scale of the representation of this Cell
     */
    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                g.setColor(getCell(x, y).getAlive() ? Color.ORANGE: Color.BLACK);
                g.fillRect(x * scale, y * scale, scale, scale);
            }
        }
    }

    public static void main(String[] args) {
        Landscape test = new Landscape(4, 5);
        System.out.println(test.toString());
        System.out.println(test.getNeighbors(3, 2));

    }
}

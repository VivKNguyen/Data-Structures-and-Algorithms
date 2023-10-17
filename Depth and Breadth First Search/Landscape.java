/*
 * filename: Landscape.java
 * author: Vivian Nguyen
 * date : October 23
 * Project 5
 */


import java.util.Random;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;



public class Landscape {

    public Cell[][] grid;
    public int row;
    public int col;
    public double chance;
    public Cell start;
    public Cell target;


    //constructor for landscape, sets rows, columns, and chance that a cell = obstacle
    public Landscape(int rows, int cols, double chance){
        this.row = rows;
        this.col = cols;
        this.chance = chance;
        this.reset();
    }


    //returns the start cell
    public Cell getStart(){

        return this.start;

    }


    //returns the target cell
    public Cell getTarget(){
        return this.target;
    }


    //returns the number of rows
    public int getRows(){
        return this.row;
    }

    //returns the number of columns
    public int getCols(){
        return this.col;
    }

    //returns cell at specified row and col
    public Cell getCell(int row, int col){
        return grid[row][col];

    }

    //resets all cells in landscape
    public void reset(){
        this.grid = new Cell[this.row][this.col];
        Random ran = new Random();
        //iterates through the grid
        for (int i = 0; i < this.row; i ++){
            for ( int j = 0; j < this.col; j++){
                //creating obstacle cell based on chance field
                if (ran.nextDouble()< this.chance){
                    this.grid[i][j] = new Cell(i,j, Cell.Type.OBSTACLE);
                }
            else{
                //if cell isn't an obstacle, it is free
                this.grid[i][j] = new Cell(i,j,Cell.Type.FREE);
            }
            }
        }


        //creates the start cell
        start = new Cell(ran.nextInt(this.row), ran.nextInt(this.col), Cell.Type.START);
        int row = ran.nextInt(this.row);
        int col = ran.nextInt(this.col);

        while ( row == start.getRow() && col == start.getCol()){
            row = ran.nextInt(this.row);
            col = ran.nextInt(this.col);
        }


        //creates the target cell
        target = new Cell(row, col, Cell.Type.TARGET);


        //puts the start and target cells on the grid
        grid[start.getRow()][start.getCol()] = start;
        grid[target.getRow()][target.getCol()] = target;

    }


    //returns an ArrayList of Cells containg all the adjacent neighbors of specified cells
    public ArrayList<Cell> getNeighbors(Cell c){
        ArrayList<Cell> neighbors = new ArrayList<Cell>();

        //top left corner of grid
        if (c.getRow() == 0){
            if (c.getCol() == 0){
                //adding the surrounding cells
                neighbors.add(this.grid[0][1]);
                neighbors.add(this.grid[1][0]);
            }

            //top right corner
            else if(c.getCol() == this.col -1){
                //add surrounding cells
                neighbors.add(this.grid[0][c.getCol()-1]);
                neighbors.add(this.grid[1][c.getCol()]);
            }
            
            //cells on top row that aren't corners
            else{
                for ( int i = c.getRow(); i <= c.getRow() +1; i++){
                    for ( int j = c.getCol() -1; j <= c.getCol() +1; j++){
                        if (( (i == c.getRow() && j == c.getCol())) || ( i == c.getRow() + 1 && j == c.getCol()-1) ||
                        ( i == c.getRow() + 1 && j == c.getCol() +1)){}
                        
                        else{
                            neighbors.add(this.grid[i][j]);
                        } 
                    }
                }
            }
        }

        //Bottom row
        else if (c.getRow() == this.row-1){
            //bottom left corner
            if ( c.getCol() == 0){
                //add surrounding cells
                neighbors.add(this.grid[c.getRow()-1][c.getCol()]);
                neighbors.add(this.grid[c.getRow()][c.getCol()+1]);
            }

            //bottom right corner
            else if ( c.getCol() == this.col-1){
                //add surrounding cells
                neighbors.add(this.grid[c.getRow()-1][c.getCol()]);
                neighbors.add(this.grid[c.getRow()][c.getCol()-1]);
            }

            //other cells in bottom row
            else{
                for (int i = c.getRow()-1; i <= c.getRow(); i++){
                    for ( int j = c.getCol() - 1; j <= c.getCol()+ 1; j++){
                        if ( (i == c.getRow() && j == c.getCol()) || ( i == c.getRow() -1 && j ==c.getCol() -1)
                        || ( i == c.getRow() - 1 && j == c.getCol() +1)) {}
                        
                        
                        else {
                            neighbors.add(this.grid[i][j]);
                        }
                    }
                }
            }
        }

        //left column excluding corners
        else if ( c.getCol() == 0){
            for (int i = c.getRow() - 1; i <= c.getRow() +1; i ++){
                for (int j = c.getCol(); j <= c.getCol()+1; j++){
                    if (( i == c.getRow() && j == c.getCol()) || ( i == c.getRow()+1 && j==c.getCol() +1)
                    || ( i == c.getRow()-1 && j==c.getCol() +1)){}
                    
                    
                    else{
                        neighbors.add(this.grid[i][j]);

                    }
                }
            }
        }

        //right column excluding corners
        else if (c.getCol() == this.col -1){
            for (int i = c.getRow()-1; i <= c.getRow() +1; i++){
                for (int j = c.getCol() -1 ; j <= c.getCol(); j++){
                    if ((i == c.getRow() && j == c.getCol())|| (i == c.getRow()-1 && j ==c.getCol()-1)
                    || (i == c.getRow()+1 && j ==c.getCol()-1)){}
                    
                    
                    else{
                        neighbors.add(this.grid[i][j]);
                    }
                }
            }
        }
        //all other cells
        else{
            for ( int i = c.getRow() -1; i <= c.getRow()+1; i++){
                for (int j = c.getCol()-1 ; j<= c.getCol() +1; j++){
                    if ((i == c.getRow() && j == c.getCol()) || (i == c.getRow()-1 && j == c.getCol()+1)
                    || (i == c.getRow()-1 && j == c.getCol()-1) || (i == c.getRow()+1 && j == c.getCol()-1)
                    || (i == c.getRow()+1 && j == c.getCol()+1)){}
                    

                    else{
                        neighbors.add(this.grid[i][j]);
                    }
                }
            }
        }
        return neighbors;
    }


    //draws the landscape, written by Prof.Bender
    public void draw(Graphics g, int scale) throws InterruptedException{
        for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getCols(); c++){
                getCell(r, c).draw(g, scale, this);
            }
        }
        g.setColor(Color.RED);
        CellQueue queue = new CellQueue();
        queue.offer(start);
        while (!(queue.size() == 0)) {
            Cell cur = queue.poll();
    
            for (Cell neighbor : getNeighbors(cur)) {
                if (neighbor.getPrev() == cur) {
                    queue.offer(neighbor);
                    g.drawLine(cur.getCol() * scale + scale / 2, cur.getRow() * scale + scale / 2,
                            neighbor.getCol() * scale + scale / 2, neighbor.getRow() * scale + scale / 2);
                }
            }
        }
    
        if (target.visited()) {
            Cell cur = target.getPrev();
            while (cur != start) {
                g.setColor(Color.GREEN);
                g.fillRect(cur.getCol() * scale + 2, cur.getRow() * scale + 2, scale - 4, scale - 3);
                cur = cur.getPrev();

                
            }
            
            cur = target;
            while (cur != start) {
                g.setColor(Color.BLUE);
                g.drawLine(cur.getCol() * scale + scale / 2, cur.getRow() * scale + scale / 2,
                        cur.getPrev().getCol() * scale + scale / 2, cur.getPrev().getRow() * scale + scale / 2);
                cur = cur.getPrev();
            }
            
           
            
        }
    }
    
    
}

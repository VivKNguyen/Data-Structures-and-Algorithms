/* 
 * filename: Cell.java
 * author: Vivian Nguyen
 * date: October 23, 2022
 * Project 5
 */

import java.awt.Graphics;
import java.awt.Color;

 public class Cell {

    //each cell has an underlying type
    public enum Type {
        FREE, OBSTACLE, START, TARGET;
    }


    //fields
    public boolean visited;
    public Cell prev;
    public int row, col;
    public Type type;
    
    

    //cell constructor
    public Cell (){
        this.prev = null;
        this.visited = false;
    }


    //constructor that sets up the row, col, and type as specified; prev should be null and visited should be false
    public Cell (int row, int col, Type type){
        this.prev = null;
        this.visited = false;
        this.row = row;
        this.col = col;
        this.type = type;
    }


    //returns the row
    public int getRow(){
        return this.row;
    }


    //returns the column
    public int getCol(){
        return this.col;
    }


    //returns the type
    public Type getType(){
        return this.type;
    }


    //returns visited
    public boolean visited(){
        return this.visited;
    }


    //returns prev
    public Cell getPrev(){
        return this.prev;
    }


    //sets visited to true and prev to c
    public void visitFrom(Cell c){
        this.visited = true;
        this.prev = c;
    }


    //sets visited to false and prev to null
    public void reset(){
        this.visited=false;
        this.prev=null;
    }


    //draws each cell, code written by Prof. Bender
    public void draw(Graphics g, int scale, Landscape scape) {
        g.setColor(Color.BLACK);
        g.drawRect(getCol() * scale, getRow() * scale, scale, scale);
        switch (getType()) {
            case FREE:
                g.setColor(visited() ? Color.YELLOW : Color.GRAY);
                break;
            case OBSTACLE:
                g.setColor(Color.BLACK);
                break;
            case START:
                g.setColor(Color.BLUE);
                break;
            case TARGET:
                g.setColor(Color.RED);
                break;
        }
        g.fillRect(getCol() * scale + 2, getRow() * scale + 2, scale - 4, scale - 3);
    
        g.setColor(Color.RED);
        if (getPrev() != null && getPrev() != this) {
            int midX = ((getCol() + getPrev().getCol()) * scale + scale) / 2;
            int midY = ((getRow() + getPrev().getRow()) * scale + scale) / 2;
            g.drawLine(getCol() * scale + scale / 2, getRow() * scale + scale / 2,
                    midX, midY);
        }
        for (Cell neighbor : scape.getNeighbors(this)) {
            if (neighbor.getPrev() == this) {
                int midX = ((getCol() + neighbor.getCol()) * scale + scale) / 2;
                int midY = ((getRow() + neighbor.getRow()) * scale + scale) / 2;
                g.drawLine(getCol() * scale + scale / 2, getRow() * scale + scale / 2,
                        midX, midY);
            }
        }
    }             
    
}

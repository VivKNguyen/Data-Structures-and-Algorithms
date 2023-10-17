/*
 * filename: Cell.java
 * author: Vivian Nguyen
 * date: October 3, 2022
 * Project 4
 */

import java.awt.Graphics;
import java.awt.Color;


 public class Cell{

    int row;
    int col;
    int value;
    boolean locked;


    //initialize all values to 0 or false
    public Cell(){
        this.row = 0;
        this.col = 0;
        this.value = 0;
        this.locked = false;

    }


    //initialize the row, column, and value fields to the given parameter values. Set the locked field to false;
    public Cell (int row, int col, int value){
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = false;
    }


    //initialize all of the Cell fields given the parameters
    public Cell (int row, int col, int value, boolean locked){
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = locked;
    }


    //return the Cell's row index
    public int getRow(){
        return this.row;
    }


    //return the Cell's column index
    public int getCol(){
        return this.col;
    }


    //return the Cell's value
    public int getValue(){
        return this.value;
    }


    //set the Cell's value
    public void setValue (int newval){
        this.value = newval;
    }


    //return the value of the locked field
    public boolean isLocked(){
        return this.locked;
    }


    //set the Cell's locked field to the new value
    public void setLocked(boolean lock){
        this.locked = lock;
    }


    //turns the cell into a string
    public String toString(){
        String numString = this.value + "";
        return numString;
    
    }


    //draws the cell
    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }

   
 }

/*
 * filename: Board.java
 * author: Vivian Nguyen
 * date: Oct 10, 2022
 * Project 4
 */

import java.io.*;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Board {

    private Cell[][] sudCell;
    public static final int SIZE = 9;
    public boolean finished;


    //creates a board and sets each cell to zero
    public Board(){
        this.sudCell = new Cell[Board.SIZE][Board.SIZE];
        for ( int r = 0; r < Board.SIZE; r++){
            for ( int c = 0 ; c < Board.SIZE; c++){
                sudCell[r][c] = new Cell(r,c,0) ;
            }
        }
    }

    //creates a board, sets each cell to zero, and dictates a locked amount
    public Board(int lockAmnt){
        Random ran = new Random();
        int count = 0;

        sudCell = new Cell[Board.SIZE][Board.SIZE];
        for ( int r = 0; r< Board.SIZE; r++){
            for ( int c = 0; c < Board.SIZE; c++){
                sudCell[r][c] = new Cell(r,c,0);
            }
        }
        while (count != lockAmnt){
            int first = ran.nextInt(0,9);
            int second = ran.nextInt(0,9);
            int third = ran.nextInt(1,10);
            if ( validValue(first, second, third)){
                set(first,second,third);
                count++;
            }
        }
    }


    //multi-line string representation of the board
    public String toString(){
        String sudRep = "";
       
        for ( int r = 0; r < Board.SIZE; r++){
            for ( int c = 0; c <Board.SIZE; c++){
                sudRep += sudCell[r][c].toString() + " " ;
                sudRep += (c==2 || c==5)?"| ":"";
            }
            sudRep += (r ==2 || r==5)?"\n------|-------|------":"";
            sudRep += "\n";
        }
       return sudRep;
    }


    //returns the number of columns
    public int getCols(){
        return Board.SIZE;
    }


    //returns the number of rows
    public int getRows(){
        return Board.SIZE;
    }

    
    //returns the Cell at location r, c
    public Cell get( int r, int c){
        return sudCell[r][c];
    }


    //returns whether the Cell at r, c, is locked
    public boolean isLocked ( int r, int c){
        return this.sudCell[r][c].isLocked();
    }


    // returns the number of locked Cells on the board
    public int numLocked(){
        int count = 0;
        for (int r = 0 ; r < Board.SIZE; r++){
            for ( int c = 0; c < Board.SIZE; c++){
                if (sudCell[r][c].isLocked() == true){
                    count++;
                }
            }
        }
        return count;
    }


    //returns the value at Cell r, c
    public int value (int r ,int c){
        return sudCell[r][c].getValue();
    }


    //sets the value of the Cell at r, c
    public void set( int r , int c, int value){
        sudCell[r][c].setValue(value);
    }

    public boolean read(String filename) {
        try {
            int r = 0;
          // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
          FileReader fr = new FileReader(filename);
          // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
          BufferedReader br = new BufferedReader(fr);
    
          // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
          String line = br.readLine();
          // start a while loop that loops while line isn't null
          while(line != null){
              // assign to an array of type String the result of calling split on the line with the argument "[ ]+"
              String[] str = line.split("[ ]+") ;
              //create cells
              for (int c =0; c<this.getCols(); c++){
                this.sudCell[r][c] = new Cell(r , c, Integer.parseInt(str[c]),Integer.parseInt(str[c])!=0);
              }
              r++;
              // print the String (line)
              System.out.println(line);
              // print the SIZE of the String array (you can use .length)
            //   System.out.println(str.length);
              // assign to line the result of calling the readLine method of your BufferedReader object.
              line = br.readLine();
          }
          // call the close method of the BufferedReader
          br.close();
          return true;
        }
        catch(FileNotFoundException ex) {
          System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("Board.read():: error reading file " + filename);
        }
    
        return false;
      }


      //tests if a given value is valid
    public boolean validValue( int row, int col, int value){
        //value has to be within the 1-9 range
        if (  value > 9 || value< 1){
        
            return false;
        }
        // System.out.println(sudCell.length + " " + sudCell[0].length);

        //the same value cannot be in the same column
        for ( int r = 0; r <Board.SIZE ; r++){
            
            if (r != row){
    
                if (sudCell[r][col].getValue()==value){
                    return false;
                }
            }

        }

        //the same value cannot be in the same row
        for (int c = 0; c<this.getCols() ; c++){
            if ( c!= col){
            if (sudCell[row][c].getValue()==value){
                return false;
            }
        }
    }

        //the same value cannot be the same 3x3 square
        int startR = (row / 3)*3;
        int startC = (col / 3)* 3;

        for (int r =startR; r < startR + 3; r ++){
            for (int c =startC; c < startC+3; c ++){
                if (!(r == row && c == col)){

                     if (sudCell[r][c].getValue() == value){
                         return false;

                     }
                }
                    

                }
            
        }
        return true;

        }


        //tests if the final solution follows the rules of sudoku
        public boolean validSolution(){
            for ( int r = 0; r < Board.SIZE; r++){
                for ( int c = 0; c< Board.SIZE; c++){
                    if (sudCell[r][c].getValue()== 0){
                        return false;
                    }
                    if (!validValue(r,c, sudCell[r][c].getValue())){
                        return false;
                    }
                }
            }
            return true;
        }
      

        //draws the board
        public void draw(Graphics g, int scale){
            for(int i = 0; i<9; i++){
                for(int j = 0; j<9; j++){
                    sudCell[i][j].draw(g, j*scale+5, i*scale+10, scale);
                }
            } if(finished){
                if(validSolution()){
                    g.setColor(new Color(0, 127, 0));
                    g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
                } else {
                    g.setColor(new Color(127, 0, 0));
                    g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
                }
            }
        }
    

      public static void main (String args[]){


        //testing out the read method
        // if (args.length == 0){
        //     System.out.println("Error: Missing command line arguments!");
        //     System.out.println("Command line should look like: java Board <filename>");
        // }
        // newBoard.read(args[0]);


        //testing out if the board will initially print a 9x9 grid with all zeroes
        Board newBoard = new Board();
        // System.out.print(newBoard.toString());
        
        //testing Board with locked numbers
        // Board test = new Board(10);
        // System.out.print(test);
        
        // //testing valid methods
        System.out.println(newBoard.validSolution());
        System.out.println(newBoard.validValue(4,3,2));



        //Testing out methods
        System.out.println(newBoard.get(3,3));
        System.out.println(newBoard.value(4,5));
        System.out.println(newBoard.numLocked());
        newBoard.set(6,3,8);
        System.out.println(newBoard.toString());
        System.out.println(newBoard.get(6,3));


        
      }
      
}

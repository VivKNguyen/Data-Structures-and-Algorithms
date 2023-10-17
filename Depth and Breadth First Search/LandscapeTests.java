/*
 * filename: CellQueueTests.java
 * author: Vivian Nguyen
 * date : October 23
 * Project 5
 */

import java.util.ArrayList;
public class LandscapeTests {

    public static void landscapeTests() {

        // case 1: testing Landscape(int, int, doubled)
        {
            // set up
            Landscape l1 = new Landscape(2, 4, 0.25);
            Landscape l2 = new Landscape(10, 10, 0.75);
            

            // verify
            new LandscapeDisplay(l1, 20);
            new LandscapeDisplay(l2, 30);

            // test
            assert l1 != null : "Error in Landscape::Landscape(int, int, chance)";
            assert l2 != null : "Error in Landscape::Landscape(int, int, chance)";
        

        // case 2: testing getStart() 
        
            //use set up from case 1
        
            

            // verify
            
            int rowOne = l2.getStart().getRow();
            int colOne = l2.getStart().getCol();

            System.out.println("ROW " + rowOne);
            System.out.println("COLUMN " + colOne);
            
            // test
            assert l2.getStart().getRow() == rowOne : "Error in Landscape :: getStart()";
            
        

        // case 3: testing getRows() and getCols()
        
            // use the set up from case 1


            // verify
            System.out.println(l1.getRows() + " == 2");
            System.out.println(l1.getCols() + " == 4");
            System.out.println(l2.getRows() + " == 10");
            System.out.println(l2.getCols() + " == 10");


            // test
            assert l1.getRows() == 2 : "Error in Landscape :: getRows()";
            assert l1.getCols() == 4 : "Error in Landscape :: getCols()";
            assert l2.getRows() == 10 : "Error in Landscape :: getRows()";
            assert l2.getCols() == 10 : "Error in Landscape :: getCols()";

        


        // case 4: testing getCell(int, int)
        
            // use set up from case 1


            // verify
            Cell c = l2.getCell(3, 3);
            System.out.println(c + " == cell reference");


            // test
            assert l2.getCell(3,3) == c : "Error in Landscape :: getCell()";

        

        // case 6: testing getNeighbors()
        
            // use set up from case 1


            // verify
            Cell cell = new Cell(3,3,Cell.Type.FREE);
            ArrayList list = l2.getNeighbors(cell);
            System.out.println(list + " == list of 4 cell references");


            // test
            assert l2.getNeighbors(c) == list : "Error in Landscape :: getNeighbors()";

        }


    }


    public static void main(String[] args) {

        landscapeTests();
    }
}
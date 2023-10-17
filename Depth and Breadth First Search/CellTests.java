

/*
 * filename: CellTests.java
 * author: Vivian Nguyen
 * date : October 23
 * Project 5
 */



public class CellTests {

    public static void main (String[] args){

        // case 1: Constructor
        {

        //setup

        Cell c1 = new Cell( 3,4, Cell.Type.OBSTACLE);

        //test
        assert c1 != null : "Error in Cell::Cell()";
        }

        //case 2: rows and columns
        {
        //setup
        Cell c1 = new Cell(3,4,Cell.Type.OBSTACLE);

        //verify
        System.out.println(c1.getRow() + " == 3");
        System.out.println(c1.getCol() + " == 4");

        //test 
        assert c1.getRow() == 3 : "Error in Cell:: Cell()";
        assert c1.getCol() == 4 : "Error in Cell :: Cell()";
        }

        // case 3: type

        {
        
        //setup
        Cell c1 = new Cell(3,4,Cell.Type.OBSTACLE);
        Cell c2 = new Cell(3,4,Cell.Type.FREE);
        Cell c3 = new Cell(3,4,Cell.Type.START);
        Cell c4 = new Cell(3,4,Cell.Type.TARGET);

        //verify
        System.out.println(c1.getType() + " == OBSTACLE");
        System.out.println(c2.getType() + " == FREE");
        System.out.println(c3.getType() + " == START");
        System.out.println(c4.getType() + " == TARGET");

        //test
        assert c1.getType() == Cell.Type.OBSTACLE : "Error in Cell :: Cell()";
        assert c2.getType() == Cell.Type.FREE : "Error in Cell :: Cell()";
        assert c3.getType() == Cell.Type.START : "Error in Cell :: Cell()";
        assert c4.getType() == Cell.Type.TARGET : "Error in Cell :: Cell()";
        }

        //case 4: visited
        {
        
        //setup
        Cell c1 = new Cell(3,4,Cell.Type.OBSTACLE);
        Cell c2 = new Cell(3,4,Cell.Type.START);
        c2.visitFrom(c1);

        //verify
        System.out.println(c1.visited() + " == false");
        System.out.println(c2.visited() + " == true");
        System.out.println(c2.getPrev() + "  == location of c1");

        //test
        assert c1.visited() == false :"Error in Cell :: Cell()";
        assert c2.visited() == true: "Error in Cell :: Cell()";
        assert c2.getPrev() == c1 : "Error in Cell :: Cell()";

        }



        
    }
    
}

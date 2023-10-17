/*
 * filename: CellQueueTests.java
 * author: Vivian Nguyen
 * date : October 23
 * Project 5
 */




public class CellQueueTests {

    public static void main (String[] args){

        // case 1: Constructor
        {
        //setup
        CellQueue queue = new CellQueue();

        //verify
        System.out.println(queue);

        //test
        assert queue != null : "Error in CellQueue::CellQueue()";

        }

        //case 2: offer
        {

        //setup
        CellQueue queue = new CellQueue();
        for (int i = 0; i < 7; i++){
            queue.offer(new Cell());
        }

        //verify
        System.out.println(queue.size() + " == 7");

        //test
        assert queue.size() == 7 : "Error in CellQueue::CellQueue()::offer()";

        }

        //case 3: peek
        {

        //setup
        CellQueue queue = new CellQueue();
        for(int i = 1; i < 5; i++){
            queue.offer(new Cell(i, i, Cell.Type.FREE));
        }
        Cell peek = queue.peek();

        //verify
        System.out.println(peek + " == reference to the peeked cell");
        System.out.println(queue.size() + " == 4");

        //test
        assert queue.peek() == peek : "Error in CellQueue::peek()";
        assert queue.size() == 4 : "Error in CellQueue::peek()";


        }

        //case 4: poll
        {
        //setup
        CellQueue queue = new CellQueue();
        for(int i = 1; i < 5; i++){
            queue.offer(new Cell(i, i, Cell.Type.FREE));
        }
        Cell poll = queue.poll();

        //verify
        System.out.println(poll + " == reference to top cell");
        System.out.println(queue.size() + " == 3");
        
        //test
        assert queue.poll() == poll : "Error in CellQueue::poll()";
        assert queue.size() == 3 : "Error in CellQueue::poll()";


        }
    }
    
}

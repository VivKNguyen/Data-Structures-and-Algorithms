

/*
 * filename: HeapTests.java
 * author: Vivian Nguyen
 * date: Nov 28, 2022
 * Project 8
 * CS231 Section B
 */

import java.util.Comparator;




public class HeapTests {

    public static void main(String[] args){

        //case 1: offer and size
        {
        //setup

        Heap<Integer> test = new Heap<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;

            }

        });

        test.offer(3);
        test.offer(8);
        test.offer(10);

        //verify
        System.out.println(test.size() + " == 3");

        //test
        assert test.size() == 3: "Error in Heap:: size()";
    }

    //case 2: capacity and resize
    {
    //setup

    Heap<Integer> test = new Heap<Integer>(new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;

        }

    });
    
    //verify

    System.out.println(test.capacity() + " == 16");
    test.resize(90);
    System.out.println(test.capacity() + " == 90");

    //test
    assert test.capacity() == 16: "Error in Heap:: capacity()";
    assert test.capacity() == 90: "Error in Heap:: capacity()";
    }

    //case 3: poll
    {
    //setup
    Heap<Integer> test = new Heap<Integer>(new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;

        }

    });

    test.offer(10);
    test.offer(3);
    test.offer(5);
    test.offer(90);

    //verify
    System.out.println(test.poll() + " == 90");
    System.out.println(test.size() + " == 3");
    System.out.println(test.poll() + " == 10");

    //test
    assert test.poll() == 90: "Error in Heap:: poll()";
    assert test.size() == 3 : "Error in Heap:: poll()";
    }
    
}}

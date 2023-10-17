/*
 * filename: CommonWordsFinderTests.java
 * author: Vivian Nguyen
 * date: Nov 28, 2022
 * Project 8
 * CS231 Section B
 */
public class CommonWordsFinderTests {


    public static void main (String [] args){
        //case 1: readFile

        {
        //setup
        CommonWordsFinder cwf = new CommonWordsFinder(4);

        //verify
        System.out.println(cwf.readFile("counttest.analyzed.txt") + " == false");

        //test
        assert cwf.readFile("counttest.analyzed.txt") == false: "Error in CommonWordsFinder::readFile()";

        }

        //case 2: getFrequency

        {
        //setup
        CommonWordsFinder cwf = new CommonWordsFinder(4);
        cwf.readFile("counttest.analyzed.txt");
        //verify
        cwf.getFrequency();

        }


    }
    
}

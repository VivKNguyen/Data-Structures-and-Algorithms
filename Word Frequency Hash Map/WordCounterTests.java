/*
 * filename: WordCounterTests.java
 * author: Vivian Nguyen
 * date: November 18, 2022
 * Project 7
 */

import java.util.ArrayList;

public class WordCounterTests {


    public static void main(String [] args){

        //case 1: constructor

        {
        //setup
        WordCounter wc = new WordCounter("bst");
        WordCounter wc2 = new WordCounter("hash");

        

        //test
        assert wc.getClass() != null: "Error in WordCounter:: WordCounter()";
        assert wc2.getClass() != null: "Error in WordCounter:: WordCounter()";
        }


        //case 2: readWords

        {
        //setup 
        WordCounter wc = new WordCounter("hash");

        //verify
        ArrayList<String> words = wc.readWords("counttest.txt");
        System.out.println(words + " != null");

        //test
        assert words != null: "Error in WordCounter :: readWords()";

        }

        //case 3: buildMap
        {
        //setup
        WordCounter wc = new WordCounter("hash");
        ArrayList<String> words = wc.readWords("counttest.txt");


        //verify
        double time = wc.buildMap(words);
        System.out.println(time + " == some time (ms)");

        //test
        assert time != 0.0 : "Error in WordCounter:: buildMap()";
        }

        //case 4: totalWordCount
        {
        //setup
        WordCounter wc = new WordCounter("hash");
        ArrayList<String> words = wc.readWords("counttest.txt");
        wc.buildMap(words);

        //verify
        System.out.println(wc.totalWordCount() + " == 27");

        //test
        assert wc.totalWordCount() == 27 : "Error in WordCounter:: totalWordCount()";

        }

        //case 5: unique word count
        {
        WordCounter wc = new WordCounter("hash");
        ArrayList<String> words = wc.readWords("counttest.txt");
        wc.buildMap(words);

        //verify
        System.out.println(wc.uniqueWordCount() + " == 11");

        //test
        assert wc.uniqueWordCount() == 11 : "Error in WordCounter:: uniqueWordCount()";
        }


        //case 6: get count and get frequency
        {
        //setup
        WordCounter wc = new WordCounter("hash");
        ArrayList<String> words = wc.readWords("counttest.txt");
        wc.buildMap(words);

        //verify
        System.out.println(wc.getCount("foolishness") + " == 1");
        System.out.println(wc.getFrequency("foolishness") + "== 0.0");

        //test
        assert wc.getCount("foolishness") == 1 : "Error in WordCounter:: getCount()";
        assert wc.getFrequency("foolishness") == 0 : "Error in WordCounter:: getFrequency()";

        }
    }
    
}

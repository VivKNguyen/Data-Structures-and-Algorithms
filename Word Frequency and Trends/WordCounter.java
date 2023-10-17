/*
 * filename: HashMap.java
 * author: Vivian Nguyen
 * date: Nov 6, 2022
 * Imported from Lab/Project 7 / Project 8
 * CS231
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class WordCounter {

    // holds the data structure
    MapSet<String, Integer> structure;
    int wordCount;

    // constructor, where data_structure is either "bst" or "hashmap". It should
    // create the appropriate data structure and store it in the map field
    public WordCounter(String data_structure) {
        this.wordCount = 0;

        if (data_structure.equals("bst")) {
            this.structure = new BSTMap<String, Integer>();
        } 
        // else if (data_structure == "hasharray") {
        //     this.structure = new HashMapArrayList<String,Integer>();
            
        // }

        else {
            this.structure = new HashMap<String, Integer>();

        }
    }

    // given the filename of a text file, read the text file and return an ArrayList
    // list of all the words in the file.
    public ArrayList<String> readWords(String filename) {

        // create empty arraylist
        ArrayList<String> file = new ArrayList<String>();

        try {

            // create buffered reader object
            BufferedReader br = new BufferedReader(new FileReader(filename));
            // get first line
            String line = br.readLine();
            // while lines exist
            while (line != null) {
                // splits the file into an array of strings
                String[] words = line.split("[^a-zA-Z0-9']");

                for (String word : words) {
                    file.add(word);
                }
                line = br.readLine();
            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return file;

    }

    // given an ArrayList of words, put the words into the map data structure.
    // Return the time taken in ms. Record the time using System.nanoTime().
    public double buildMap(ArrayList<String> words) {
        //clear the structure
        this.structure.clear();

        long start = System.nanoTime();

        for (String word : words) {
            int count = this.structure.get(word) != null ? this.structure.get(word) + 1 : 1;
            this.structure.put(word, count);
        }
        long end = System.nanoTime();
        double time = end - start;
        //converts from nanoseconds to milliseconds
        time = time/1000000;
        return time;
    }


    // clears the data structure
    public void clearMap() {
        this.structure.clear();
    }


    // return the total word count from the last time readWords was called
    public int totalWordCount() {
        int count = 0;
        //counts how many items are in an arraylist from values
        ArrayList<Integer> values = this.structure.values();
        for (Integer i : values) {
            count += i;
        }
        return count;

    }


    // return the unique word count
    public int uniqueWordCount() {
        return this.structure.size();
    }


    // return the number of times the word occurred in the list of words.
    public int getCount(String word) {
        if (this.structure.containsKey(word) == false) {
            return 0;
        }

        else {
            return this.structure.get(word) != null ? this.structure.get(word) : 0;
        }
    }


    // returns a double that represents the frequency of a word
    public double getFrequency(String word) {
        int count = this.getCount(word);
        int total = this.totalWordCount();

        // return 0 if the word does not exist in the structure
        if (this.structure.containsKey(word) == false) {
            return 0;
        }
        double freq = (double)count / total;

        return freq;
    }


    // writes a word count file given the current set of words in data structure
    public boolean writeWordCount(String filename) {

        try {
            // create filewriter object
            FileWriter writer = new FileWriter(filename);
            // first line is the total word count
            writer.write("totalWordCount: " + this.totalWordCount() + "\n");
            // writes the bst in preorder using the toString method
            writer.write(this.structure.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;

    }

    // read a word count file given the filename, clears the map, puts the words
    // into map structure

    public boolean readWordCount(String filename) {
        this.structure.clear();
        this.wordCount = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(filename));
            // get first line
            String line = br.readLine();
            // while lines exist
            while (line != null) {
                // split word
                String[] words = line.split(" ");
                // if first line is totalWordCount
                if (words[0].equals("totalWordCount: ")) {
                    this.wordCount = Integer.parseInt(words[1]);
                }
                // add to map
                else {
                    this.structure.put(words[0], Integer.parseInt(words[1]));
                }
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IOException");
        }

        return false;
    }


    //for exploration, gives an average of the run times.
    public double robustAverage(String filename) {
        //create an arraylist of words from readWords
        ArrayList<String> robList = this.readWords(filename);
        //create an empty arraylist that will hold the times taken to build the maps
        ArrayList<Double> times = new ArrayList<Double>();
        //takes 5 times
        for (int i = 0; i < 6; i++) {
            //buildMap from the readWords arraylist
            times.add(this.buildMap(robList));
            
        }

        //get the maximum time from list of times
        double maximum = times.get(0);
        for (int i = 1; i < times.size(); i++) {
            if (maximum < times.get(i)) {
                maximum = times.get(i);
            }
        }
        //get the minimum time from list of times
        double minimum = times.get(0);
        for ( int i = 1; i < times.size(); i++){
            if (minimum > times.get(i)){
                minimum = times.get(i);
            }
        }

        //remove the max and min values
        times.remove(times.indexOf(maximum));
        times.remove(times.indexOf(minimum));
        
        //find average of the times now that the max and min are removed
        double sum = 0;
        for (double i : times){
            sum += i;

        }

        double average = (sum/ times.size()); 
        return average;
        }

    

    public static void main(String[] args) {

        // testing methods for hash data type
        // WordCounter counter = new WordCounter("hash");

        // System.out.println(counter.readWords("counttest.txt"));

        // System.out.println(counter.buildMap(counter.readWords("counttest.txt")));

        // System.out.println(counter.getCount("of"));
        // System.out.println(counter.totalWordCount());
        // System.out.println(counter.getFrequency("of"));
        // counter.writeWordCount(args[0]+".analyzed.txt");

        // testing methods for bst data type
        WordCounter mapCounter = new WordCounter("bst");
        System.out.println(mapCounter.readWords("counttest.txt"));

        System.out.println(mapCounter.buildMap(mapCounter.readWords("counttest.txt")));

        // System.out.println(mapCounter.getCount("of"));
        // System.out.println(mapCounter.totalWordCount());
        System.out.println("Freq" + mapCounter.getFrequency("of"));
        // mapCounter.writeWordCount(args[0]+".analyzed.txt");

        //TO RUN: java WordCounter <datastructure><filename>
        // WordCounter counter = new WordCounter(args[0]);
        // System.out.println("ROBUST AVERAGE: " +counter.robustAverage(args[1]) + "ms");
        // // System.out.println("COLLISIONS: " + ((HashMap<String, Integer>) counter.structure).getCollisions());

        // System.out.println("TOTAL WORD COUNT: " +counter.totalWordCount());
        // System.out.println("UNIQUE WORD COUNT: " + counter.uniqueWordCount());
        
        // System.out.println("DEPTH: " + ((BSTMap<String, Integer>) counter.structure).maxDepth());

        // counter.writeWordCount(args[0] +".analyzed.txt");
        

        

    }

}
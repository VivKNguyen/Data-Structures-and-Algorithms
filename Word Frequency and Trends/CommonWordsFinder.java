
/* 
 * filename: CommonWordsFinder.java
 * author: Vivian Nguyen
 * date: Nov 14, 2022
 * Project 8
 * CS231 
 */



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CommonWordsFinder {

    public Heap<MapSet.KeyValuePair<String, Integer>> heap;
    public int wordCount;
    public int N;


    //constructs the CommonWordsFinder with a set amount of how many frequent words you want to find
    public CommonWordsFinder(int N){
        this.N = N;
        this.heap = new Heap<>(new KeyValuePairComparatorByValue<String,Integer>());
        this.wordCount = 0;

    }

    //create a method to read a file into a heap
    //NOTE: has to run with the analyzed files
    public boolean readFile( String filename){

        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
             // while lines exist
             while (line != null) {
                // split word by spaces
                String[] words = line.split(" ");
               
                // if first line is totalWordCount
                if (words[0].equals("totalWordCount:")) {
                    this.wordCount = Integer.parseInt(words[1]);
                }
                if (words.length == 1) {
                    System.out.println("HERE " +words[0]);
                }
                // add to map
                else {
                    this.heap.offer(new MapSet.KeyValuePair<String,Integer>(words[0], Integer.parseInt(words[1])));
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


    //gets the frequency
    public void getFrequency(){
        //N is how many frequent words I want to get
        System.out.println("WORD COUNT " +this.wordCount);
        for ( int i = 0; i < this.N; i ++){
            //get the highest priority 
            MapSet.KeyValuePair<String,Integer> kvp = this.heap.poll();
            // System.out.println("KEY " + kvp.getKey());
            // System.out.println("VALUE " + kvp.getValue());
            //get the frequency of the word
            double freq = (double) kvp.getValue() / (this.wordCount)*1.0 ;
            System.out.println("FREQ " +freq*100.0);
            System.out.println(kvp.getKey() + " " + freq);
        }
        System.out.println();
    }

    //NOTE: Has to run with the analyzed files
    public static void main(String[] args){
        //can use the command line to set how many words you want to find
        int diffWords = Integer.parseInt(args[0]);
        
       
        CommonWordsFinder finder = new CommonWordsFinder(diffWords);
        //args[1] is the filename
        finder.readFile(args[1]);
        finder.getFrequency();

            
    }

    


    
}

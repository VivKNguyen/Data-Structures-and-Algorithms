/* 
 * filename: WordCounter.java
 * author: Vivian Nguyen
 * date: 10/31/2022
 * Project 6
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;




import java.io.FileWriter;

public class WordCounter {

    BSTMap<String, Integer> bst;
    int wordCount;


    //constructs a word counter that initializes the bst and wordcount
    public WordCounter(){
        this.bst = new BSTMap<String,Integer> ();
        this.wordCount = 0;
    }


    //gives the wordcount from a file
    public void analyze(String filename) {
        try {
          // cleap map
          this.bst.clear();
          this.wordCount = 0;
          //creates the bufferedreader object
          BufferedReader br = new BufferedReader(new FileReader(filename));
          // read first line
          String line = br.readLine();
          // while lines remain
          while(line != null) {
            // split word
            String[] words = line.split("[^a-zA-Z0-9']");
            // for each word
            for (String word : words) {
              // if word is not empty
              if(word.length()>0) {
                word = word.toLowerCase();
                // if word is present, update count by 1
                if(this.bst.containsKey(word)) {
                  this.bst.put(word, this.bst.get(word) + 1);
                }
                // else put it on the map
                else {
                  this.bst.put(word, 1);
                }
                //increase the word count
                this.wordCount++;
              }
              
            }
            line = br.readLine();
          }
          //closes
          br.close();
        } catch (FileNotFoundException e) {
          System.out.println("File not found");
        } catch (IOException e) {
          System.out.println("IOException");
        }
      }


      //returns the total word count
    public int getTotalWordCount(){
        return this.wordCount;
    }


    //returns how many unique words there are
    public int getUniqueWordCount(){
        return this.bst.size();
    }


    //returns frequency value associated with the word
    public int getCount(String word){
        if (this.bst.containsKey(word)){
            return this.bst.get(word);
        }
        else {
            return 0;
        }

    }


    //returns associated frequency=value of word/total word count
    public double getFrequency( String word ){
        if (this.bst.containsKey(word)) {
            return (double) this.bst.get(word) / this.wordCount;
          }
          else {
            return 0;
          }
        }



    //writes the bst of a file into a new file
    public void writeWordCountFile(String filename){
      
      try {
        //create filewriter object
        FileWriter writer = new FileWriter(filename);
        //first line is the total word count
        writer.write("totalWordCount: " + this.wordCount + "\n");
        //writes the bst in preorder using the toString method
        writer.write(this.bst.toString());
        writer.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }



    //reads the contents of a word count file and reconstructs the fields of the WordCount object, including the BSTMap
    public void readWordCountFile( String filename ){
      this.bst.clear();
      this.wordCount = 0;

    try {
      
         BufferedReader br = new BufferedReader(new FileReader(filename));
         // get first line
          String line = br.readLine();
         // while lines exist
         while(line != null) {
           // split word
             String[] words = line.split(" ");
             // if first line is totalWordCount
             if(words[0].equals("totalWordCount: ")) {
                 this.wordCount = Integer.parseInt(words[1]);
               }
          // add to map
        else {
             this.bst.put(words[0], Integer.parseInt(words[1]));
        }
          line = br.readLine();
      }
      br.close();
       } catch (FileNotFoundException e) {
         System.out.println("File not found");
       } catch (IOException e) {
          System.out.println("IOException");
       }
   }




    

    public static void main ( String[] args){
      //TESTING THE METHODS
        // WordCounter test = new WordCounter();
        // test.analyze("counttest.txt");
        // System.out.println(test.bst);
        // System.out.println(test.getTotalWordCount());
        // System.out.println(test.getUniqueWordCount());
        // System.out.println(test.getFrequency("times"));
        // System.out.println(test.getCount("times"));

        WordCounter count = new WordCounter();

        long before = System.currentTimeMillis();
        //command line dictates what file is being analyzed
        count.analyze(args[0]);
        long after = System.currentTimeMillis();
        //finding the time it takes to analyze a file
        System.out.println("Time before analyzing: " + before);
        System.out.println("Time after analyzing: " + after);
        System.out.println("Difference in time: " + (before-after));
        //prints out the unique word count for use in exploration
        System.out.println(count.getUniqueWordCount());
        //saves the analyzed file
        count.writeWordCountFile(args[0]+".analyzed.txt");
        
        

 
        
    }

    
    
}

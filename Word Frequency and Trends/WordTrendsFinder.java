/* 
 * filename: WordTrendsFinder.java
 * author: Vivian Nguyen
 * date: Nov 28, 2022
 * Project 8
 * CS231 
 */



public class WordTrendsFinder {

    public static void main (String [] args){

        //TO RUN: java WordTrendsFinder reddit_comments_ 2008 2015 "word 1" "word 2" "word 3"...
        //baseFilename is reddit_comments_
        String baseFilename = args[0];
        //fileNumberBegin is 2008
        int fileNumberBegin = Integer.parseInt(args[1]);
        //fileNumberEnd is 2015
        int fileNumberEnd = Integer.parseInt(args[2]);
        //create an array for the words that will be analyzed, will be the length of all the arguments minus the basics args
        String[] words = new String [args.length -3];
        //add the command line word args into the previously created array
        for ( int i = 3; i < args.length; i++){

            words[i-3] = args[i];

        }

       for (String word : words){
        System.out.print("Frequency of: " + word + "  ");
        for (int i = fileNumberBegin; i <= fileNumberEnd; i++){
            String fileName = baseFilename + i + ".txt";
            WordCounter counter = new WordCounter("bst");
            //find the frequencies of each word in the word array
            counter.buildMap(counter.readWords(fileName));
            System.out.print(counter.getFrequency(word) + " | ");
        }
        System.out.println();
       }
       //testing the frequency getter
    //    WordCounter wc = new WordCounter("bst");
    //    wc.buildMap(wc.readWords("counttest.txt"));
      
    //    System.out.println(wc.getFrequency("of"));


    }

    
}

/*
 * filename: HashMaoTests.java
 * author: Vivian Nguyen
 * date: Nov 18, 2022
 * Project 7 
 */




public class HashMapTests {
    


    public static void main (String [] args){

        //case 1: constructor

        {
        
        //setup:
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        HashMap<String,Integer> hm2 = new HashMap<String, Integer>(16);
        HashMap<String,Integer> hm3 = new HashMap<String, Integer>(16,0.75);

        //verify and test
        assert hm != null : "Error in HashMap:: HashMap()";
        assert hm2 != null : "Error in HashMap :: HashMap()";
        assert hm3 != null : "Error in HashMap :: HashMap()";
        }

        //case 2: put and size 

        {
        //setup:
        HashMap<String,Integer> hm = new HashMap<String,Integer>();

        //verify
        hm.put("Key", 3);
        hm.put("One", 1);
        hm.put("cow", 3);
        System.out.println(hm.size() + " == 3");

        //test
        assert hm.size() == 3 : "Error in HashMap:: size()";

        }

        //case 3: clear
        {
        //setup
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        hm.put("Key", 3);
        hm.put("One", 1);
        hm.put("cow", 3);

        //verify
        hm.clear();
        System.out.println(hm.size() + "== 0");

        //test
        assert hm.size() == 0 : "Error in HashMap:: clear()";

        }

        //case 4: get
        {
        //setup 
        HashMap<String,Integer> hm= new HashMap<String, Integer>(16);
        hm.put("Key", 3);
        hm.put("One", 1);
        hm.put("cow", 3);

        //verify
       System.out.println(hm.get("cow") + " == 3");

       //test
       assert hm.get("cow") == 3 : "Error in HashMap :: get()";

        }

        //case 5: contains Key
        {
        
        //setup
        HashMap<String,Integer> hm= new HashMap<String, Integer>(16);
        hm.put("Key", 3);
        hm.put("One", 1);
        hm.put("cow", 3);

        //verify
        System.out.println(hm.containsKey("Key") + " == true");
        System.out.println(hm.containsKey("three") + " == false");

        //test
        assert hm.containsKey("Key") == true : "Error in HashMap :: containsKey()";
        assert hm.containsKey("three") == false : "Error in HashMap :: containsKey()";
        }

        //case 6: remove

        {
        //setup
        HashMap<String,Integer> hm= new HashMap<String, Integer>(16);
        hm.put("Key", 3);
        hm.put("One", 1);
        hm.put("cow", 3);
        
        //verify
        hm.remove("Key");
        System.out.println(hm.size() + " == 2");

        //test
        assert hm.size() == 2: "Error in HashMap :: remove()";
        
        }

        //case 7: keySet
        {
        //setup
        HashMap<String,Integer> hm= new HashMap<String, Integer>(16);

        //verify
        hm.put("Key", 3);
        hm.put("One", 1);
        hm.put("cow", 3);
        System.out.println(hm.keySet() + " != null");

        assert hm.keySet() != null: "Error in HashMap :: keySet()";
        
        }

        //case 8: values
        {
        //setup 
        HashMap<String,Integer> hm= new HashMap<String, Integer>(16);

        //verify
        hm.put("Key", 3);
        hm.put("One", 1);
        hm.put("cow", 3);
        System.out.println(hm.values() + " != null" );

        //test

        assert hm.values() != null: "Error in HashMap :: values()";

        }

        //case 9: entryset

        {
         //setup 
         HashMap<String,Integer> hm= new HashMap<String, Integer>(16);

         //verify
         hm.put("Key", 3);
         hm.put("One", 1);
         hm.put("cow", 3);
         System.out.println(hm.entrySet() + " != null");
        
        }



    }
}

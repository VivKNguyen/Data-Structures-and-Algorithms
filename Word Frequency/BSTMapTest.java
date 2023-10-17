/*
 * filename: BSTMapTest.java
 * author: Vivian Nguyen
 * date: November 3, 2022
 * Project 6
 */



import java.util.ArrayList;
public class BSTMapTest {

    public static void main (String[]args){
        //case 1: constructor 

        {
        //setup
        BSTMap bst = new BSTMap();

        //verify
        System.out.println(bst);

        //test
        assert bst != null : "Error in BSTMAP :: BSTMap()";

        }

        //case 2: size
        {
        
        //setup
        BSTMap bst = new BSTMap<String,Integer>();

        //verify
        System.out.println(bst.size() + " == 0");

        //test 
        assert bst.size() == 0 : "Error in BSTMap :: size()";

        }

        //case 3: put
        {
        
        //setup
        BSTMap bst = new BSTMap<String,Integer>();
        bst.put("Key", 3);
       

        //verify
        System.out.println((bst)+ " == Key 3");

        //test
        assert bst.put("Key", 3) == "Key 3" : "Error in BSTMap :: put()";

        }

        // case 4: containsKey
        {
        //setup
        BSTMap bst = new BSTMap<String,Integer>();
        bst.put("Key", 3);

        //verify
        System.out.println(bst.containsKey("Key") + " == true");

        //test 
        assert bst.containsKey("Key") == true: "Error in BSTMap :: containsKey()";
        }

        //case 5: keySet
        {

        //setup
        BSTMap bst = new BSTMap<String,Integer>();
        bst.put("3", 3);
        bst.put("4", 4);
        bst.put("5", 5);
        bst.put("6", 6);
        ArrayList keys = bst.keySet();

        //verify 
        System.out.println(keys.toString() + " == [3, 4, 5, 6]");

        //test
        assert bst.keySet() == keys: "Error in BSTMap :: keySet()";

        }

        //case 6:values
        {

        //setup
        BSTMap bst = new BSTMap<String,Integer>();
        bst.put("3", 5);
        bst.put("4", 10);
        bst.put("5", 38);
        bst.put("6", 2);
        ArrayList values = bst.values();
        

        //verify 
        System.out.println(values.toString()+ " == [5,10,38,2]");

        //test
        assert bst.values() == values: "Error in BSTMap :: values()";
        }

        //case 7: entrySet
        {
        //setup
        BSTMap bst = new BSTMap<String,Integer>();
        bst.put("3", 5);
        bst.put("4", 10);
        bst.put("5", 38);
        bst.put("6", 2);
        ArrayList kvps = bst.entrySet();

        //verify
        System.out.println(kvps.toString() + " == [<3 -> 5>, <4 -> 10>, <5 -> 38>, <6 -> 2>]");

        //test
        assert bst.entrySet() == kvps: "Error in BSTMap :: entrySet()";
        }

        //case 8: get
        {
        //setup
        BSTMap bst = new BSTMap<String,Integer>();
        bst.put("3", 5);
        bst.put("4", 10);
        bst.put("5", 38);
        bst.put("6", 2);
        

        //verify
        System.out.println(bst.get("3") + " == 5");

        //test
        assert bst.get("3") == "5": "Error in BSTMap :: get()";

        }

        //case 9: minValue and remove

        {
        //setup
        BSTMap bst = new BSTMap<String,Integer>();
        bst.put("3", 5);
        bst.put("4", 10);
        
        
        //verify
        System.out.println(bst.minValue(bst.root) + " == 3");
        System.out.println(bst + "== 3 5 4 10");
        bst.remove("4");
        System.out.println(bst + " == 3 5");

        //test
        assert bst.minValue(bst.root) == "3": "Error in BSTMap :: minValue()";

        }


    }
    
}

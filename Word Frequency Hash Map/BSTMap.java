/*
 * filename: BSTMap.java
 * author: Vivian Nguyen
 * date: October 24
 * Project 6
 */


//imported from project 6
import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements MapSet<K,V>{

    //creating a private Node class
    private class Node{
        Node left;
        Node right;
        KeyValuePair<K,V> kvp;

        //constructor that initializes left and right nodes, and the kvp object
        public Node (KeyValuePair<K,V> kvp){
            left = null;
            right = null;
            this.kvp = kvp;
        }

        //returns the key of a kvp
        public K getKey(){
            return kvp.getKey();
        }

        //returns the value of a kvp
        public V getValue(){
            return kvp.getValue();
        }
    }

    //fields for BSTMap
    //note: had to make the root public for my testing files
    public Node root;
    protected int size;


    //standard constructor, initializes fields to defaults 0/null
    public BSTMap(){
        this.root = null;
        this.size = 0;
    }


    //returns the size
    public int size(){
        return this.size;
    }


    //resets the fields to default values
    public void clear(){
        root = null;
        size = 0;
    }

    //puts the designated key and value kvp into the tree
    public V put(K key, V value){
        return put(key, value, root);
    }


    //recursive helper function for put method
    private V put(K key, V value, Node cur){
        //if the node is null
        if (cur == null){
            //create a new node with a new kvp
            Node newNode = new Node (new KeyValuePair<K,V>(key, value));
            //set the root to this new node
            root = newNode ;
            //increment the size
            this.size++;
            return null;
        }

        //if the designated key is less than the node's key
        if (key.compareTo(cur.getKey()) < 0){
            if ( cur.left != null){
                return put(key, value,cur.left);
                //if the left reference is null
            } else{
                //create a new node and add it to the tree
                Node newNode = new Node( new KeyValuePair<K,V>(key, value));
                cur.left = newNode;
                size ++;
                return null;
            }
            //if the designated key is more than the node's key
        } else if (key.compareTo(cur.getKey()) > 0){
            //if the right reference is not null
            if (cur.right != null){
                return put(key , value,cur.right);
                //if the right reference is null
            } else{
                //create a new node and add it to the tree
                Node newNode = new Node ( new KeyValuePair<K,V>(key, value));
                cur.right = newNode;
                size++;
                return null;
            }
        } else{
            //create a value and then return it after setting it to the designated value
            V returnVal = cur.getValue();
            cur.kvp.setValue(value);
            return returnVal;
        }

    }


    //true if the map contains the designated key
    public boolean containsKey(K key){
        return this.get(key) !=null;
    }


    //Returns an ArrayList of all the keys in the map in sorted order from least to greatest. 
    public ArrayList<K> keySet(){
        //creates an arraylist that holds keys
        ArrayList<K> output = new ArrayList<K>();
        //calls recursive methods
        keySet(root, output);
        return output;

    }


    //helper function for keySet method
    private void keySet(Node cur, ArrayList<K> output){
        //if the current node is null, return
        if (cur==null) return;
        //call the method for the left reference
        keySet(cur.left, output);
        //add the node to the arraylist
        output.add(cur.getKey());
        //do the same for the right reference
        keySet(cur.right, output);
    }


    //Returns an ArrayList of all the values in the map in the same order returned by keySet()
    public ArrayList<V> values(){
        ArrayList<V> output = new ArrayList<V>();
        values(root,output);
        return output;
    }


    //helper function for values method
    private void values(Node cur, ArrayList<V> output){
        if (cur == null) return;
        //call method for left node reference
        values(cur.left, output);
        output.add(cur.getValue());
        //call method for right node reference
        values(cur.right, output);
    }


    //Returns an ArrayList of each KeyValuePair in the map in the same order as the keys as returned by keySet()
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K, V>> output = new ArrayList<KeyValuePair<K, V>> ();
        entrySet(root,output);
        return output;
    }    


    //helper function for entryset method
    private void entrySet(Node cur, ArrayList<KeyValuePair<K, V>> output ){
        if (cur == null) return;
        //call method for left node reference
        entrySet(cur.left, output);
        output.add(new KeyValuePair<K,V>(cur.getKey(), cur.getValue()));
        //call the method for the right node reference 
        entrySet(cur.right, output);
    }


    //Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    public V get(K key){
        return get(key,root);
    }


    //helper for get method
    private V get(K key, Node cur){
        if ( cur == null){
            return null;
        }
        //if designated key is less than the key of the current node
        if (key.compareTo(cur.getKey()) < 0){
            return get(key, cur.left);
        //if designated key is greater than the key of the current node
        }else if (key.compareTo(cur.getKey())>0){
            return get(key, cur.right);
        } else {
            return cur.getValue();
        }
    }


    //returns a string representation of BSTMap
    public String toString() {
        return this.toString(this.root);
      }
    

      //returns a preorder string of the tree
      private String toString(Node root) {
        if(root != null) {
          return root.getKey() + " " + root.getValue() + "\n" + toString(root.left) + toString(root.right);
        }
        else {
          return "";
        }
      }


    //EXTENSION - creating a remove method

    //returns the minimum value
    public K minValue(Node root){
        K min = root.getKey();
        while (root.left != null){
            min = root.left.getKey();
            root = root.left;
        }
        return min;
    }


    //calls the recursive method, removes a designated key
    public void remove (K key){
        root = remove(root,key);
    }


    //private helper method for the remove
    private Node remove(Node root, K key){
        //if root is null, then there is no tree
        if( root == null){
            return null;
        }

        //if node is a leaf, then disconnect it
        if (key == root.getKey()){
            //if left and right references are null, return null
            if (root.left == null && root.right == null){
                return null;
            }
            //if the node has one child, the child succeeds it
            //if only the right reference is null, return the left reference
            if (root.right == null){
                return root.left;
            }
            //if only the left reference is null, return the right reference
            if (root.left == null){
                return root.right;
            }
            //if the node has 2 children, find smallest value on right subtree
        
            //minVal is the key with the minimum value
            K minVal = minValue(root.right);
            //set minVal = the root's key
            minVal = root.getKey();
            //recursive call, deletes duplicate smallest value node
            root.right = remove(root.right, minVal);
            return root;

        }
        //traverses until the key is found
        if (key.compareTo(root.getKey()) < 0){
            root.left = remove(root.left,key);
            return root;
        }
        root.right= remove(root.right,key);
        return root;

    }


    //method to find the depth of the bst (used for project 7)
    public int maxDepth(){
        return this.maxDepth(root);
    }

    private int maxDepth(Node root){
        if ( root == null){
            return 0;
        }
        else{
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);

            if (leftDepth > rightDepth){
                return (leftDepth + 1);
            } else{
                return (rightDepth +1);
            }
        }

    }


    public static void main (String [] args){
        //makes a new bstmap with string and integer objects
        BSTMap<String, Integer> test = new BSTMap<String, Integer>();
        //putting keys and values into the bst map
        test.put("30", 70);
        test.put("40", 40);
        test.put("50", 50);
        test.put("60", 60);
        test.put("70", 70);
        //prints out the bst
        System.out.println(test);
        //remove the key: "60"
        test.remove("60");
        //testing the remove method
        System.out.println("After remove: "+"\n"+test);
        //testing get method
        System.out.println(test.get("30"));
        //testing size function
        System.out.println(test.size());
        //testing containsKey
        System.out.println(test.containsKey("900"));
        System.out.println(test.maxDepth());
        
    }
    
}
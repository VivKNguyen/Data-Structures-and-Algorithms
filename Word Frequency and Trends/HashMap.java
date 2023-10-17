/*
 * filename: HashMap.java
 * author: Vivian Nguyen
 * date: Nov 20, 2022
 * Imported from Lab/Project 7, Project 8
 * CS231
 */


import java.util.ArrayList;
import java.util.LinkedList;



 @SuppressWarnings({"unchecked"})
 public class HashMap<K, V> implements MapSet<K,V>{

    

    public LinkedList<KeyValuePair<K,V>>[]map;
    public int size;
    public int collisions;


    //constructor 
    public HashMap(){
        map = (LinkedList<KeyValuePair<K,V>>[]) new LinkedList[100];
        size =0;
        collisions =0;
    }


    //constructor that sets an initial capacity
    public HashMap(int initialCapacity){
        map = (LinkedList<KeyValuePair<K,V>>[]) new LinkedList[initialCapacity];
        size = 0;
        collisions =0;
    }

    //with capacity and loadfactor
    public HashMap(int capacity, double loadFactor){
        map = (LinkedList<KeyValuePair<K,V>>[]) new LinkedList[capacity];
        loadFactor = 0.75;
        collisions = 0;
    }

    //returns the size
    public int size(){
        return this.size;
    }


    //sets fields to defaults
    public void clear(){

        map = (LinkedList<KeyValuePair<K,V>>[]) new LinkedList[100];
        size =0;
        collisions = 0;
    }


    //returns capacity
    private int capacity(){
        return map.length;
    }

    //returns the index that will be used by any given key for this
    private int hash (K key){
        return Math.abs(key.hashCode()%capacity());
    }
       
       
    //puts a specifiec key and value into the hashmap
    public V put(K key, V value){
        int index = hash(key);
        //if the spot is null
        if (map[index] == null){
            //create a new bucket
            map[index]= new LinkedList<KeyValuePair<K,V>>();
            map[index].add(new KeyValuePair<K,V>(key, value));
            size++;
            return null;
    
        } 
        else{
            for (KeyValuePair<K,V> kvp : map[index]){
                if (kvp.getKey().equals(key)){
                    V oldValue = kvp.getValue();
                    kvp.setValue(value);
                    return oldValue;
                }
            }
            //add the new kvp into the hashmap and increase the size
            map[index].add(new KeyValuePair<K,V>(key,value));
            size++;
        }
            //making sure the load factor does not exceed 0.75
        if (this.size >= this.capacity()* 0.75){
            this.rehash();

            //if size gets too big in comparison to capacity, map needs to get bigger
            
        }
        return null;
    }

    //used geeks for geeks website, rehashes to account for changing the capacity
    private void rehash(){
        LinkedList<KeyValuePair<K,V>>[] temp = map; 
        map = (LinkedList<KeyValuePair<K,V>>[]) new LinkedList[map.length*2];
        size = 0;
        
        for (LinkedList<KeyValuePair<K,V>> hm : temp){
            if (hm != null){
                for (KeyValuePair<K,V> kvp : hm){
                    this.put(kvp.getKey(), kvp.getValue());
                }
            }

        }
    }


    //gets the value of a specified key
    public V get(K key){
        int index = hash(key);

        if (map[index]== null){
            return null;
        } else{
            for (KeyValuePair<K,V>kvp : map[index]){
                if (kvp.getKey().equals(key)){
                    return kvp.getValue();
                }
            }
            return null;
        }
    }


    //returns true if the map contains a certain key
    public boolean containsKey(K key){
        return this.get(key) != null;

    }

    //removes a kvp at the specified key
    public V remove(K key){
        V val = null;
        int index = hash(key);
        if ( map[index] == null){
            return null;
        } 

        else{
            for (KeyValuePair<K,V> kvp : map[index]){
                if (kvp.getKey().equals(key)){
                    map[index].remove(kvp);
                    val = kvp.getValue();
                    size--;
                   
                }
            }
            
        }
        //makes sure the loadfactor stays above a certain amount
        if (this.size <= this.capacity()* 0.4218){
            this.rehash();
        }
        return val;
    }

   
    //returns an arraylisy of all the kvps in a hashmap
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K,V>> entry = new ArrayList<KeyValuePair<K,V>> ();
        for (LinkedList<KeyValuePair<K,V>> kvps : this.map ){
            if ( kvps != null ){
                for (KeyValuePair<K, V> kvp : kvps){
                    entry.add(kvp);
                }
            }

        }
        return entry;
    }



    //returns an arraylisy of all the ksys
    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<K>();
        for (KeyValuePair<K,V> k : this.entrySet() ){
            keys.add(k.getKey());
        }
        return keys;

    }


    //returns an arraylist of all the values in a hashmap
    public ArrayList<V> values(){
        ArrayList<V> values = new ArrayList<V>();
        for (KeyValuePair<K,V> v : this.entrySet()){
            values.add(v.getValue());
        }
        return values;
    }


    //converts the mao into a readable string
    public String toString(){
        String str = " ";
        for (LinkedList<KeyValuePair<K,V>> kvps : this.map ){
            if (kvps != null){
                for (KeyValuePair<K, V> kvp : kvps){
                    str += kvp.toString() + "\n";
                }
            } 
        }
        return str;

    }


    //returns how many collisions occured
    public int getCollisions(){
        //for each linked list in the map
        for (LinkedList<KeyValuePair<K,V>> list : this.map){
            //if the linked list isn't empty and is greater than one
            if(list != null && list.size() > 1){
                this.collisions ++;
            }

        }
        return this.collisions;

    }

   

    public static void main (String [] args){
        //tetsing out the methids
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>(2);


        for (int i =0; i< 5; i++){
            hm.put(i,i+1);
        }

        System.out.println(hm.get(1));
        System.out.println(hm.get(4));
        System.out.println(hm.toString());
        System.out.println(hm.entrySet());
        System.out.println(hm.keySet());
        System.out.println(hm.values());
        System.out.println(" SIZE " +hm.size() );
        System.out.println("Capacity: " + hm.capacity());
        System.out.println(hm.containsKey(9));

        hm.remove(2);
        System.out.println("After remove: " +"\n"+hm.toString());
        hm.clear();
        System.out.println("After clear: " +hm.toString());
       

    }


 }
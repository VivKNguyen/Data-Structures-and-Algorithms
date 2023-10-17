/*
 * filename: HashMapArrayList.java
 * author: Vivian Nguyen
 * date: 11/17/2022
 * Project 7 Exploration
 */


import java.util.ArrayList;
    
//exact same methods but the data structure is different, refer to code comments from HashMap.java

    @SuppressWarnings({"unchecked"})
 public class HashMapArrayList<K, V> implements MapSet<K,V>{

    
    
    public ArrayList<KeyValuePair<K,V>>[] map;
    public int size;
    public int collisions;

    //constructors
    public HashMapArrayList(){
        map = (ArrayList<KeyValuePair<K,V>>[]) new ArrayList[100];
        size =0;
        collisions =0;
    }

    public HashMapArrayList(int initialCapacity){
        map = (ArrayList<KeyValuePair<K,V>>[]) new ArrayList[initialCapacity];
        size = 0;
        collisions =0;
    }

    public HashMapArrayList(int capacity, double loadFactor){
        map = (ArrayList<KeyValuePair<K,V>>[]) new ArrayList[capacity];
        loadFactor = 0.75;
        collisions = 0;
    }


    //returns the size of the hashmap
    public int size(){
        return this.size;
    }


    //sets the fields to their defaults
    public void clear(){

        map = (ArrayList<KeyValuePair<K,V>>[]) new ArrayList[100];
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
       
       
    //puts a kvp in
    public V put(K key, V value){
        int index = hash(key);

        if (map[index] == null){
            map[index]= new ArrayList<KeyValuePair<K,V>>();
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
            map[index].add(new KeyValuePair<K,V>(key,value));
            size++;
        }
            
        if (this.size >= this.capacity()* 0.75){
            this.rehash();

            //if size gets too big in comparison to capacity, map needs to get bigger
            
        }
        return null;
    }


    //used geeks for geeks website, rehashes when capacity is changed
    private void rehash(){
        ArrayList<KeyValuePair<K,V>>[] temp = map; 
        map = (ArrayList<KeyValuePair<K,V>>[]) new ArrayList[map.length*2];
        size = 0;
        
        for (ArrayList<KeyValuePair<K,V>> list : temp){
            if (list != null){
                for (KeyValuePair<K,V> kvp : list){
                    this.put(kvp.getKey(), kvp.getValue());
                }
            }

        }
    }


    //gets a value at the specified key
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


    //returns whether the map contains a key or not
    public boolean containsKey(K key){
        return this.get(key) != null;

    }

    //removes a specified key
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
        //maintains the loadfactor doesn;t get too small
        if (this.size <= this.capacity()* 0.4218){
            this.rehash();
        }
        return val;
    }

   
    //returns an arraylust of all the kvps
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K,V>> entry = new ArrayList<KeyValuePair<K,V>> ();
        for (ArrayList<KeyValuePair<K,V>> kvps : this.map ){
            if ( kvps != null ){
                for (KeyValuePair<K, V> kvp : kvps){
                    entry.add(kvp);
                }
            }

        }
        return entry;
    }


    // //returns an arraylust of all the keys
    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<K>();
        for (KeyValuePair<K,V> k : this.entrySet() ){
            keys.add(k.getKey());
        }
        return keys;

    }


     //returns an arraylust of all the values
    public ArrayList<V> values(){
        ArrayList<V> values = new ArrayList<V>();
        for (KeyValuePair<K,V> v : this.entrySet()){
            values.add(v.getValue());
        }
        return values;
    }


    //string representation
    public String toString(){
        String str = " ";
        for (ArrayList<KeyValuePair<K,V>> kvps : this.map ){
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
        for (ArrayList<KeyValuePair<K,V>> list : this.map){
            //if the linked list isn't empty and is greater than one
            if(list != null && list.size() > 1){
                this.collisions ++;
            }

        }
        return this.collisions;

    }

   

    public static void main (String [] args){
        HashMapArrayList<Integer,Integer> list = new HashMapArrayList<Integer,Integer>(2);


        for (int i =0; i< 5; i++){
            list.put(i,i+1);
        }

        System.out.println(list.get(1));
        System.out.println(list.get(4));
        System.out.println(list.toString());
        System.out.println(list.entrySet());
        System.out.println(list.keySet());
        System.out.println(list.values());
        System.out.println(" SIZE " +list.size() );
        System.out.println("Capacity: " + list.capacity());
        System.out.println(list.containsKey(9));

        list.remove(2);
        System.out.println("After remove: " +"\n"+list.toString());
        list.clear();
        System.out.println("After clear: " +list.toString());
       

    }


 }


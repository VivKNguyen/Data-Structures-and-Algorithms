/*
* filename: GraphAlgorithms.java
* author: Vivian Nguyen
* date: Nov 30, 2022
* CS231 Section B
* Project 9
*/

import java.util.ArrayList;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


public class GraphAlgorithms {

    public static Graph<String, Object> readData(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        Graph<String, Object> newGraph = new Graph<>();
        HashMap<String, Graph.Vertex<String, Object>> cities = new HashMap<>();
        //read each line of the file
        br.readLine();
        String line = br.readLine();
        //while the line is not null
        while (line != null) {
            //create an array of strings that holds each word 
            String[] contents = line.split(",");
            //create strings that represent the two states in each line, ask about this
            String state1 = contents[1];
            String state2 = contents[3];
            //if the hashmap doesn't have either state, add it
            if (!cities.containsKey(state1))
                cities.put(state1, newGraph.addVertex(state1));
            if (!cities.containsKey(state2))
                cities.put(state2, newGraph.addVertex(state2));
            //add an edge to the graph with the vertex of both states and then the seconds 
            newGraph.addEdge(cities.get(state1), cities.get(state2), Integer.parseInt(contents[5]));
            line = br.readLine();
        }
        br.close();
        return newGraph;
    }

    public static <V, E> HashMap<Graph.Vertex<V, E>, Double> shortestPaths(Graph<V, E> g, Graph.Vertex<V, E> source) {
        HashMap<Graph.Vertex<V, E>, Double> distances = new HashMap<>();
        
        //for each vertex in the vertices of the graph
        for (Graph.Vertex<V, E> vertex : g.getVertices()) {
            //put the vertex(key) and a positive infinity double into the hash map(value)
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }
        //puts the source (parameter argument) into the hashmap with a value of 0.0
        distances.put(source, 0.0);
        //create a priority queue
        PriorityQueue<Graph.Vertex<V, E>> queue = new PriorityQueue<>(new Comparator<Graph.Vertex<V, E>>() {
            @Override
            public int compare(Graph.Vertex<V, E> o1, Graph.Vertex<V, E> o2) {
                return distances.get(o1).compareTo(distances.get(o2));
            }
        });
        //for each vertex in the all the graph's vertices
        for (Graph.Vertex<V, E> vertex : g.getVertices()) {
            //put each vertex into the queue
            queue.offer(vertex);
        }
        //while the queue is not empty
        while (!queue.isEmpty()) { // O(V) where V is number of vertices
            //cur vertex is equal to the highest priority elemet into the queue
            Graph.Vertex<V, E> cur = queue.poll(); // O(log V)
            //for each edge in the edges connected to the cur vertex
            for (Graph.Edge<V, E> edgeOut : cur.edgesOut()) { // O(V)
                //
                Graph.Vertex<V, E> next = edgeOut.other(cur);

                double curDistToNext = distances.get(next);
                double newDist = distances.get(cur) + ((Graph.WeightedEdge<V, E>) edgeOut).weight;
                if (newDist < curDistToNext) {
                    distances.put(next, newDist);
                    queue.remove(next); // O(log V) for Java, but could be O(1) if implemented better
                    queue.offer(next);
                }
            }
        }
        return distances;
    }

    public static <V, E> Collection<List<Graph.Edge<V, E>>> allHamCycles(Graph<V, E> g, Graph.Vertex<V, E> start) {
        Collection<List<Graph.Edge<V, E>>> output = new ArrayList<>();
        List<Graph.Vertex<V, E>> curPath = new ArrayList<Graph.Vertex<V, E>>();
        curPath.add(start);
        allHamCycles(g, output, curPath);
        return output;
    }

    private static <V, E> void allHamCycles(Graph<V, E> g, Collection<List<Graph.Edge<V, E>>> output,List<Graph.Vertex<V, E>> curPath) {
        //if the size of the list is equal to the amount of vertices, that means that all the vertices have been visited
        if (curPath.size() == g.getVertices().size()) {
            // I'm done, I can convert this path into a sequence of edges and add that
            // sequence to my output

            //cycle is a list of edges
            List<Graph.Edge<V,E>> cycle = new ArrayList<Graph.Edge<V,E>>(); 
            for ( int index = 0; index < curPath.size() - 1; index++){
                //create an edge 
                // Graph.Edge<V,E> newEdge = new Graph.Edge<V,E>(curPath.get(index), curPath.get(index+1), g.getEdge(curPath.get(index), curPath.get(index+1)).data);
                
                //add edge to list
                
                cycle.add(g.getEdge(curPath.get(index), curPath.get(index+1)));
            }
            // Graph.Edge<V,E> newEdge = new Graph.Edge<V,E>(curPath.get(curPath.size()-1), curPath.get(0), g.getEdge(curPath.get(0), curPath.get(curPath.size()-1)).data);
                //add edge to list
            cycle.add(g.getEdge(curPath.get(curPath.size()-1), curPath.get(0)));


            //add cycle to output
            output.add(cycle);
            
            
            return;
        }

        // otherwise,

        // look at the last vertex in curPath
        Graph.Vertex<V, E> last = curPath.get(curPath.size()-1);
        
            // for each neighbor of that last vertex that I haven't visited
            for (Graph.Vertex<V,E> vertex : last.neighborsOut()){
                if (!curPath.contains(vertex)){
                    curPath.add(vertex);
                    allHamCycles(g, output, curPath);
                    curPath.remove(vertex);
                }

            }
    }

    //private method that gets the total distance of a list of edges
    private static <V,E> double getDist(List<Graph.Edge<V,E>> cycle){
        double total =0;
        
        for (Graph.Edge<V,E> edge : cycle){
            total += ((Graph.WeightedEdge<V,E>)edge).weight;
            
        }
        return total;
    }

    //returns the miminmum distance Hamiltonian cycle
    public static <V, E> List<Graph.Edge<V, E>> minTSP(Graph<V, E> g, Graph.Vertex<V, E> source){
        long startTime = System.nanoTime();
        //get a collection that has all the hamiltonian cycles
        Collection<List<Graph.Edge<V, E>>> output = allHamCycles(g, source);
        //create an arraylist that will hold all the different distances
        // ArrayList<Double> distances = new ArrayList<Double>();
        double minDist = Double.POSITIVE_INFINITY;

        List<Graph.Edge<V, E>> minTSP = new ArrayList<Graph.Edge<V,E>>();

        //for each hamiltonian cycle
        for (List<Graph.Edge<V,E>> cycle : output){


            if (getDist(cycle) < minDist){
                minTSP = cycle;
                minDist = getDist(cycle);
            }
        }
        //gets the runtime
        long endTime = System.nanoTime();
        long runTime = (endTime-startTime)/1000000;
        System.out.println(runTime + " ms");
        
        return minTSP;
    }

    //create a mst from a graph
    public static <V, E> ArrayList<Graph.Edge<V, E>> mst(Graph<V, E> g){
        long startTime = System.nanoTime();
        //Prim's algorithm

        //select some starting node
        Graph.Vertex<V, E> start = g.getVertex(0);
        
        //initalize an empty set for storing visited vertices to which we add the start
        Collection<Graph.Vertex<V,E>> visited = new HashSet<Graph.Vertex<V,E>>();
        visited.add(start);
        //initalize an empty collection of edges to retun
        ArrayList<Graph.Edge<V,E>> edges = new ArrayList<Graph.Edge<V,E>>();

        //create a priority queue, will give you the minimum edge each time
        PriorityQueue<Graph.WeightedEdge<V,E>> queue = new PriorityQueue<>(new Comparator<Graph.WeightedEdge<V,E>>() {
            @Override
            public int compare(Graph.WeightedEdge<V, E> o1, Graph.WeightedEdge<V, E> o2) {
                //if the weights of o1 - o2 is greater than 0, then o1 is greater than o2, return 1
                int returnInt = 0;
                if ( o1.weight - o2.weight > 0){
                    returnInt = 1;
                }
                //if o1 - -2 is less than 0, then o1 is less than o2, return -1
                if ( o1.weight - o2.weight < 0){
                    returnInt= -1;
                }
                // if o1 - o1 is equal to 0, then the values are equal, return 0
                if ( o1.weight - o2.weight == 0){
                    returnInt= 0;
                }
            return returnInt;
            }
        });
        
            // add edges around start to q
            for (Graph.Edge<V,E> eds : start.edgesOut()){
                queue.offer((Graph.WeightedEdge<V,E>)eds);
            }
            

        //while not all vertices are visited
            while( ! (visited.size() == g.getVertices().size())){
                
                
                //pick the minimal edge leaving visited component
                Graph.WeightedEdge<V,E> minEdge = queue.poll();

                //getting the two vertices that the minimum edge is connected to
                Graph.Vertex<V,E> v1 = minEdge.vertices().get(0);
                Graph.Vertex<V,E> v2 = minEdge.vertices().get(1);
            

                //if the edge is connected to two vertices that has already been visited, it cannot be used for the mst
                if (( visited.contains(v1) && !visited.contains(v2)) ){
                    edges.add(minEdge);
                }

                //for all the vertices connected to the minimum weight edge
                for (Graph.Vertex<V,E> v :minEdge.vertices()){
                    //if the vertex hasn't been visited 
                    if (!(visited.contains(v))){
                        //add it to the visited list
                        visited.add(v);
                        // add edges around v to q if they are not in output
                        
                        for (Graph.Edge<V,E> vEdge : v.edgesOut()){
                            if(!edges.contains(vEdge)){
                                queue.offer((Graph.WeightedEdge<V,E>)vEdge);
                            }
                        }

                    }
                }
            
        }
        long endTime = System.nanoTime();
        long runTime = (endTime-startTime)/1000000;
        System.out.println(runTime + " ms");
        return edges;
    }

    //depth first search
    private static <V,E> Collection<Graph.Vertex<V,E>> DFS(Graph.Vertex<V,E> start){

        Stack<Graph.Vertex<V,E>> stack = new Stack<Graph.Vertex<V,E>>();
        Collection<Graph.Vertex<V,E>> visited = new ArrayList<Graph.Vertex<V,E>>();

        stack.push(start);

        while (!stack.isEmpty()){

            Graph.Vertex<V,E> current = stack.pop();

            if (!visited.contains(current)){
                visited.add(current);
            }

            for (Graph.Vertex<V,E> neighbor : current.neighborsOut()){

                if (!visited.contains(neighbor)){
                    stack.push(neighbor);
                }
            }
        }
        return visited;

    }



    public static <V, E> Collection<Graph.Edge<V, E>> tspApprox(Graph<V, E> g){
        long startTime = System.nanoTime();

        //create an empty arraylist that will be returned
        Collection<Graph.Edge<V,E>> output = new ArrayList<Graph.Edge<V,E>>();

        //get a collection of edges from the mst
        ArrayList<Graph.Edge<V, E>> mst = mst(g);

        System.out.println(mst);
        //pick a starting edge from the mst
        Graph.Edge<V,E> startEdge = mst.get(0);

        //get the vertices connected to that edge
        Collection<Graph.Vertex<V,E>> startVertices = startEdge.vertices();
        //get a collection of vertices from the depth first search

        //For exploration, making the start vertex random
        Random rand = new Random();
        int randInt = rand.nextInt(mst(g).size());
        Graph.Edge<V,E> startEdgeRand = mst.get(randInt);
        Collection<Graph.Vertex<V,E>> startVertices2 =startEdgeRand.vertices();
        

        ArrayList<Graph.Vertex<V,E>> vertices = (ArrayList<Graph.Vertex<V,E>>)DFS(startVertices.iterator().next());
        //ArrayList<Graph.Vertex<V,E>> vertices = (ArrayList<Graph.Vertex<V,E>>)DFS(startVertices2.iterator().next());
        //get edges between the vertices
        for (int i = 0; i < vertices.size()-1; i++){
            Graph.Edge<V,E> edge = g.getEdge(vertices.get(i), vertices.get(i+1));
            output.add(edge);
            
        }
        output.add(g.getEdge(vertices.get(vertices.size()-1), vertices.get(0)));

        long endTime = System.nanoTime();
        long runTime = (endTime-startTime)/1000000;
        System.out.println(runTime + " ms");
        return output;

    }






    public static void main(String[] args) throws IOException{
        Graph<String, Object> graph = readData("stateData.csv");
        Collection<Graph.Edge<String, Object>> mstEdges = mst(graph);
        double sum = 0.0;
        for (Graph.Edge<String, Object> edge : mstEdges){
            sum += ((Graph.WeightedEdge<String, Object>) edge).weight;
        } System.out.println("MST Cost: " + sum);
        Collection<Graph.Edge<String, Object>> cycle = tspApprox(graph);
        sum = 0.0;
        for (Graph.Edge<String, Object> edge : cycle){
            sum += ((Graph.WeightedEdge<String, Object>) edge).weight;
        } System.out.println("Cycle Cost: " + sum);
    }
    }
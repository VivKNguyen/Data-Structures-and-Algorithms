/*
 * filename: Landscape.java
 * author: Vivian Nguyen
 * date: October 3, 2022
 * Project 3
 */


 import java.util.ArrayList;
 import java.awt.Graphics;
public class Landscape {


    //intializes fiels for Landscape class
    private int width;
    private int height; 
    private LinkedList<Agent> agents;


    //constructor that sets width, height, and agent list
    public Landscape(int w, int h){
        this.width = w;
        this.height = h;
        this.agents = new LinkedList<Agent>(); 
    }


    //returns the height
    public int getHeight(){
        return this.height;
    }


    //returns the width
    public int getWidth(){
        return this.width;
    }


    //inserts agent at beginning of agent list
    public void addAgent( Agent a){
        agents.add(a);
    }


    //returns string representation of Landscape
    public String toString(){
        String agentNum = "Number of agents: " + agents.size();
        return agentNum;
    }


    //returns a list of agents within radius distance of x0, y0
    public LinkedList<Agent> getNeighbors(double x0, double y0, double radius){
        ArrayList<Agent> result = new ArrayList<Agent>();
        ArrayList<Agent> agentList = agents.toArrayList();
        
        for ( int i = 0; i < agents.size(); i++){
            Agent curr = agentList.get(i);
            double distX = curr.getX() - x0;
            double distY = curr.getY() - y0;
            
            if (Math.pow(distX, 2) + Math.pow(distY, 2) <= Math.pow(radius, 2)){
                result.add(curr);
            }
        }

        LinkedList<Agent> linkedResult = new LinkedList<Agent>();
        for (Agent arr : result){
            linkedResult.add(arr);
        }
        return linkedResult;
    }


    //updates the state of each agent
    public void updateAgents(){
        for (Agent agent: agents){
            agent.updateState(this);
        }
    }



    //draws the agents in the list
    public void draw(Graphics g){

        for (Agent item:agents){
            item.draw(g);
        }
    }
    
}

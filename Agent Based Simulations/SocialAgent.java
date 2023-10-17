/*
 * filename: SocialAgent.java
 * author: Vivian Nguyen
 * date: October 3, 2022
 * Project 3
 */



import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;


public class SocialAgent extends Agent { //SocialAgent is a subclass of Agent

    //creates fields of the SocialAgent class
    private boolean moved;
    private int R;


    //constructor 
    public SocialAgent(double x0, double y0, int radius){
        //remainder of constructor code
        super(x0 , y0);
        //sets radius field
        this.R = radius;
    }


    //sets the cell's radius of sensitivity to the value of radius
    public void setRadius(int radius){
        this.R = radius;
    }


    //returns the cell's radius of sensitivity
    public int getRadius(){
        return this.R;
    }


    //draws a circle
    public void draw(Graphics g){
        if(this.moved ){
            g.setColor(new Color(51,204,255));
        }

        else{ 
            g.setColor(new Color(0,0,153));
        }
    
        g.drawOval((int)getX(),(int)getY(),5,5);
        g.fillOval((int)getX(),(int)getY(), 5, 5);
    }


    //if there are less than 4 agents in the radius, the agent moves by a random value btwn -10 to 10 units
    public void updateState(Landscape scape){
        double x0 = this.x;
        double y0 = this.y;
        Random ran = new Random();
        double moveX = ran.nextDouble()*20-10;
        double moveY = ran.nextDouble()*20-10;


        if (scape.getNeighbors(x0, y0, this.R).size <4){
            setX(x0+moveX);
            setY(y0+moveY);

            this.moved = true;

        }
        else{
        this.moved = false;
        }
        
        
    }
}

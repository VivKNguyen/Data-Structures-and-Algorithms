/*
 * filename: AntiSocialAgent.java
 * author: Vivian Nguyen
 * date: October 3, 2022
 * Project 3
 */


import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;



public class AntiSocialAgent extends Agent {

    private boolean moved;
    private int R;


    //constructor
    public AntiSocialAgent(double x0, double y0, int radius){
        //remainder of constructor code
        super (x0, y0);
        //sets radius field
        this.R = radius;
    }


    //sets cell radius of sensitivity to radius
    public void setRadius (int radius){
        this.R = radius;
    }


    //returns the cell's radius of sensitivity
    public int getRadius(){
        return this.R;
    }


    //draws the agents
    public void draw(Graphics g){
        if(!(this.moved)) {
            g.setColor(new Color(255, 0, 0));}
            
    else {
        g.setColor(new Color(255, 0, 255));}

    g.fillOval((int) getX(), (int) getY(), 5, 5);

    }


    //moves the agent if there is more than 1 neighbor in the radius
    public void updateState(Landscape scape){

        double x0 = this.x;
        double y0 = this.y;
        Random ran = new Random();
        double moveX = ran.nextDouble()*20-10;
        double moveY = ran.nextDouble()*20-10;


        if (scape.getNeighbors(x0, y0, this.R).size > 1){
            setX(x0+moveX);
            setY(y0+moveY);
            
            this.moved = true;
        }
        else{
            this.moved = false;
        }
    }
    
}

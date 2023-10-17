/*
 * filename: AgentSimulation.java 
 * author: Vivian Nguyen
 * date: October 3, 2022
 * Project 3
 */


import java.util.Random;


public class AgentSimulation {
    //fields for the AgentSimulation class
    Landscape scape;
    LandscapeDisplay display;


    //constructor that creates new landscape and landscape display
    public AgentSimulation( int width, int height, int radius, int N){

        Landscape scape = new Landscape(width , height);
  
        LandscapeDisplay display = new LandscapeDisplay(scape);
    }

    public static void main (String [] args) throws InterruptedException {
        Landscape scape = new Landscape(500 , 500);
        Random gen = new Random();
        LandscapeDisplay display = new LandscapeDisplay(scape);


        //creates 100 social and 100 antisocial agents
        for ( int i = 0; i < 100; i++){
            
            scape.addAgent(new SocialAgent(gen.nextDouble()*scape.getWidth(), gen.nextDouble()*scape.getHeight(), 20));
            scape.addAgent(new AntiSocialAgent(gen.nextDouble()*scape.getWidth(), gen.nextDouble()*scape.getHeight(), 20));

            
        }

       
        
        //hard coded 500 iterations of the simulation
        for ( int i = 0; i < 500; i++){
            Thread.sleep(100);
            scape.updateAgents();
            display.repaint();
            
        }

    }
    
}

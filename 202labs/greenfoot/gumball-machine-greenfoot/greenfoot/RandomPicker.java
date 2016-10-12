import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandomPicker here.
 * 
 * @author (Danhua Yang) 
 * @version (09/12/2016)
 */
public class RandomPicker extends Picker
{
    /**
     * Act - do whatever the RandomPicker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
   public void pickOther()
    {
        World world =  getWorld();
        int gumball = Greenfoot.getRandomNumber(3); // 1 is blue; 2 is red; 3 is green
        Gumball randomBall;
        if(gumball == 1)
        {
            randomBall = new BlueGumball();
        }
        else if(gumball == 2)
        {
            randomBall = new RedGumball();
        }
        else
        {
            randomBall = new GreenGumball();
        }   
        world.addObject(randomBall, 400, 400);
    }
}

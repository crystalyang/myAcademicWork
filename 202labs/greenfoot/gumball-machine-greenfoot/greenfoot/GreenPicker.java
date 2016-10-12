import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GreenPicker here.
 * 
 * @author (Danhua Yang) 
 * @version (09/12/2016)
 */
public class GreenPicker extends Picker
{
    public void pickGreen()
    {
        World world =  getWorld();
        Gumball greenBall = new GreenGumball();
        world.addObject(greenBall, 400, 400);
    }
}

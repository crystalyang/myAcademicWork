import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coin extends Actor
{
    int value;

    public void act() 
    {
        int mouseX, mouseY ;
        
        if(Greenfoot.mouseDragged(this)) 
        {          
            MouseInfo mouse = Greenfoot.getMouseInfo();  
            mouseX=mouse.getX();  
            mouseY=mouse.getY();  
            setLocation(mouseX, mouseY);  
        }
    }
    
    public boolean isQuarter()
    {
        return value == 25;
    }
    
    public boolean isPenny()
    {
        return value == 1;
    }
}

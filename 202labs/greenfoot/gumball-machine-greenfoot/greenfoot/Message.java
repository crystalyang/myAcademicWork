import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Message here.
 * 
 * @author (Danhua Yang) 
 * @version (09/12/2016)
 */
public class Message extends Actor
{
    GreenfootImage gi;
    public Message()
    {
        gi = new GreenfootImage(200,50);
        setImage(gi);
    }
    

    public void act() 
    {
        // Add your action code here.
        // remove msg box after clicked
        if(Greenfoot.mousePressed(this))
        {
            World world = getWorld();
            world.removeObject(this);
        }
    }
    
    public void setText(String msg)
    {
        gi.clear();
        gi.setColor(java.awt.Color.YELLOW);
        gi.fill();
        gi.setColor(java.awt.Color.BLACK);
        gi.drawString(msg,0,25);
    }
}

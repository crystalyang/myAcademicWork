import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class GumballMachine here.
 * 
 * @author (Danhua Yang) 
 * @version (09/12/2016)
 */
public class GumballMachine extends Actor
{
    Message msg = new Message();
    Inspector inspector;
    Coin thisCoin = null;
    GreenPicker greenPicker;
    RandomPicker randamPicker;
    
    public GumballMachine()
    {
        GreenfootImage image = getImage() ;
        image.scale( 350, 400 ) ; 
    }

    public void act() 
    {
        //get coin object when it hover over.
        Actor coin = getOneObjectAtOffset(+10, +10, Coin.class);
        if(coin!=null)
        {
            if(thisCoin != null)
            {
                //do nothing if already have coin
            }
            else
            {
                thisCoin = (Coin) coin;
                displayMsg("Have Coin!Click to continue.");
                World world = getWorld();
                world.removeObject(coin); //inserted coin
            }
        }
        
        if(Greenfoot.mousePressed(this))
        {
            
            int mouseX,mouseY;
            World world = getWorld();
            MouseInfo m = Greenfoot.getMouseInfo();
            mouseX = m.getX();
            mouseY = m.getY();
            inspector = (Inspector)this.getOneIntersectingObject(Inspector.class);
            inspector.setLocation(mouseX,mouseY);
            displayMsg("Inspector check Coin");
            Greenfoot.delay(100);
            if(thisCoin == null)
            {
                displayMsg("No Coin!");
                inspector.setLocation(533,291); // move back inspector
            }
            else
            {
                checkCoin(thisCoin); //check if right coin
                Greenfoot.delay(100);
                inspector.setLocation(533,291);
            }
        }
    }
    
    public void checkCoin(Coin coin)
    {
        
        if(coin.isQuarter())
        {
            displayMsg("One quarter inserted");
            Greenfoot.delay(100);
            selectPicker();
            thisCoin = null;
        }
        else if(coin.isPenny())
        {
            displayMsg("please use quarter");
        }
        else
        {
            displayMsg("Fake coin!");
        }
        
    }
    
    public void selectPicker()
    {
        World world = getWorld();
        //1 is green;  else is random
        int ball = Greenfoot.getRandomNumber(2);
        if(ball == 1)
        {
            List<GreenPicker> pickers = world.getObjects(GreenPicker.class);
            GreenPicker greenPicker = pickers.remove(0);
            displayMsg("Green!");
            Greenfoot.delay(50);
            greenPicker.setLocation(367,237);
            greenPicker.pickGreen();
            displayMsg("GreenPicker picked Green");
            Greenfoot.delay(50);
            greenPicker.setLocation(669,465);
        }
        else
        {
            List<RandomPicker> pickers = world.getObjects(RandomPicker.class);
            RandomPicker randomPicker = pickers.remove(0);
            displayMsg("Random Color!");
            Greenfoot.delay(50);
            randomPicker.setLocation(367,237);
            randomPicker.pickOther();
            displayMsg("RandomPicker picked random!");
            Greenfoot.delay(50);
            randomPicker.setLocation(655,94);
        }
    }
    
    public void displayMsg(String msgText)
    {
        int mouseX,mouseY;
        World world = getWorld();
        MouseInfo m = Greenfoot.getMouseInfo();
        mouseX = m.getX();
        mouseY = m.getY();
        if(msg.getWorld()!=null)
        {
            world.removeObject(msg);
        }
        world.addObject(msg,mouseX,mouseY);
        msg.setText(msgText);
    }
}



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GumballMachineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GumballMachineTest
{
    private GumballMachine testM;
    /**
     * Default constructor for test class GumballMachineTest
     */
    public GumballMachineTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        testM = new GumballMachine(10);//initialize a gumball machine has 10 gumballs
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    
    @Test
    public void testCrankNotEnoughCoins(){
       System.out.println("//test crank when not enough 50 cents");
       System.out.println("crank no coin");
       testM.turnCrank();
       assertEquals(false,testM.isGumballInSlot());
       System.out.println("insert a quarter then crank");
       testM.insertQuarter();
       testM.turnCrank();
       assertEquals(false,testM.isGumballInSlot());
    }
    
    @Test
    public void testCrank50CentCoins(){
        System.out.println("//test crank when exact or more than 50 cents");
        System.out.println("insert enough 50 cents");
        testM.insertQuarter();
        testM.insertQuarter();
        testM.turnCrank();
        assertEquals(true,testM.isGumballInSlot());
        testM.takeGumballFromSlot();
        assertEquals(false, testM.isGumballInSlot());
        System.out.println("insert more than 50 cents");
        testM.insertQuarter();
        testM.insertQuarter();
        testM.insertQuarter();
        testM.turnCrank();
        assertEquals(true,testM.isGumballInSlot());
        testM.takeGumballFromSlot();
        assertEquals(false,testM.isGumballInSlot());
        assertEquals(0,testM.getCoins());
    }
    
    @Test
    public void testCrank2CrankEnoughCoins(){
        System.out.println("//test 1 crank when has 50 cents, then insert another 50 cents for two cranks");
        System.out.println("insert 50 cents");
        testM.insertQuarter();
        testM.insertQuarter();
        testM.turnCrank();
        assertEquals(true,testM.isGumballInSlot());
        System.out.println("insert another 50 cents");
        testM.insertQuarter();
        testM.insertQuarter();
        testM.turnCrank();
        assertEquals(true,testM.isGumballInSlot());
        testM.takeGumballFromSlot();
        assertEquals(false,testM.isGumballInSlot());
    }
    
    @Test
    public void testInsertNickel(){
        System.out.println("//test insert Nickels");
        testM.insertNickel();
        assertEquals(5,testM.getCoins());
        testM.insertNickel();
        assertEquals(10,testM.getCoins());
    }
    
    @Test
    public void testInsertDime(){
        System.out.println("//test insert dime.");
        testM.insertDime();
        assertEquals(10,testM.getCoins());
        testM.insertDime();
        assertEquals(20,testM.getCoins());
    }
}


/**
 * Write a description of interface IGumballMachine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IGumballMachine
{
    void insertQuarter();
    void insertDime();
    void insertNickel();
    void turnCrank();
    boolean isGumballInSlot();
    void takeGumballFromSlot();
}

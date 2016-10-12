

import java.util.Random;

public class Has50CentState implements State {
    GumballMachine gumballMachine;
 
    public Has50CentState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
  
    public void insertCoin(int coin) {
         if(coin == 25){
            System.out.println("You inserted a quarter");
        }else if(coin == 10){
            System.out.println("You inserted a dime");
        }else if(coin == 5){
            System.out.println("You inserted a nickel");
        }else{
            System.out.println("Plese insert quarter,dime or nickel");
        }
        int coins = gumballMachine.getCoins();
        coins += coin;
        gumballMachine.setCoins(coins);
    }
 
    public void ejectCoin() {
        System.out.println("Coins returned");
        gumballMachine.setCoins(0);
        gumballMachine.setState(gumballMachine.getNo50CentState());
    }
 
    public void turnCrank() {
        System.out.println("You turned...");
        gumballMachine.setState(gumballMachine.getSoldState());
    }

    public void dispense() {
        System.out.println("No gumball dispensed");
    }
 
    public String toString() {
        return "waiting for turn of crank";
    }
}



public class No50CentState implements State {
    GumballMachine gumballMachine;
 
    public No50CentState(GumballMachine gumballMachine) {
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
        if(coins>=50){
            gumballMachine.setState(gumballMachine.getHas50CentState());
            System.out.println("has 50 cents");
        }else{
            System.out.println("not enough 50 cents");
        }
    }
    //changed to coin
    public void ejectCoin() {
        int coins = gumballMachine.getCoins();
        if(coins<=0){
            System.out.println("no change coins");
        }else{
            System.out.println("ejected "+coins+"coins");
            gumballMachine.setCoins(0);
        }
    }
 
    public void turnCrank() {
        int coins = gumballMachine.getCoins();
        
        if(coins>0){
            System.out.println("You turned, but there's not enough 50 cents");
        }else{
            System.out.println("You turned, but there's no coin");
        }
     }
 
    public void dispense() {
        System.out.println("You need to pay first");
    } 
 
    public String toString() {
        return "waiting for quarter";
    }
}

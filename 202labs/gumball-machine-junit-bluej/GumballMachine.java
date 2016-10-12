

public class GumballMachine implements IGumballMachine {
 
    State soldOutState;
    State no50CentState; //changed to 50Cents
    State has50CentState;
    State soldState;
 
    State state = soldOutState;
    int count = 0;
    int coins = 0;
    int gumballs = 0;
    
 
    public GumballMachine(int numberGumballs) {
        soldOutState = new SoldOutState(this);
        no50CentState = new No50CentState(this);
        has50CentState = new Has50CentState(this);
        soldState = new SoldState(this);

        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = no50CentState; // changed quarter to 50 cent
        } 
    }
 
    public void insertQuarter() {
        System.out.println("You inserted a quarter.");
        state.insertCoin(25); //changed to coin
    }
    
    //added Nickel
    public void insertNickel(){
        System.out.println("You inserted a Nickel.");
        state.insertCoin(5);
    }
    
    //added Dime
    public void insertDime(){
        System.out.println("You inserted a Dime.");
        state.insertCoin(10);
    }
       
    //changed to coin
    public void ejectCoin() {
        state.ejectCoin();
    }
 
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void setState(State state) {
        this.state = state;
    }
 
    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count = count - 1;
            coins -=50; //cost 50 cents
            gumballs ++; //add one gumball in slot
        }
    }
 
    int getCount() {
        return count;
    }
 
    void refill(int count) {
        this.count = count;
        state = no50CentState;
    }

    public State getState() {
        return state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNo50CentState() {
        return no50CentState;
    }

    public State getHas50CentState() {
        return has50CentState;
    }

    public State getSoldState() {
        return soldState;
    }
 
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nMighty Gumball, Inc.");
        result.append("\nJava-enabled Standing Gumball Model #2004");
        result.append("\nInventory: " + count + " gumball");
        if (count != 1) {
            result.append("s");
        }
        result.append("\n");
        result.append("Machine is " + state + "\n");
        return result.toString();
    }
    
    public boolean isGumballInSlot( ){
        if(gumballs>0){
            return true;
        }else{
            return false;
        }
    }
    
    public void takeGumballFromSlot(){
        if(isGumballInSlot()){
            System.out.println("take"+gumballs+"gumballs");
            gumballs = 0;
        }else{
            System.out.println("no gumball in the slot");
        }
    }
    
    public int getCoins(){
        return coins;
    }
    
    public void setCoins(int coin){
        coins = coin;
    }
}

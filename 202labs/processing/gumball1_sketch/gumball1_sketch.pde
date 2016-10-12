
int[] btnInsert = new int[5]; //initialize array for btn dimension
int[] btnTurn = new int[5];
int btnCol = 255; //set button color to white
int txtCol = 0; //set test color to black
boolean overInsert = false; //indicate if mouse hove the button
boolean overTurn = false;
GumballMachine gumballMachine = new GumballMachine(5); //initialize the gumball machine for global use

void setup() 
{
  size(800, 800) ;
  background(255) ;
  smooth() ;
  strokeWeight(3);
  strokeCap(ROUND);
  
  // load font
  PFont font;
  font = loadFont("BookAntiqua-48.vlw");
  textFont(font, 32);
  
  btnInsert[0] = 100; //x
  btnInsert[1] = 700; //y
  btnInsert[2] = 250; //w
  btnInsert[3] = 50; //h
  btnInsert[4] = 0; // indicate if pressed
  
  btnTurn[0] = 500;
  btnTurn[1] = 700;
  btnTurn[2] = 250;
  btnTurn[3] = 50;
  btnTurn[4] = 0; //indicate if pressed 
  
  // Only draw once
  //noLoop();
}

void draw() {
  //determine if the mouse hover the buttons
  if((mouseY<(btnInsert[1]+btnInsert[3]))&&(mouseY>(btnInsert[1]))){
    if((mouseX<(btnInsert[0]+btnInsert[2]))&&(mouseX>(btnInsert[0]))){
      overInsert = true;
    }
  }else{
    overInsert = false;
  }
  
  if((mouseY<(btnTurn[1]+btnTurn[3]))&&(mouseY>(btnTurn[1]))){
    if((mouseX<(btnTurn[0]+btnTurn[2]))&&(mouseX>(btnTurn[0]))){
      overTurn = true;
    }
  }else{
    overTurn = false;
  }
  
  if(btnInsert[4] == 0 && btnTurn[4] == 0){
    if(overInsert || overTurn){
      background(100);
    }else{
      background(255);
    }
  }

  fill(0);  
  text("The Gumball Machine", 250, 60);
  PImage image = loadImage("gumball.jpg");
  image(image, (width-image.width)/2, (height-image.height)/2);  
  
  //draw buttons 
  fill(btnCol);
  rect(btnInsert[0], btnInsert[1],btnInsert[2],btnInsert[3]);
  rect(btnTurn[0], btnTurn[1],btnTurn[2],btnTurn[3]);
  fill(txtCol);
  text("Insert Quarter", btnInsert[0]+5, btnInsert[1]+30);
  text("Turn Crank",btnTurn[0]+10,btnTurn[1]+30);
  //runTest() ;
}

void mousePressed(){
  //mouse clicked insert button
  if((mouseY<(btnInsert[1]+btnInsert[3]))&&(mouseY>(btnInsert[1]))){
    if((mouseX<(btnInsert[0]+btnInsert[2]))&&(mouseX>(btnInsert[0]))){
      btnInsert[4] = 1;
      background(0);
      System.out.println("clicked insert");
      //implement the insertQuarter method for gumball machine
      gumballMachine.insertQuarter();
    }
  }
  
  //mouse clicked turn button
  if((mouseY<(btnTurn[1]+btnTurn[3]))&&(mouseY>(btnTurn[1]))){
    if((mouseX<(btnTurn[0]+btnTurn[2]))&&(mouseX>(btnTurn[0]))){
      btnTurn[4] = 1;
      background(0);
      System.out.println("clicked turn");
      //implement the turnCrank method for gumball machine.
      gumballMachine.turnCrank();
    }
  }
}

public void runTest() {
  //GumballMachine gumballMachine = new GumballMachine(5);
  //System.out.println(gumballMachine);
  //gumballMachine.insertQuarter();
  //gumballMachine.turnCrank();
  //System.out.println(gumballMachine);
  //gumballMachine.insertQuarter();
  //gumballMachine.turnCrank();
  //gumballMachine.insertQuarter();
  //gumballMachine.turnCrank();
  //System.out.println(gumballMachine);
}
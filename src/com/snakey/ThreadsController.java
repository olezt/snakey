package com.snakey;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;


//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread{
	ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
	Snakey snakey;
	Tuple headSnakePos;
	ThreadsController otherThreadsController;
	int id;
	int sizeSnake=3;
	int directionSnake;

	public int getDirectionSnake() {
		return directionSnake;
	}

	public void setDirectionSnake(int directionSnake) {
		this.directionSnake = directionSnake;
	}
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ThreadsController getOtherThreadsController() {
		return otherThreadsController;
	}

	public void setOtherThreadsController(ThreadsController otherThreadsController) {
		this.otherThreadsController = otherThreadsController;
	}

	ArrayList<Tuple> positions = new ArrayList<Tuple>();
	Tuple foodPosition;
	 
	 //Constructor of ControlleurThread 
	ThreadsController(int threadId){
		super();
        snakey = new Snakey();	
		id = threadId;
		
		//Get all the threads
		Squares=Window.Grid;
		
		init();
		spawnFood();
	 }
	 
	 //Important part :
	 public void run() {
		 while(!Snakey.getCollision()){
			 moveInterne(directionSnake);
			 checkCollision();
			 moveExterne();
			 deleteTail();
			 pauser();
		 }
		 if(Snakey.getCollision()){
			 if(id==1){
				 int input = JOptionPane.showOptionDialog(null, "Play again?", "Collision!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				 if(input == JOptionPane.OK_OPTION){
					 Snakey.setCollision(false);
					 // Restart game!
					 init();
					 run();
					 JOptionPane.getRootFrame().dispose();
				 }else{
					 // End game!
					 JOptionPane.getRootFrame().dispose();
				 }
			 }else if(id==2){
				 waitForRestart();
			 }
		 }
	 }
	 
	public void waitForRestart() {
		if(Snakey.getCollision()){
			 try {
				 TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			waitForRestart();
		}else{
			init();
			run();
		}
	}

	//delay between each move of the snake
	 public void init(){
		sizeSnake=3;
		Random rand = new Random();
		// initial position of the snake
		Tuple position = new Tuple(rand.nextInt(Constants.SIZE) + 1,rand.nextInt(Constants.SIZE) + 1);
		// passing this value to the controller
		headSnakePos=new Tuple(position.x,position.y);
		
		//Set random direction for each snakey
		directionSnake = rand.nextInt(4) + 1;
		
		//!!! Pointer !!!!
		Tuple headPos = new Tuple(headSnakePos.getX(),headSnakePos.getY());
		positions.add(headPos);
			
	 }
	 
	 //delay between each move of the snake
	 private void pauser(){
		 try {
			sleep(snakey.getSpeed());
		 } catch (InterruptedException e) {
			e.printStackTrace();
		 }
	 }
	 
	 //Checking for collisions or eating
	 private void checkCollision() {
		 Tuple posCritique = positions.get(positions.size()-1);
		 
		 for(int i = 0;i<=positions.size()-2;i++){
			 boolean biteItself = posCritique.getX()==positions.get(i).getX() && posCritique.getY()==positions.get(i).getY();
			 if(biteItself){
				stopTheGame();
			 }
		 }
		 
		 if(id==2){
			 for(int i = 0;i<=otherThreadsController.positions.size()-2;i++){
				 boolean biteSnakey1 = posCritique.getX()==otherThreadsController.positions.get(i).getX() && posCritique.getY()==otherThreadsController.positions.get(i).getY();
				 if(biteSnakey1){
					 stopTheGame();
				 }
			 }
			 
			 Tuple posCritique1 = otherThreadsController.positions.get(otherThreadsController.positions.size()-1);
			 for(int i = 0;i<=positions.size()-2;i++){
				 boolean biteSnakey2 = posCritique1.getX()==positions.get(i).getX() && posCritique1.getY()==positions.get(i).getY();
				 if(biteSnakey2){
					 stopTheGame();
				 }
			 }
		 }
		 
		 boolean eatingFood = posCritique.getX()==snakey.foodPosition.y && posCritique.getY()==snakey.foodPosition.x;
		 if(eatingFood){
			 sizeSnake=sizeSnake+1;
			 System.out.println("Yummy for snakey "+id+" ! Sizey now is "+sizeSnake);
			 snakey.foodPosition = getValAleaNotInSnake();
			 spawnFood();	
		 }
	 }
	 
	 //Stops The Game
	 private void stopTheGame(){
		 System.out.println("COLISION! \n");
		 Snakey.setCollision(true);
	 }
	 
	 //Put food in a position and displays it
	 private void spawnFood(){
		 Squares.get(snakey.foodPosition.x).get(snakey.foodPosition.y).lightMeUp(1);
	 }
	 
	 //return a position not occupied by the snake
	 private Tuple getValAleaNotInSnake(){
		 Tuple p ;
		 int ranX= 0 + (int)(Math.random()*Constants.SIZE-1); 
		 int ranY= 0 + (int)(Math.random()*Constants.SIZE-1); 
		 p=new Tuple(ranX,ranY);
		 for(int i = 0;i<=positions.size()-1;i++){
			 if(p.getY()==positions.get(i).getX() && p.getX()==positions.get(i).getY()){
				 ranX= 0 + (int)(Math.random()*Constants.SIZE-1); 
				 ranY= 0 + (int)(Math.random()*Constants.SIZE-1); 
				 p=new Tuple(ranX,ranY);
				 i=0;
			 }
		 }
		 return p;
	 }
	 
	 //Moves the head of the snake and refreshes the positions in the arraylist
	 //1:right 2:left 3:top 4:bottom 0:nothing
	 private void moveInterne(int dir){
		 switch(dir){
		 	case 4:
				 headSnakePos.ChangeData(headSnakePos.x,(headSnakePos.y+1)%Constants.SIZE);
				 positions.add(new Tuple(headSnakePos.x,headSnakePos.y));
		 		break;
		 	case 3:
		 		if(headSnakePos.y-1<0){
		 			 headSnakePos.ChangeData(headSnakePos.x,Constants.SIZE-1);
		 		 }
		 		else{
				 headSnakePos.ChangeData(headSnakePos.x,Math.abs(headSnakePos.y-1)%Constants.SIZE);
		 		}
				 positions.add(new Tuple(headSnakePos.x,headSnakePos.y));
		 		break;
		 	case 2:
		 		 if(headSnakePos.x-1<0){
		 			 headSnakePos.ChangeData(Constants.SIZE-1,headSnakePos.y);
		 		 }
		 		 else{
		 			 headSnakePos.ChangeData(Math.abs(headSnakePos.x-1)%Constants.SIZE,headSnakePos.y);
		 		 } 
		 		positions.add(new Tuple(headSnakePos.x,headSnakePos.y));

		 		break;
		 	case 1:
				 headSnakePos.ChangeData(Math.abs(headSnakePos.x+1)%Constants.SIZE,headSnakePos.y);
				 positions.add(new Tuple(headSnakePos.x,headSnakePos.y));
		 		 break;
		 }
	 }
	 
	 //Refresh the squares that needs to be 
	 private void moveExterne(){
		 for(Tuple t : positions){
			 int y = t.getX();
			 int x = t.getY();
			 Squares.get(x).get(y).lightMeUp(0);
			 
		 }
	 }
	 
	 //Refreshes the tail of the snake, by removing the superfluous data in positions arraylist
	 //and refreshing the display of the things that is removed
	 private void deleteTail(){
		 int cmpt = sizeSnake;
		 for(int i = positions.size()-1;i>=0;i--){
			 if(cmpt==0){
				 Tuple t = positions.get(i);
				 Squares.get(t.y).get(t.x).lightMeUp(2);
			 }
			 else{
				 cmpt--;
			 }
		 }
		 cmpt = sizeSnake;
		 for(int i = positions.size()-1;i>=0;i--){
			 if(cmpt==0){
				 positions.remove(i);
			 }
			 else{
				 cmpt--;
			 }
		 }
	 }
}

package com.snakey;

public class Snakey {
	public Tuple foodPosition = new Tuple(Window.height-1,Window.width-1);
	// Default options
	public static long speed = Constants.SPEED;
	public static boolean collision = false;
	public static boolean multiplayer = Constants.MULTIPLAYER;
	public static int size = Constants.SIZE; 

	public Tuple getFoodPosition() {
		return foodPosition;
	}

	public void setFoodPosition(Tuple foodPosition) {
		this.foodPosition = foodPosition;
	}

	public static long getSpeed() {
		return speed;
	}

	public static void setSpeed(long speed) {
		Snakey.speed = speed;
	}

	public static boolean getCollision() {
		return collision;
	}

	public static void setCollision(boolean collision) {
		Snakey.collision = collision;
	}

	public static boolean isMultiplayer() {
		return multiplayer;
	}

	public static void setMultiplayer(boolean multiplayer) {
		Snakey.multiplayer = multiplayer;
	}

	public static int getSize() {
		return size;
	}

	public static void setSize(int size) {
		Snakey.size = size;
	}
	
}
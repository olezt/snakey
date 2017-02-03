package com.snakey;

public class Snakey {
	public Tuple foodPosition = new Tuple(Window.height-1,Window.width-1);
	public long speed = 50;
	public static boolean collision = false;
	public static boolean multiplayer = false;

	public Tuple getFoodPosition() {
		return foodPosition;
	}

	public void setFoodPosition(Tuple foodPosition) {
		this.foodPosition = foodPosition;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
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
	
}

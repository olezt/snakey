package com.snakey;

public class Snakey {
	public Tuple foodPosition = new Tuple(Window.height-1,Window.width-1);
	public long speed = 50;

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
	
}

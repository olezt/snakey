package com.snakey;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		boolean multiplayer = false;
        for (String s: args) {
        	if(s.equals("multiplayer")){
            	System.out.println("Multiplayer mode!");
                multiplayer = true;
        	}
        }
		
		//Creating the window with all its awesome snaky features
		Window f1= new Window(multiplayer);
		
		//Setting up the window settings
		f1.setTitle("Snake");
		f1.setSize(300,300);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

	}
}

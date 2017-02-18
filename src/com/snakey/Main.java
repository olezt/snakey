package com.snakey;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		// Apply user's given arguments else Default Constants are used
        for (String s: args) {
        	// Multiplayer mode.
        	if(s.equals("multiplayer")){
            	System.out.println("Multiplayer mode!");
                Snakey.multiplayer = true;
            // Set given size.
        	}else if(s.startsWith("size")){
        		Snakey.size = Integer.parseInt(s.substring(s.lastIndexOf("e")+1));
        		System.out.println("Size is " + Snakey.size + "!");
        	// Set given speed
        	}else if(s.startsWith("speed")){
        		Snakey.speed = Integer.parseInt(s.substring(s.lastIndexOf("d")+1));
        		System.out.println("Speed is " + Snakey.speed + "!");
        	}
        }
        
        // Get screen dimensions
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
		//Creating the window with all its awesome snaky features
		Window f1= new Window();
		
		//Setting up the window settings
		f1.setTitle("Snake");
		f1.setSize(Snakey.size*10,Snakey.size*10);
		f1.setLocation(dim.width/2-f1.getSize().width/2, dim.height/2-f1.getSize().height/2);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

	}
}

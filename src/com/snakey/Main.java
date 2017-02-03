package com.snakey;

import java.awt.Dimension;
import java.awt.Toolkit;

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
        // Get screen dimensions
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
		//Creating the window with all its awesome snaky features
		Window f1= new Window(multiplayer);
		
		//Setting up the window settings
		f1.setTitle("Snake");
		f1.setSize(300,300);
		f1.setLocation(dim.width/2-f1.getSize().width/2, dim.height/2-f1.getSize().height/2);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

	}
}

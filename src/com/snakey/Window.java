package com.snakey;

import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

class Window extends JFrame{
	private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public static int width = Constants.SIZE;
	public static int height = Constants.SIZE;
	public Window(boolean multiplayer){
		
		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;
		
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++){
			data= new ArrayList<DataOfSquare>();
			for(int j=0;j<height;j++){
				DataOfSquare c = new DataOfSquare(2);
				data.add(c);
			}
			Grid.add(data);
		}
		
		// Setting up the layout of the panel
		getContentPane().setLayout(new GridLayout(width,height,0,0));		

		// Start & pauses all threads, then adds every square of each thread to the panel
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				getContentPane().add(Grid.get(i).get(j).square);
			}
		}
		
		// passing this value to the controller
		ThreadsController c1 = new ThreadsController(1);
		//Let's start the game now..
		c1.start();

		// Links the window to the keyboardlistenner.
		this.addKeyListener((KeyListener) new KeyboardListener(c1));

		// If Multiplayer mode
		if(multiplayer){
			ThreadsController c2 = new ThreadsController(2);
			c2.setOtherThreadsController(c1);
			c2.start();
			
			c1.setOtherThreadsController(c2);
			this.addKeyListener((KeyListener) new KeyboardListener(c2));
		}
	}
}

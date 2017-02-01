package com.snakey;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 public class KeyboardListener extends KeyAdapter{
	 	private ThreadsController c;
	 
	 	KeyboardListener(ThreadsController thisThreadController){
	 		c = thisThreadController;
	 	}
	 	
 		public void keyPressed(KeyEvent e){
 			if(c.getId()==1){
 				switch(e.getKeyCode()){
 					case 39:	// -> Right 
		    				//if it's not the opposite direction
		    				if(c.getDirectionSnake()!=2) 
		    					c.setDirectionSnake(1);
		    			  	break;
 					case 38:	// -> Top
							if(c.getDirectionSnake()!=4) 
								c.setDirectionSnake(3);
		    				break;
		    				
 					case 37: 	// -> Left 
							if(c.getDirectionSnake()!=1)
								c.setDirectionSnake(2);
		    				break;
		    				
 					case 40:	// -> Bottom
							if(c.getDirectionSnake()!=3)
								c.setDirectionSnake(4);
		    				break;
		    	
 					default: 	break;
 				}
 			}else if(c.getId()==2){
 	 		    switch(e.getKeyCode()){
 			    	case KeyEvent.VK_D:	// -> Right 
 			    				//if it's not the opposite direction
 			    				if(c.getDirectionSnake()!=2) 
 			    					c.setDirectionSnake(1);
 			    			  	break;
 			    	case KeyEvent.VK_W:	// -> Top
 								if(c.getDirectionSnake()!=4) 
 									c.setDirectionSnake(3);
 			    				break;
 			    				
 			    	case KeyEvent.VK_A: 	// -> Left 
 								if(c.getDirectionSnake()!=1)
 									c.setDirectionSnake(2);
 			    				break;
 			    				
 			    	case KeyEvent.VK_S:	// -> Bottom
 								if(c.getDirectionSnake()!=3)
 									c.setDirectionSnake(4);
 			    				break;
 			    	
 			    	default: 	break;
 	 		    }
 	 		}
 		}
 	
 }

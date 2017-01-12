/* Gazi Mahir Ahmed Naven 
 * Project 4
 * Lab: Wednesday/4 50 
 * TA: Tarin Rickett
 * I did not collaborate with anyone on this project.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LobPong extends JFrame implements KeyListener, ActionListener{
	// used for mantaining the motion and reflections of the ball
	boolean b = true;
	boolean d = true;
	// variable that increases the x co ordinate of the ball with each level
	int increase=1;
	// co-ordinates for moving the paddle;
	int x;
	boolean move= true;
	// serves the purpose for the level number
	int w= 1;
	int points;
	// figure out a way to divide the timer for every time it runs. mod the timer
	double time;
	// takes in value of the x and y co-ordinates of the ball
	double x1;
	double y1;
	Timer t;
	Timer t1;
	// default random co-ordinates to begin with for the obstacle
	int ranX= 100;
	int ranY= 150;
	// random numbers for co-ordinates of the reflective stuff
	Random rand;
	Random rand1;
	// y co-ordinate of the ball drawn
	int by;
	public LobPong(){
		addKeyListener(this);
		setFocusable(true);
		t = new Timer(20, this); // runs every 20 millisecond
		setSize(1000,500);
		
		t.start();
		// adding the draw panel
		draw d = new draw();
		add(d,BorderLayout.CENTER);
		
	}
	public static void main(String[] args) {
		
		LobPong l = new LobPong();
		l.setVisible(true);
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
    @Override
	public void keyPressed(KeyEvent e) {
		
		// if statements for moving the paddle
		if (e.getKeyCode()==KeyEvent.VK_RIGHT){
			x=x+15;
			
			if(x>=getWidth()-85){
				x=getWidth()-85;
			}
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			x=x-15;
			// resets to zero so that paddle does not exit screen
			if(x<=0){
				x=0;
		           }
		     }
	   }

	@Override
    public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	// used for the number of lives user has left
	int liveCount;
	// angle of the time ticker
	double angle;
    protected class draw extends JPanel{
		
    	public draw(){
    		setBackground(Color.getColor("blue", 50));
			
		}
    	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.setColor(Color.BLUE);
	
		//paddle drawing
		g.fillRect(x, getHeight()-40, 70 , 10);
		g.setColor(Color.RED);
		
		int bx= (int) x1;
		by= (int) (getHeight()-y1-60);
		
		if (by+15>=getHeight()-50 && bx>=x && bx<=x+70){
			xdecoy=0;
		}
		// ball drawing
		g.fillOval(bx, by, 15, 15);
		System.out.println(by);
		String p;
		// converts integer to string so that it can be displayed
		p= Integer.toString(points);
		// points text
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Bodoni MT", Font.PLAIN, 30));
		g.drawString(p, 10, 30);
		// level showing text
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Garamond", Font.BOLD, 20));
		g.drawString("Level "+w, 50, 30);
		g.setColor(Color.PINK);
		// reflective item
		
		if (ranX==0){
			move=true;
		}
		if (ranX+60<=getWidth()&& move==true){
		ranX++;
		} else if (ranX+60>getWidth()||move==false){
		ranX--;
		move= false;
		}
		
		if (ranY>= 0.75*getHeight()){
			ranY-=60;
		}
		
		//g.fillRect(ranX, ranY, 40, 15);
		g.fillRoundRect(ranX, ranY, 60, 15, 8, 8);
		// to stop the game if the ball falls down the bottom of the screen
		// to make sure the motion of the ball is when the ball won't reflect with the ceiling
		if (by>getHeight()&&getHeight()>545){
			//t.stop();
			// increments livecount everytime the ball goes down to show the amount of lives left on the screen
			liveCount++;
			d= false;
		    ranX= RandomNumX();   
			ranY = RandomNumY(); 
			x1=0;
		System.out.println("Height big");
		// motion of the ball is according to when the ball will reflect the screen
		}else if (by>getHeight()&&getHeight()<=545){
			liveCount++;
			d= true;
			ranX= RandomNumX();
			ranY= RandomNumY();
			x1=0;
			System.out.println("Height small");
		}
		//drawing for the amount of lives left
		g.setColor(Color.RED);
    	switch (liveCount){
    	
		case 0: 
			g.fillOval(10,40 , 10, 10);
			g.fillOval(25,40 , 10, 10);
			g.fillOval(40,40 , 10, 10);
			break;
		case 1:
			g.fillOval(10,40 , 10, 10);
			g.fillOval(25,40 , 10, 10);
			break;
		case 2:
			g.fillOval(10,40 , 10, 10);
			break;
		case 3:
			t.stop();
		}
    	// making the timer
    	g.setColor(Color.GREEN);
    	
    	// drawing for the time keeper
    	int x5= (int) (30*(Math.cos(Math.toRadians(angle))));
    	int y5= (int)(30*Math.sin(Math.toRadians(angle)));
    	g.drawLine(getWidth()-40-x5, 50+y5, getWidth()-40, 50);
		
		
      }
    }


 // decoy made to reset the x value without hampering the ball motion
	int xdecoy;
	
	// motion method for going down
	public double DOWN (){
		xdecoy-=1;
		double time = xdecoy/(9000*Math.cos(Math.toRadians(80)));
		double y= 9000*(Math.sin(Math.toRadians(80))*time-(0.5*9.81*time*time));
		return y;
	}
	// motion method for going up
	public double UP (){
		xdecoy+=1;
		double time = xdecoy/(9000*Math.cos(Math.toRadians(80)));
		double y= 9000*(Math.sin(Math.toRadians(80))*time-(0.5*9.81*time*time));
		return y;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (x1<=0){
			b=true;
			x1=x1+increase;
			System.out.println("Ball here");
		}
		if(x1>=0&& b == true){
			x1=x1+increase;
	    }
		if ((x1+30)>=getWidth()|| b== false){
			x1=x1-increase;
			b= false;
		
		}
		// for reflection when ball approaches obstacle from beneath
          if ( by <= ranY+15 && by >= ranY && x1+15>= ranX && x1<= ranX + 60){
        	   y1=DOWN();
        	   d= false;

		}else
			// for relection when the ball comes in from upwards
			if (by<= ranY && by>= ranY -15 && x1+15>= ranX && x1<= ranX + 60 && d== false){
			   y1= DOWN();
			   d= true;
			   // System.out.println("YOOYOOYOYYOYOYOOYYOYOOYOY");
			}else 
		// if else statements put to make the ball reflect if it hits the ceiling
		       if (y1+90<getHeight()&& d==true){
		          
	    	        y1= UP();
	              // reflection of paddle
	    	        
	               System.out.println("TRUUUUUUUUEEEEEEEEEEEEEEEEEEEEEEEE");
	               
	               if (y1<=5 && x1>=x && x1<=x+70){
	    		       d=true;
	    		       points++;
	    		       System.out.println("GLOWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
	    		    }
	               
	        //motion of ball for going down
		}else if(y1+90>=getHeight()||d==false){
        	y1=DOWN();
		    d=false;
		    System.out.println("LALALLALALALALALALALALALALALALALALALAL");
		    // to maintain the reflection from hitting the paddle
		    if (y1<=5 && x1>=x && x1<=x+70){
		       d=true;
		       points++;
		       System.out.println("GLOWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
		    }
		    
		}
        
        
		time++;
		// makes sure that the line takes a full revolution in 60 seconds
		if (time%25==0.0){
		// angle increases every 500 milliseconds
		angle+=3;
		// gives bonus points for 30 seconds survival
		if (angle%180==0.0&& w==1){
			points+=10;
			w++;
			increase++;
		// bonus for 90 second survival
		}else if (angle % 540==0.0 && w==2){
			points +=10;
			w++;
			increase++;
		
			// bonus for 165 second survival 
		}else if (angle% 990==0.0 && w==3 ){
			points +=20;
			w++;
			increase++;
			
		}
		}
		repaint();
	}
	public int RandomNumX(){
		Random Randn= new Random();
		int rand= Randn.nextInt(getWidth());
		// to make sure the obstacle is not too right or left
		if (rand>=getWidth()/2){
			rand-=20;
		}else {
			rand+=20;
		}
		return rand;
		
	}
	public int RandomNumY(){
		Random Randn= new Random();
		int rand= Randn.nextInt(getHeight());
		// to make sure the obstacle is not too down or up
		if (rand>=getHeight()/4){
			rand-=50;
	}
		return rand;
	}
	
}
// take into account the random variables so that they don't go too below

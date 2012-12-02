import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/*
Manages game events and redirects these events to the proper objects
*/

public class JRaceManager extends JComponent implements ActionListener, KeyListener, FocusListener{

	protected Timer master_clock;
	protected JRaceCar car;
	
	protected int turn_state;
	protected int accelerate_state;
	protected boolean running;
	
	public JRaceManager(JRaceCar car){
	
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addFocusListener(this);
		
		master_clock = new Timer(JRaceConstants.default_time_interval, null);
		master_clock.addActionListener(this);
		
		this.car = car;
		
		turn_state = 0; //no turn to process
		accelerate_state = 0; //no accelerate or brake request;
		running = false; //at creation the game should not be running
		
		addKeyListener(this);
	}
	
	public void start(){
		running = true;
		if(requestFocusInWindow()){
			System.out.println("Attempting to shift keyboard focus for game...");
		}
		else{
			System.out.println("Could not set keyboard focus for the game.");
		}
		master_clock.start();
	}
	
	public void stop(){
		running = false;
		master_clock.stop();
	}
	
	public void actionPerformed(ActionEvent e){
		
		car.turn(turn_state);
		
		if(accelerate_state == 1){
			car.accelerate();
		}
		else if(accelerate_state == -1){
			car.brake();
		}
		else{
			car.coast();
		}
		
		turn_state = 0;
		accelerate_state = 0;
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyChar() == 'a' && turn_state == 0){
			System.out.println("Turning Left...");
			turn_state = JRaceCar.LEFT;
		}
		else if(e.getKeyChar() == 'd' && turn_state == 0){
			System.out.println("Turning Right...");
			turn_state = JRaceCar.RIGHT;
		}
		else if(e.getKeyChar() == 'w' && accelerate_state == 0){
			System.out.println("Accelerating...");
			accelerate_state = 1;
		}
		else if(e.getKeyChar() == 's' && accelerate_state == 0){
			System.out.println("Braking...");
			accelerate_state = -1;
		}
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	public void focusGained(FocusEvent e){
		System.out.println("Keyboard input will control the car successfully");
	}
	
	public void focusLost(FocusEvent e){
		System.out.println("Keyboard input will no longer controll the car");
		if(running){
			requestFocusInWindow();
		}
	}
}
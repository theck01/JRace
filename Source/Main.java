import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.geometry.*;


public class Main extends JFrame implements ActionListener, KeyListener, FocusListener{

	protected Timer master_clock;
	protected JRaceCar car;
	
	protected int turn_state;
	protected int accelerate_state;
	protected boolean running;
	
    public static void main(String[] args) {
		new Main();
    }
	
    public Main() {
	
		setSize (500, 500);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		SimpleUniverse universe = new SimpleUniverse(canvas3D, 4);
		car = new JRaceCar(universe, 0, 0, 255);
		universe.getViewer().getView().setBackClipDistance(35.0); //sets the view distance so more of the track is visible
		JRaceScene scene = new JRaceScene(car);
		
		//initialize methods and variables required to control car
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addFocusListener(this);
		
		master_clock = new Timer(JRaceConstants.default_time_interval, null);
		master_clock.addActionListener(this);
		
		turn_state = 0; //no turn to process
		accelerate_state = 0; //no accelerate or brake request;
		running = false; //at creation the game should not be running
		
		addKeyListener(this);
		
		//add scene
		System.out.println("Compiling scene graph... this may take a while");
		scene.compile();
		System.out.println("Compiled");
		universe.addBranchGraph(scene);
		
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add(canvas3D, BorderLayout.CENTER);
		
		setVisible(true);
		
		start();
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
	}
	
	//when select keys is pressed set relevant state flag
	public void keyPressed(KeyEvent e){
		if(e.getKeyChar() == 'a' && turn_state == 0){
			turn_state = JRaceCar.LEFT;
		}
		else if(e.getKeyChar() == 'd' && turn_state == 0){
			turn_state = JRaceCar.RIGHT;
		}
		else if(e.getKeyChar() == 'w' && accelerate_state == 0){
			accelerate_state = 1;
		}
		else if(e.getKeyChar() == 's' && accelerate_state == 0){
			accelerate_state = -1;
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyChar() == 'a' && turn_state == JRaceCar.LEFT){
			turn_state = 0;
		}
		else if(e.getKeyChar() == 'd' && turn_state == JRaceCar.RIGHT){
			turn_state = 0;
		}
		else if(e.getKeyChar() == 'w' && accelerate_state == 1){
			accelerate_state = 0;
		}
		else if(e.getKeyChar() == 's' && accelerate_state == -1){
			accelerate_state = 0;
		}
	}
	
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


import javax.media.j3d.*;
import javax.vecmath.*;
import java.lang.Math;
import com.sun.j3d.utils.universe.*;
import java.awt.geom.*;

/*
class that encapsulates all data needed by the car, including view platform,
transforms, methods for movement, etc.
*/
public class JRaceCar extends BranchGroup{

	//Mutable transforms that move the car (and ViewPlatform) around
	protected TransformGroup group_translate;
	protected TransformGroup group_rotate;
	
	//car model,  for braking animation
	protected JRaceCarModel model;
	
	//position, direction, and speed data
	protected Point2D.Float location; //location of the car in world coordinates
	protected float direction; //angle in degrees describing current orientation of the car
	protected float speed; //speed in miles per hour, for display purposes
	protected int gear; //a total of 6 gears is offered, 1-5 being forward gears
						//and 0 being reverse
	
	//constants
	public static final float MAX_SPEED = 115.0f; //in miles per hour
	public static final float MAX_REVERSE = 20.0f; //in miles per hour
	public static final float BRAKE_RATE = 10.22f; //in miles per hour per second
	public static final float COAST_RATE = 1.022f; //in miles per hour per second
	public static final float BASE_ACCEL_RATE = 15.0f; //in miles per hour per second
	public static final float GEAR_2_SPEED = 15.0f; //in miles per hour
	public static final float GEAR_3_SPEED = 30.0f; //in miles per hour
	public static final float GEAR_4_SPEED = 50.0f; //in miles per hour
	public static final float GEAR_5_SPEED = 80.0f; //in miles per hour
	public static final float TURNING_CUTOFF = 45.0f; //in miles per hour
	public static final float MAX_ANG_SPEED = 45.0f; //in degrees per second
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	
	public JRaceCar(SimpleUniverse universe, int r, int g, int b){
		
		super();
		
		speed = 0.0f;
		gear = 1; //Always start in first gear
		location = new Point2D.Float(JRaceConstants.car_start_x, JRaceConstants.car_start_z);
		direction = JRaceConstants.car_rotate_y;
		
		MultiTransformGroup multi_group = universe.getViewingPlatform().getMultiTransformGroup();
		
		//Mutable Transforms addition
		Transform3D car_translate = new Transform3D();
		car_translate.setTranslation(new Vector3f(JRaceConstants.car_start_x, JRaceConstants.car_start_y, JRaceConstants.car_start_z));
		group_translate = multi_group.getTransformGroup(0);
		group_translate.setTransform(car_translate);
		
		Transform3D car_rotate = new Transform3D();
		car_rotate.rotY(Math.toRadians(direction - JRaceConstants.rot_y_offset));
		group_rotate = multi_group.getTransformGroup(1);
		group_rotate.setTransform(car_rotate);
		
		//add car to scene
		model = new JRaceCarModel(r,g,b);
		group_rotate.addChild(model);
		
		//add transforms to offset ViewPlatform from the car model
		Transform3D camera_offset = new Transform3D();
		camera_offset.setTranslation(new Vector3f(JRaceConstants.camera_offset_x, JRaceConstants.camera_offset_y, JRaceConstants.camera_offset_z));
		multi_group.getTransformGroup(2).setTransform(camera_offset);
		
		Transform3D camera_rotate = new Transform3D();
		camera_rotate.rotY(Math.toRadians(-JRaceConstants.rot_y_offset)); //so the camera points behind the car
		multi_group.getTransformGroup(3).setTransform(camera_rotate);
	}
	
	//function increases car speed and calls move()
	public void accelerate(){
		speed += ((8.0f-gear)/7.0f)*JRaceConstants.MPHToMPHS(BASE_ACCEL_RATE);
		if(speed > MAX_SPEED){
			speed = MAX_SPEED;
		}
		switchGears();
		move();
	}
	
	//function gradually pushes car speed to 0 and calls move()
	public void coast(){
		if(speed > 0){
			speed -= JRaceConstants.MPHToMPHS(COAST_RATE);
			if(speed < 0){
				speed = 0;
			}
		}
		else if(speed < 0){
			speed += JRaceConstants.MPHToMPHS(COAST_RATE);
			if(speed > 0){
				speed = 0;
			}
		}
		switchGears();
		move();
	}
	
	//function rapidly decreases car speed and calls move()
	public void brake(){
		speed -= JRaceConstants.MPHToMPHS(BRAKE_RATE);
		if(speed < MAX_REVERSE){
			speed = MAX_REVERSE;
		}
		switchGears();
		move();
	}
	
	//function switches gears as speed decreases when appropriate
	protected void switchGears(){
		if(speed < 0){
			gear = 0;
		}
		else if(speed < GEAR_2_SPEED){
			gear = 1;
		}
		else if(speed < GEAR_3_SPEED){
			gear = 2;
		}
		else if(speed < GEAR_4_SPEED){
			gear = 3;
		}
		else if(speed < GEAR_5_SPEED){
			gear = 4;
		}
		else{
			gear = 5;
		}
	}
	
	//function manipulates car position
	protected void move(){
		
		if(speed == 0){
			return;
		}
		
		float move_x = (float)Math.sin(direction);
		float move_z = (float)Math.cos(direction);
		
		float origin_x = (float)location.getX();
		float origin_z = (float)location.getY();
		
		float x = move_x*speed+origin_x;
		float z = move_z*speed+origin_z;
		
		location.setLocation(x,z);
		
		//update model
		Transform3D new_translation = new Transform3D();
		new_translation.setTranslation(new Vector3f(x, 0.0f, z));
		group_translate.setTransform(new_translation);
	}
	
	//function turns the car
	public void turn(int left_or_right){
		
		if(left_or_right == LEFT){
			if(speed < TURNING_CUTOFF){
				direction += speed/100;
			}
			else{
				direction += MAX_ANG_SPEED/100;
			}
		}
		else{
			if(speed < TURNING_CUTOFF){
				direction -= speed/100;
			}
			else{
				direction -= MAX_ANG_SPEED/100;
			}
		}
		
		//update model
		Transform3D new_rotation = new Transform3D();
		new_rotation.rotY(Math.toRadians(direction - JRaceConstants.rot_y_offset));
		group_rotate.setTransform(new_rotation);
	}

}

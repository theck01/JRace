

public class JRaceConstants{

	//Lighting Constants
	public static final float ambient = 0.5f; //Sets the default ambient reflectivity
	
	//Car positioning constants
	public static final float car_start_x = 0.0f;
	public static final float car_start_y = 0.0f;
	public static final float car_start_z = 0.0f;
	public static final float car_rotate_y = 0;
	public static final float rot_y_offset = 90.0f;
	
	//Camera positioning constants
	public static final float camera_offset_x = -10.0f;
	public static final float camera_offset_y = 2.0f;
	public static final float camera_offset_z = 0.0f;
	
	private JRaceConstants(){}
	
	//function that converts miles per hour into meters per hundredth second
	public static float MPHToMPHS(float mph){
		return (0.44704f*mph)/100; //mph to m/s * 1/100 to get m/hs
	}
}
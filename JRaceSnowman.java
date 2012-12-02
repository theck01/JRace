
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;

/*
Model of a snowman that will sit at the end of the track
*/

public class JRaceSnowman extends BranchGroup{

	protected boolean raise_right_arm;
	protected float arm_angle;
	protected TransformGroup left_arm_group;
	protected TransformGroup right_arm_group;
	
	public static final float RIGHT_ARM_OFFSET = 20.f;
	public static final float LEFT_ARM_OFFSET = -60.f;
	public static final float ARM_SPEED = 40.f;
	public static final float MAX_ANGLE = 40.f;
	
	public JRaceSnowman(){
	
		super();
		
		raise_right_arm = true;
		arm_angle = 0;
		
		Material snow_mat = new Material();
		snow_mat.setDiffuseColor(1.0f,1.0f,1.0f);
		snow_mat.setAmbientColor(1.0f*JRaceConstants.ambient, 1.0f*JRaceConstants.ambient, 1.0f*JRaceConstants.ambient);
		Appearance snow_app = new Appearance();
		snow_app.setMaterial(snow_mat);
		
		Transform3D base_trans = new Transform3D();
		base_trans.setTranslation(new Vector3f(0.f,0.4f, 0.f));
		TransformGroup base_group = new TransformGroup(base_trans);
		this.addChild(base_group);
		base_group.addChild(new Sphere(0.5f, Sphere.GENERATE_NORMALS, snow_app));
		
		Transform3D mid_trans = new Transform3D();
		mid_trans.setTranslation(new Vector3f(0.0f,1.05f,0.f));
		TransformGroup mid_group = new TransformGroup(mid_trans);
		this.addChild(mid_group);
		mid_group.addChild(new Sphere(0.375f, Sphere.GENERATE_NORMALS, snow_app));
		
		Transform3D head_trans = new Transform3D();
		head_trans.setTranslation(new Vector3f(0.0f,1.5f,0.f));
		TransformGroup head_group = new TransformGroup(head_trans);
		this.addChild(head_group);
		head_group.addChild(new JRaceSnowmanHead());
		
		Transform3D right_arm_trans = new Transform3D();
		right_arm_trans.setTranslation(new Vector3f(0.25f, 1.15f, 0.f));
		TransformGroup right_arm_translate = new TransformGroup(right_arm_trans);
		this.addChild(right_arm_translate);
		
		Transform3D right_arm_rotate = new Transform3D();
		right_arm_rotate.rotZ(Math.toRadians(RIGHT_ARM_OFFSET));
		right_arm_group = new TransformGroup(right_arm_rotate);
		right_arm_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		right_arm_translate.addChild(right_arm_group);
		right_arm_group.addChild(new JRaceSnowmanArm(false));
		
		Transform3D left_arm_trans = new Transform3D();
		left_arm_trans.setTranslation(new Vector3f(-0.25f, 1.15f, 0.f));
		TransformGroup left_arm_translate = new TransformGroup(left_arm_trans);
		this.addChild(left_arm_translate);
		
		Transform3D left_arm_rotate = new Transform3D();
		left_arm_rotate.rotZ(Math.toRadians(LEFT_ARM_OFFSET));
		left_arm_group = new TransformGroup(left_arm_rotate);
		left_arm_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		left_arm_translate.addChild(left_arm_group);
		left_arm_group.addChild(new JRaceSnowmanArm(true));
	}
	
	public void move(){
		
		if(raise_right_arm){
			arm_angle += ARM_SPEED/100;
			if(arm_angle >= MAX_ANGLE){
				raise_right_arm = false;			}
		}
		else{
			arm_angle -= ARM_SPEED/100;
			if(arm_angle <= 0){
				raise_right_arm = true;
			}
		}
		
		Transform3D left = new Transform3D();
		left.rotZ(Math.toRadians(LEFT_ARM_OFFSET + arm_angle));
		left_arm_group.setTransform(left);
		
		Transform3D right = new Transform3D();
		right.rotZ(Math.toRadians(RIGHT_ARM_OFFSET + arm_angle));
		right_arm_group.setTransform(right);
	}
}

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;

/*
Snowmans arm, to ease rotation and so that it can be reproduced easily
By default sticks out horizontally
*/

public class JRaceSnowmanArm extends BranchGroup{

	public JRaceSnowmanArm(boolean left_arm){
		
		super();
		
		//Materials
		Material snow_mat = new Material();
		snow_mat.setDiffuseColor(1.0f,1.0f,1.0f);
		snow_mat.setAmbientColor(1.0f*JRaceConstants.ambient, 1.0f*JRaceConstants.ambient, 1.0f*JRaceConstants.ambient);
		Appearance snow_app = new Appearance();
		snow_app.setMaterial(snow_mat);
		
		Material glove_mat = new Material();
		glove_mat.setDiffuseColor(1.0f, 0.1f, 0.1f);
		glove_mat.setAmbientColor(1.0f*JRaceConstants.ambient, 0.1f*JRaceConstants.ambient, 0.1f*JRaceConstants.ambient);
		Appearance glove_app = new Appearance();
		glove_app.setMaterial(glove_mat);
		
		Material metal_mat = new Material();
		metal_mat.setDiffuseColor(0.56f,0.56f,0.56f);
		metal_mat.setAmbientColor(0.56f*JRaceConstants.ambient,0.56f*JRaceConstants.ambient,0.56f*JRaceConstants.ambient);
		Appearance metal_app = new Appearance();
		metal_app.setMaterial(metal_mat);
		
		Material flag_mat = new Material();
		flag_mat.setDiffuseColor(1.0f,0.5f,0.0f);
		flag_mat.setAmbientColor(1.0f*JRaceConstants.ambient,0.5f*JRaceConstants.ambient,0.0f);
		Appearance flag_app = new Appearance();
		flag_app.setMaterial(flag_mat);
		
		//Add rotation transform if required
		Transform3D base_trans = new Transform3D();
		if(left_arm){
			base_trans.rotY(Math.toRadians(180));
		}
		TransformGroup base_group = new TransformGroup(base_trans);
		this.addChild(base_group);
		
		//Joint
		Sphere joint = new Sphere(0.075f, Sphere.GENERATE_NORMALS, snow_app);
		base_group.addChild(joint);
		
		//Arm
		Transform3D arm_rotate = new Transform3D();
		arm_rotate.rotZ(Math.toRadians(-90));
		TransformGroup arm_group = new TransformGroup(arm_rotate);
		base_group.addChild(arm_group);
		
		Transform3D arm_trans = new Transform3D();
		arm_trans.setTranslation(new Vector3f(0.f, 0.25f, 0.f));
		TransformGroup arm_translate = new TransformGroup(arm_trans);
		arm_group.addChild(arm_translate);
		
		Cylinder arm = new Cylinder(0.075f, 0.5f, Cylinder.GENERATE_NORMALS, snow_app);
		arm_translate.addChild(arm);
		
		//Hand
		Transform3D hand_trans = new Transform3D();
		hand_trans.setTranslation(new Vector3f(0.5f, 0.f, 0.f));
		TransformGroup hand_group = new TransformGroup(hand_trans);
		base_group.addChild(hand_group);
		hand_group.addChild(new Sphere(0.1f, Sphere.GENERATE_NORMALS, glove_app));
		
		//Flag
		Transform3D flag_trans = new Transform3D();
		flag_trans.setTranslation(new Vector3f(0.5f, 0.f, 0.f));
		TransformGroup flag_group = new TransformGroup(flag_trans);
		base_group.addChild(flag_group);
		
		Transform3D flag_rotate = new Transform3D();
		flag_rotate.rotZ(Math.toRadians(-30));
		TransformGroup flag_r_group = new TransformGroup(flag_rotate);
		flag_group.addChild(flag_r_group);
		
		//pole
		Transform3D pole_trans = new Transform3D();
		pole_trans.setTranslation(new Vector3f(0.f,0.5f,0.0f));
		TransformGroup pole_group = new TransformGroup(pole_trans);
		flag_r_group.addChild(pole_group);
		pole_group.addChild(new Cylinder(0.025f, 1f, Cylinder.GENERATE_NORMALS, metal_app));
		
		//cloth
		Transform3D cloth_trans = new Transform3D();
		cloth_trans.setTranslation(new Vector3f(0.4f, 0.7f, 0.f));
		TransformGroup cloth_group = new TransformGroup(cloth_trans);
		flag_r_group.addChild(cloth_group);
		cloth_group.addChild(new Box(0.4f, 0.3f, 0.01f, Box.GENERATE_NORMALS, flag_app));
	}
}
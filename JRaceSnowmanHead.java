
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;

/*
 Snowmans Head, to simplify construction
 */

public class JRaceSnowmanHead extends BranchGroup{
	
	public JRaceSnowmanHead(){
		
		super();
		
		//Materials
		Material snow_mat = new Material();
		snow_mat.setDiffuseColor(1.0f,1.0f,1.0f);
		snow_mat.setAmbientColor(1.0f*JRaceConstants.ambient, 1.0f*JRaceConstants.ambient, 1.0f*JRaceConstants.ambient);
		Appearance snow_app = new Appearance();
		snow_app.setMaterial(snow_mat);
		
		Material hat_mat = new Material();
		hat_mat.setDiffuseColor(0.0f,0.0f,0.0f);
		hat_mat.setAmbientColor(0.0f,0.0f,0.0f);
		Appearance hat_app = new Appearance();
		hat_app.setMaterial(hat_mat);
		
		//head
		this.addChild(new Sphere(0.25f,Sphere.GENERATE_NORMALS,snow_app));
		
		//hat
		Transform3D hat_trans = new Transform3D();
		hat_trans.setTranslation(new Vector3f(0.f, 0.2f, 0.f));
		TransformGroup hat_group = new TransformGroup(hat_trans);
		this.addChild(hat_group);
		hat_group.addChild(new Cylinder(0.3f,0.05f,Cylinder.GENERATE_NORMALS, hat_app));
		
		Transform3D top_hat_trans = new Transform3D();
		top_hat_trans.setTranslation(new Vector3f(0.f,0.15f,0.f));
		TransformGroup top_hat_group = new TransformGroup(top_hat_trans);
		hat_group.addChild(top_hat_group);
		top_hat_group.addChild(new Cylinder(0.2f,0.3f,Cylinder.GENERATE_NORMALS, hat_app));
	}
}
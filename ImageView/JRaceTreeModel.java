
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;

/*
model of a tree which will be copied and spread around the JRaceTrack
*/
public class JRaceTreeModel extends BranchGroup{
	
	public static final int SPRING = 0;
	public static final int WINTER = 1;
	public static final int FALL = 2;
	public static final int DEFAULT_HEIGHT = 5; //in meters
	public static final int BROWN[] = {165,100,42};
	
	public JRaceTreeModel(int height, int season){
		
		//Bound and set arguments
		if(season > 2 || season < 0){
			season = SPRING;
		}
		if(height < 3 || height > 10){
			height = DEFAULT_HEIGHT;
		}
		
		
		//Get object color
		
		int red;
		int green;
		int blue;
		
		if(season == SPRING){
			red = (int)(90*Math.random());
			green = (int)(200+55*Math.random());
			blue = (int)(90*Math.random());
		} //If season is green, generate green color pallet
		else if(season == FALL){
			red = (int)(220+35*Math.random());
			green = (int)(255*Math.random());
			blue = (int)(50*Math.random());
		} //If season is fall, generate red-orange-yellow pallet
		else{
			red = 250;
			green = 250;
			blue = 250;			
		} //Else season is winter and generate the white snow color
		
		//Appearances
		Material tree_mat = new Material();
		tree_mat.setDiffuseColor((float)red/255, (float)green/255, (float)blue/255);
		tree_mat.setAmbientColor((float)(red/255)*JRaceConstants.ambient, (float)(green/255)*JRaceConstants.ambient, (float)(blue/255)*JRaceConstants.ambient);
		Appearance tree = new Appearance();
		tree.setMaterial(tree_mat);
		
		Material trunk_mat = new Material();
		trunk_mat.setDiffuseColor((float)BROWN[0]/255, (float)BROWN[1]/255, (float)BROWN[2]/255);
		trunk_mat.setAmbientColor((float)(BROWN[0]/255)*JRaceConstants.ambient, (float)(BROWN[1]/255)*JRaceConstants.ambient, (float)(BROWN[2]/255)*JRaceConstants.ambient);
		Appearance trunk = new Appearance();
		trunk.setMaterial(trunk_mat);
		
		//Sizing
		//radii
		float trunk_radius = 0.25f + (height-3)*(0.25f/7);
		float base_radius = .25f*height;
		float mid_radius = .2f*height;
		float top_radius = .15f*height;
		
		//Trunk
		Transform3D trunk_trans = new Transform3D();
		trunk_trans.setTranslation(new Vector3d(0.0, height*0.25, 0.0));
		TransformGroup trunk_group = new TransformGroup(trunk_trans);
		this.addChild(trunk_group);
		Cylinder tree_trunk = new Cylinder(trunk_radius, height*0.5f, Cylinder.GENERATE_NORMALS, trunk); 
		trunk_group.addChild(tree_trunk);
		
		//Bottom boughs
		Transform3D bottom_trans = new Transform3D();
		bottom_trans.setTranslation(new Vector3d(0.0, height*0.5, 0.0));
		TransformGroup bottom_group = new TransformGroup(bottom_trans);
		this.addChild(bottom_group);
		Cone bottom_bough = new Cone(base_radius, 0.5f*height, Cone.GENERATE_NORMALS, tree);
		bottom_group.addChild(bottom_bough);
		
		//Middle boughs
		Transform3D mid_trans = new Transform3D();
		mid_trans.setTranslation(new Vector3d(0.0, height*(2.0f/3.0f), 0.0));
		TransformGroup mid_group = new TransformGroup(mid_trans);
		this.addChild(mid_group);
		Cone mid_bough = new Cone(mid_radius, (0.4f)*height, Cone.GENERATE_NORMALS, tree);
		mid_group.addChild(mid_bough);
		
		//Top boughs
		Transform3D top_trans = new Transform3D();
		top_trans.setTranslation(new Vector3d(0.0, height*0.75f, 0.0));
		TransformGroup top_group = new TransformGroup(top_trans);
		this.addChild(top_group);
		Cone top_bough = new Cone(top_radius, 0.25f*height, Cone.GENERATE_NORMALS, tree);
		top_group.addChild(top_bough);		
	}
}
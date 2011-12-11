
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;

public class JRaceRoadCorner extends JRaceGrid{
												//				   _______
	public static final int TOP_LEFT = 0;		//	TOP_RIGHT	  /		  \  TOP_LEFT
	public static final int TOP_RIGHT = 1;		//				  |       |
	public static final int BOTTOM_RIGHT = 2;	//				  |		  |
	public static final int BOTTOM_LEFT = 3;	//	BOTTOM_RIGHT  \_______/  BOTTOM_LEFT
	
	public JRaceRoadCorner(int season, int corner_type){
		
		super(true);
		
		int red;
		int green;
		int blue;
		
		int road_red = 120;
		int road_green = 120;
		int road_blue = 120;
		
		if(season == JRaceTreeModel.WINTER){
			red = 250;
			green = 250;
			blue = 250;
		}
		else{
			red = (int)(90*Math.random());
			green = (int)(200+55*Math.random());
			blue = (int)(90*Math.random());
		}
		
		//Materials
		Material ground_mat = new Material();
		ground_mat.setDiffuseColor((float)red/255, (float)green/255, (float)blue/255);
		ground_mat.setAmbientColor((float)(red/255)*JRaceConstants.ambient, (float)(green/255)*JRaceConstants.ambient, (float)(blue/255)*JRaceConstants.ambient);
		Appearance ground_app = new Appearance();
		ground_app.setMaterial(ground_mat);
		
		Material road_mat = new Material();
		road_mat.setDiffuseColor((float)road_red/255, (float)road_green/255, (float)road_blue/255);
		road_mat.setAmbientColor((float)(road_red/255)*JRaceConstants.ambient, (float)(road_green/255)*JRaceConstants.ambient, (float)(road_blue/255)*JRaceConstants.ambient);
		Appearance road_app = new Appearance();
		road_app.setMaterial(road_mat);
		
		//Rotation
		Transform3D rotate = new Transform3D();
		rotate.rotY(Math.toRadians(90*corner_type));
		TransformGroup type = new TransformGroup(rotate);
		this.addChild(type);
		
		TriangleArray ground_square = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.NORMALS);
		ground_square.setCoordinate(0, new Point3d(-4, 0.0, -4));
		ground_square.setCoordinate(1, new Point3d(4, 0.0, 4));
		ground_square.setCoordinate(2, new Point3d(4, 0.0, -4));
		for(int i=0; i<3; i++){
			ground_square.setNormal(i, new Vector3f(0.0f, 1.0f, 0.0f));
		}
		type.addChild(new Shape3D(ground_square, ground_app));
		
		TriangleArray road_square = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.NORMALS);
		road_square.setCoordinate(0, new Point3d(-4, 0.0, -4));
		road_square.setCoordinate(1, new Point3d(-4, 0.0, 4));
		road_square.setCoordinate(2, new Point3d(4, 0.0, 4));
		for(int i=0; i<3; i++){
			road_square.setNormal(i, new Vector3f(0.0f, 1.0f, 0.0f));
		}
		type.addChild(new Shape3D(road_square, road_app));	
	}
}

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;

public class JRaceSceneGrid extends JRaceGrid{
	
	public JRaceSceneGrid(boolean valid_road, int season){
		
		super(valid_road);
		
		int red;
		int green;
		int blue;
		
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
		
		Material ground_mat = new Material();
		ground_mat.setDiffuseColor((float)red/255, (float)green/255, (float)blue/255);
		ground_mat.setAmbientColor((float)(red/255)*JRaceConstants.ambient, (float)(green/255)*JRaceConstants.ambient, (float)(blue/255)*JRaceConstants.ambient);
		Appearance ground_app = new Appearance();
		ground_app.setMaterial(ground_mat);
		
		QuadArray ground_square = new QuadArray(4, QuadArray.COORDINATES | QuadArray.NORMALS);
		ground_square.setCoordinate(0, new Point3d(4, 0.0, 4));
		ground_square.setCoordinate(1, new Point3d(4, 0.0, -4));
		ground_square.setCoordinate(2, new Point3d(-4, 0.0, -4));
		ground_square.setCoordinate(3, new Point3d(-4, 0.0, 4));
		for(int i=0; i<4; i++){
			ground_square.setNormal(i, new Vector3f(0.0f, 1.0f, 0.0f));
		}
		this.addChild(new Shape3D(ground_square, ground_app));		
	}
	
	public JRaceSceneGrid(int season){
		this(true, season);
	}
}
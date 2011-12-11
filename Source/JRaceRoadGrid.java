
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;

public class JRaceRoadGrid extends JRaceGrid{
	
	public JRaceRoadGrid(){
		
		super(true);
		
		int red = 120;
		int green = 120;
		int blue = 120;
		
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
}
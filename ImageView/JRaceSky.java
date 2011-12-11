
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;

public class JRaceSky extends BranchGroup{

	public static final int MAX_SIZE = 5000;
	
	public JRaceSky(){
	
		super();
		
		Material sky_mat = new Material();
		sky_mat.setDiffuseColor((float)50/255,(float)170/255,(float)200/255);
		sky_mat.setAmbientColor((float)50/255,(float)170/255,(float)200/255);
		Appearance sky_app = new Appearance();
		sky_app.setMaterial(sky_mat);
		
		Cylinder sky = new Cylinder(30.0f, 40.0f, Cylinder.GENERATE_NORMALS_INWARD, sky_app);
		this.addChild(sky);
	}
}
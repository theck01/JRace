import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;

import java.awt.Font;

/**
 * Create scene graph here.
 */
public class Scene extends BranchGroup {
    public Scene () {
		
		//
		
		// A car, in a third location
		Transform3D translate3 = new Transform3D();	
		translate3.setTranslation (new Vector3d (0, 0, -5.));
		TransformGroup objTranslate3 = new TransformGroup(translate3);	
		this.addChild (objTranslate3);
		
		Transform3D rotate3 = new Transform3D();
		rotate3.rotY(Math.toRadians(-90));
		TransformGroup objRotate3 = new TransformGroup(rotate3);
		objTranslate3.addChild(objRotate3);
		
		// Add the car, this time make it a separate class
		objRotate3.addChild (new JRaceTreeModel(3, JRaceTreeModel.WINTER));
    }
}
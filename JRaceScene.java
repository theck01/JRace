import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.*;

import com.sun.j3d.utils.geometry.*;

public class JRaceScene extends BranchGroup {
    public JRaceScene (JRaceCar car) {
		
		DirectionalLight sun = new DirectionalLight(new Color3f (1.f, 1.f, 1.f), new Vector3f (-1.f, -0.5f, -1.f));
		sun.setInfluencingBounds (new BoundingSphere (new Point3d(), Float.MAX_VALUE));
		this.addChild(sun);
		
		DirectionalLight filler = new DirectionalLight (new Color3f (.5f, .5f, .5f), new Vector3f (1.f, 0.5f, 1.0f));
		filler.setInfluencingBounds (new BoundingSphere (new Point3d(), Float.MAX_VALUE));
        this.addChild(filler);
		
		AmbientLight ambient = new AmbientLight ();
		ambient.setInfluencingBounds (new BoundingSphere (new Point3d(), Float.MAX_VALUE));
		this.addChild(ambient);
	
		JRaceTrack track = new JRaceTrack();
		car.setTrack(track);
		this.addChild(new JRaceTrack());
    }
}
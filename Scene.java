import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.*;

import com.sun.j3d.utils.geometry.*;

import java.awt.Font;

/**
 * Create scene graph here.
 */
public class Scene extends BranchGroup {
    public Scene (JRaceCar car) {
		JRaceTrack track = new JRaceTrack();
		car.setTrack(track);
		this.addChild (car);
		this.addChild(new JRaceTrack());
    }
}
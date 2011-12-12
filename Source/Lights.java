
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;

/**
 * An object to hold our lights, plugs them into given scene,
 * also sets up ivars mainLight, fillLight, and ambientLight
 *
 * Uses typical setup, like portrait or TV studio:
 * 	Main (key) light, directional, from 45 deg. user's right, above
 * 	Fill light, directional, from 45deg. user's left, dimmer
 */
public class Lights {
    // So main can get them to send to buttons
    private DirectionalLight mainLight;
    private DirectionalLight fillLight;
    private AmbientLight ambientLight;
	
    public Lights (Scene scene) {
		// Main light
		mainLight = new DirectionalLight (
										  new Color3f (1.f, 1.f, 1.f),
										  new Vector3f (-1.f, -0.5f, -1.f));
		mainLight.setInfluencingBounds (new BoundingSphere (new Point3d(), Float.MAX_VALUE));
		mainLight.setCapability(Light.ALLOW_STATE_WRITE);
        scene.addChild(mainLight);
		
		// Fill light
		fillLight = new DirectionalLight (
										  new Color3f (.5f, .5f, .5f),
										  new Vector3f (1.f, 0.5f, 1.0f));
		fillLight.setInfluencingBounds (new BoundingSphere (new Point3d(), Float.MAX_VALUE));
		fillLight.setCapability(Light.ALLOW_STATE_WRITE);
        scene.addChild(fillLight);
		
		// Ambient
		ambientLight = new AmbientLight ();
		ambientLight.setInfluencingBounds (new BoundingSphere (new Point3d(), Float.MAX_VALUE));
		ambientLight.setCapability(Light.ALLOW_STATE_WRITE);
		scene.addChild(ambientLight);
    }
	
    public Light getMainLight () {
		return mainLight;
    }
	
    public Light getFillLight () {
		return fillLight;
    }
	
    public Light getAmbientLight () {
		return ambientLight;
    }
}
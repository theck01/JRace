import java.awt.*;
import javax.swing.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.geometry.*;

/**
 * Example using lighting
 */
public class Main extends JFrame {
    public static void main (String[] args) {
		new Main();
    }
	
    public Main() {
		/*
		 * Here, we really need our own canvas, and our own JFrame,
		 * so we can put some Swing widgets around the edge
		 */
		setSize (500, 500);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		/*
		 * Set up SimpleUniverse, with our own Canvas3D
		 */
		Canvas3D canvas3D = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
		SimpleUniverse universe = new SimpleUniverse (canvas3D, 4);
		Scene scene = new Scene (universe);
		Lights lights = new Lights (scene);
		addKbdNavig (universe, scene);
		
		/*
		 * Install our scene graph
		 */
		scene.compile();
		universe.addBranchGraph (scene);
		
		/*
		 * Make a GUI layout in our JFrame,
		 * with the 3D canvas in the center
		 */
		Container content = getContentPane();
		content.setLayout (new BorderLayout());
		content.add (canvas3D, BorderLayout.CENTER);
		
		/*
		 * Our buttons, each is connected to one of the lights we
		 * created in Scene
		 */
		JPanel panel = new JPanel ();
		content.add (panel, BorderLayout.SOUTH);
		panel.add (new LightButton (lights.getMainLight(), "Main light"));
		panel.add (new LightButton (lights.getFillLight(), "Fill light"));
		panel.add (new LightButton (lights.getAmbientLight(), "Ambient light"));
		
		setVisible (true);
    }
	
    /*
     * Set up for keyboard navigation, as usual.
     */
    private void addKbdNavig (SimpleUniverse universe, BranchGroup scene) {
		KeyNavigatorBehavior kbdBehavior = new KeyNavigatorBehavior (
																	 universe.getViewingPlatform().getViewPlatformTransform());
		kbdBehavior.setSchedulingBounds (new BoundingSphere (new Point3d(), Float.MAX_VALUE));
		scene.addChild (kbdBehavior);
    }
}
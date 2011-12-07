
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.media.j3d.*;

public class LightButton extends JCheckBox implements ItemListener {
    private Light light;
	
    public LightButton (Light light, String label) {
		this.light = light;
		setText (label);
		setSelected (true);
		addItemListener (this);
    }
	
    /**
     * Note no need for repaint()
     * canvas updates from scene graph automatically
     * whenever scene graph data change
     */
    public void itemStateChanged (ItemEvent e) {
		light.setEnable (e.getStateChange()==ItemEvent.SELECTED);
    }
}

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;

public class JRaceTreeScene extends JRaceSceneGrid{
	
	public JRaceTreeScene(int season){
		
		super(false, season);
		
		int rand = (int)(Math.random()*4);
		
		Transform3D grid_rot = new Transform3D();
		grid_rot.rotY(Math.toRadians(rand*90));
		TransformGroup rotate = new TransformGroup(grid_rot);
		this.addChild(rotate);
		
		rand = (int)(Math.random()*3);
		
		if(rand == 0){
			//create 3 small trees and add to the grid
			int size = (int)(Math.random()*2+3);
			JRaceTreeModel tree1 = new JRaceTreeModel(size, season);
			
			Transform3D tree1_trans = new Transform3D();
			tree1_trans.setTranslation(new Vector3f(2.0f, 0.0f, 0.0f));
			TransformGroup tree1_group = new TransformGroup(tree1_trans);
			rotate.addChild(tree1_group);
			tree1_group.addChild(tree1);
		
			size = (int)(Math.random()*2+3);
			JRaceTreeModel tree2 = new JRaceTreeModel(size, season);
			
			Transform3D tree2_trans = new Transform3D();
			tree2_trans.setTranslation(new Vector3f(-2.0f, 0.0f, 2.0f));
			TransformGroup tree2_group = new TransformGroup(tree2_trans);
			rotate.addChild(tree2_group);
			tree2_group.addChild(tree2);
			
			size = (int)(Math.random()*2+3);
			JRaceTreeModel tree3 = new JRaceTreeModel(size, season);
			
			Transform3D tree3_trans = new Transform3D();
			tree3_trans.setTranslation(new Vector3f(-2.0f, 0.0f, -2.0f));
			TransformGroup tree3_group = new TransformGroup(tree3_trans);
			rotate.addChild(tree3_group);
			tree3_group.addChild(tree3);
		}
		else if(rand == 1){	
			//Add 2 medium sized trees to the grid
			int size = (int)(Math.random()*3+5);
			JRaceTreeModel tree1 = new JRaceTreeModel(size, season);
			
			Transform3D tree1_trans = new Transform3D();
			tree1_trans.setTranslation(new Vector3f(2.0f, 0.0f, 2.0f));
			TransformGroup tree1_group = new TransformGroup(tree1_trans);
			rotate.addChild(tree1_group);
			tree1_group.addChild(tree1);
			
			size = (int)(Math.random()*3+5);
			JRaceTreeModel tree2 = new JRaceTreeModel(size, season);
			
			Transform3D tree2_trans = new Transform3D();
			tree2_trans.setTranslation(new Vector3f(-2.0f, 0.0f, -2.0f));
			TransformGroup tree2_group = new TransformGroup(tree2_trans);
			rotate.addChild(tree2_group);
			tree2_group.addChild(tree2);
		}
		else{		
			//Add 1 large sized tree to the grid
			int size = (int)(Math.random()*2+9);
			JRaceTreeModel tree1 = new JRaceTreeModel(size,season);
			rotate.addChild(tree1);	
		}
	}
}
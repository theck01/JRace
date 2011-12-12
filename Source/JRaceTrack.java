
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import java.lang.Math;
import java.awt.geom.*;

public class JRaceTrack extends BranchGroup{

	protected JRaceGrid[][] track_array; //first index is z direction, second is in the x direction
	protected int[][] seasons;
	
	protected static final int INVALID_TERRAIN = -1;
	protected static final int SPRING_CHECKPOINT = 0;
	protected static final int SUMMER_CHECKPOINT = 1;
	protected static final int FALL_CHECKPOINT = 2;
	protected static final int WINTER_CHECKPOINT = 3;
	
	
	public JRaceTrack(){
	
		track_array = new JRaceGrid[30][30];
		for(int i=0; i<30; i++){
			for(int j=0; j<30; j++){
				track_array[i][j] = null;
			}
		}
		
		seasons = new int[30][30];
		
		for(int i=0; i<15; i++){
			for(int j=0; j<15; j++){
				seasons[i][j] = JRaceTreeModel.WINTER;
			}
		}
		
		for(int i=0; i<15; i++){
			for(int j=15; j<30; j++){
				seasons[i][j] = JRaceTreeModel.FALL;
			}
		}
		
		for(int i=15; i<30; i++){
			for(int j=0; j<30; j++){
				seasons[i][j] = JRaceTreeModel.SPRING;
			}
		}
		
		for(int i=0; i<2; i++){
			for(int j=3; j<27; j++){
				add(new JRaceRoadGrid(), 3+i, j);
				add(new JRaceRoadGrid(), 25+i, j);
			}
		}
		for(int i=0; i<2; i++){
			for(int j=5; j<25; j++){
				add(new JRaceRoadGrid(), j, 3+i);
				add(new JRaceRoadGrid(), j, 25+i);
			}
		}
		
		for(int i=5; i<25; i++){
			add(new JRaceSceneGrid(seasons[5][i]), i, 5);
			add(new JRaceSceneGrid(seasons[24][i]), i, 24);
		}
		
		for(int i=6; i<24; i++){
			add(new JRaceSceneGrid(seasons[i][5]), 5, i);
			add(new JRaceSceneGrid(seasons[i][24]), 24, i);
		}
		
		for(int i=2; i<28; i++){
			add(new JRaceSceneGrid(seasons[i][2]), 2, i);
			add(new JRaceSceneGrid(seasons[i][27]), 27, i);
		}
		
		for(int i=3; i<27; i++){
			add(new JRaceSceneGrid(seasons[2][i]), i, 2);
			add(new JRaceSceneGrid(seasons[27][i]), i, 27);
		}
		
		for(int i=9; i<22; i++){
			for(int j=9; j<22; j++){
				add(new JRaceSceneGrid(false, seasons[j][i]), i, j);
			}
		}
		
		fill();
	}
	
	//Function fills all gaps in track with tree scenes. should be called after the road has been completely constructed
	protected void fill(){
		
		for(int i=0; i<30; i++){
			for(int j=0; j<30; j++){
				if(track_array[i][j] == null){
					add(new JRaceTreeScene(seasons[i][j]), j, i);
				}
			}
		}
	}
	
	//Function adds grid to array at index z,x and also adds the grid to the scene
	//with the proper location
	protected void add(JRaceGrid grid, int x, int z){
		//if x and z are within the bounds, add the grid
		if(x < 50 && z < 50 && x>=0 && z >=0){
			Transform3D translation = new Transform3D();
			translation.setTranslation(new Vector3f(x*JRaceGrid.SIZE+4, 0.0f, z*JRaceGrid.SIZE+4));
			TransformGroup trans_group = new TransformGroup(translation);
			this.addChild(trans_group);
			track_array[z][x] = grid;
			trans_group.addChild(grid);
		}
		else{
			System.out.println("Cannot add, indicies out of bounds");
		}
	}
	
	public Point2D.Float getCheckpointLocation(int checkpoint){
		switch(checkpoint){
				case SPRING_CHECKPOINT:
					return new Point2D.Float(3.5f*JRaceGrid.SIZE, 17f*JRaceGrid.SIZE);
				case SUMMER_CHECKPOINT:
					return new Point2D.Float(17f*JRaceGrid.SIZE, 25.5f*JRaceGrid.SIZE);
				case FALL_CHECKPOINT:
					return new Point2D.Float(25.5f*JRaceGrid.SIZE, 13f*JRaceGrid.SIZE);
				default:
					return new Point2D.Float(13f*JRaceGrid.SIZE, 3.5f*JRaceGrid.SIZE);
		}
	}
	
	public float getCheckpointDirection(int checkpoint){
		switch(checkpoint){
			case SPRING_CHECKPOINT:
				return 0f;
			case SUMMER_CHECKPOINT:
				return 90f;
			case FALL_CHECKPOINT:
				return 180f;
			default:
				return 270f;
		}
	}
	
	public int validTerrain(float x, float z){
		
		int x_index = (int)(x/JRaceGrid.SIZE);
		int z_index = (int)(z/JRaceGrid.SIZE);
		
		if(!track_array[z_index][x_index].navagatable()){
			return INVALID_TERRAIN;
		}
		
		if(x_index < 15){
			if(z_index < 15){
				return WINTER_CHECKPOINT;
			}
			else{
				return SPRING_CHECKPOINT;
			}
		}
		else{
			if(z_index < 15){
				return FALL_CHECKPOINT;
			}
			else{
				return SUMMER_CHECKPOINT;
			}
		}
	}
}
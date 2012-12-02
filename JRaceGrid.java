
import javax.media.j3d.*;

abstract class JRaceGrid extends BranchGroup{

	public static final float SIZE = 8.0f; //each grid should be 8x8 units in dimension
	
	protected boolean valid_road;
	
	public JRaceGrid(boolean valid_road){
		super();
		this.valid_road = valid_road;
	}
	
	public boolean navagatable(){
		return valid_road;
	}
}
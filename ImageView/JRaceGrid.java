
import javax.media.j3d.*;

abstract class JRaceGrid extends BranchGroup{

	protected boolean valid_road;
	
	public JRaceGrid(boolean valid_road){
		super();
		this.valid_road = valid_road;
	}
	
	public boolean navagatable(){
		return valid_road;
	}
}
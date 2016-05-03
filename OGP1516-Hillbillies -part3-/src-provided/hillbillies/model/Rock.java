package hillbillies.model;

import java.util.HashSet;

import hillbillies.model.GameObject;
import hillbillies.model.PositionVector;

public class Rock extends SolidCube {

	public Rock(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public Rock(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}

	@Override
	public int getTerrainType() {
		return 1;
	}

}

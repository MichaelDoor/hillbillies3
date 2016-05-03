package hillbillies.model;

import java.util.HashSet;

import hillbillies.model.GameObject;
import hillbillies.model.PositionVector;

public abstract class PassableCube extends Cube {

	public PassableCube(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public PassableCube(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}


}


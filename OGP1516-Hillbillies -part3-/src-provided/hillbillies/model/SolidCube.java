package hillbillies.model;

import java.util.HashSet;

import hillbillies.model.GameObject;
import hillbillies.model.PositionVector;

public abstract class SolidCube extends Cube {

	public SolidCube(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public SolidCube(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}

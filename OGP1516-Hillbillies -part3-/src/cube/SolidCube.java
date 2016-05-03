package cube;

import java.util.HashSet;

import objects.GameObject;
import position.PositionVector;

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

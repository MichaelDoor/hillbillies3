package cube;

import java.util.HashSet;

import objects.GameObject;
import position.PositionVector;

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

package cube;

import java.util.HashSet;

import objects.GameObject;
import position.PositionVector;

public class Workshop extends PassableCube {

	public Workshop(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public Workshop(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}

	@Override
	public int getTerrainType() {
		return 3;
	}

}

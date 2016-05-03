package cube;

import java.util.HashSet;

import objects.GameObject;
import position.PositionVector;

public class Tree extends SolidCube {

	public Tree(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public Tree(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}

	@Override
	public int getTerrainType() {
		return 2;
	}

}

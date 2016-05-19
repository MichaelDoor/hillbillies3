package cube;

import java.util.HashSet;

import objects.GameObject;
import position.PositionVector;

/**
 * A class of rock cubes.
 * @author Michaël
 *
 */
public class Rock extends SolidCube {
	
	/**
	 * Initialize a new rock cube with a given position.
	 * @param position	The given position.
	 * @effect	A new cube with the given position is initialized.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this new rock cube.
	 */
	public Rock(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	/**
	 * Initialize a new rock cube with a given position and content.
	 * @param position	The given position.
	 * @param content	The given content.
	 * @effect	A new cube is initialized with a given position and content.
	 * @throws IllegalArgumentException
	 * 			The given position or content is not valid for this new rock cube.
	 */
	public Rock(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	/**
	 * Return the terrain type of this rock cube.
	 * @return	3
	 */
	@Override
	public int getTerrainType() {
		return 1;
	}

}

package cube;

import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Raw;
import objects.GameObject;
import position.PositionVector;

/**
 * A class of passable cubes.
 * @author Michaël
 *
 */
public abstract class PassableCube extends Cube {

	/**
	 * Initialize a new passable cube, with a given position.
	 * @param position	The given position.
	 * @effect	A new cube is initialized with the given position.
	 * @throws IllegalArgumentException	
	 * 			The given position is not a valid position for the new passable cube.
	 */
	public PassableCube(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	/**
	 * Initialize a new passable cube with a given position and content.
	 * @param position	The given position.
	 * @param content	The given content.
	 * @effect	A new cube is initialized with a given position and content.
	 * @throws IllegalArgumentException
	 * 			The given position or content is not valid for this passable cube.
	 */
	public PassableCube(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	/**
	 * Check whether this passable cube is solid.
	 * @effect	Return false.
	 */
	@Override @Raw
	public boolean isSolid() {
		return false;
	}


}

package cube;

import java.util.HashSet;

import objects.GameObject;
import position.PositionVector;

/**
 * A class of workshop cubes.
 * @author Michaël
 *
 */
public class Workshop extends PassableCube {
	
	/**
	 * Initialize a new workshop cube with a given position.
	 * @param position	The given position.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this new workshop cube.
	 */
	public Workshop(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	/**
	 * Initialize a new workshop cube with a given position and content.
	 * @param position	The given position.
	 * @param content	The given content.
	 * @throws IllegalArgumentException
	 * 			The given position or content is not valid for this new workshop cube.
	 */
	public Workshop(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	/**
	 * Return the terrain type of this workshop cube.
	 * @return	3
	 */
	@Override
	public int getTerrainType() {
		return 3;
	}

}
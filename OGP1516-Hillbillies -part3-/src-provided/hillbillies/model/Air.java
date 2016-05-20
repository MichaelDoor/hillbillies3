package hillbillies.model;

import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.GameObject;
import hillbillies.model.PositionVector;

/**
 * A class of air cubes.
 * @author Michaël
 *
 */
public class Air extends PassableCube {
	
	/**
	 * Initialize a new air cube with a given position.
	 * @param position	The given position.
	 * @effect	A cube with the given position is created.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this air cube.
	 */
	public Air(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	/**
	 * Initialize a new air cube with a given position and content.
	 * @param position	The given position.
	 * @param content	The given content.
	 * @throws IllegalArgumentException
	 * 			The given position or contant is not valid for this air cube.
	 */
	public Air(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	/**
	 * Return the terrain type of this air cube.
	 * @return	0
	 */
	@Override @Raw
	public int getTerrainType() {
		return 0;
	}

}

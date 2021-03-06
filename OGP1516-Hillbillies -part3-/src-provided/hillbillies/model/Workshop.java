package hillbillies.model;

import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.GameObject;
import hillbillies.model.PositionVector;

/**
 * A class of workshop cubes.
 * @author Micha�l
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
	@Override @Raw
	public int getTerrainType() {
		return 3;
	}

}
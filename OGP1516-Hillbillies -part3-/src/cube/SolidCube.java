package cube;

import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import objects.GameObject;
import position.PositionVector;

/**
 * A class of solid cubes.
 * @author Michaël
 *
 */
public abstract class SolidCube extends Cube {
	
	/**
	 * Initialize a new solid cube with a given position.
	 * @param position	The given position.
	 * @effect	A new cube with the given position is initialized.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this new solid cube.
	 */
	@Model
	SolidCube(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	/**
	 * Initialize a new solid cube with a given position and content.
	 * @param position	The given position.
	 * @param content	The given content.
	 * @effect	A new cube with the given position and content is initialized.
	 * @throws IllegalArgumentException
	 * 			The given position or content is not valid for this new solid cube.
	 */
	public SolidCube(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	/**
	 * Check if this solid cube is solid.
	 * @return	Always true.
	 */
	@Override @Raw
	public boolean isSolid() {
		return true;
	}
}

package cube;

import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Raw;
import objects.GameObject;
import position.PositionVector;

/**
 * A class of tree cubes.
 * @author Michaël
 *
 */
public class Tree extends SolidCube {
	
	/**
	 * Initialize a new tree cube with a given position.
	 * @param position	The given position.
	 * @effect	A new cube with the given position is initialized.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this new tree cube.
	 */
	public Tree(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	/**
	 * Initialize a new tree cube with a given position and content.
	 * @param position	The given position.
	 * @param content	The given content.
	 * @effect	A new cube with the given position and content is initialized.
	 * @throws IllegalArgumentException
	 * 			The given position or content is not valid for this new tree cube.
	 */
	public Tree(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}
	
	/**
	 * Return the terrain type of this tree cube.
	 * @return	2
	 */
	@Override @Raw
	public int getTerrainType() {
		return 2;
	}

}

package objects;

import position.PositionVector;

/**
 * A class of boulders, having a position and weight.
 * @author Michaël
 * @version	1.00
 */
public class Boulder extends Material {
	
	/**
	 * Create a new boulder with a given position and a random weight.
	 * @param position	The given position.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	public Boulder(PositionVector position) {
		super(position);
	}

}

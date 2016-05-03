package hillbillies.model;

import hillbillies.model.PositionVector;

/**
 * A class of boulders, having a position and weight.
 * @author Michaël
 *
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

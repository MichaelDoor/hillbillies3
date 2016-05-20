package hillbillies.model;

import hillbillies.model.Material;
import hillbillies.model.PositionVector;

/**
 * A class of logs, having a position and weight.
 * @author Michaël
 * @version	1.00
 */
public class Log extends Material {
	
	/**
	 * Create a new log with a given position and random weight.
	 * @param position	The given position.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	public Log(PositionVector position) {
		super(position);
	}

}

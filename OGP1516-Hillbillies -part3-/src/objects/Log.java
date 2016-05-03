package objects;

import position.PositionVector;

/**
 * A class of logs, having a position and weight.
 * @author Michaël
 *
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

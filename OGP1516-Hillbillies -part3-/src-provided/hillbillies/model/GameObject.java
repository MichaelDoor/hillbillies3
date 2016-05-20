package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.PositionVector;
import hillbillies.model.World;

/**
 * A class of game objects, having a position and weight.
 * @invar  The activityStatus of each game object  must be a valid activityStatus.
 *       | this.isValidActivityStatus(getActivityStatus())
 * @invar  The current velocity of each game object must be a valid current velocity for any
 *         game object.
 *       | isValidCurrentVelocity(getCurrentVelocity())
 * @invar  The targeted next position of each game object must be a next targeted adjacent position.
 *       | this.isValidNextPosition(getNextPosition())
 * @invar  The unitPosition of each game object must be a valid unitPosition.
 *       | this.isValidUnitPosition(getUnitPosition())
 * @invar  The weight of this game object must be a valid weight.
 *       | this.isValidWeight(getWeight())
 * @invar  The world of each game object must be a valid world.
 *       | this.isValidWorld(getWorld())
 * 
 * @author Michaël
 * @version	3.00
 *
 */
public abstract class GameObject {
	
	/**
	 * Create a new game object with a given position.
	 * @param position	The given position.
	 * @effect 	The world of this new game object is set to null, its unit position to the given position, its activity status to
	 * 			default, its next position to its unit position and its velocity to the zero vector.
	 * 			| this.setUnitPosition(position)
	 *       	| this.setWorld(null)
	 *       	| this.setActivityStatus("default")
	 *       	| this.setNextPosition(this.getUnitPosition())
	 *       	| this.setCurrentVelocity(new PositionVector(0,0,0))
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws	IllegalArgumentException
	 * 			The given weight is not a valid weight for this new game object.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	public GameObject(PositionVector position) throws IllegalArgumentException, NullPointerException {
		this.setUnitPosition(position);
		this.setWorld(null);
		this.setActivityStatus("default");
		this.setNextPosition(this.getUnitPosition());
		this.setCurrentVelocity(new PositionVector(0,0,0));
	}
	
	/**
	 * Return the unitPosition of this game object.
	 */
	@Basic @Raw
	public PositionVector getUnitPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given unitPosition is a valid unitPosition for
	 * any game object.
	 *  
	 * @param  unitPosition
	 *         The unitPosition to check.
	 * @return 	True if and only if this game object has no world or it is a valid standing position in this game objects world.
	 *       	| result == (this.getWorld() == null) || (this.getWorld().isValidPosition(position))
	 * @throws	NullPointerException
	 * 			The position is not effective.
	 * 			| position == null
	*/
	protected boolean isValidUnitPosition(PositionVector position) throws NullPointerException {
		if(position == null)
			throw new NullPointerException();
		if(this.getWorld() == null)
			return true;
		else{
			return this.getWorld().isValidPosition(position);
		}
	}
	
	/**
	 * Set the unitPosition of this game object to the given unitPosition.
	 * 
	 * @param  position
	 *         The new unitPosition for this game object.
	 * @post   The unitPosition of this game object is equal to
	 *         the given unitPosition.
	 *       	| new.getUnitPosition().equals(position)
	 * @effect	If this game object belongs to a world, this unit's world updates it's position of this gameObject.
	 * @throws IllegalArgumentException
	 *         The given unitPosition is not a valid unitPosition for any
	 *         game object.
	 *       	| ! isValidUnitPosition(getUnitPosition())
	 * @throws	NullPointerException
	 * 			The position is not effective.
	 * 			| position == null
	 */
	@Raw @Model
	protected void setUnitPosition(PositionVector position) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidUnitPosition(position))
			throw new IllegalArgumentException("Out of bounds!");
		PositionVector oldCubePosition = null;
		if((this.getWorld() != null))
			oldCubePosition = this.getCubePositionVector();
		this.position = new PositionVector (position.getXArgument(), position.getYArgument(), position.getZArgument());
		
		if((oldCubePosition != null) && (! oldCubePosition.equals(new PositionVector((int) position.getXArgument(), 
				(int) position.getYArgument(), (int) position.getZArgument())))){
			this.getWorld().updateObjectPosition(this, oldCubePosition, this.getCubePositionVector());
		}
	}
	
	/**
	 * Variable registering the unitPosition of this game object.
	 */
	protected PositionVector position;
	
	/**
	 * Return the cubePosition of this unit.
	 * @return	The position of the cube on which this unit is standing.
	 * 			| result == {(int) this.getUnitPosition().getXArgument(), (int) this.getUnitPosition().getYArgument(),
	 * 			|										(int) this.getUnitPosition().getZArgument()}
	 */
	public int[] getCubePosition() {
		int x = (int) this.getUnitPosition().getXArgument();
		int y = (int) this.getUnitPosition().getYArgument();
		int z = (int) this.getUnitPosition().getZArgument();
		int[] position = {x,y,z};
		return position;
	}
	
	/**
	 * Return the weight of this unit.
	 */
	@Basic @Raw
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Set the weight of this game object to the given weight.
	 * @param weight	The given weight.
	 * @post	This game object's weight equals the given weight if it is a valid weight for this game object.
	 */
	@Raw @Model
	protected void setWeight(int weight) throws IllegalArgumentException {
		if(this.isValidWeight(weight))
			this.weight = weight;
	}
	
	/**
	 * Check whether the given weight is a valid weight for
	 * this game object.
	 *  
	 * @param  weight
	 *         The weight to check.
	 * @return The given weight is greater than or equal to 0
	 *       | result == weight >= 0
	*/
	@Raw
	protected boolean isValidWeight(int weight) {
		return weight >= 0;
	}
	
	/**
	 * Variable registering the weight of this unit.
	 */
	protected int weight;
	
	/**
	 * Advance time for this game object by a given amount of time.
	 * @param dt	The given amount of time.
	 */
	public abstract void advanceTime(double dt);
	
	/**
	 * Return the world of this game object.
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Check whether the given world is a valid world for
	 * any game object.
	 *  
	 * @param  world
	 *         The world to check.
	 * @return True if and only if the given world is not effective or this game object's cube position is a valid position
	 * 			for the given world.
	 *       | result == (world == null) || (world.isValidPosition(new PositionVector(x,y,z)))
	*/
	protected boolean isValidWorld(World world) {
		int[] position = this.getCubePosition();
		int x = position[0];
		int y = position[1];
		int z = position[2];
		return ((world == null) || (world.isValidPosition(new PositionVector(x,y,z))));
	}
	
	/**
	 * Set the world of this game object to the given world.
	 * 
	 * @param  world
	 *         The new world for this game object.
	 * @post   The world of this new game object is equal to
	 *         the given world.
	 *       | new.getWorld() == world
	 * @throws IllegalArgumentException
	 *         The given world is not a valid world for this
	 *         game object.
	 *       | ! isValidWorld(getWorld())
	 */
	@Raw @Model
	private void setWorld(World world) 
			throws NullPointerException, IllegalArgumentException {
		try{
			if (! isValidWorld(world))
				throw new IllegalArgumentException();
			this.world = world;
		}
		catch (IllegalArgumentException exc){
			
		}
	}
	
	/**
	 * Variable registering the world of this game object.
	 */
	protected World world;
	
	/**
	 * Change this game object's current world to a given world.
	 * @param world	The given world.
	 * @effect	This game object's world is set to the given world.
	 * 			|	this.setWorld(world)
	 */
	@Raw
	public void changeWorld(World world){
		this.setWorld(world);
	}
	/**
	 * Return this game object's cube position as a position vector.
	 * @return	The first second and third component of this game object's cube position.
	 * 			| result == new PositionVector(this.getCubePosition()[0], this.getCubePosition()[1],
	 * 																						 this.getCubePosition()[2])
	 */
	public PositionVector getCubePositionVector(){
		int[] cubePosition = this.getCubePosition();
		return new PositionVector(cubePosition[0], cubePosition[1], cubePosition[2]);
	}
	
	/**
	 * Return the targeted adjacent position of this unit.
	 */
	@Basic @Raw
	public PositionVector getNextPosition() {
		return this.nextPosition;
	}
	
	/**
	 * Check whether the given targeted adjacent position is a valid targeted adjacent position for
	 * any unit.
	 *  
	 * @param  targeted adjacent position
	 *         The targeted adjacent position to check.
	 * @return True if and only if the given next position is equal to this game object's current position or is a valid adjacent position
	 * 			of this game object.
	 *       | result == (this.isValidAdjacent(nextPosition)) ||  (this.getUnitPosition().equals(nextPosition))
	*/
	protected boolean isValidNextPosition(PositionVector nextPosition) {
		return ((this.getUnitPosition().equals(nextPosition)) || (this.isValidAdjacent(nextPosition)));
	}
	
	/**
	 * Set the targeted adjacent position of this unit to the given targeted adjacent position.
	 * 
	 * @param  nextPosition
	 *         The new targeted adjacent position for this unit.
	 * @post   The targeted adjacent position of this new unit is equal to
	 *         the given targeted adjacent position.
	 *       | new.getNextPosition() == new PositionVector(nextPosition.getXArgument(), nextPosition.getYArgument(),
	 *       | 																					 nextPosition.getZArgument())
	 * @throws IllegalArgumentException
	 *         The given targeted adjacent position is not a valid targeted adjacent position for any
	 *         unit.
	 *       | (! isValidNextPosition(getNextPosition()))
	 * @throws	NullPointerException
	 * 			The next position is not effective.
	 * 			| nextPosition == null
	 */
	@Raw @Model
	protected void setNextPosition(PositionVector nextPosition) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidNextPosition(nextPosition)) {
			throw new IllegalArgumentException();
		}
		this.nextPosition = new PositionVector(nextPosition.getXArgument(), nextPosition.getYArgument(), nextPosition.getZArgument());
	}
	
	/**
	 * Variable registering the targeted adjacent position of this unit.
	 */
	protected PositionVector nextPosition;
	
	/**
	 * Check whether a given position is located in an adjacent cube of the position of this game object, or is this unit's position.
	 * @param position	The position to check.
	 * @return	True if and only if the absolute values of the components of the difference vector between this unit's cube position 
	 * 			and the given position aren't greater than 1 and it's a valid position in this game object's world.
	 * 			| result == (((Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == 1) 
	 * 			|				|| (Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == 0) 
	 * 			|					|| (Math.abs(this.getCubePosition()[0] - (int) position.getXArgument()) == -1))
	 *			|		&& (((Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == 1) 
	 *			|				|| (Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == 0) 
	 *			|					|| (Math.abs(this.getCubePosition()[1] - (int) position.getYArgument()) == -1))
	 *			|			&& (((Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 1) 
	 *			|				|| (Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 0) 
	 *			|					|| (Math.abs(this.getCubePosition()[2] - (int) position.getZArgument()) == 1))
	 *			|				&& (this.isValidUnitPosition(PositionVector.sum(position, this.getUnitPosition())))))
	 *			| 				&& (! this.getWorld().isValidPosition(position))
	 */
	public boolean isValidAdjacent(PositionVector position) {
		if(! this.getWorld().isValidPosition(position))
			return false;
		int positionX = (int) position.getXArgument();
		int positionY = (int) position.getYArgument();
		int positionZ = (int) position.getZArgument();
		int unitX = this.getCubePosition()[0];
		int unitY = this.getCubePosition()[1];
		int unitZ = this.getCubePosition()[2];
		int[] difference = {Math.abs(unitX-positionX), Math.abs(unitY-positionY), Math.abs(unitZ-positionZ)};
		if (((difference[0] == -1) || (difference[0] == 0) || (difference[0] == 1))
				&& ((difference[1] == -1) || (difference[1] == 0) || (difference[1] == 1))
				&& ((difference[2] == -1) || (difference[2] == 0) || (difference[2] == 1))){
			return true;
		}
		return false;
	}
	
	/**
	 * Variable registering the falling velocity of any game object.
	 */
	protected static PositionVector fallVelocity = new PositionVector(0, 0, -3);
	
	/**
	 * Makes this game object fall.
	 * @effect	This game object's activity status is set to 'fall', it's current velocity to the fall velocity of any game object.
	 * 			| this.setActivityStatus("fall")
	 * 			| this.setNextPosition(PositionVector.centrePosition(this.getWorld().getPositionUnderneath(this.getCubePosition())))
	 */
	@Raw
	protected void fall() {
		this.setActivityStatus("fall");
		this.setCurrentVelocity(fallVelocity);
	}
	
	/**
	 * Check whether this game object should fall.
	 */
	protected abstract boolean fallCheck();
	
	/**
	 * Return the current velocity of this game object.
	 */
	@Basic @Raw
	public PositionVector getCurrentVelocityBasic() {
		return this.currentVelocity;
	}
	
	/**
	 * Check whether the given current velocity is a valid current velocity for
	 * any game object.
	 *  
	 * @param  current velocity
	 *         The current velocity to check.
	 * @return 
	 *       | result == true
	*/
	protected static boolean isValidCurrentVelocity(PositionVector currentVelocity) {
		return true;
	}
	
	/**
	 * Set the current velocity of this game object to the given current velocity.
	 * 
	 * @param  currentVelocity
	 *         The new current velocity for this game object.
	 * @post   The current velocity of this game object is equal to
	 *         the given current velocity.
	 *       | new.getCurrentVelocity() == currentVelocity
	 * @throws IllegalArgumentException
	 *         The given current velocity is not a valid current velocity for any
	 *         game object.
	 *       | ! isValidCurrentVelocity(getCurrentVelocity())
	 * @throws	NullPointerException
	 * 			The given velocity is not effective.
	 * 			| currentVelocity == null
	 */
	@Raw @Model
	protected void setCurrentVelocity(PositionVector currentVelocity) 
			throws IllegalArgumentException, NullPointerException {
		if (! isValidCurrentVelocity(currentVelocity))
			throw new IllegalArgumentException();
		this.currentVelocity = currentVelocity;
	}
	
	/**
	 * Variable registering the current velocity of this game object.
	 */
	protected PositionVector currentVelocity;
	
	/**
	 * Terminate this game object.
	 * @effect	This game object's next position and world are set null, it's velocity to zero.
	 * @throws IllegalStateException
	 * 			This game object is already terminated.
	 */
	@Raw
	protected void terminate() throws IllegalStateException {
		if(this.isTerminated())
			throw new IllegalStateException("Game object already terminated!");
		this.setCurrentVelocity(new PositionVector(0,0,0));
		this.nextPosition = null;
		this.world = null;
	}
	
	/**
	 * Check whether this game object is terminated.
	 * @return	True if and only if this game object's next position and world are null and it's velocity zero.
	 */
	@Raw
	public boolean isTerminated() {
		PositionVector velocity = this.getCurrentVelocityBasic();
		return ((velocity.equals(new PositionVector(0,0,0))) && (this.getNextPosition() == null)
				&& (this.getWorld() == null));
	}
	
	/**
	 * Return the activityStatus of this game object.
	 */
	@Basic @Raw
	public String getActivityStatus() {
		return this.activityStatus;
	}
	
	/**
	 * Check whether the given activityStatus is a valid activityStatus for
	 * this game object.
	 *  
	 * @param  activityStatus
	 *         The activityStatus to check.
	 * @return 
	 *       | result == (activityStatus.equals("fall")) || (activityStatus.equals("default"))
	 */
	protected boolean isValidActivityStatus(String activityStatus) {
		return ((activityStatus.equals("default")) || (activityStatus.equals("fall")));
	}
	
	/**
	 * Set the activityStatus of this game object to the given activityStatus.
	 * 
	 * @param  activityStatus
	 *         The new activityStatus for this game object.
	 * @post   The activityStatus of this game object is equal to
	 *         the given activityStatus.
	 *       | new.getActivityStatus() == activityStatus
	 * @throws IllegalArgumentException
	 *         The given activityStatus is not a valid activityStatus for any
	 *         game object.
	 *       | ! isValidActivityStatus(getActivityStatus())
	 * @throws	NullPointerException
	 * 			The activity status is not effective.
	 * 			| activityStatus == null
	 */
	@Raw @Model
	protected void setActivityStatus(String activityStatus) 
			throws IllegalArgumentException, NullPointerException {
		if (! this.isValidActivityStatus(activityStatus))
			throw new IllegalArgumentException();
		this.activityStatus = activityStatus;
	}
	
	/**
	 * Variable registering the activityStatus of this game object.
	 */
	protected String activityStatus;
	
	/**
	 * A method to make this game object move for a given time with a multiplied speed.
	 * @param dt	Time
	 * @param multiplier	The given multiplyer.
	 */
	protected abstract void miniMove(double dt, int multiplier);
}

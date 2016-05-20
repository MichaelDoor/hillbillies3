package objects;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.Util;
import position.PositionVector;

/**
 * A class of materials, having a position and weight.
 * @invar  The weight of this material must be a valid weight.
 *       | this.isValidWeight(getWeight())
 * @author Michaël
 * @version	2.00
 */
public abstract class Material extends GameObject {

	/**
	 * Create a material, with a given position and a random weight.
	 * @param position	The given position.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	@Model
	Material(PositionVector position){
		super(position);
		this.setWeight();
	}
	
	/**
	 * Set the weight of this material.
	 * @effect	The weight of this material is set to a random value between 10 and 50.
	 */
	@Raw
	private void setWeight() {
		Random generator = new Random();
		super.setWeight(generator.nextInt(41)+10);
	}
	
	/**
	 * Check whether the given weight is a valid weight for
	 * this material.
	 *  
	 * @param  weight
	 *         The weight to check.
	 * @return The given weight is greater than or equal to 0 and smaller than or equal to 50.
	 *       | result == (weight >= 0) && (weight <= 50)
	*/
	@Override @Raw
	protected boolean isValidWeight(int weight) {
		return (weight >= 0) && (weight <= 50);
	}
	
	/**
	 * Makes this material move for a given amount of time at a given amount time it's speed.
	 * @param dt	The amount of time.
	 * @param multiplyer How many times the material speed.
	 * @effect	This material orientation is updated according to the direction it'll move.
	 * 			| this.setOrientation( Math.atan2(this.getCurrentVelocity().getYArgument(),
	 * 			|												 this.getCurrentVelocity().getXArgument()))
	 * @effect	This material covers a distance by moving at it's speed times multiplier for the given time, 
	 * 			when it doesn't reach it's next position within the given time.
	 * 			| this.setUnitPosition(PositionVector.sum(this.getUnitPosition(), PositionVector.multiplyBy(dt, this.getCurrentVelocity())))
	 * @effect	This material has reached it's next position if time was sufficient to reach it, it gains 1xp if it wasn't falling,
	 * 			it's activity status, is set to default and it's current velocity to the zero vector. 
	 * 			| this.setUnitPosition(new PositionVector(this.getNextPosition().getXArgument(),this.getNextPosition().getYArgument(),
	 *			|	this.getNextPosition().getZArgument())))
	 *			| if(! this.getActivityStatus().equals("fall"))
	 *			| 	this.gainExp(1)
	 *			| this.setActivityStatus("default")
	 *			| this.setCurrentVelocity(new PositionVector(0, 0, 0))
	 * @effect	In case the material has time left after reaching it's next position, time advances.
	 * 			| this.advanceTime(restingTime)
	 * @effect	The automatic rest counter is increased with the given amount of time.
	 * 			| this.increaseAutRestCounter(dt)
	 * @throws	IllegalArgumentException
	 * 			Time is negative.
	 * 			| time < 0
	 */
	protected void miniMove(double dt, int multiplier) throws IllegalArgumentException {
		if (dt <0)
			throw new IllegalArgumentException();
		double distance = PositionVector.calcDistance(this.getUnitPosition(), this.getNextPosition());
		double speed = PositionVector.calcDistance((new PositionVector(0,0,0)), this.getCurrentVelocityBasic());
		double travelTime = distance/speed;
		if (travelTime <= dt) {
			this.setUnitPosition(new PositionVector(this.getNextPosition().getXArgument(),this.getNextPosition().getYArgument(),
					this.getNextPosition().getZArgument()));
			this.setActivityStatus("default");
			this.setCurrentVelocity(new PositionVector(0, 0, 0));
			double restingTime = dt-travelTime;
			if (restingTime > 0) {
				this.advanceTime(restingTime);
			}
		}
		else {
			this.setUnitPosition(PositionVector.sum(this.getUnitPosition(), PositionVector.multiplyBy(dt, this.getCurrentVelocityBasic())));
		}
	}
	
	/**
	 * Advances the time for this material by a given amount of time.
	 * @param	time	The given amount of time.
	 * @effect	It is checked if this material should fall, if so, it falls.
	 * 			If this material's activity status is 'fall', it moves a bit for the given time by it's normal speed.
	 * @throws	IllegalArgumentException
	 * 			The given time is negative or greater than or equal to 2.0.
	 */
	@Override
	public void advanceTime(double time) throws IllegalArgumentException {
		if((time < 0) || (time >= 2.0))
			throw new IllegalArgumentException();
		if(this.fallCheck()){
			this.fall();
		}
		if(this.getActivityStatus().equals("fall")){
			this.miniMove(time, 1);
		}
	}
	
	/**
	 * Make this material fall.
	 * @effect	This material falls as a game object. It's next position is set to the position at the bottom of the cube
	 * 			straight under it or the bottom of its current cube if it's not there yet.
	 * @throws IllegalStateException
	 * 			This material should not fall.
	 */
	@Override
	public void fall() throws IllegalStateException {
		if(! this.fallCheck())
			throw new IllegalStateException();
		super.fall();
		PositionVector materialPosition = this.getUnitPosition();
		double x = materialPosition.getXArgument();
		double y = materialPosition.getYArgument();
		double z = ((int) materialPosition.getZArgument()) - 1.0;
		// not at the bottom of its current cube
		if( this.getUnitPosition().getZArgument() > (int) this.getUnitPosition().getZArgument())
			z = z + 1.0;
		PositionVector fallToPosition = new PositionVector(x,y,z);
		this.setNextPosition(fallToPosition);
	}
	
	/**
	 * Check whether this material should fall.
	 * @return	True if and only if the material is not on the bottom of it's current cube or the cube underneath is not solid or
	 * 			the bottom of this material's world.
	 */
	@Override
	protected boolean fallCheck(){
		if(this.getCubePosition()[2] == 0){
			if(Util.fuzzyEquals(this.getUnitPosition().getZArgument(),(int) this.getUnitPosition().getZArgument()))
				return false;
			else
				return true;
		}
		// at the bottom of its current cube
		int[] cubePosition = this.getCubePosition();
		if((this.getWorld().isSolidPosition(new PositionVector(cubePosition[0],cubePosition[1],cubePosition[2]-1)))
				&& (Util.fuzzyEquals(this.getUnitPosition().getZArgument(),(int) this.getUnitPosition().getZArgument())))
			return false;
		return true;
	}
}

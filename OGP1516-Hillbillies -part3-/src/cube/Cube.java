package cube;
import objects.*;
import objects.Log;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.Util;
import position.PositionVector;

/**
 * A class of cubes, having a position and holding objects.
 * 
 * @invar  The position of each cube must be a valid position for any
 *         cube.
 *       | isValidPosition(getPosition())
 * @invar  The content of each cube must be a valid content for any
 *         cube.
 *       | isValidContent(getContent())
 *       
 * @author Michaël Dooreman
 * @version	0.2
 *
 */
public abstract class Cube {
	
	
	
	/**
	 * Initialize this new cube with given position.
	 *
	 * @param  position
	 *         The position for this new cube.
	 * @effect The position of this new cube is set to
	 *         the given position.
	 *       | this.setPosition(position)
	 * @effect The content of this new cube is set empty.
	 *       | this.setContent(new HashSet<GameObject>())
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this cube.
	 */
	public Cube(PositionVector position)
			throws IllegalArgumentException {
		this.setPosition(position);
		this.setContent(new HashSet<GameObject>());
	}
	
	/**
	 * Initialize this new cube with given position and content.
	 *
	 * @param  position
	 *         The position for this new cube.
	 * @param	content
	 * 			The content of this new cube.
	 * @effect The position of this new cube is set to
	 *         the given position.
	 *       | this.setPosition(position)
	 * @effect The content of this new cube is equal to the given content.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this cube.
	 */
	public Cube(PositionVector position, HashSet<GameObject> content)
			throws IllegalArgumentException {
		this.setPosition(position);
		this.setContent(content);
	}
	
	
	/**
	 * Return the position of this cube.
	 */
	@Basic @Raw
	public PositionVector getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any cube.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return 
	 *       | result == (position != null) && (Util.fuzzyEquals(position.getXArgument(),(int)position.getXArgument()))
	 *       |					&& (Util.fuzzyEquals(position.getYArgument(),(int)position.getYArgument()))
	 *       |						&& (Util.fuzzyEquals(position.getZArgument(),(int)position.getZArgument()))
	*/
	public static boolean isValidPosition(PositionVector position) {
		return ((position != null) && (Util.fuzzyEquals(position.getXArgument(),(int)position.getXArgument()))
				&& (Util.fuzzyEquals(position.getYArgument(),(int) position.getYArgument()))
					&& (Util.fuzzyEquals(position.getZArgument(),(int)position.getZArgument())));
	}
	
	/**
	 * Set the position of this cube to the given position.
	 * 
	 * @param  position
	 *         The new position for this cube.
	 * @post   The position of this new cube is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given position is not a valid position for any
	 *         cube.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	private void setPosition(PositionVector position) 
			throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable registering the position of this cube.
	*/
	private PositionVector position;
	
	/**
	 * Return the terrain type of this cube.
	 */
	@Basic @Immutable
	public abstract int getTerrainType();
	
	
	/**
	 * Return the content of this cube.
	 */
	@Basic @Raw
	public HashSet<GameObject> getContent() {
		return this.content;
	}
	
	/**
	 * Check whether the given content is a valid content for
	 * any cube.
	 *  
	 * @param  content
	 *         The content to check.
	 * @return 
	 *       | result == (content != null)
	*/
	public static boolean isValidContent(Set<GameObject> content) {
		return (content != null);
	}
	
	/**
	 * Set the content of this cube to the given content.
	 * 
	 * @param  content
	 *         The new content for this cube.
	 * @post   The content of this new cube is equal to
	 *         the given content.
	 *       | new.getContent() == content
	 * @throws NullPointerException
	 *         The given content is not a valid content for any
	 *         cube.
	 *       | ! isValidContent(getContent())
	 */
	@Raw
	private void setContent(HashSet<GameObject> content) 
			throws NullPointerException {
		if (! isValidContent(content))
			throw new NullPointerException();
		this.content = content;
	}
	
	/**
	 * Variable registering the content of this cube.
	 */
	private HashSet<GameObject> content;
	
	/**
	 * Add an object to the content of this cube.
	 * @param object	The given object.
	 * @effect	The given object is added to the content of this cube.
	 * @throws IllegalArgumentException
	 * 			The given object is not a valid content for this cube or is already in this cube's content.
	 */
	public void addAsContent(GameObject object) throws IllegalArgumentException{
		if ((! this.canHaveAsContent(object)) || (this.hasAsContent(object)))
			throw new IllegalArgumentException();
		this.content.add(object);
	}
	
	/**
	 * Remove an object from the content of this cube.
	 * @param object	The given object.
	 * @effect	The given object is deleted from the content of this cube.
	 * @throws	IllegalArgumentException
	 * 			This cube does not contain the given object.
	 * @throws	NullPointerException
	 * 			The given object is not effective.
	 */
	public void removeAsContent(GameObject object) throws IllegalArgumentException, NullPointerException {
		if (! this.hasAsContent(object))
			throw new IllegalArgumentException("This cube does not contain the given object!");
		this.content.remove(object);
	}
	
	/**
	 * Check whether the given object can belong to the content of this cube.
	 * @param object	The given object.
	 * @return	True if and only if the object is effective and is located in this cube..
	 */
	public boolean canHaveAsContent(GameObject object) {
		PositionVector objectPosition = new PositionVector(object.getCubePosition()[0],object.getCubePosition()[1]
				,object.getCubePosition()[2]);
		return (object != null) && (objectPosition.equals(this.getPosition()));
	}
	
	/**
	 * Check whether a given position is in the content of this cube.
	 * @param object	The given object.
	 * @return	True if and only if this cube's content contains the object.
	 * @throws	NullPointerException
	 * 			The object is not effective.
	 */
	public boolean hasAsContent(GameObject object) {
		if (object == null)
			throw new NullPointerException();
		return this.content.contains(object);
	}
	
	/**
	 * Check whether this cube can have the elements of its content as its content.
	 * @return	True if and only if this cube can have each element of it's content as content.
	 */
	public boolean hasProperContent() {
		HashSet<GameObject> content = this.getContent();
		for (GameObject object : content)
			if (! this.canHaveAsContent(object))
				return false;
		return true;
	}
	
	/**
	 * Variable registering the side length of any cube.
	 */
	private static final int sideLength = 1;
	
	/**
	 * Return the side length of any cube.
	 * @return	The side length of any cube.
	 */
	@Basic
	public static int getSideLength() {
		return sideLength;
	}
	
	/**
	 * Check whether this cube is solid or not.
	 * @return	True if and only if this cube i solid.
	 */
	@Immutable @Raw
	public abstract boolean isSolid();
	
	/**
	 * Check whether this cube has a log as content.
	 * @return	True if and only if the content of this cube contains a game object that is a log.
	 */
	public boolean containsLog(){
		HashSet<GameObject> content = this.getContent();
		for(GameObject object : content){
			if(object.getClass().equals(Log.class))
				return true;
		}
		return false;
	}
	
	/**
	 * Check whether this cube has a boulder as content.
	 * @return	True if and only if the content of this cube contains a game object that is a boulder.
	 */
	public boolean containsBoulder(){
		HashSet<GameObject> content = this.getContent();
		for(GameObject object : content){
			if(object.getClass().equals(Boulder.class))
				return true;
		}
		return false;
	}
	
	/**
	 * Return a boulder that's in this cube.
	 * @return	A boulder that is in this cube's content.
	 * @throws IllegalArgumentException
	 * 			This cube does not contain a boulder.
	 */
	public Boulder getABoulder() throws IllegalArgumentException {
		if(! this.containsBoulder())
			throw new IllegalArgumentException("This cube does not contain a boulder!");
		HashSet<GameObject> content = this.getContent();
		Boulder boulder = null;
		for(GameObject object : content){
			if(object.getClass().equals(Boulder.class))
				boulder = (Boulder) object;
		}
		return boulder;
	}
	
	/**
	 * Return a log that's in this cube.
	 * @return	A log that is in this cube's content.
	 * @throws IllegalArgumentException
	 * 			This cube does not contain a log.
	 */
	public Log getALog() throws IllegalArgumentException {
		if(! this.containsLog())
			throw new IllegalArgumentException("This cube does not contain a log!");
		HashSet<GameObject> content = this.getContent();
		Log log = null;
		for(GameObject object : content){
			if(object.getClass().equals(Log.class))
				log = (Log) object;
		}
		return log;
	}
	
	/**
	 * Return a set of all units in this cube.
	 * @return	A set with all the game objects of this cub's content that are units.
	 */
	public Set<Unit> getUnits() {
		Set<GameObject> content = this.getContent();
		Set<Unit> units = new HashSet<Unit>();
		for(GameObject object : content){
			if(object.getClass().equals(Unit.class)){
				units.add((Unit) object);
			}
		}
		return units;
	}
}

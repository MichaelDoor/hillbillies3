package faction;

import be.kuleuven.cs.som.annotate.*;
import java.util.*;
import objects.*;
import scheduler.Scheduler;

/**
 * A class of factions.
 * 
 * @invar  The unit set of each faction must be a valid unit set for any
 *         faction.
 *       | isValidUnitSet(getUnitSet())
 * @invar  The scheduler of each faction must be a valid scheduler for any
 *         faction.
 *       | isValidScheduler(getScheduler())
 * @author Michaël
 * @version 0.1
 *
 */
public class Faction {
	
	/**
	 * Initialize this new faction.
	 *
	 * @effect The unit set of this new faction is set to an empty hash set.
	 * @effect	The scheduler of this new faction is set to null.
	 */
	public Faction()
			throws NullPointerException {
		this.setUnitSet(new HashSet<Unit>());
		this.setScheduler(null);
	}
	
	/**
	 * Initialize this new faction with given scheduler.
	 *
	 * @param  scheduler
	 *         The scheduler for this new faction.
	 * @effect	A new faction is initialized.
	 * @effect The scheduler of this new faction is set to
	 *         the given scheduler.
	 *       | this.setScheduler(scheduler)
	 * 
	 */
	public Faction(Scheduler scheduler) {
		this();
		this.setScheduler(scheduler);
	}
	
	
	/**
	 * Return the unit set of this faction.
	 */
	@Basic @Raw
	public HashSet<Unit> getUnitSet() {
		return this.unitSet;
	}
	
	/**
	 * Check whether the given unit set is a valid unit set for
	 * any faction.
	 *  
	 * @param  unit set
	 *         The unit set to check.
	 * @return 
	 *       | result == (unitSet != null)
	*/
	private static boolean isValidUnitSet(HashSet<Unit> unitSet) {
		return (unitSet != null);
	}
	
	/**
	 * Set the unit set of this faction to the given unit set.
	 * 
	 * @param  unitSet
	 *         The new unit set for this faction.
	 * @post   The unit set of this new faction is equal to
	 *         the given unit set.
	 *       | new.getUnitSet() == unitSet
	 * @throws NullPointerException
	 *         The given unit set is not a valid unit set for any
	 *         faction.
	 *       | ! isValidUnitSet(getUnitSet())
	 */
	@Raw @Model
	private void setUnitSet(HashSet<Unit> unitSet) 
			throws NullPointerException {
		if (! isValidUnitSet(unitSet))
			throw new NullPointerException();
		this.unitSet = unitSet;
	}
	
	/**
	 * Variable registering the unit set of this faction.
	 */
	private HashSet<Unit> unitSet;
	
	
	/**
	 * Add a given unit to this faction.
	 * @param unit	The given unit.
	 * @effect	The given unit is added to this action's unit set.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * @throws IllegalStateException
	 * 			This faction is full.
	 */
	public void addUnit(Unit unit) throws NullPointerException, IllegalStateException {
		try{if(unit == null)
				throw new NullPointerException();
			if(this.getUnitSet().size() == maxNbOfUnits)
				throw new IllegalStateException();
			this.getUnitSet().add(unit);
		}
		catch(IllegalStateException exc){
			
		}
	}
	
	/**
	 * Remove a given unit from this faction.
	 * @param unit	The given unit.
	 * @effect	The given unit is removed from this faction's unit set.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * @throws IllegalArgumentException
	 * 			The given unit does not belong to this faction.
	 */
	public void removeUnit(Unit unit) throws NullPointerException, IllegalArgumentException {
		if(unit == null)
			throw new NullPointerException();
		if(! this.hasAsUnit(unit))
			throw new IllegalArgumentException("The given unit does not belong to this faction!");
		this.getUnitSet().remove(unit);
	}
	
	/**
	 * Check whether a given unit belongs to this faction.
	 * @param unit	The given unit.
	 * @return	True if and only if this faction's unit set contains the given unit.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 */
	public boolean hasAsUnit(Unit unit) throws NullPointerException {
		if(unit == null)
			throw new NullPointerException();
		return this.getUnitSet().contains(unit);
	}
	
	/**
	 * Variable registering the maximum amount of units that can belong to a faction.
	 */
	private static int maxNbOfUnits = 50;
	
	/**
	 * Return the maximum amount of units any faction can have.
	 * @return	The maximum amount of units any faction can have.
	 */
	public static int getMaxNbOfUnits() {
		return maxNbOfUnits;
	}	
	
	/**
	 * Return the scheduler of this faction.
	 */
	@Basic @Raw
	public Scheduler getScheduler() {
		return this.scheduler;
	}
	
	/**
	 * Set the scheduler of this faction to the given scheduler.
	 * 
	 * @param  scheduler
	 *         The new scheduler for this faction.
	 * @post   The scheduler of this new faction is equal to
	 *         the given scheduler.
	 *       | new.getScheduler() == scheduler
	 * @throws	IllegalArgumentException
	 * 			This faction can not have the given scheduler as it's scheduler.
	 * 			| (! this.canHaveAsScheduler(scheduler))
	 */
	@Raw
	public void setScheduler(Scheduler scheduler) throws IllegalArgumentException {
		if(! this.canHaveAsScheduler(scheduler)){
			throw new IllegalArgumentException();
		}
		this.scheduler = scheduler;
	}
	
	/**
	 * Check whether this faction can have the given scheduler as it's scheduler.
	 * @param scheduler	The given scheduler.
	 * @return	True if and only if the given scheduler is not effective or has this faction as its faction.
	 * 			| result == (scheduler == null) || (scheduler.getFaction().equals(this))
	 */
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return (scheduler == null) || (scheduler.getFaction().equals(this));
	}
	
	
	/**
	 * Variable registering the scheduler of this faction.
	 */
	private Scheduler scheduler;
	
	/**
	 * Check whether this faction has work for the given unit.
	 * @param unit	The given unit.
	 * @return	True if and only if this faction's scheduler has work for the given unit.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * @throws IllegalArgumentException
	 * 			The given unit is not from this faction.
	 */
	public boolean hasWork(Unit unit) throws NullPointerException, IllegalArgumentException {
		try {
			return this.getScheduler().hasWork(unit);
		}
		// No work available
		catch (IllegalStateException exc) {
			return false;
		}
		// In case this faction has no scheduler
		catch(NullPointerException exc) {
			if(unit == null)
				throw new NullPointerException();
			else
				return false;
			}
		}
}

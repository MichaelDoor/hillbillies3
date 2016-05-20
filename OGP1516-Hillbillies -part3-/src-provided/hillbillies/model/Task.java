package hillbillies.model;


import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Unit;
import hillbillies.model.PositionVector;
import hillbillies.model.Scheduler;
import hillbillies.model.statement.MyStatement;

/**
 * A class of tasks, made to be scheduled by a scheduler.
 * 
 * @invar  The name of each task must be a valid name for any
 *         task.
 *       | isValidName(getName())
 * @invar  The activity of each task must be a valid activity for any
 *         task.
 *       | isValidActivity(getActivity())
 * @invar  The executor of each task must be a valid executor for any
 *         task.
 *       | isValidExecutor(getExecutor())
 * @invar  The specific of each task must be a valid specific.
 *       | this.isValidSpecificUnit(getSpecificUnit())
 * @invar  The scheduler set of each task must be a valid scheduler set for any
 *         task.
 *       | isValidSchedulerSet(getSchedulerSet())
 * @invar	Each task must have a proper scheduler set.
 * 		 | hasPorperSchedulerSet()
 *       
 * @author Michaël
 * @version	1.0
 */
public class Task {
	
	/**
	 * Initialize this new task having a given name, priority and activity.
	 * @param  name
	 *         The name for this new task.
	 * @param  priority
	 *         The priority for this new task.
	 * @param  activity
	 *         The activity for this new task.
	 * @effect The name of this new task is set to
	 *         the given name.
	 *       | this.setName(name)
	 * @effect The priority of this new task is set to
	 *         the given priority.
	 *       | this.setPriority(priority)
	 * @effect The activity of this new task is set to
	 *         the given activity.
	 *       | this.setActivity(activity)
	 * @effect The executor of this new task is set to
	 *         the given executor.
	 *       | this.setExecutor(executor)
	 * @effect The specific of this new task is set to null.
	 *       | this.setSpecificUnit(null)
	 * @throws	NullPointerException
	 * 			The given name is not effective.
	 * 			| name == null
	 * @throws	NullPointerException
	 * 			The given activity is not effective.
	 * 			| activity == null
	 */
	public Task(String name, int priority, @Raw MyStatement activity) throws NullPointerException{
		this.setName(name);
		this.setPriority(priority);
		this.setActivity(activity);
		this.setExecutor(null);
		this.setSpecificUnit(null);
	}
	
	/**
	 * Return the name of this task.
	 */
	@Basic @Raw
	public String getName() {
		return this.name;
	}
	
	/**
	 * Check whether the given name is a valid name for
	 * any task.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return 
	 *       | result == (name != null)
	*/
	public static boolean isValidName(String name) {
		return (name != null);
	}
	
	/**
	 * Set the name of this task to the given name.
	 * 
	 * @param  name
	 *         The new name for this task.
	 * @post   The name of this new task is equal to
	 *         the given name.
	 *       | new.getName() == name
	 * @throws NullPointerException
	 *         The given name is not a valid name for any
	 *         task.
	 *       | ! isValidName(getName())
	 */
	@Raw
	public void setName(String name) 
			throws NullPointerException {
		if (! isValidName(name))
			throw new NullPointerException();
		this.name = name;
	}
	
	/**
	* Variable registering the name of this task.
	*/
	private String name;
	
	
	/**
	 * Return the priority of this task.
	 */
	@Basic @Raw
	public int getPriority() {
		return this.priority;
	}
	
	/**
	 * Set the priority of this task to the given priority.
	 * 
	 * @param  priority
	 *         The new priority for this task.
	 * @post   The priority of this new task is equal to
	 *         the given priority.
	 *       | new.getPriority() == priority
	 */
	@Raw
	private void setPriority(int priority)  {
		this.priority = priority;
	}
	
	/**
	 * Reduce the priority of this task, by a standard amount.
	 * @effect	This tasks priority is subtracted by 10.
	 * 			| this.setPriority(this.getPriority() - 10)
	 */
	@Raw
	public void reducePriority() {
		int old = this.getPriority();
		this.setPriority(old - 10);
	}
	
	/**
	 * Variable registering the priority of this task.
	 */
	private int priority;
	
	
	/**
	 * Return the activity of this task.
	 */
	@Basic @Raw
	public MyStatement getActivity() {
		return this.activity;
	}
	
	/**
	 * Check whether the given activity is a valid activity for
	 * any task.
	 *  
	 * @param  activity
	 *         The activity to check.
	 * @return True if and only if the given activity is effective
	 *       | result == (activity != null)
	*/
	public static boolean isValidActivity(MyStatement activity) {
		return (activity != null);
	}
	
	/**
	 * Set the activity of this task to the given activity.
	 * 
	 * @param  activity
	 *         The new activity for this task.
	 * @post   The activity of this new task is equal to
	 *         the given activity.
	 *       | new.getActivity() == activity
	 * @throws NullPointerException
	 *         The given activity is not a valid activity for any
	 *         task.
	 *       | ! isValidActivity(getActivity())
	 */
	@Raw
	public void setActivity(MyStatement activity) 
			throws NullPointerException {
		if (! isValidActivity(activity))
			throw new NullPointerException();
		this.activity = activity;
	}
	
	/**
	 * Variable registering the activity of this task.
	 */
	private MyStatement activity;
	
	/**
	 * Return the executor of this task.
	 */
	@Basic @Raw
	public Unit getExecutor() {
		return this.executor;
	}
	
	/**
	 * Check whether the given executor is a valid executor for
	 * any task.
	 *  
	 * @param  executor
	 *         The executor to check.
	 * @return True if and only if the given executor is not effective or is not terminated and is not resting, nor falling (has
	 * 			falling speed as velocity), nor has any defend attempts (defending) and has it's default behaviour enabled.
	 *       | result == ((executor == null) || ((! executor.isTerminated()) && (! executor.getActivityStatus().equals("rest")) 
	 *       | 				&& (executor.getDefendAttempts().isEmpty()) && (executor.getDefaultBehaviour())
	 *       |					&& (! executor.getCurrentVelocity().equals(new PositionVector(0,0,-3)))))
	 * @note	It's better to compare this unit's velocity with the falling velocity, because game object only adopt the 'fall'
	 * 			activity status for a very short moment.
	*/
	public static boolean isValidExecutor(Unit executor) {
		return ((executor == null) || ((! executor.isTerminated()) && (! executor.getActivityStatus().equals("rest")) 
				&& (executor.getDefendAttempts().isEmpty()) && (executor.getDefaultBehaviour())
						&& (! executor.getCurrentVelocity().equals(new PositionVector(0,0,-3)))));
	}
	
	/**
	 * Set the executor of this task to the given executor.
	 * 
	 * @param  executor
	 *         The new executor for this task.
	 * @post   The executor of this new task is equal to
	 *         the given executor.
	 *       | new.getExecutor() == executor
	 * @throws IllegalArgumentException
	 *         The given executor is not a valid executor for any
	 *         task.
	 *       | ! isValidExecutor(getExecutor())
	 * @throws	IllegalArgumentException
	 *			This task has a specific unit assignment and the given executor does not match it.
	 *			| (this.getSpecificUnit() != null) && (! executor.equals(this.getSpecificUnit()))
	 */
	@Raw
	public void setExecutor(Unit executor) 
			throws IllegalArgumentException {
		if (! isValidExecutor(executor))
			throw new IllegalArgumentException();
		if((this.getSpecificUnit() != null) && (! executor.equals(this.getSpecificUnit())))
			throw new IllegalArgumentException();
		this.executor = executor;
	}
	
	/**
	 * Check whether this task has a proper executor.
	 * @return	True if and only if this class' executor is a valid executor.
	 */
	public boolean hasProperExecutor() {
		return (isValidExecutor(this.getExecutor()));
	}
	
	/**
	 * Variable registering the executor of this task.
	 */
	private Unit executor;
	
	/**
	 * Run this task.
	 * @effect	If something happened and this taks's executor is not a proper executor anymore for this task, this task is interrupted.
	 * 			| this.interrupt()
	 * @effect	If this tasks executor has executed this task's activity, this task is terminated.
	 * 			| this.terminate()
	 * @effect	This task's activity is executed if this task's executor is idle.
	 * 			| this.getActivity().run(this.getExecutor())
	 * @effect	This task is interrupted if a NullPointerException or IllegalArgumentException was thrown when executing this task.
	 * 			| this.interrupt()
	 * @throws IllegalStateException
	 * 			This task does not have an executor.
	 * 			| this.getExecutor() == null
	 */
	public void execute() throws IllegalStateException {
		if(this.getExecutor() == null)
			throw new IllegalStateException();
		try {
			if(! this.hasProperExecutor()){
				this.interrupt();
			}
			else if(this.getActivity().isExecuted(this.getExecutor())){
				this.terminate();
				return;
			}
			else if(this.getExecutor().isIdle())
				this.getActivity().run(this.getExecutor());
		}
		// something is inaccessible or does not exist
		catch (NullPointerException exc) {
			this.interrupt();
		}
		catch (IllegalArgumentException exc) {
			this.interrupt();
		}
	}
	
	/**
	 * Interrupt this task.
	 * @effect	This task's executor is removed from it's faction's scheduler's worker set, this task's executor's task is set to null
	 * 			this faction's executor is set to null, it's activity is rolled back and it's priority is reduced.
	 * 			| this.getExecutor().getFaction().getScheduler().removeWorker(this.getExecutor())
	 * 			| this.getExecutor().setTask(null);
	 * 			| this.setExecutor(null)
	 * 			| this.getActivity().rollback()
	 * 			| this.reducePriority()
	 */
	public void interrupt() {
		this.getExecutor().getFaction().getScheduler().removeWorker(this.getExecutor());
		this.getExecutor().setTask(null);
		this.setExecutor(null);
		this.getActivity().rollback();
		this.reducePriority();
	}
	
	/**
	 * Terminate this task.
	 * @effect	This task's executor's task is set to null, this tasks executor is set to null, 
	 * 			it's specific unit also and it's schedulerSet is emptied and it's activity is set to null.
	 * 			| this.emptySchedulerSet()
	 * 			| this.getExecutor().setTask(null)
	 * 			| this.setExecutor(null)
	 * 			| this.setSpecificUnit(null)
	 * 			| this.activity = null
	 */
	private void terminate() {
		this.emptySchedulerSet();
		this.getExecutor().setTask(null);
		this.setExecutor(null);
		this.setSpecificUnit(null);
		this.activity = null;
	}
	
	/**
	 * Check whether this task is terminated.
	 * @return	True if and only if this task's executor is not effective, neither it's specific unit, neither it's activity
	 * 			 and it's scheduler set is empty.
	 * 			| result == (this.getExecutor() == null) && (this.getSpecificUnit() == null) && (this.getSchedulerSet().isEmpty())
	 * 			| 				&& (this.getActivity() == null)
	 */
	@Raw
	public boolean isTerminated() {
		return (this.getExecutor() == null) && (this.getSpecificUnit() == null) && (this.getSchedulerSet().isEmpty())
				&& (this.getActivity() == null);
	}
	
	/**
	 * Return the specific of this task.
	 */
	@Basic @Raw
	public Unit getSpecificUnit() {
		return this.specificUnit;
	}
	
	/**
	 * Check whether the given specific is a valid specific for
	 * this task.
	 *  
	 * @param  specific
	 *         The specific to check.
	 * @return True if and only if either the given specific unit is not effective or this task's executor is not effective or
	 * 			this task's executor equals the given specific unit.
	 *       | result == (specificUnit == null) ||  (this.getExecutor() == null) || (this.getExecutor().equals(specificUnit))
	*/
	public boolean isValidSpecificUnit(Unit specificUnit) {
		return (specificUnit == null) || (this.getExecutor() == null) || (this.getExecutor().equals(specificUnit));
	}
	
	/**
	 * Set the specific of this task to the given specific.
	 * 
	 * @param  specificUnit
	 *         The new specific for this task.
	 * @post   The specific of this new task is equal to
	 *         the given specific.
	 *       | new.getSpecificUnit() == specificUnit
	 * @throws IllegalArgumentException
	 *         The given specific is not a valid specific for any
	 *         task.
	 *       | ! isValidSpecificUnit(getSpecificUnit())
	 */
	@Raw
	public void setSpecificUnit(Unit specificUnit) 
			throws IllegalArgumentException {
		if (! isValidSpecificUnit(specificUnit))
			throw new IllegalArgumentException();
		this.specificUnit = specificUnit;
	}
	
	/**
	 * Variable registering the specific of this task.
	 */
	private Unit specificUnit;
	
	/**
	 * Return the scheduler set of this task.
	 */
	@Basic @Raw
	public Set<Scheduler> getSchedulerSet() {
		return this.schedulerSet;
	}
	
	/**
	 * Check whether the given scheduler set is a valid scheduler set for
	 * any task.
	 *  
	 * @param  scheduler set
	 *         The scheduler set to check.
	 * @return 
	 *       | result == (schedulerSet != null)
	*/
	public static boolean isValidSchedulerSet(Set<Scheduler> schedulerSet) {
		return (schedulerSet != null);
	}
	
	/**
	 * Adds a given scheduler to this unit's scheduler set.
	 * @param scheduler	The given scheduler.
	 * @effect	The given scheduler is added to this task's scheduler set.
	 * 			| this.getSchedulerSet().add(scheduler)
	 * @throws IllegalArgumentException
	 * 			This task can't have the given scheduler as it's scheduler.
	 * 			| (! this.canHaveAsScheduler(scheduler))
	 */
	public void addScheduler(@Raw Scheduler scheduler) throws IllegalArgumentException {
		if(! this.canHaveAsScheduler(scheduler))
			throw new IllegalArgumentException();
		this.getSchedulerSet().add(scheduler);
	}
	
	/**
	 * Check whether this task can have the given scheduler as one of it's schedulers.
	 * @param scheduler	The given scheduler.
	 * @return	True if and only if the given scheduler is effective and this task doesn't already have it as one of its schedulers.
	 * 			| result == (scheduler != null) && (! this.getSchedulerSet().contains(scheduler))
	 */
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return (scheduler != null) && (! this.getSchedulerSet().contains(scheduler));
	}
	
	/**
	 * Remove the given scheduler from this task's scheduler set.
	 * @param scheduler	The given scheduler.
	 * @effect	The given scheduler is removed from this task's scheduler set.
	 * 			| this.getSchedulerSet().remove(scheduler)
	 * @throws NullPointerException
	 * 			The given scheduler is not effective.
	 * 			| scheduler == null
	 * @throws IllegalArgumentException
	 * 			This task does not have the given scheduler as one of it's schedulers.
	 * 			| (! this.hasAsScheduler(scheduler)
	 */
	public void removeScheduler(Scheduler scheduler) throws NullPointerException, IllegalArgumentException{
		if(scheduler == null)
			throw new NullPointerException();
		if(! this.hasAsScheduler(scheduler))
			throw new IllegalArgumentException();
		this.getSchedulerSet().remove(scheduler);
	}
	
	/**
	 * Check whether this task has a given scheduler as one of it's schedulers.
	 * @param scheduler	The given scheduler.
	 * @return	True if and only if this task's scheduler set contains the given scheduler.
	 * 			result == this.getSchedulerSet().contains(scheduler)
	 * @throws NullPointerException
	 * 			The given scheduler is not effective.
	 * 			| scheduler == null
	 */
	public boolean hasAsScheduler(Scheduler scheduler) throws NullPointerException {
		if(scheduler == null)
			throw new NullPointerException();
		return this.getSchedulerSet().contains(scheduler);
	}
	
	/**
	 * Check whether this task has a proper scheduler set.
	 * @return	True if and only if this task can have all the schedulers in its scheduler set as its scheduler.
	 * 			| for(Scheduler scheduler : this.getSchedulerSet())
	 * 			| 		if(! this.canHaveAsScheduler(scheduler)
	 * 			|			result == false
	 */
	public boolean hasProperSchedulerSet() {
		for(Scheduler scheduler : this.getSchedulerSet()){
			if(! this.canHaveAsScheduler(scheduler))
				return false;
		}
		return true;
	}
	
	/**
	 * Empty this task's scheduler set and also removes this task's executor from the workers map of the scheduler corresponding
	 * to the executor's faction.
	 * @effect	If this tasks executor is effective, it is remove from the workers map of the scheduler corresponding to its faction. 
	 * 			| scheduler.removeWorker(executor)
	 * @effect	Each scheduler in this task's scheduler set removes this task.
	 * 			| scheduler.remove(this)
	 */
	private void emptySchedulerSet() {
		Unit executor = this.getExecutor();
		Set<Scheduler> copy = new HashSet<Scheduler>();
		for(Scheduler scheduler : this.getSchedulerSet())
			copy.add(scheduler);
		for(Scheduler scheduler : copy){
			if((executor != null) && (executor.getFaction().equals(scheduler.getFaction())))
				scheduler.removeWorker(executor);
			scheduler.removeTask(this);
		}
	}
	
	/**
	 * Variable registering the scheduler set of this task.
	 */
	private final Set<Scheduler> schedulerSet = new HashSet<Scheduler>();
}

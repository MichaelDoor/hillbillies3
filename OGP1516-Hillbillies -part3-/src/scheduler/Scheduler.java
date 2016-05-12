package scheduler;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import faction.Faction;
import task.Task;

/**
 * A class of schedulers.
 * @invar  The faction of each scheduler must be a valid faction for any
 *         scheduler.
 *       | isValidFaction(getFaction())
 * @invar   Each scheduler must have proper tasks.
 *       | hasProperTasks()
 * 
 * @author Michaël
 * @version	0.00
 *
 */
public class Scheduler {
	
	/**
	 * Initialize this new scheduler as a non-terminated scheduler with 
	 * no tasks yet having a given faction.
	 * @param  faction
	 *         The faction for this new scheduler.
	 * @effect The faction of this new scheduler is set to
	 *         the given faction.
	 *       | this.setFaction(faction)
	 * @post   This new scheduler has no tasks yet.
	 *       | new.getNbTasks() == 0
	 * @throws NullPointerException
	 * 			The given faction is not effective.
	 * 			| faction == null
	 */
	public Scheduler(Faction faction) throws NullPointerException {
		this.setFaction(faction);
	}
	
	/**
	 * Return the faction of this scheduler.
	 */
	@Basic @Raw
	public Faction getFaction() {
		return this.faction;
	}
	
	/**
	 * Check whether the given faction is a valid faction for
	 * any scheduler.
	 *  
	 * @param  faction
	 *         The faction to check.
	 * @return True if and only if the given faction is effective.
	 *       | result == (faction != null)
	*/
	public static boolean isValidFaction(Faction faction) {
		return (faction != null);
	}
	
	/**
	 * Set the faction of this scheduler to the given faction.
	 * 
	 * @param  faction
	 *         The new faction for this scheduler.
	 * @post   The faction of this new scheduler is equal to
	 *         the given faction.
	 *       | new.getFaction() == faction
	 * @throws NullPointerException
	 *         The given faction is not a valid faction for any
	 *         scheduler.
	 *       | ! isValidFaction(getFaction())
	 */
	@Raw
	public void setFaction(Faction faction) 
			throws NullPointerException {
		if (! isValidFaction(faction))
			throw new NullPointerException();
		this.faction = faction;
	}
	
	/**
	 * Variable registering the faction of this scheduler.
	 */
	private Faction faction;

	/**
	 * Return the task associated with this scheduler at the
	 * given index.
	 * 
	 * @param  index
	 *         The index of the task to return.
	 * @throws IndexOutOfBoundsException
	 *         The given index is not positive or it exceeds the
	 *         number of tasks for this scheduler.
	 *       | (index < 1) || (index > getNbTasks())
	 */
	@Basic
	@Raw
	public Task getTaskAt(int index) throws IndexOutOfBoundsException {
		return tasks.get(index - 1);
	}

	/**
	 * Return the number of tasks associated with this scheduler.
	 */
	@Basic
	@Raw
	public int getNbTasks() {
		return tasks.size();
	}

	/**
	 * Check whether this scheduler can have the given task
	 * as one of its tasks.
	 * 
	 * @param  task
	 *         The task to check.
	 * @return True if and only if the given task is effective
	 *         and that task can have this scheduler as its scheduler.
	 *       | result ==
	 *       |   (task != null) &&
	 *       |   task.canHaveAsScheduler(this)
	 */
	@Raw
	public boolean canHaveAsTask(Task task) {
		return (task != null) && (task.canHaveAsScheduler(this));
	}

	/**
	 * Check whether this scheduler can have the given task
	 * as one of its tasks at the given index.
	 * 
	 * @param  task
	 *         The task to check.
	 * @return False if the given index is not positive or exceeds the
	 *         number of tasks for this scheduler + 1.
	 *       | if ( (index < 1) || (index > getNbTasks()+1) )
	 *       |   then result == false
	 *         Otherwise, false if this scheduler cannot have the given
	 *         task as one of its tasks.
	 *       | else if ( ! this.canHaveAsTask(task) )
	 *       |   then result == false
	 *         Otherwise, true if and only if the given task is
	 *         not registered at another index than the given index.
	 *       | else result ==
	 *       |   for each I in 1..getNbTasks():
	 *       |     (index == I) || (getTaskAt(I) != task)
	 */
	@Raw
	public boolean canHaveAsTaskAt(Task task, int index) {
		if ((index < 1) || (index > getNbTasks() + 1))
			return false;
		if (!this.canHaveAsTask(task))
			return false;
		for (int i = 1; i < getNbTasks(); i++)
			if ((i != index) && (getTaskAt(i) == task))
				return false;
		return true;
	}

	/**
	 * Check whether this scheduler has proper tasks attached to it.
	 * 
	 * @return True if and only if this scheduler can have each of the
	 *         tasks attached to it as a task at the given index,
	 *         and if each of these tasks references this scheduler as
	 *         the scheduler to which they are attached.
	 *       | result ==
	 *       |   for each I in 1..getNbTasks():
	 *       |     ( this.canHaveAsTaskAt(getTaskAt(I) &&
	 *       |       (getTaskAt(i).hasAsScheduler(this)) )
	 */
	public boolean hasProperTasks() {
		for (int i = 1; i <= getNbTasks(); i++) {
			if (!canHaveAsTaskAt(getTaskAt(i), i))
				return false;
			if (! getTaskAt(i).hasAsScheduler(this))
				return false;
		}
		return true;
	}

	/**
	 * Check whether this scheduler has the given task as one of its
	 * tasks.
	 * 
	 * @param  task
	 *         The task to check.
	 * @return The given task is registered at some position as
	 *         a task of this scheduler.
	 *       | for some I in 1..getNbTasks():
	 *       |   getTaskAt(I) == task
	 */
	public boolean hasAsTask(@Raw Task task) {
		return tasks.contains(task);
	}

	/**
	 * Add the given task to the list of tasks of this scheduler.
	 * 
	 * @param  task
	 *         The task to be added.
	 * @pre    The given task is effective and does not already reference
	 *         this scheduler, and this scheduler does not yet have the given
	 *         task as one of its tasks.
	 *       | (task != null) && (task.hasAsScheduler(this)) &&
	 *       | (! this.hasAsTask(task))
	 * @post   The number of tasks of this scheduler is
	 *         incremented by 1.
	 *       | new.getNbTasks() == getNbTasks() + 1
	 * @post   This scheduler has the given task as its very last task.
	 *       | new.getTaskAt(getNbTasks()+1) == task
	 * @effect	The given task adds this scheduler to its scheduler set.
	 * 			| task.addScheduler(this)
	 */
	public void addTask(@Raw Task task) {
		assert (task != null) && (! task.hasAsScheduler(this))
				&& (!this.hasAsTask(task));
		tasks.add(task);
		task.addScheduler(this);
	}

	/**
	 * Remove the given task from the list of tasks of this scheduler.
	 * 
	 * @param  task
	 *         The task to be removed.
	 * @pre    The given task is effective, this scheduler has the
	 *         given task as one of its tasks, and the given
	 *         task references to this scheduler.
	 *       | (task != null) &&
	 *       | this.hasAsTask(task) &&
	 *       | (task.hasAsScheduler(this))
	 * @post   The number of tasks of this scheduler is
	 *         decremented by 1.
	 *       | new.getNbTasks() == getNbTasks() - 1
	 * @post   This scheduler no longer has the given task as
	 *         one of its tasks.
	 *       | ! new.hasAsTask(task)
	 * @post   All tasks registered at an index beyond the index at
	 *         which the given task was registered, are shifted
	 *         one position to the left.
	 *       | for each I,J in 1..getNbTasks():
	 *       |   if ( (getTaskAt(I) == task) and (I < J) )
	 *       |     then new.getTaskAt(J-1) == getTaskAt(J)
	 * @post	The given task removes this scheduler from it's scheduler set.
	 * 			| task.removeScheduler(this)
	 */
	@Raw
	public void removeTask(Task task) {
		assert (task != null) && this.hasAsTask(task)
				&& (task.hasAsScheduler(this));
		tasks.remove(task);
		task.removeScheduler(this);
	}

	/**
	 * Variable referencing a list collecting all the tasks
	 * of this scheduler.
	 * 
	 * @invar  The referenced list is effective.
	 *       | tasks != null
	 * @invar  Each task registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each task in tasks:
	 *       |   ( (task != null) &&
	 *       |     (! task.isTerminated()) )
	 * @invar  No task is registered at several positions
	 *         in the referenced list.
	 *       | for each I,J in 0..tasks.size()-1:
	 *       |   ( (I == J) ||
	 *       |     (tasks.get(I) != tasks.get(J))
	 */
	private final List<Task> tasks = new ArrayList<Task>();

}

package scheduler;

import java.util.*;
import java.util.function.Function;
import java.util.Comparator;

import be.kuleuven.cs.som.annotate.*;
import faction.Faction;
import objects.Unit;
import task.Task;

/**
 * A class of schedulers.
 * @invar  The faction of each scheduler must be a valid faction for any
 *         scheduler.
 *       | isValidFaction(getFaction())
 * @invar   Each scheduler must have proper tasks.
 *       | hasProperTasks()
 * @invar	Each scheduler must have proper workers.
 * 		 | hasProperWorkers()
 * 
 * @author Michaël
 * @version	1.00
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
	 * @effect	The given faction's scheduler is set to this scheduler.
	 * 			| faction.setScheduler(this)
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
		faction.setScheduler(this);
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
	
	/**
	 * Add a set of tasks to this scheduler's task list.
	 * @param taskSet
	 * 			The given set of tasks.
	 * @effect	Each task of the given set of tasks is added to this scheduler's task list.
	 * 			| for(Task task : taskSet)
	 * 			| 	this.addTask(task)
	 * @throws NullPointerException
	 * 			The given set of tasks is not effective.
	 * 			| taskSet == null
	 */
	public void addTasks(Set<Task> taskSet) throws NullPointerException {
		for(Task task : taskSet)
			this.addTask(task);
	}
	
	/**
	 * Remove a set of tasks from this scheduler's task set.
	 * @param taskSet	The given set of tasks.
	 * @effect	Each task of the given set of tasks is removed from this scheduler's task list.
	 * 			| for(Task task : taskSet)
	 * 			| 	this.removeTask(task)
	 * @throws NullPointerException
	 * 			The given set of tasks is not effective.
	 * 			| taskSet == null
	 */
	public void removeTasks(Set<Task> taskSet) throws NullPointerException {
		for(Task task : taskSet)
			this.removeTask(task);
	}
	
	/**
	 * Replace the given task by the other given task in this scheduler.
	 * @param oldTask	The given task that needs to be replaced.
	 * @param newTask	The given task that is the replacement.
	 * @effect	The new task takes the place of the old task in this scheduler's task list.
	 * 			| tasks.set(tasks.indexOf(oldTask), newTask);
	 * @throws IllegalArgumentException
	 * 			This scheduler does not have the given old task as one of it's tasks.
	 * 			| (! this.hasAsTask(oldTask))
	 * @throws IllegalArgumentException
	 * 			This scheduler can not have the given new task as one of it's tasks.
	 * 			| (! this.canHaveAsTask(newTask))
	 * @throws NullPointerException
	 * 			At least one of the given tasks is not effective.
	 * 			| ((oldTask == null) || (newTask == null))
	 */
	public void replaceTask(Task oldTask, Task newTask) throws IllegalArgumentException, NullPointerException {
		if((oldTask == null) || (newTask == null))
			throw new NullPointerException();
		if(! this.hasAsTask(oldTask))
			throw new IllegalArgumentException();
		if(! this.canHaveAsTask(newTask))
			throw new IllegalArgumentException();
		
		tasks.set(tasks.indexOf(oldTask), newTask);
	}
	
	/**
	 * Check whether this scheduler has all the tasks of a given collection as it's task.
	 * @param taskCollection	The given collection of tasks.
	 * @return	True if and only if this scheduler has each element of the given collection of tasks as one of it's tasks.
	 * 			| for each(Task task : taskCollection) {this.hasAsTask(task)}
	 * 			| result == true
	 * @throws NullPointerException
	 */
	public boolean hasAsTasks(Collection<Task> taskCollection) throws NullPointerException{
		if(taskCollection == null)
			throw new NullPointerException();
		for(Task task : taskCollection){
			if(! this.hasAsTask(task))
				return false;
		}
		return true;
	}
	
	/**
	 * Return this scheduler's highest priority task that is not being executed.
	 * @return	The element which's priority is greater than or equal to that of all the other elements in this scheduler's tasks
	 * 			and does not have an executor.
	 * 			| Iterator<Task> i = this.getTaskIterator()
	 * 			| Task best = null
	 * 			| while((i.hasNext()))
	 * 			| 	Task temp = i.next()
	 * 			| 	if(((best == null) || (temp.getPriority() > best.getPriority())) && (temp.getExecutor() == null))
	 * 			| 	best = temp
	 * 			| return best
	 */
	public Task getHighestPriorityTask() {
		Iterator<Task> i = this.getTaskIterator();
		Task best = null;
		while((i.hasNext())){
			Task temp = i.next();
			if(((best == null) || (temp.getPriority() > best.getPriority())) && (temp.getExecutor() == null))
				best = temp;
		}
		return best;
	}
	
	/**
	 * Return an iterator for this scheduler's tasks.
	 * @return An iterator for this scheduler's tasks.
	 * 			| result == tasks.iterator()
	 */
	public Iterator<Task> getTaskIterator() {
		return tasks.iterator();
	}
	
	/**
	 * Return a list of all the tasks, currently being managed by this scheduler.
	 */
	@Basic @Raw
	public List<Task> getTasks() {
		return this.tasks;
	}
	
	/**
	 * Return a set of all tasks that are managed by this scheduler, that satisfy a given condition.
	 * @param condition	The given condition, being a function with a task as input and a boolean constant as output, true if the 
	 * 					task satisfies the condition. It is recommended that this function does not alter the objects that it gets
	 * 					as input.
	 * @return	All tasks from this scheduler's tasks, that evaluate true, when the given condition is applied to them.
	 * 			| Set<Task> temp = new HashSet<>()
	 * 			| for(Task task : tasks)
	 * 			| 	 if(condition.apply(task)
	 * 			| 		temp.add(task)
	 * 			| result == temp
	 */
	public Set<Task> getTasksThat(Function<Task,Boolean> condition) {
		Set<Task> temp = new HashSet<>();
		temp.addAll(tasks);
		Set<Task> result = new HashSet<>();
		Iterator<Task> i = temp.iterator();
		while(i.hasNext()) {
			Task task = i.next();
			boolean flag = condition.apply(task);
			if(flag)
				result.add(task);
		}
		return result;
	}
	
	/**
	 * Return an iterator for this scheduler's tasks in descending priority.
	 * @return	An iterator for the version of tasks that is sorted by descending priority.
	 * 			| result == prioritySort(new ArrayList<Task>()).addAll(tasks).iterator()
	 */
	public Iterator<Task> getPriorityIterator() {
		List<Task> temp = new ArrayList<>();
		temp.addAll(tasks);
		prioritySort(temp);
		return temp.iterator();
	}
	
	/**
	 * Sort the given task list for descending priority.
	 * @param taskList	The given task list.
	 * @effect	The given task list is sorted for descending priority.
	 * 			| Comparator<Task> priorityComparator = (t,u) -> t.getPriority()-u.getPriority()
	 * 			| taskList.sort(priorityComparator)
	 * @throws NullPointerException
	 */
	public static void prioritySort(List<Task> taskList) throws NullPointerException {
		if(taskList == null)
			throw new NullPointerException();
		// yes it's u-t, because t-u gives the ascending order
		Comparator<Task> priorityComparator = (t,u) -> u.getPriority()-t.getPriority();
		taskList.sort(priorityComparator);
	}
	
	/**
	 * Mark a task as scheduled for execution by a specific unit that is given.
	 * @param unit	The given unit.
	 * @param task	The given task.
	 * @effect	The given unit is set as the specific unit of the given task.
	 * 			| task.setSpecificUnit(unit)
	 * @throws NullPointerException
	 * 			The given unit or the given task is not effective.
	 * 			| (unit == null) || (task == null)
	 * @throws IllegalArgumentException
	 * 			The given unit does not belong to this scheduler's faction or this scheduler does not have the given task
	 * 			as one of its tasks.
	 * 			| (! this.getFaction().hasAsUnit(unit)) || (! this.hasAsTask(task))
	 */
	public void assignSpecificUnit(@Raw Unit unit, @Raw Task task) throws NullPointerException, IllegalArgumentException {
		if((unit == null) || (task == null))
			throw new NullPointerException();
		if((! this.getFaction().hasAsUnit(unit)) || (! this.hasAsTask(task)))
			throw new IllegalArgumentException();
		task.setSpecificUnit(unit);
	}
	
	/**
	 * Reset the marking of a given task to be executed by a specific unit.
	 * @param task	The given task.
	 * @effect	The given task's specific unit is set to null.
	 * 			| task.setSpecificUnit(null)
	 * @throws NullPointerException
	 * 			The given task is not effective.
	 * 			| task == null
	 * @throws IllegalArgumentException
	 * 			This scheduler does not have the given task as one of its tasks.
	 * 			| ! this.hasAsTask(task)
	 */
	@Raw
	public void resetSpecificUnit(@Raw Task task) throws NullPointerException, IllegalArgumentException {
		if(task == null)
			throw new NullPointerException();
		if(! this.hasAsTask(task))
			throw new IllegalArgumentException();
		task.setSpecificUnit(null);
	}
	
	/**
	 * Check whether this scheduler has some task for the given unit.
	 * @param unit	The given unit.
	 * @return	True if and only if either this unit is already working on a task of this scheduler and its scheduler delay is zero 
	 * 			or it's not a worker of this scheduler and this scheduler still has an available task that can be executed.
	 * 			| result == ((this.hasAsWorker(unit)) && (unit.getSchedulerDelay() == 0)) 
	 * 			| 				|| ((! this.hasAsWorker(unit)) && (this.getHighestPriorityTask() != null))
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * 			| unit == null
	 * @throws IllegalArgumentException
	 * 			The given unit is not of this scheduler's faction.
	 * 			| (! unit.getFaction().equals(this.getFaction()))
	 */
	public boolean hasWork(Unit unit) throws NullPointerException, IllegalArgumentException {
		if(! unit.getFaction().equals(this.getFaction()))
			throw new IllegalArgumentException();
		return ((this.hasAsWorker(unit)) && (unit.getSchedulerDelay() == 0)) 
				|| ((! this.hasAsWorker(unit)) && (this.getHighestPriorityTask() != null));
	}
	
	/**
	 * Check whether this scheduler could have the given unit as one of it's workers.
	 * @param unit	The given unit.
	 * @return	True if and only if the given unit is not yet in this schedulers map of workers.
	 * 			| result == (! this.workers.containsKey(unit))
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * 			| unit == null
	 */
	public boolean canHaveAsWorker(Unit unit) throws NullPointerException{
		if(unit == null)
			throw new NullPointerException();
		if(this.workers.containsKey(unit))
			return false;
		return true;
	}
	
	/**
	 * Check whether this scheduler has a proper set of workers.
	 * @return	True if and only if each worker of this scheduler's workers map is the executor of the task it's mapped to.
	 * 			| for(Unit worker : this.workers.keySet()){
	 * 			| 	this.workers.get(worker).getExecutor().equals(worker)}
	 */
	public boolean hasProperWorkers() {
		for(Unit worker : this.workers.keySet()){
			if(! this.workers.get(worker).getExecutor().equals(worker))
				return false;
		}
		return true;
	}
	
	/**
	 * Add a given unit to this scheduler's map of workers.
	 * @param unit	The given unit.
	 * @param task	The given task.
	 * @effect	The given unit and task are put in this scheduler's map of workers.
	 * 			| this.workers.put(unit,task)
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * 			| unit == null
	 * @throws IllegalArgumentException
	 * 			This scheduler can not have the given unit as one of its workers.
	 * 			| (! this.canHaveAsWorker(unit))
	 * @throws	IllegalArgumentException
	 * 			This scheduler does not have the given task as one of its tasks.
	 * 			| (! this.hasAsTask(task))
	 */
	private void addWorker(Unit unit, Task task) throws NullPointerException, IllegalArgumentException{
		if(! this.canHaveAsWorker(unit))
			new IllegalArgumentException();
		if(! this.hasAsTask(task))
			throw new IllegalArgumentException();
		this.workers.put(unit,task);
	}
	
	/**
	 * Remove the given unit from this scheduler's map of workers.
	 * @param unit	The given unit.
	 * @effect	The given unit is removed from this scheduler's map of workers.
	 * 			| this.workers.remove(unit)
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * 			| unit == null
	 * @throws IllegalArgumentException
	 * 			This scheduler does not have the given unit as one of its workers.
	 * 			| (! this.hasAsWorker(unit))
	 */
	public void removeWorker(Unit unit) throws NullPointerException, IllegalArgumentException {
		if(! this.hasAsWorker(unit))
			throw new IllegalArgumentException();
		this.workers.remove(unit);
	}
	
	/**
	 * Check whether a given unit is working on a task of this scheduler.
	 * @param unit	The given unit.
	 * @return	True if and only if this scheduler's map of workers contains the given worker.
	 * 			| workers.containsKey(unit)
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * 			| unit == null
	 */
	public boolean hasAsWorker(@Raw Unit unit) throws NullPointerException {
		if(unit == null)
			throw new NullPointerException();
		return workers.containsKey(unit);
	}
	
	/**
	 * Return the task a given worker is working on.
	 * @param unit	The given worker.
	 * @return	The value of the worker as key in this schedulers workers map.
	 * 			| result == workers.get(worker)
	 * @throws NullPointerException
	 * 			The given worker is not effective.
	 * 			| worker == null
	 * @throws IllegalArgumentException
	 * 			This scheduler does not have the given worker as one of its workers.
	 */
	public Task getTaskOf(Unit worker) throws NullPointerException, IllegalArgumentException {
		if(! this.hasAsWorker(worker))
			throw new IllegalArgumentException();
		return workers.get(worker);
	}
	
	/**
	 * Variable registering the units working on tasks of this scheduler, together with the task they're working on.
	 * @invar	The referenced map is effective.
	 * 			| workers != null
	 * @invar	Each unit in this map has it's mapped task as its task.
	 * @invar	Each task in this map has its key as its executor.
	 */
	private final Map<Unit,Task> workers = new HashMap<Unit,Task>();
	
	/**
	 * Let the given unit execute a task.
	 * @param unit	The given unit.
	 * @effect	If the given unit is already a worker of this scheduler, the task it's mapped to is executed.
	 * 			| this.workers.get(unit).execute()
	 * @effect	If the given unit is not yet a worker of this scheduler, it is set set as the executor of this scheduler's highest 
	 * 			priority task, its task is set to that task it is also mapped to it as a worker of this scheduler and the task is executed.
	 * 			| this.getHighestPriorityTask().setExecutor(unit)
	 * 			| this.addWorker(unit, task)
	 * 			| task.execute()
	 * @effect	The given unit's scheduler delay is reset if the given unit is assigned a task.
	 * 			| unit.resetSchedulerDelay()
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * 			| unit == null
	 * @throws IllegalArgumentException
	 * 			The given unit is not of this scheduler's faction. 
	 * 			| (! unit.getFaction().equals(this.getFaction()))
	 * @throws IllegalStateException
	 * 			This scheduler does not have any available work.
	 * 			| (! this.hasWork(unit))
	 */
	public void giveWork(@Raw Unit unit) throws NullPointerException, IllegalArgumentException, IllegalStateException {
		if(this.hasAsWorker(unit)){
			this.workers.get(unit).execute();
		}
		else {
			if(! this.hasWork(unit))
				throw new IllegalStateException();
			Task task = this.getHighestPriorityTask();
			unit.setTask(task);
			task.setExecutor(unit);
			this.addWorker(unit, task);
			task.execute();
		}
		// only when the unit can actually complete the task
		if(unit.getTask() != null)
			unit.resetSchedulerDelay();
	}
}

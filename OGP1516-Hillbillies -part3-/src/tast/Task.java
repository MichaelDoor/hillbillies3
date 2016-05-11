package tast;


import be.kuleuven.cs.som.annotate.*;
import objects.Unit;
import statement.MyStatement;

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
 *       
 * @author Michaël
 * @version	0.0
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
	 * @param  executor
	 *         The executor for this new task.
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
	 * @throws	NullPointerException
	 * 			The given name is not effective.
	 * 			| name == null
	 * @throws	NullPointerException
	 * 			The given activity is not effective.
	 * 			| activity == null
	 */
	public Task(String name, int priority, MyStatement activity) throws NullPointerException{
		this.setName(name);
		this.setPriority(priority);
		this.setActivity(activity);
		this.setExecutor(null);
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
	private void reducePriority() {
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
	 * @return True if and only if the given executor is not effective, is not terminated or has it's default behavior enabled. 
	 *       | result == ((executor == null) || (! executor.isTerminated()) || (executor.getDefaultBehaviour()))
	*/
	public static boolean isValidExecutor(Unit executor) {
		return ((executor == null) || ((! executor.isTerminated()) && (executor.getDefaultBehaviour()))
				&& (! executor.getActivityStatus()).equals("rest")) && (! executor.getActivityStatus().equals(anObject));
		// make the unit interrupt when something happens (no control over defend!) 
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
	 */
	@Raw
	public void setExecutor(Unit executor) 
			throws IllegalArgumentException {
		if (! isValidExecutor(executor))
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
	 * 			| this.interrupt();
	 * @effect	If this tasks executor has executed this task's activity, this task is terminated.
	 * 			| this.terminate()
	 * @effect	This task's activity is executed.
	 * 			| this.getActivity().run(this.getExecutor())
	 * @throws IllegalStateException
	 * 			This task does not have an executor.
	 * 			| this.getExecutor() == null
	 */
	public void execute() throws IllegalStateException {
		if(this.getExecutor() == null)
			throw new IllegalStateException();
		if(! this.hasProperExecutor()){
			this.interrupt();
		}
		else if(this.getActivity().isExecuted(this.getExecutor())){
			this.terminate();
			return;
		}
		else
			this.getActivity().run(this.getExecutor());
	}
}

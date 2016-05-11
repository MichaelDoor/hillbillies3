package statement;

import objects.Unit;

public abstract class MyStatement {

	private boolean executionStatus;
	
	/**
	 * Create a new statement. It's execution status is automatically set to false.
	 */
	public MyStatement() {
		this.setExecuted(false);
	}
	
	public abstract void run(Unit unit) throws NullPointerException;
	
	/**
	 * Check whether this statement has been executed by the given unit.
	 * @param unit	The given unit.
	 * @return	True if and only if this statement's execution status is true;
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 */
	public boolean isExecuted(Unit unit) throws NullPointerException{
		if(unit == null)
			throw new NullPointerException();
		return this.getExecuted();
	}
	
	protected void setExecuted(boolean completion) {
		this.executionStatus = completion;
	}
	
	public boolean getExecuted() {
		return this.executionStatus;
	}
	
	/**
	 * Rolls this statement back to its initial state, as if it hasn't been executed yet.
	 */
	public void rollback() {
		this.setExecuted(false);
	}

}

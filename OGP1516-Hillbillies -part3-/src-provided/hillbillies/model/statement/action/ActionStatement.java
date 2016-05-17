package hillbillies.model.statement.action;

import hillbillies.model.expression.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.statement.MyStatement;

public abstract class ActionStatement<O> extends MyStatement {

	public ActionStatement(MyExpression target) {
		this.setTarget(target);
		this.setExecutionTarget(null);
	}
	
	public MyExpression getTarget() {
		return this.target;
	}
	
	private void setTarget(MyExpression target) throws NullPointerException {
		if(target == null)
			throw new NullPointerException();
		this.target = target;
	}
	
	private MyExpression target;

	public abstract void run(Unit unit) throws NullPointerException;
	
	/**
	 * Return the target of the action performed last time this action statement was executed. Null if this action statement
	 * has not been executed yet.
	 */
	public O getExecutionTarget(){
		return this.executionTarget;
	}
	
	protected void setExecutionTarget(O target){
		this.executionTarget = target;
	}
	
	/**
	 * Variable registering the target of the action performed last time this action statement was executed.
	 */
	private O executionTarget;
	
	@Override
	public void rollback() {
		super.rollback();
		this.setExecutionTarget(null);
	}

}

//public abstract class ActionStatement<O> extends MyStatement {
//
//	public ActionStatement(MyExpression<?,O> target) {
//		this.setTarget(target);
//		this.setExecutionTarget(null);
//	}
//	
//	public MyExpression<?,O> getTarget() {
//		return this.target;
//	}
//	
//	private void setTarget(MyExpression<?,O> target) throws NullPointerException {
//		if(target == null)
//			throw new NullPointerException();
//		this.target = target;
//	}
//	
//	private MyExpression<?,O> target;
//
//	public abstract void run(Unit unit) throws NullPointerException;
//	
//	/**
//	 * Return the target of the action performed last time this action statement was executed. Null if this action statement
//	 * has not been executed yet.
//	 */
//	public O getExecutionTarget(){
//		return this.executionTarget;
//	}
//	
//	protected void setExecutionTarget(O target){
//		this.executionTarget = target;
//	}
//	
//	/**
//	 * Variable registering the target of the action performed last time this action statement was executed.
//	 */
//	private O executionTarget;
//	
//	@Override
//	public void rollback() {
//		super.rollback();
//		this.setExecutionTarget(null);
//	}
//
//}

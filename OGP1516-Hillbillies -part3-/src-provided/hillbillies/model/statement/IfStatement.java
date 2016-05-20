package hillbillies.model.statement;

import hillbillies.model.expression.booleans.BooleanExpression;
import hillbillies.model.Unit;

public class IfStatement extends MyStatement {

	public IfStatement(BooleanExpression<?> condition, MyStatement ifBody, MyStatement elseBody) throws NullPointerException{
		super();
		this.setCondition(condition);
		this.setIfBody(ifBody);
		this.setElseBody(elseBody);
		this.setSelectedStatement(null);
	}
	
	public BooleanExpression<?> getCondition(){
		return this.condition;
	}
	
	private void setCondition(BooleanExpression<?> condition) throws NullPointerException {
		if(condition == null)
			throw new NullPointerException();
		this.condition = condition;
	}
	
	private BooleanExpression<?> condition;
	
	public MyStatement getIfBody(){
		return this.ifBody;
	}
	
	private void setIfBody(MyStatement ifBody) throws NullPointerException {
		if(ifBody == null)
			throw new NullPointerException();
		this.ifBody = ifBody;
	}
	
	private MyStatement ifBody;
	
	public MyStatement getElseBody() {
		return this.elseBody;
	}
	
	private void setElseBody(MyStatement elseBody) {
		this.elseBody = elseBody;
	}
	
	private MyStatement elseBody;

	@Override
	public void run(Unit unit) throws NullPointerException {
		if(this.getCondition().evaluate(unit)){
			this.getIfBody().run(unit);
			this.setSelectedStatement(this.getIfBody());
		}
		else if(this.getElseBody() != null){
			this.getElseBody().run(unit);
			this.setSelectedStatement(this.getElseBody());
		}
		else {
			this.setExecuted(true);
		}
	}
	
	/**
	 * Return the statement that was selected, the last time this if statement was executed. Returns null if not executed before.
	 */
	public MyStatement getSelectedStatement() {
		return this.selectedStatement;
	}
	
	private void setSelectedStatement(MyStatement statement){
		this.selectedStatement = statement;
	}
	
	/**
	 * Variable registering what statement was picked when the run method was invoked last time.
	 */
	private MyStatement selectedStatement;
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException{
		if(this.getExecuted())
			return true;
		this.setExecuted(this.getSelectedStatement().isExecuted(unit));
		return this.getExecuted();
	}
	
	@Override
	public void rollback() {
		super.rollback();
		this.setSelectedStatement(null);
	}

}

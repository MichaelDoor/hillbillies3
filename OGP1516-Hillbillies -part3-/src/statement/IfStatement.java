package statement;

import expression.booleans.BooleanExpression;
import objects.Unit;

public class IfStatement extends MyStatement {

	public IfStatement(BooleanExpression<?> condition, MyStatement ifBody, MyStatement elseBody) throws NullPointerException{
		this.setCondition(condition);
		this.setIfBody(ifBody);
		this.setElseBody(elseBody);
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
	
	private void setElseBody(MyStatement elseBody) throws NullPointerException {
		if(elseBody == null)
			throw new NullPointerException();
		this.setElseBody(elseBody);
	}
	
	private MyStatement elseBody;

	@Override
	public void run(Unit unit) throws NullPointerException {
		if(this.getCondition().evaluate(unit))
			this.getIfBody().run(unit);
		else
			this.getElseBody().run(unit);
	}

}

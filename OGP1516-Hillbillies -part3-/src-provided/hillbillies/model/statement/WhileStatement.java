package hillbillies.model.statement;

import hillbillies.model.expression.booleans.BooleanExpression;
import hillbillies.model.Unit;

public class WhileStatement extends MyStatement {

	public WhileStatement(BooleanExpression<?> condition, MyStatement body) throws NullPointerException{
		this.setCondition(condition);
		this.setBody(body);
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
	
	public MyStatement getBody() {
		return this.body;
	}
	
	private void setBody(MyStatement body) throws NullPointerException {
		if(body == null)
			throw new NullPointerException();
		this.body = body;
	}
	
	private MyStatement body;

	@Override
	public void run(Unit unit) throws NullPointerException {
		if(this.getCondition().evaluate(unit))
			this.getBody().run(unit);
	}
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException {
		if ((! this.getCondition().evaluate(unit)) && (unit.isIdle()))
			return true;
		return false;
	}

}

package statement.action;

import expression.MyExpression;
import objects.Unit;
import statement.MyStatement;

public abstract class ActionStatement<O> extends MyStatement {

	public ActionStatement(MyExpression<?,O> target) {
		this.setTarget(target);
	}
	
	public MyExpression<?,O> getTarget() {
		return this.target;
	}
	
	private void setTarget(MyExpression<?,O> target) throws NullPointerException {
		if(target == null)
			throw new NullPointerException();
		this.target = target;
	}
	
	private MyExpression<?,O> target;

	public abstract void run(Unit unit) throws NullPointerException;

}

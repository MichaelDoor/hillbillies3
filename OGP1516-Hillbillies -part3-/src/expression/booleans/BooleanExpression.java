package expression.booleans;

import expression.MyExpression;
import objects.Unit;

public abstract class BooleanExpression<I> extends MyExpression {

	protected BooleanExpression(I value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException {
		return (Boolean) this.getValue();
	}

}

//public abstract class BooleanExpression<I> extends MyExpression<I, Boolean> {
//
//	public BooleanExpression(I value) {
//		super(value);
//	}
//
//}

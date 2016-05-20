package hillbillies.model.expression.booleans;

import hillbillies.model.expression.MyExpression;
import hillbillies.model.Unit;

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

package hillbillies.model.expression.booleans.isSomething;

import hillbillies.model.expression.booleans.BooleanExpression;

public abstract class IsSomethingExpression<I> extends BooleanExpression<I> {

	public IsSomethingExpression(I value) {
		super(value);
	}
	
}

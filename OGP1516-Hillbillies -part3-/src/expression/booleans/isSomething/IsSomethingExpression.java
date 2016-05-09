package expression.booleans.isSomething;

import expression.booleans.BooleanExpression;

public abstract class IsSomethingExpression<I> extends BooleanExpression<I> {

	public IsSomethingExpression(I value) {
		super(value);
	}

}

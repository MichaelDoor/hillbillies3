package expression.booleans;

import expression.MyExpression;

public abstract class BooleanExpression<I> extends MyExpression<I, Boolean> {

	public BooleanExpression(I value) {
		super(value);
	}

}

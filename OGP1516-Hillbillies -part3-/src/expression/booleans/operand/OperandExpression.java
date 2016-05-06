package expression.booleans.operand;

import expression.booleans.BooleanExpression;

public abstract class OperandExpression<I> extends BooleanExpression<I> {

	public OperandExpression(I value) {
		super(value);
	}

}

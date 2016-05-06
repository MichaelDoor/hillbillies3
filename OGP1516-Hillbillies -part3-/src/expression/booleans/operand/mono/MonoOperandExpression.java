package expression.booleans.operand.mono;

import expression.booleans.operand.OperandExpression;

public abstract class MonoOperandExpression<I> extends OperandExpression<I> {

	public MonoOperandExpression(I value) {
		super(value);
	}

}

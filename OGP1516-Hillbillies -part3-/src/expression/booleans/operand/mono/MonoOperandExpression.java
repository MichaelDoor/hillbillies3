package expression.booleans.operand.mono;

import expression.booleans.BooleanExpression;
import expression.booleans.operand.OperandExpression;

public abstract class MonoOperandExpression<I> extends OperandExpression<I> {

	public MonoOperandExpression(I value) {
		super(value);
	}
	
	@Override
	public BooleanExpression<?> getValue() {
		return (BooleanExpression<?>) super.getValue();
	}

}

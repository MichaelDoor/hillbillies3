package hillbillies.model.expression.booleans.operand.mono;

import hillbillies.model.expression.booleans.BooleanExpression;
import hillbillies.model.expression.booleans.operand.OperandExpression;

public abstract class MonoOperandExpression<I> extends OperandExpression<I> {

	MonoOperandExpression(I value) {
		super(value);
	}
	
	@Override
	protected BooleanExpression<?> getValue() {
		return (BooleanExpression<?>) super.getValue();
	}

}

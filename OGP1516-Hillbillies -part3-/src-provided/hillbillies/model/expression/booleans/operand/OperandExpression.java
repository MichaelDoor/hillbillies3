package hillbillies.model.expression.booleans.operand;

import hillbillies.model.expression.booleans.BooleanExpression;

public abstract class OperandExpression<I> extends BooleanExpression<I> {

	protected OperandExpression(I value) {
		super(value);
	}

}

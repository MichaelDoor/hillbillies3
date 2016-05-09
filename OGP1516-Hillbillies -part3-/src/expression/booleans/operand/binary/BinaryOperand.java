package expression.booleans.operand.binary;

import expression.booleans.operand.OperandExpression;

public abstract class BinaryOperand<I> extends OperandExpression<I> {

	public BinaryOperand(I value) {
		super(value);
	}

}

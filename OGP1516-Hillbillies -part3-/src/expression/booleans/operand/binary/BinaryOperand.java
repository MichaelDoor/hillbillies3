package expression.booleans.operand.binary;

import java.util.List;

import expression.booleans.BooleanExpression;
import expression.booleans.operand.OperandExpression;

public abstract class BinaryOperand<I> extends OperandExpression<I> {

	public BinaryOperand(I value) {
		super(value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<? extends BooleanExpression<?>> getValue() {
		return (List<BooleanExpression<?>>) super.getValue();
	}
}

//public abstract class BinaryOperand<I> extends OperandExpression<I> {
//
//	public BinaryOperand(I value) {
//		super(value);
//	}
//
//}

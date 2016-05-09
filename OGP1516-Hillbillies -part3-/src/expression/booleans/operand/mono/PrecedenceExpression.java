package expression.booleans.operand.mono;

import expression.booleans.BooleanExpression;
import objects.Unit;

public class PrecedenceExpression<I extends BooleanExpression<I>> extends MonoOperandExpression<I> {

	public PrecedenceExpression(I value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return (this.getValue().evaluate(unit));
	}

}

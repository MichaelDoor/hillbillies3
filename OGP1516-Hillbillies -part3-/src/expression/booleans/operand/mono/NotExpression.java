package expression.booleans.operand.mono;

import expression.booleans.BooleanExpression;
import objects.Unit;

public class NotExpression<I extends BooleanExpression<I>> extends MonoOperandExpression<I> {

	public NotExpression(I value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return (! this.getValue().evaluate(unit));
	}

}

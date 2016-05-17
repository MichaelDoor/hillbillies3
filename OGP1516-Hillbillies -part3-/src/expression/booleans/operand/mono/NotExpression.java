package expression.booleans.operand.mono;

import expression.booleans.BooleanExpression;
import objects.Unit;

public class NotExpression extends MonoOperandExpression<BooleanExpression<?>> {

	public NotExpression(BooleanExpression<?> value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return (! this.getValue().evaluate(unit));
	}

}

//public class NotExpression extends MonoOperandExpression<BooleanExpression<?>> {
//
//	public NotExpression(BooleanExpression<?> value) {
//		super(value);
//	}
//	
//	@Override
//	public Boolean evaluate(Unit unit) throws NullPointerException{
//		return (! this.getValue().evaluate(unit));
//	}
//
//}

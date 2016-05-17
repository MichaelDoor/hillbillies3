package hillbillies.model.expression.booleans.operand.mono;

import hillbillies.model.expression.booleans.BooleanExpression;
import hillbillies.model.Unit;

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

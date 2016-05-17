package expression.booleans.operand.mono;

import expression.booleans.BooleanExpression;
import objects.Unit;

public class PrecedenceExpression extends MonoOperandExpression<BooleanExpression<?>> {

	public PrecedenceExpression(BooleanExpression<?> value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return this.getValue().evaluate(unit);
	}

}

//public class PrecedenceExpression extends MonoOperandExpression<BooleanExpression<?>> {
//
//	public PrecedenceExpression(BooleanExpression<?> value) {
//		super(value);
//	}
//	
//	@Override
//	public Boolean evaluate(Unit unit) throws NullPointerException{
//		return this.getValue().evaluate(unit));
//	}
//
//}

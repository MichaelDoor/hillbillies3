package expression.position;

import expression.MyExpression;
import objects.Unit;
import position.PositionVector;

public abstract class PositionExpression<I> extends MyExpression {

	public PositionExpression(I value) {
		super(value);
	}

	
	@Override
	public PositionVector evaluate(Unit unit) throws NullPointerException {
		return (PositionVector) this.getValue();
	}

}

//public abstract class PositionExpression<I> extends MyExpression<I, PositionVector> {
//
//	public PositionExpression(I value) {
//		super(value);
//	}
//
//}

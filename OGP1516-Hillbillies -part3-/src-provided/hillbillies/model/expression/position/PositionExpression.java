package hillbillies.model.expression.position;

import hillbillies.model.expression.MyExpression;
import hillbillies.model.Unit;
import hillbillies.model.PositionVector;

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

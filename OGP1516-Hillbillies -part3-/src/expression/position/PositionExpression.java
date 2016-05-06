package expression.position;

import expression.MyExpression;
import position.PositionVector;

public abstract class PositionExpression<I> extends MyExpression<I, PositionVector> {

	public PositionExpression(I value) {
		super(value);
	}

}

package expression.position;

import expression.unit.UnitExpression;
import objects.Unit;
import position.PositionVector;

public class PositionOfExpression<I extends UnitExpression> extends PositionExpression<I> {

	public PositionOfExpression(I value) {
		super(value);
	}
	
	@Override
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return this.getValue().evaluate(unit).getCubePositionVector();
	}

}

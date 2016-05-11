package expression.position;

import objects.Unit;
import position.PositionVector;

public class NextToExpression extends PositionExpression<PositionExpression<?>> {

	public NextToExpression(PositionExpression<?> value) {
		super(value);
	}
	
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().getAdjacentStandingPosition(this.getValue().evaluate(unit));
	}

}

package expression.position;

import objects.Unit;
import position.PositionVector;

public class NextToExpression extends PositionExpression<PositionVector> {

	public NextToExpression(PositionVector value) {
		super(value);
	}
	
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().getAdjacentStandingPosition(unit.getCubePositionVector());
	}

}

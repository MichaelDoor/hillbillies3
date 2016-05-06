package expression.position;

import objects.Unit;
import position.PositionVector;

public class BoulderExpression extends PositionExpression<PositionVector> {

	public BoulderExpression() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().getAccessibleBoulder(unit.getCubePositionVector());
	}

}

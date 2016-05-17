package hillbillies.model.expression.position;

import hillbillies.model.Unit;
import hillbillies.model.PositionVector;

public class BoulderExpression extends PositionExpression<PositionVector> {

	public BoulderExpression() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().getAccessibleBoulder(unit.getCubePositionVector());
	}

}

package expression.position;

import objects.Unit;
import position.PositionVector;

public class WorkshopExpression extends PositionExpression<PositionVector> {

	public WorkshopExpression() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().getAccesibleWorkshop(unit.getCubePositionVector());
	}

}

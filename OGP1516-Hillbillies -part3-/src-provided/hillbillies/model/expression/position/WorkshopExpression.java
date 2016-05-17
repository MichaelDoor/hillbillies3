package hillbillies.model.expression.position;

import hillbillies.model.Unit;
import hillbillies.model.PositionVector;

public class WorkshopExpression extends PositionExpression<PositionVector> {

	public WorkshopExpression() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().getAccesibleWorkshop(unit.getCubePositionVector());
	}

}

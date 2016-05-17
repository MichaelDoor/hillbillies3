package hillbillies.model.expression.position;

import hillbillies.model.Unit;
import hillbillies.model.PositionVector;

public class LogExpression extends PositionExpression<PositionVector> {

	public LogExpression() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit){
		return unit.getWorld().getAccessibleLog(unit.getCubePositionVector());
	}

}

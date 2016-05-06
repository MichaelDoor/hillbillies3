package expression.position;

import objects.Unit;
import position.PositionVector;

public class LogExpression extends PositionExpression<PositionVector> {

	public LogExpression() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit){
		return unit.getWorld().getAccessibleLog(unit.getCubePositionVector());
	}

}

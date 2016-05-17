package hillbillies.model.expression.position;

import hillbillies.model.Unit;
import hillbillies.model.PositionVector;

public class HereExpression extends PositionExpression<PositionVector> {

	public HereExpression() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit){
		return unit.getCubePositionVector();
	}

}

package expression.position;

import objects.Unit;
import position.PositionVector;

public class HereExpression extends PositionExpression<PositionVector> {

	public HereExpression() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit){
		return unit.getCubePositionVector();
	}

}

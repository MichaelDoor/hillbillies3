package expression.position;

import objects.Unit;
import position.PositionVector;

public class HerePosition extends PositionExpression<PositionVector> {

	public HerePosition() {
		super(null);
	}
	
	public PositionVector evaluate(Unit unit){
		return unit.getCubePositionVector();
	}

}

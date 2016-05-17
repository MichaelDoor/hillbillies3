package hillbillies.model.expression.booleans.isSomething.cube;

import hillbillies.model.expression.position.PositionExpression;
import hillbillies.model.Unit;

public class IsPassableExpression extends CubeIsSomething<PositionExpression<?>> {

	public IsPassableExpression(PositionExpression<?> value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return (! unit.getWorld().isSolidPosition(this.getValue().evaluate(unit)));
	}

}

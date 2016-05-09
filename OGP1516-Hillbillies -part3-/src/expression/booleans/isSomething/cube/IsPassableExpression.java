package expression.booleans.isSomething.cube;

import expression.position.PositionExpression;
import objects.Unit;

public class IsPassableExpression extends CubeIsSomething<PositionExpression<?>> {

	public IsPassableExpression(PositionExpression<?> value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return (! unit.getWorld().isSolidPosition(this.getValue().evaluate(unit)));
	}

}

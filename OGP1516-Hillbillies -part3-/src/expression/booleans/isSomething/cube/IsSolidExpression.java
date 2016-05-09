package expression.booleans.isSomething.cube;

import expression.position.PositionExpression;
import objects.Unit;

// met een wildcard voor de superklasse werken?
public class IsSolidExpression extends CubeIsSomething<PositionExpression<?>> {

	public IsSolidExpression(PositionExpression<?> value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().isSolidPosition(this.getValue().evaluate(unit));
	}

}

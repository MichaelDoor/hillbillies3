package expression.booleans.isSomething.unit;

import expression.unit.UnitExpression;
import objects.Unit;

public class IsFriendExpression extends UnitIsSomethingExpression<UnitExpression> {

	public IsFriendExpression(UnitExpression value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		Unit target = this.getValue().evaluate(unit);
		return unit.getWorld().areAllies(unit, target);
	}

}

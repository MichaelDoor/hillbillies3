package expression.booleans.isSomething.unit;

import expression.unit.UnitExpression;
import objects.Unit;

public class IsEnemyExpression extends UnitIsSomethingExpression<UnitExpression> {

	public IsEnemyExpression(UnitExpression value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		Unit target = this.getValue().evaluate(unit);
		return (! unit.getWorld().areAllies(unit, target));
	}

}

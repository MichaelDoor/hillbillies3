package hillbillies.model.expression.booleans.isSomething.unit;

import hillbillies.model.expression.unit.UnitExpression;
import hillbillies.model.Unit;

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

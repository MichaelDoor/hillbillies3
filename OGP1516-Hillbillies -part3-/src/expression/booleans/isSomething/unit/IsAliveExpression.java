package expression.booleans.isSomething.unit;

import expression.unit.UnitExpression;
import objects.Unit;

public class IsAliveExpression extends UnitIsSomethingExpression<UnitExpression> {

	public IsAliveExpression(UnitExpression value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return (! this.getValue().evaluate(unit).isTerminated());
	}

}

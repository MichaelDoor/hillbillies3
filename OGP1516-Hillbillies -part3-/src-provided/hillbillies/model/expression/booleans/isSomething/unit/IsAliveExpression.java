package hillbillies.model.expression.booleans.isSomething.unit;

import hillbillies.model.expression.unit.UnitExpression;
import hillbillies.model.Unit;

public class IsAliveExpression extends UnitIsSomethingExpression<UnitExpression> {

	public IsAliveExpression(UnitExpression value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return (! this.getValue().evaluate(unit).isTerminated());
	}

}

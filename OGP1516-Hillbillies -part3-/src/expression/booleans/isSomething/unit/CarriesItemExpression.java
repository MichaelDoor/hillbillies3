package expression.booleans.isSomething.unit;

import expression.unit.UnitExpression;
import objects.Unit;

public class CarriesItemExpression extends UnitIsSomethingExpression<UnitExpression> {

	public CarriesItemExpression(UnitExpression value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return this.getValue().evaluate(unit).isCarryingItem();
	}

}

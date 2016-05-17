package hillbillies.model.expression.booleans.isSomething.unit;

import hillbillies.model.expression.unit.UnitExpression;
import hillbillies.model.Unit;

public class CarriesItemExpression extends UnitIsSomethingExpression<UnitExpression> {

	public CarriesItemExpression(UnitExpression value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return this.getValue().evaluate(unit).isCarryingItem();
	}

}

package hillbillies.model.expression.unit;

import hillbillies.model.Unit;

public class AnyExpression extends UnitExpression {

	public AnyExpression() {
		super();
	}
	
	public Unit evaluate(Unit unit){
		return unit.getWorld().getAccessibleUnit(unit);
	}

}

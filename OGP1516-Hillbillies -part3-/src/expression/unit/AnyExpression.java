package expression.unit;

import objects.Unit;

public class AnyExpression extends UnitExpression {

	public AnyExpression() {
		super();
	}
	
	public Unit evaluate(Unit unit){
		return unit.getWorld().getAccessibleUnit(unit);
	}

}

package expression.unit;

import objects.Unit;

public class ThisExpression extends UnitExpression {

	public ThisExpression() {
		super();
	}
	
	public Unit evaluate(Unit unit){
		return unit;
	}

}

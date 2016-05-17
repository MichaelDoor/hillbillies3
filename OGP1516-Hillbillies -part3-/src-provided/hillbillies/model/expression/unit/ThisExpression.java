package hillbillies.model.expression.unit;

import hillbillies.model.Unit;

public class ThisExpression extends UnitExpression {

	public ThisExpression() {
		super();
	}
	
	public Unit evaluate(Unit unit){
		return unit;
	}

}

package hillbillies.model.expression.booleans.constant;

import hillbillies.model.Unit;

public class FalseExpression extends BooleanConstantExpression {

	public FalseExpression() {
	}
	
	public Boolean evaluate(Unit unit){
		return false;
	}

}

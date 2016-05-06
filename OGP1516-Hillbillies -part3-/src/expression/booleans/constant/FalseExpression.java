package expression.booleans.constant;

import objects.Unit;

public class FalseExpression extends BooleanConstantExpression {

	public FalseExpression() {
	}
	
	public Boolean evaluate(Unit unit){
		return false;
	}

}

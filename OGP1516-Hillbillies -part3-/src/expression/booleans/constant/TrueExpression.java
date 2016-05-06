package expression.booleans.constant;

import objects.Unit;

public class TrueExpression extends BooleanConstantExpression{

	public TrueExpression() {
		super();
	}
	
	@Override
	public Boolean evaluate(Unit unit){
		return true;
	}
	

}

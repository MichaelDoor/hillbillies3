package hillbillies.model.expression.booleans.constant;

import hillbillies.model.Unit;

public class TrueExpression extends BooleanConstantExpression{

	public TrueExpression() {
		super();
	}
	
	@Override
	public Boolean evaluate(Unit unit){
		return true;
	}
	

}

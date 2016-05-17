package hillbillies.model.expression.unit;

import hillbillies.model.Unit;

public class FriendExpression extends UnitExpression {

	public FriendExpression() {
		super();
	}
	
	public Unit evaluate(Unit unit){
		return unit.getWorld().getAccessibleAlly(unit);
	}

}

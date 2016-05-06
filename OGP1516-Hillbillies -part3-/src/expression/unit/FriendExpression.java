package expression.unit;

import objects.Unit;

public class FriendExpression extends UnitExpression {

	public FriendExpression() {
		super();
	}
	
	public Unit evaluate(Unit unit){
		return unit.getWorld().getAccessibleAlly(unit);
	}

}

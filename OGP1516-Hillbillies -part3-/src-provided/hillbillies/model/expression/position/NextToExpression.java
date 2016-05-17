package hillbillies.model.expression.position;

import hillbillies.model.Unit;
import hillbillies.model.PositionVector;

public class NextToExpression extends PositionExpression<PositionExpression<?>> {

	public NextToExpression(PositionExpression<?> value) {
		super(value);
	}
	
	@Override
	protected PositionExpression<?> getValue() {
		return (PositionExpression<?>) super.getValue();
	}
	
	@Override
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().getAdjacentStandingPosition(this.getValue().evaluate(unit));
	}

}

//public class NextToExpression extends PositionExpression<PositionExpression<?>> {
//
//	public NextToExpression(PositionExpression<?> value) {
//		super(value);
//	}
//	
//	public PositionVector evaluate(Unit unit) throws NullPointerException{
//		return unit.getWorld().getAdjacentStandingPosition(this.getValue().evaluate(unit));
//	}
//
//}

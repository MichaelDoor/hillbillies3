package hillbillies.model.expression.booleans.isSomething.cube;

import hillbillies.model.expression.booleans.isSomething.IsSomethingExpression;
import hillbillies.model.expression.position.PositionExpression;
import hillbillies.model.Unit;

public abstract class CubeIsSomething<I> extends IsSomethingExpression<I> {

	public CubeIsSomething(I value) {
		super(value);
	}
	
	@Override
	protected PositionExpression<?> getValue() {
		return (PositionExpression<?>) super.getValue();
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		return unit.getWorld().isSolidPosition(this.getValue().evaluate(unit));
	}

}

//public abstract class CubeIsSomething<I> extends IsSomethingExpression<I> {
//
//	public CubeIsSomething(I value) {
//		super(value);
//	}
//
//}

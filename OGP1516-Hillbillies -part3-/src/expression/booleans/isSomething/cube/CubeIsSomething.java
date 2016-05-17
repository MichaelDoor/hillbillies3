package expression.booleans.isSomething.cube;

import expression.booleans.isSomething.IsSomethingExpression;
import expression.position.PositionExpression;
import objects.Unit;

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

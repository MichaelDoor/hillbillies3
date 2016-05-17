package expression.booleans.isSomething.unit;

import expression.booleans.isSomething.IsSomethingExpression;
import expression.unit.UnitExpression;

public abstract class UnitIsSomethingExpression<I> extends IsSomethingExpression<I> {

	public UnitIsSomethingExpression(I value) {
		super(value);
	}
	
	@Override
	protected UnitExpression getValue() {
		return (UnitExpression) super.getValue();
	}

}

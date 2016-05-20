package expression.booleans.isSomething.unit;

import expression.booleans.isSomething.IsSomethingExpression;
import expression.unit.UnitExpression;

public abstract class UnitIsSomethingExpression<I> extends IsSomethingExpression<I> {

	UnitIsSomethingExpression(I value) {
		super(value);
	}
	
	@Override
	public UnitExpression getValue() {
		return (UnitExpression) super.getValue();
	}

}

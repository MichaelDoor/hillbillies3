package hillbillies.model.expression.booleans.isSomething.unit;

import hillbillies.model.expression.booleans.isSomething.IsSomethingExpression;
import hillbillies.model.expression.unit.UnitExpression;

public abstract class UnitIsSomethingExpression<I> extends IsSomethingExpression<I> {

	UnitIsSomethingExpression(I value) {
		super(value);
	}
	
	@Override
	protected UnitExpression getValue() {
		return (UnitExpression) super.getValue();
	}

}

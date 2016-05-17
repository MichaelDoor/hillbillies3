package hillbillies.model.expression.unit;

import hillbillies.model.expression.MyExpression;
import hillbillies.model.Unit;

public abstract class UnitExpression extends MyExpression {

	public UnitExpression() {
		super(null);
	}
	
	@Override
	public Unit evaluate(Unit unit) throws NullPointerException {
		return (Unit) this.getValue();
	}

}

//public abstract class UnitExpression extends MyExpression<UnitExpression,Unit> {
//
//	public UnitExpression() {
//		super(null);
//	}
//
//}

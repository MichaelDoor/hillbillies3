package expression.unit;

import expression.IMyExpression;
import objects.Unit;

public interface IUnitExpression extends IMyExpression {

	@Override
	public Object evaluate(Unit unit);
}

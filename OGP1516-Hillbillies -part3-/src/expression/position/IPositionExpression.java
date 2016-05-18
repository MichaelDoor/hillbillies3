package expression.position;

import expression.IMyExpression;
import objects.Unit;

public interface IPositionExpression extends IMyExpression {

	@Override
	public Object evaluate(Unit unit) throws NullPointerException;
}

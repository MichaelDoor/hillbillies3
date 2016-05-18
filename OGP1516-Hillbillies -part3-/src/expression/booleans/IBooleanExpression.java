package expression.booleans;

import expression.IMyExpression;
import objects.Unit;

public interface IBooleanExpression extends IMyExpression{

	@Override
	public Object evaluate(Unit unit) throws NullPointerException;
}

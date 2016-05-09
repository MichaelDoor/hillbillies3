package statement.action;

import expression.MyExpression;
import objects.Unit;
import position.PositionVector;

public class WorkStatement extends ActionStatement<PositionVector> {

	public WorkStatement(MyExpression<?, PositionVector> target) {
		super(target);
	}

	@Override
	public void run(Unit unit) throws NullPointerException {
		unit.work(this.getTarget().evaluate(unit));
	}

}

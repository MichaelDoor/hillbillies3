package statement.action;

import expression.MyExpression;
import objects.Unit;

public class FollowStatement extends ActionStatement<Unit> {

	public FollowStatement(MyExpression<?, Unit> target) {
		super(target);
	}

	@Override
	public void run(Unit unit) throws NullPointerException {
		unit.follow(this.getTarget().evaluate(unit));
	}

}

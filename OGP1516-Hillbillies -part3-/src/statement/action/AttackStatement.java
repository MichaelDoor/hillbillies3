package statement.action;

import expression.MyExpression;
import objects.Unit;

public class AttackStatement extends ActionStatement<Unit> {

	public AttackStatement(MyExpression<?, Unit> target) {
		super(target);
	}

	@Override
	public void run(Unit unit) throws NullPointerException {
		unit.attack(this.getTarget().evaluate(unit));
	}

}

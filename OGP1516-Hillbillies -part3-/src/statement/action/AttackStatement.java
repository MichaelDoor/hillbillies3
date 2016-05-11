package statement.action;

import expression.MyExpression;
import objects.Unit;

public class AttackStatement extends ActionStatement<Unit> {

	public AttackStatement(MyExpression<?, Unit> target) {
		super(target);
	}

	@Override
	public void run(Unit unit) throws NullPointerException {
		Unit target = this.getTarget().evaluate(unit);
		unit.attack(target);
		this.setExecutionTarget(target);
		this.setExecuted(true);
	}
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException {
		if((this.isExecuted(unit)) && (unit.isIdle()) && (this.getExecutionTarget() != null))
			this.setExecuted(true);
		return this.getExecuted();
	}

}

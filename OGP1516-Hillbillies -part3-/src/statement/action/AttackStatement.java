package statement.action;

import expression.MyExpression;
import objects.Unit;

public class AttackStatement extends ActionStatement<Unit> {

	public AttackStatement(MyExpression target) {
		super(target);
	}

	@Override
	public void run(Unit unit) throws NullPointerException, IllegalArgumentException {
		Unit target = (Unit) this.getTarget().evaluate(unit);
		unit.attack(target);
		this.setExecutionTarget(target);
	}
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException {
		if((unit.isIdle()) && (this.getExecutionTarget() != null))
			this.setExecuted(true);
		return this.getExecuted();
	}

}

//public class AttackStatement extends ActionStatement<Unit> {
//
//	public AttackStatement(MyExpression<?, Unit> target) {
//		super(target);
//	}
//
//	@Override
//	public void run(Unit unit) throws NullPointerException, IllegalArgumentException {
//		Unit target = this.getTarget().evaluate(unit);
//		unit.attack(target);
//		this.setExecutionTarget(target);
//	}
//	
//	@Override
//	public boolean isExecuted(Unit unit) throws NullPointerException {
//		if((unit.isIdle()) && (this.getExecutionTarget() != null))
//			this.setExecuted(true);
//		return this.getExecuted();
//	}
//
//}

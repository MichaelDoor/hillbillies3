package statement.action;

import expression.MyExpression;
import objects.Unit;
import position.PositionVector;

public class WorkStatement extends ActionStatement<PositionVector> {

	public WorkStatement(MyExpression<?, PositionVector> target) {
		super(target);
	}

	@Override
	public void run(Unit unit) throws NullPointerException,IllegalArgumentException {
		PositionVector target = this.getTarget().evaluate(unit);
		unit.work(target);
		this.setExecutionTarget(target);
	}
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException {
		boolean flag1 = unit.isIdle();
		boolean flag2 = (this.getExecutionTarget() != null);
		if(flag1 && flag2 )
			this.setExecuted(true);
		return this.getExecuted();
	}

}

package statement.action;

import expression.MyExpression;
import objects.Unit;
import position.PositionVector;

public class MoveToStatement extends ActionStatement<PositionVector> {

	
	public MoveToStatement(MyExpression<?, PositionVector> position) {
		super(position);
	}
	

	@Override
	public void run(Unit unit) throws NullPointerException, IllegalArgumentException {
		PositionVector target = this.getTarget().evaluate(unit);
		unit.moveTo(target);
		this.setExecutionTarget(target);
	}
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException {
		try {
			boolean flag1 = unit.isIdle();
			boolean flag2 = unit.getCubePositionVector().equals(this.getExecutionTarget());
			if(flag1 && flag2)
				this.setExecuted(true);
			return this.getExecuted();
		}
		catch (NullPointerException exc) {
			if(this.getExecutionTarget() == null)
				return this.getExecuted();
			else
				throw new NullPointerException();
		}
	}
	

}

package statement.action;

import java.util.Set;

import expression.MyExpression;
import objects.Unit;
import position.PositionVector;

public class FollowStatement extends ActionStatement<Unit> {

	public FollowStatement(MyExpression<?, Unit> target) {
		super(target);
	}

	@Override
	public void run(Unit unit) throws NullPointerException {
		Unit target = this.getTarget().evaluate(unit);
		unit.moveTo(target.getCubePositionVector());
		this.setExecutionTarget(target);
	}
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException {
		try {
			// just to save some processing time
			if(this.getExecuted())
				return this.getExecuted();
			
			PositionVector targetPosition = this.getExecutionTarget().getCubePositionVector();
			Set<PositionVector> correctPositions = unit.getWorld().getAdjacentStandingPositions(targetPosition);
			correctPositions.add(targetPosition);
			if((unit.isIdle()) && (correctPositions.contains(unit.getCubePositionVector())))
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

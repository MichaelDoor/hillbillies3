package statement.action;

import expression.MyExpression;
import objects.Unit;
import position.PositionVector;

public class MoveToStatement extends ActionStatement<PositionVector> {

	
	public MoveToStatement(MyExpression<?, PositionVector> position) {
		super(position);
	}
	

	@Override
	public void run(Unit unit) throws NullPointerException {
		unit.moveTo(this.getTarget().evaluate(unit));
	}

}

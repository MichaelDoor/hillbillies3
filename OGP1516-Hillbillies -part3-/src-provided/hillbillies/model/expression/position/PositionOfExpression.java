package hillbillies.model.expression.position;

import hillbillies.model.expression.MyExpression;
import hillbillies.model.expression.unit.UnitExpression;
import hillbillies.model.GameObject;
import hillbillies.model.Unit;
import hillbillies.model.PositionVector;

public class PositionOfExpression<I extends UnitExpression> extends PositionExpression<I> {

	public PositionOfExpression(I value) {
		super(value);
	}
	
	@Override
	public PositionVector evaluate(Unit unit) throws NullPointerException{
		return ((GameObject) ((MyExpression) this.getValue()).evaluate(unit)).getCubePositionVector();
	}

}

//public class PositionOfExpression<I extends UnitExpression> extends PositionExpression<I> {
//
//	public PositionOfExpression(I value) {
//		super(value);
//	}
//	
//	@Override
//	public PositionVector evaluate(Unit unit) throws NullPointerException{
//		return this.getValue().evaluate(unit).getCubePositionVector();
//	}
//
//}

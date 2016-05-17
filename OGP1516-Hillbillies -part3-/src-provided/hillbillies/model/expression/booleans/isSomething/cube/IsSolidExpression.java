package hillbillies.model.expression.booleans.isSomething.cube;

import hillbillies.model.expression.position.PositionExpression;

// met een wildcard voor de superklasse werken?
public class IsSolidExpression extends CubeIsSomething<PositionExpression<?>> {

	public IsSolidExpression(PositionExpression<?> value) {
		super(value);
	}

}

//public class IsSolidExpression extends CubeIsSomething<PositionExpression<?>> {
//
//	public IsSolidExpression(PositionExpression<?> value) {
//		super(value);
//	}
//}

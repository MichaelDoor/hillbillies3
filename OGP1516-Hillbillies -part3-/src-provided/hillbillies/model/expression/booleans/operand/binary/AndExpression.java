package hillbillies.model.expression.booleans.operand.binary;

import java.util.List;

import hillbillies.model.expression.booleans.BooleanExpression;
import hillbillies.model.Unit;

public class AndExpression extends BinaryOperand<List<? extends BooleanExpression<?>>> {

	public AndExpression(List<? extends BooleanExpression<?>> value) {
		super(value);
	}
	
	@Override
	public Boolean evaluate(Unit unit) throws NullPointerException{
		List<? extends BooleanExpression<?>> list = this.getValue();
		Boolean left = list.get(0).evaluate(unit);
		Boolean right = list.get(1).evaluate(unit);
		return (left && right);
	}

}

//public class AndExpression extends BinaryOperand<List<? extends BooleanExpression<?>>> {
//
//	public AndExpression(List<? extends BooleanExpression<?>> value) {
//		super(value);
//	}
//	
//	@Override
//	public Boolean evaluate(Unit unit) throws NullPointerException{
//		Boolean left = this.getValue().get(0).evaluate(unit);
//		Boolean right = this.getValue().get(1).evaluate(unit);
//		return (left && right);
//	}
//
//}

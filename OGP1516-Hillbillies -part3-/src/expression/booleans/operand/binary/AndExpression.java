package expression.booleans.operand.binary;

import java.util.List;

import expression.booleans.BooleanExpression;
import objects.Unit;

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

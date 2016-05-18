package expression;

import java.util.List;

import expression.booleans.IBooleanExpression;
import expression.position.IPositionExpression;
import expression.unit.IUnitExpression;
import objects.Unit;
import statement.*;

public class ReadVariableExpression<O> implements IBooleanExpression, IPositionExpression, IUnitExpression {

	public ReadVariableExpression(String value) {
		this.setValue(value);
	}
	
	public String getValue() {
		return (String) this.value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public O evaluate(Unit unit) throws NullPointerException, IllegalArgumentException {
		if(! unit.getFaction().getScheduler().getTaskOf(unit).getActivity().getClass().equals(SequenceStatement.class)) {
			return ((AssignmentStatement<O>) unit.getFaction().getScheduler().getTaskOf(unit).getActivity()).evaluate(unit);
		}
		else {
			List<MyStatement> statements = 
					((SequenceStatement) unit.getFaction().getScheduler().getTaskOf(unit).getActivity()).getStatements();
			O result = null;
			for(MyStatement statement : statements){
				if((statement.getClass().equals(AssignmentStatement.class)) 
						&& (((AssignmentStatement<O>) statement).getName().equals(this.getValue()))) {
					result =((AssignmentStatement<O>) statement).evaluate(unit);
					break;
				}
			}
			return result;
		}
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	private Object value;

//	@Override
//	public Object evaluate(Unit unit) {
//		return this.getValue();
//	}

}

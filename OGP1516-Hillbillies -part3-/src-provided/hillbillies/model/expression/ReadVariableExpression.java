package hillbillies.model.expression;

import java.util.List;

import hillbillies.model.Unit;
import hillbillies.model.statement.*;

public class ReadVariableExpression<O> extends MyExpression {

	public ReadVariableExpression(String value) {
		super(value);
	}
	
	@Override
	protected String getValue() {
		return (String) super.getValue();
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

}

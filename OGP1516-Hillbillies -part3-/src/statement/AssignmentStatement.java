package statement;

import expression.MyExpression;
import objects.Unit;

public class AssignmentStatement<O> extends MyStatement {

	public AssignmentStatement(String variableName, MyExpression<?,O> value) throws NullPointerException{
		this.setName(variableName);
		this.setValue(value);
	}
	
	public String getName() {
		return this.variableName;
	}
	
	private void setName(String variableName) throws NullPointerException{
		if(variableName == null)
			throw new NullPointerException();
		this.variableName = variableName;
	}
	
	private String variableName;
	
	public MyExpression<?, O> getValue() {
		return this.value;
	}
	
	private void setValue(MyExpression<?, O> value) throws NullPointerException {
		if(value == null)
			throw new NullPointerException();
		this.value = value;
	}
	
	private MyExpression<?, O> value;
	
	@Override
	public void run(Unit unit) {
	}
	
	public O evaluate(Unit unit) throws NullPointerException {
		return this.getValue().evaluate(unit);
	}
}

package hillbillies.model.statement;

import hillbillies.model.expression.MyExpression;
import hillbillies.model.Unit;

public class AssignmentStatement<O> extends MyStatement {

	public AssignmentStatement(String variableName, MyExpression value) throws NullPointerException{
		this.setName(variableName);
		this.setValue(value);
		this.setExecuted(false);
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
	
	public MyExpression getValue() {
		return this.value;
	}
	
	private void setValue(MyExpression value) throws NullPointerException {
		if(value == null)
			throw new NullPointerException();
		this.value = value;
	}
	
	private MyExpression value;
	
	@Override
	public void run(Unit unit) {
		this.setExecuted(true);
	}
	
	@SuppressWarnings("unchecked")
	public O evaluate(Unit unit) throws NullPointerException {
		return (O) this.getValue().evaluate(unit);
	}
	
}


//public class AssignmentStatement<O> extends MyStatement {
//
//	public AssignmentStatement(String variableName, MyExpression<?,O> value) throws NullPointerException{
//		this.setName(variableName);
//		this.setValue(value);
//		this.setExecuted(true);
//	}
//	
//	public String getName() {
//		return this.variableName;
//	}
//	
//	private void setName(String variableName) throws NullPointerException{
//		if(variableName == null)
//			throw new NullPointerException();
//		this.variableName = variableName;
//	}
//	
//	private String variableName;
//	
//	public MyExpression<?, O> getValue() {
//		return this.value;
//	}
//	
//	private void setValue(MyExpression<?, O> value) throws NullPointerException {
//		if(value == null)
//			throw new NullPointerException();
//		this.value = value;
//	}
//	
//	private MyExpression<?, O> value;
//	
//	@Override
//	public void run(Unit unit) {
//	}
//	
//	public O evaluate(Unit unit) throws NullPointerException {
//		return this.getValue().evaluate(unit);
//	}
//	
//}

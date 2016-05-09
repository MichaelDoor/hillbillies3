package expression;

import objects.Unit;

public abstract class MyExpression<I,O> {

	public MyExpression(I value) {
		this.setValue(value);
	}
	
	private I value;
	
	protected void setValue(I value){
		this.value = value;
	}
	
	protected I getValue(){
		return this.value;
	}
	
	@SuppressWarnings("unchecked")
	public O evaluate(Unit unit){
		return (O) this.getValue();
	}

}
package statement;

import expression.MyExpression;
import objects.Unit;

public class PrintStatement extends MyStatement {

	public PrintStatement(MyExpression value) throws NullPointerException {
		super();
		this.setValue(value);
	}
	
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
	public void run(Unit unit) throws NullPointerException {
		System.out.println(this.getValue().evaluate(unit));
		this.setExecuted(true);
	}

}


//public class PrintStatement extends MyStatement {
//
//	public PrintStatement(MyExpression<?,?> value) throws NullPointerException {
//		super();
//		this.setValue(value);
//	}
//	
//	public MyExpression<?,?> getValue() {
//		return this.value;
//	}
//	
//	private void setValue(MyExpression<?,?> value) throws NullPointerException {
//		if(value == null)
//			throw new NullPointerException();
//		this.value = value;
//	}
//	
//	private MyExpression<?,?> value;
//
//	@Override
//	public void run(Unit unit) throws NullPointerException {
//		System.out.println(this.getValue().evaluate(unit));
//		this.setExecuted(true);
//	}
//
//}

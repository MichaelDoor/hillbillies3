package statement;

import objects.Unit;

public abstract class MyStatement {

	public MyStatement() {
	}
	
	public abstract void run(Unit unit) throws NullPointerException;

}

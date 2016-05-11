package statement;

import java.util.*;

import objects.Unit;

public class SequenceStatement extends MyStatement {

	public SequenceStatement(List<MyStatement> statements) throws NullPointerException {
		super();
		this.setStatements(statements);
	}
	
	public List<MyStatement> getStatements() {
		return this.statements;
	}
	
	private void setStatements(List<MyStatement> statements) throws NullPointerException {
		if(statements == null)
			throw new NullPointerException();
		this.statements = statements;
	}
	
	private List<MyStatement> statements;
	
	@Override
	public void run(Unit unit) throws NullPointerException {
		for(MyStatement statement : this.getStatements())
			statement.run(unit);
	}
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException {
		this.setExecuted(true);
		for(MyStatement statement : this.getStatements()){
			if(! statement.isExecuted(unit))
				this.setExecuted(false);
		}
		return this.isExecuted(unit);
	}
	
	@Override
	public void rollback() {
		super.rollback();
		for(MyStatement statement : this.getStatements())
			statement.rollback();
	}

}

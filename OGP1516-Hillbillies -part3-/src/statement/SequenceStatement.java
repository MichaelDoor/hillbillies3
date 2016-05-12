package statement;

import java.util.*;

import objects.Unit;

public class SequenceStatement extends MyStatement {

	public SequenceStatement(List<MyStatement> statements) throws NullPointerException {
		super();
		this.setStatements(statements);
		this.setIterator(this.getStatements().iterator());
		this.setLastStatement(null);
		if(this.getStatements().isEmpty())
			this.setExecuted(true);
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
		try{
			if((this.getLastStatement() == null) || (this.getLastStatement().isExecuted(unit))){
				MyStatement temp = this.getIterator().next();
				temp.run(unit);
				this.setLastStatement(temp);
			}
		}
		catch (NoSuchElementException exc){
			
		}
	}
	
	@Override
	public boolean isExecuted(Unit unit) throws NullPointerException {
		if(this.getExecuted())
			return this.getExecuted();
		if((! this.getIterator().hasNext())){
			if(this.getLastStatement().isExecuted(unit))
				this.setExecuted(true);
		}
		return this.getExecuted();
	}
	
	@Override
	public void rollback() {
		super.rollback();
		for(MyStatement statement : this.getStatements())
			statement.rollback();
		this.setIterator(this.getStatements().iterator());
	}
	
	public Iterator<MyStatement> getIterator() {
		return this.iterator;
	}
	
	private void setIterator(Iterator<MyStatement> iterator) throws NullPointerException {
		if(iterator == null)
			throw new NullPointerException();
		this.iterator = iterator;
	}
	
	private Iterator<MyStatement> iterator;
	
	/**
	 * Return the last statement that was executed.
	 */
	public MyStatement getLastStatement() {
		return this.lastStatement;
	}
	
	private void setLastStatement(MyStatement statement) {
		this.lastStatement = statement;
	}
	
	/**
	 * Variable storing the last statement that was executed.
	 */
	private MyStatement lastStatement;

}

package hillbillies.part3.programs;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.PositionVector;
import hillbillies.model.expression.*;
import hillbillies.model.expression.booleans.*;
import hillbillies.model.expression.booleans.constant.*;
import hillbillies.model.expression.booleans.isSomething.cube.*;
import hillbillies.model.expression.booleans.isSomething.unit.*;
import hillbillies.model.expression.booleans.operand.binary.*;
import hillbillies.model.expression.booleans.operand.mono.*;
import hillbillies.model.expression.position.*;
import hillbillies.model.expression.unit.*;
import hillbillies.model.statement.*;
import hillbillies.model.statement.action.*;
import hillbillies.model.Task;



public class TaskFactory implements ITaskFactory<MyExpression, MyStatement, Task> {

	public TaskFactory() {
	}

	@Override
	public List<Task> createTasks(String name, int priority, MyStatement activity, List<int[]> selectedCubes) {
		Task task = new Task(name, priority, activity);
		List<Task> result = new ArrayList<>();
		result.add(task);
		return result;
	}

	@Override
	public MyStatement createAssignment(String variableName, MyExpression value, SourceLocation sourceLocation) {
		
		return (MyStatement) new AssignmentStatement<>(variableName, value);
	}

	@Override
	public MyStatement createWhile(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		return new WhileStatement((BooleanExpression<?>) condition, body);
	}

	@Override
	public MyStatement createIf(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation sourceLocation) {
		return (MyStatement) new IfStatement((BooleanExpression<?>) condition, ifBody, elseBody);
	}
	
	// don't have to implement
	@Override
	public MyStatement createBreak(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public MyStatement createPrint(MyExpression value, SourceLocation sourceLocation) {
		return (MyStatement) new PrintStatement(value);
	}

	@Override
	public MyStatement createSequence(List<MyStatement> statements, SourceLocation sourceLocation) {
		return (MyStatement) new SequenceStatement(statements);
	}

	@Override
	public MyStatement createMoveTo(MyExpression position, SourceLocation sourceLocation) {
		return (MyStatement) new MoveToStatement(position);
	}

	@Override
	public MyStatement createWork(MyExpression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return (MyStatement) new WorkStatement(position);
	}

	@Override
	public MyStatement createFollow(MyExpression unit, SourceLocation sourceLocation) {
		return (MyStatement) new FollowStatement(unit);
	}

	@Override
	public MyStatement createAttack(MyExpression unit, SourceLocation sourceLocation) {
		return (MyStatement) (new AttackStatement(unit));
	}

	@Override
	public MyExpression createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVariableExpression<>(variableName);
	}

	@Override
	public MyExpression createIsSolid(MyExpression position, SourceLocation sourceLocation) {
		return new IsSolidExpression((PositionExpression<?>) position);
	}

	@Override
	public MyExpression createIsPassable(MyExpression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IsPassableExpression((PositionExpression<?>) position);
	}

	@Override
	public MyExpression createIsFriend(MyExpression unit, SourceLocation sourceLocation) {
		return new IsFriendExpression((UnitExpression) unit);
	}

	@Override
	public MyExpression createIsEnemy(MyExpression unit, SourceLocation sourceLocation) {
		return new IsEnemyExpression((UnitExpression) unit);
	}

	@Override
	public MyExpression createIsAlive(MyExpression unit, SourceLocation sourceLocation) {
		return new IsAliveExpression((UnitExpression) unit);
	}

	@Override
	public MyExpression createCarriesItem(MyExpression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new CarriesItemExpression((UnitExpression) unit);
	}

	@Override
	public MyExpression createNot(MyExpression expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new NotExpression((BooleanExpression<?>)expression);
	}

	@Override
	public MyExpression createAnd(MyExpression left, MyExpression right, SourceLocation sourceLocation) {
		List<BooleanExpression<?>> expressions = new ArrayList<>();
		expressions.add((BooleanExpression<?>) left);
		expressions.add((BooleanExpression<?>) right);
		return new AndExpression(expressions);
	}

	@Override
	public MyExpression createOr(MyExpression left, MyExpression right, SourceLocation sourceLocation) {
		List<BooleanExpression<?>> expressions = new ArrayList<>();
		expressions.add((BooleanExpression<?>) left);
		expressions.add((BooleanExpression<?>) right);
		return (new OrExpression(expressions));
	}

	@Override
	public MyExpression createHerePosition(SourceLocation sourceLocation) {
		return new HereExpression();
	}

	@Override
	public MyExpression createLogPosition(SourceLocation sourceLocation) {
		return new LogExpression();
	}

	@Override
	public MyExpression createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderExpression();
	}

	@Override
	public MyExpression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopExpression();
	}
	
	// does not need to be implemented
	@Override
	public MyExpression createSelectedPosition(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public MyExpression createNextToPosition(MyExpression position, SourceLocation sourceLocation) {
		return new NextToExpression((PositionExpression<?>) position);
	}

	@Override
	public MyExpression createPositionOf(MyExpression unit, SourceLocation sourceLocation) {
		return new PositionOfExpression<UnitExpression>((UnitExpression) unit);
	}

	@Override
	public MyExpression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		PositionVector position = new PositionVector(x,y,z);
		return new LiteralPositionExpression(position);
	}

	@Override
	public MyExpression createThis(SourceLocation sourceLocation) {
		return new ThisExpression();
	}

	@Override
	public MyExpression createFriend(SourceLocation sourceLocation) {
		return new FriendExpression();
	}

	@Override
	public MyExpression createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression();
	}

	@Override
	public MyExpression createAny(SourceLocation sourceLocation) {
		return new AnyExpression();
	}

	@Override
	public MyExpression createTrue(SourceLocation sourceLocation) {
		return new TrueExpression();
	}

	@Override
	public MyExpression createFalse(SourceLocation sourceLocation) {
		return new FalseExpression();
	}
}

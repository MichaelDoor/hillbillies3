package hillbillies.part3.programs;

import java.util.ArrayList;
import java.util.List;

import expression.MyExpression;
import expression.booleans.constant.FalseExpression;
import expression.booleans.constant.TrueExpression;
import expression.booleans.isSomething.cube.IsPassableExpression;
import expression.booleans.isSomething.cube.IsSolidExpression;
import expression.booleans.isSomething.unit.CarriesItemExpression;
import expression.booleans.isSomething.unit.IsAliveExpression;
import expression.booleans.isSomething.unit.IsEnemyExpression;
import expression.booleans.isSomething.unit.IsFriendExpression;
import expression.booleans.operand.binary.AndExpression;
import expression.booleans.operand.binary.OrExpression;
import expression.booleans.operand.mono.NotExpression;
import expression.position.BoulderExpression;
import expression.position.HereExpression;
import expression.position.LiteralPositionExpression;
import expression.position.LogExpression;
import expression.position.NextToExpression;
import expression.position.PositionExpression;
import expression.position.PositionOfExpression;
import expression.position.WorkshopExpression;
import expression.unit.AnyExpression;
import expression.unit.EnemyExpression;
import expression.unit.FriendExpression;
import expression.unit.ThisExpression;
import expression.unit.UnitExpression;
import hillbillies.model.MyStatement;
import hillbillies.model.PositionVector;
import hillbillies.model.Task;
import javafx.beans.binding.BooleanExpression;
import statement.AssignmentStatement;
import statement.IfStatement;
import statement.PrintStatement;
import statement.SequenceStatement;
import statement.WhileStatement;
import statement.action.AttackStatement;
import statement.action.FollowStatement;
import statement.action.MoveToStatement;
import statement.action.WorkStatement;

public class TaskFactory implements ITaskFactory<MyExpression<?,?>, MyStatement, Task> {

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
		return (new AssignmentStatement(variableName, value));
	}

	@Override
	public MyStatement createWhile(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		return new WhileStatement(condition, body);
	}

	@Override
	public MyStatement createIf(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation sourceLocation) {
		return (MyStatement) new IfStatement(condition, ifBody, elseBody);
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
		return (MyStatement) new MoveToStatement((PositionExpression) position);
	}

	@Override
	public MyStatement createWork(MyExpression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return (MyStatement) new WorkStatement((PositionExpression<?>) position);
	}

	@Override
	public MyStatement createFollow(MyExpression unit, SourceLocation sourceLocation) {
		return (MyStatement) new FollowStatement((UnitExpression) unit);
	}

	@Override
	public MyStatement createAttack(MyExpression unit, SourceLocation sourceLocation) {
		return (MyStatement) (new AttackStatement((UnitExpression) unit));
	}

	@Override
	public MyExpression createReadVariable(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;?
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
		return new NotExpression((BooleanExpression) expression);
	}

	@Override
	public MyExpression createAnd(MyExpression left, MyExpression right, SourceLocation sourceLocation) {
		List<BooleanExpression> expressions = new ArrayList<>();
		expressions.add((BooleanExpression) left);
		expressions.add((BooleanExpression) right);
		return new AndExpression(expressions);
	}

	@Override
	public MyExpression createOr(MyExpression left, MyExpression right, SourceLocation sourceLocation) {
		List<BooleanExpression> expressions = new ArrayList<>();
		expressions.add((BooleanExpression) left);
		expressions.add((BooleanExpression) right);
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
		return new LiteralPositionExpression(new PositionVector(x,y,z));
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

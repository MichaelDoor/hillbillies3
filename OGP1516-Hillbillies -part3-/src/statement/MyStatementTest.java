package statement;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import faction.Faction;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import objects.Unit;
import position.PositionVector;
import statement.action.*;
import world.World;
import expression.booleans.constant.*;
import expression.booleans.isSomething.cube.IsSolidExpression;
import expression.position.BoulderExpression;
import expression.position.HereExpression;
import expression.position.LiteralPositionExpression;
import expression.position.NextToExpression;
import expression.unit.EnemyExpression;
import expression.unit.ThisExpression;

public class MyStatementTest {

	private World testWorld;
	private Unit unit;
	private Unit ally;
	private Unit enemy;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 1;
		matrix[0][1][0] = 1; matrix[1][1][0] = 0; matrix[2][1][0] = 2;
		matrix[0][2][0] = 2; matrix[1][2][0] = 0; matrix[2][2][0] = 3;
		testWorld =  new World(matrix, new DefaultTerrainChangeListener());
		testWorld.advanceTime(1);
		
		// create a boulder
		testWorld.collapse(new PositionVector(0,1,0));
		// create a log
		testWorld.collapse(new PositionVector(0,2,0));
		
		// create the unit to be used and an enemy and ally of it
		unit = new Unit(new PositionVector(0,0,0), "Ikke", new Faction());
		testWorld.addUnit(unit);
		ally = new Unit(new PositionVector(1,0,0), "Ikke", unit.getFaction());
		enemy = new Unit(new PositionVector(1,1,0), "Ikke", new Faction());
		testWorld.addUnit(ally);
		testWorld.addUnit(enemy);
		testWorld.advanceTime(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void attackStatement() {
		MyStatement temp = new AttackStatement(new EnemyExpression());
		temp.run(unit);
		testWorld.advanceTime(0.05);
		assertEquals(true, unit.getActivityStatus().equals("attack"));
		assertEquals(true, unit.getTarget().equals(enemy));
		testWorld.advanceTime(5);
		assertEquals(true, unit.getActivityStatus().equals("default"));
		assertEquals(true, temp.isExecuted(unit));
	}
	
	@Test
	public void moveToStatement() {
		MyStatement temp = new MoveToStatement(new BoulderExpression());
		temp.run(unit);
		testWorld.advanceTime(0.2);
		assertEquals(true, unit.getActivityStatus().equals("move"));
		assertEquals(true, unit.getDestination().equals(PositionVector.centrePosition(new PositionVector(0,1,0))));
		testWorld.advanceTime(5);
		assertEquals(true, unit.getActivityStatus().equals("default"));
		assertEquals(true, unit.getCubePositionVector().equals(new PositionVector(0,1,0)));
		assertEquals(true, temp.isExecuted(unit));
	}
	
	@Test
	public void followStatement() {
		enemy.moveTo(new PositionVector(1,2,0));
		testWorld.advanceTime(3);
		MyStatement temp = new FollowStatement(new EnemyExpression());
		temp.run(unit);
		testWorld.advanceTime(5);
		assertEquals(true, (unit.getWorld().areAdjacents(unit.getCubePositionVector(), enemy.getCubePositionVector())) 
				|| (unit.getCubePositionVector().equals(enemy.getCubePositionVector())));
		assertEquals(true, unit.isIdle());
		assertEquals(true, temp.isExecuted(unit));
	}
	
	@Test
	public void workStatement() {
		MyStatement temp = new WorkStatement(new NextToExpression(new HereExpression()));
		temp.run(unit);
		testWorld.advanceTime(0.2);
		assertEquals(true, unit.getActivityStatus().equals("work"));
		testWorld.advanceTime(20);
		assertEquals(true, unit.isIdle());
		assertEquals(true, temp.isExecuted(unit));
	}
	
	@Test
	public void assignmentStatement() {
		AssignmentStatement<Unit> temp = new AssignmentStatement<>("w", new ThisExpression());
		testWorld.advanceTime(5);
		assertEquals(true, temp.evaluate(unit).equals(unit));
	}
	
	@Test
	public void ifStatement() {
		TrueExpression flag1 = new TrueExpression();
		FalseExpression flag2= new FalseExpression();
		PositionVector workPosition1 = new PositionVector(1,0,0);
		PositionVector workPosition2 = new PositionVector(0,1,0);
		MyStatement ifBody = new WorkStatement(new LiteralPositionExpression(workPosition1));
		MyStatement elseBody = new WorkStatement(new LiteralPositionExpression(workPosition2));
		
		MyStatement temp1 = new IfStatement(flag1, ifBody, elseBody);
		MyStatement temp2 = new IfStatement(flag2, ifBody, elseBody);
		
		temp1.run(unit);
		testWorld.advanceTime(0.2);
		assertEquals(true, workPosition1.equals(PositionVector.getIntegerPositionVector(unit.getWorkPosition())));
		testWorld.advanceTime(20);
		assertEquals(true, temp1.isExecuted(unit));
		
		temp2.run(unit);
		testWorld.advanceTime(0.2);
		assertEquals(true, workPosition2.equals(PositionVector.getIntegerPositionVector(unit.getWorkPosition())));
		testWorld.advanceTime(20);
		assertEquals(true, temp2.isExecuted(unit));
	}
	
	@Test
	public void printStatement() {
		MyStatement temp = new PrintStatement(new BoulderExpression());
		temp.run(unit);
	}
	
	@Test
	public void sequenceStatement() {
		List<MyStatement> statements = new ArrayList<MyStatement>();
		
		TrueExpression flag1 = new TrueExpression();
		FalseExpression flag2= new FalseExpression();
		PositionVector workPosition1 = new PositionVector(1,0,0);
		PositionVector workPosition2 = new PositionVector(0,1,0);
		MyStatement ifBody = new WorkStatement(new LiteralPositionExpression(workPosition1));
		MyStatement elseBody = new WorkStatement(new LiteralPositionExpression(workPosition2));
		
		MyStatement temp1 = new IfStatement(flag1, ifBody, elseBody);
		MyStatement temp2 = new IfStatement(flag2, ifBody, elseBody);
		
		statements.add(temp1);
		statements.add(temp2);
		
		SequenceStatement temp = new SequenceStatement(statements);
		
		Iterator<MyStatement> i = temp.getStatements().iterator();
		
		i.next().run(unit);
		testWorld.advanceTime(0.2);
		assertEquals(true, workPosition1.equals(PositionVector.getIntegerPositionVector(unit.getWorkPosition())));
		testWorld.advanceTime(20);
		assertEquals(true, temp1.isExecuted(unit));
		
		i.next().run(unit);
		testWorld.advanceTime(0.2);
		assertEquals(true, workPosition2.equals(PositionVector.getIntegerPositionVector(unit.getWorkPosition())));
		testWorld.advanceTime(20);
		assertEquals(true, temp2.isExecuted(unit));
	}
	
	@Test
	public void whileStatement() {
		PositionVector flagPosition = new PositionVector(2,0,0);
		IsSolidExpression condition = new IsSolidExpression(new LiteralPositionExpression(flagPosition));
		MyStatement body = new WorkStatement(new LiteralPositionExpression(new PositionVector(1,0,0)));
		MyStatement temp = new WhileStatement(condition, body);
		
		temp.run(unit);
		testWorld.advanceTime(0.2);
		assertEquals(true, unit.getActivityStatus().equals("work"));
		testWorld.advanceTime(20);
		assertEquals(false,  temp.isExecuted(unit));
		
		temp.run(unit);
		testWorld.advanceTime(0.2);
		assertEquals(true, unit.getActivityStatus().equals("work"));
		testWorld.collapse(flagPosition);
		testWorld.advanceTime(20);
		assertEquals(true,  temp.isExecuted(unit));
	}

}

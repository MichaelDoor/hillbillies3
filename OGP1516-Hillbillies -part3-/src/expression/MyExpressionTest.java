package expression;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import expression.booleans.constant.*;
import expression.booleans.isSomething.cube.IsPassableExpression;
import expression.booleans.isSomething.cube.IsSolidExpression;
import expression.booleans.isSomething.unit.CarriesItemExpression;
import expression.booleans.isSomething.unit.IsAliveExpression;
import expression.booleans.isSomething.unit.IsEnemyExpression;
import expression.booleans.isSomething.unit.IsFriendExpression;
import expression.booleans.operand.binary.AndExpression;
import expression.booleans.operand.binary.OrExpression;
import expression.booleans.operand.mono.NotExpression;
import expression.booleans.operand.mono.PrecedenceExpression;
import expression.position.*;
import expression.unit.*;
import faction.Faction;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import objects.Unit;
import position.PositionVector;
import world.World;

public class MyExpressionTest {
	
	private static World testWorld;
	private static Unit unit;
	private static Unit ally;
	private static Unit enemy;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	// UnitExpression tests

	@Test
	public void anyUnit() {
		UnitExpression temp = new AnyExpression();
		Unit result = temp.evaluate(unit);
		assertEquals(true, ((result.equals(enemy)) || (result.equals(ally))));
	}
	
	@Test
	public void enemyUnit() {
		UnitExpression temp = new EnemyExpression();
		Unit result = temp.evaluate(unit);
		assertEquals(true, result.equals(enemy));
	}
	
	@Test
	public void friendUnit() {
		UnitExpression temp = new FriendExpression();
		Unit result = temp.evaluate(unit);
		assertEquals(true, result.equals(ally));
	}
	
	@Test
	public void thisUnit() {
		UnitExpression temp = new ThisExpression();
		Unit result = temp.evaluate(unit);
		assertEquals(true, result.equals(unit));
	}
	
	
	// PositionExpression tests
	
	@Test
	public void herePosition() {
		HereExpression temp = new HereExpression();
		PositionVector result = temp.evaluate(unit);
		assertEquals(true, result.equals(unit.getCubePositionVector()));
	}
	
	@Test
	public void logPosition() {
		LogExpression temp = new LogExpression();
		PositionVector result = temp.evaluate(unit);
		assertEquals(true, result.equals(new PositionVector(0,2,0)));
	}
	
	@Test
	public void boulderPosition() {
		BoulderExpression temp = new BoulderExpression();
		PositionVector result = temp.evaluate(unit);
		assertEquals(true, result.equals(new PositionVector(0,1,0)));
	}
	
	@Test
	public void workshopPosition() {
		WorkshopExpression temp = new WorkshopExpression();
		PositionVector result = temp.evaluate(unit);
		assertEquals(true, result.equals(new PositionVector(2,2,0)));
	}
	
	@Test
	public void literalPosition() {
		PositionVector position = new PositionVector(1,1,1);
		LiteralPositionExpression temp = new LiteralPositionExpression(position);
		PositionVector result = temp.evaluate(unit);
		assertEquals(true, result.equals(position));
	}
	
	@Test
	public void nextToPosition() {
		LiteralPositionExpression position = new LiteralPositionExpression(new PositionVector(0,0,0));
		NextToExpression temp = new NextToExpression(position);
		PositionVector result = temp.evaluate(unit);
		PositionVector option1 = new PositionVector(1,0,0);
		PositionVector option2 = new PositionVector(1,1,0);
		PositionVector option3 = new PositionVector(0,1,0);
		assertEquals(true, result.equals(option1) || result.equals(option2) || result.equals(option3));
	}
	
	@Test
	public void positionOfUnit() {
		PositionOfExpression<ThisExpression> temp = new PositionOfExpression<>(new ThisExpression());
		PositionVector result = temp.evaluate(unit);
		assertEquals(true, result.equals(unit.getCubePositionVector()));
	}
	
	
	// BooleanExpression tests
	
	@Test
	public void trueBoolean() {
		TrueExpression temp = new TrueExpression();
		boolean result = temp.evaluate(unit);
		assertEquals(true, result);
	}
	
	@Test
	public void falseBoolean() {
		FalseExpression temp = new FalseExpression();
		boolean result = temp.evaluate(unit);
		assertEquals(false, result);
	}
	
	@Test
	public void precedenceBoolean() {
		PrecedenceExpression temp = new PrecedenceExpression(new TrueExpression());
		boolean result = temp.evaluate(unit);
		assertEquals(true, result);
	}
	
	@Test
	public void notExpression() {
		NotExpression temp = new NotExpression(new TrueExpression());
		boolean result = temp.evaluate(unit);
		assertEquals(false, result);
	}
	
	@Test 
	public void orExpression() {
		List<BooleanConstantExpression> tempList = new ArrayList<BooleanConstantExpression>();
		tempList.add(new TrueExpression());
		tempList.add(new FalseExpression());
		OrExpression temp = new OrExpression(tempList);
		boolean result = temp.evaluate(unit);
		assertEquals(true, result);
	}
	
	@Test
	public void andExpression() {
		List<BooleanConstantExpression> tempList = new ArrayList<BooleanConstantExpression>();
		tempList.add(new TrueExpression());
		tempList.add(new FalseExpression());
		AndExpression temp = new AndExpression(tempList);
		boolean result = temp.evaluate(unit);
		assertEquals(false, result);
	}
	
	@Test
	public void isSolidPosition() {
		IsSolidExpression temp = new IsSolidExpression(new LiteralPositionExpression(new PositionVector(2,0,0)));
		boolean result = temp.evaluate(unit);
		assertEquals(true, result);
	}
	
	@Test
	public void isPassablePosition() {
		IsPassableExpression temp = new IsPassableExpression(new LiteralPositionExpression(new PositionVector(2, 2, 0)));
		boolean result = temp.evaluate(unit);
		assertEquals(true, result);
	}
	
	@Test
	public void isFriendUnit() {
		IsFriendExpression temp = new IsFriendExpression(new FriendExpression());
		IsFriendExpression temp2 = new IsFriendExpression(new EnemyExpression());
		boolean result1 = temp.evaluate(unit);
		boolean result2 = temp2.evaluate(unit);
		assertEquals(true, result1);
		assertEquals(false, result2);
	}
	
	@Test
	public void isEnemyUnit() {
		IsEnemyExpression temp = new IsEnemyExpression(new EnemyExpression());
		IsEnemyExpression temp2 = new IsEnemyExpression(new FriendExpression());
		boolean result1 = temp.evaluate(unit);
		boolean result2 = temp2.evaluate(unit);
		assertEquals(true, result1);
		assertEquals(false, result2);
	}
	
	@Test
	public void isAliveUnit() {
		IsAliveExpression temp = new IsAliveExpression(new ThisExpression());
		boolean result = temp.evaluate(unit);
		assertEquals(true, result);
	}
	
	@Test
	public void carriesItemUnit() {
		CarriesItemExpression temp = new CarriesItemExpression(new ThisExpression());
		boolean result = temp.evaluate(unit);
		assertEquals(false, result);
	}
	
	@Test
	public void myExpressionType() {
		MyExpression<UnitExpression,Boolean> temp = new CarriesItemExpression(new ThisExpression());
		boolean result = temp.evaluate(unit);
		assertEquals(false, result);
	}

}

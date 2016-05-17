package task;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import expression.position.BoulderExpression;
import expression.position.LiteralPositionExpression;
import faction.Faction;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import objects.Unit;
import position.PositionVector;
import scheduler.Scheduler;
import statement.MyStatement;
import statement.SequenceStatement;
import statement.action.MoveToStatement;
import statement.action.WorkStatement;
import world.World;

public class TaskTest {
	
	private World testWorld;
	private Unit unit;
	private Unit ally;
	private Unit enemy;
	private MyStatement testActivity;
	private Task testTask;
	private Task testTask2;
	private Faction testFaction;
	private Scheduler testScheduler;

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
		
		this.testFaction = new Faction();
		
		// create the unit to be used and an enemy and ally of it
		unit = new Unit(new PositionVector(0,0,0), "Ikke", testFaction);
		testWorld.addUnit(unit);
		ally = new Unit(new PositionVector(1,0,0), "Ikke", testFaction);
		enemy = new Unit(new PositionVector(1,1,0), "Ikke", new Faction());
		testWorld.addUnit(ally);
		testWorld.addUnit(enemy);
		testWorld.advanceTime(2);
		
		List<MyStatement> statements = new ArrayList<>();
		statements.add(new WorkStatement(new BoulderExpression()));
		statements.add(new MoveToStatement(new LiteralPositionExpression(new PositionVector(0,1,0))));
		this.testActivity = new SequenceStatement(statements);
		testTask = new Task("test", 101, testActivity);
		this.testScheduler = new Scheduler(testFaction);
		testScheduler.addTask(testTask);
		unit.startDefaultBehaviour();
		testTask.setExecutor(unit);
		
		this.testTask2 = new Task("test", 100, new WorkStatement(new LiteralPositionExpression(new PositionVector(0,1,0))));
		testScheduler.addTask(testTask2);
		testTask2.setExecutor(unit);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// normally you can't give a unit more than one task
	@Test
	public void createTask() {
		Task temp = new Task("test", 100, testActivity);
		assertEquals(true, temp.getName().equals("test"));
		assertEquals(true, temp.getPriority() == 100);
		assertEquals(true, temp.getActivity().equals(testActivity));
		
		Scheduler scheduler = new Scheduler(new Faction());
		temp.addScheduler(scheduler);
		assertEquals(true, temp.getSchedulerSet().contains(scheduler));
		assertEquals(true, temp.getSchedulerSet().size() == 1);
		
		temp.setSpecificUnit(unit);
		assertEquals(true, temp.getSpecificUnit().equals(unit));
		
		temp.setExecutor(unit);
		assertEquals(true, temp.getExecutor().equals(unit));
	}
	
	@Test
	public void decreasePriority() {
		int old = testTask.getPriority();
		testTask.reducePriority();
		assertEquals(true, testTask.getPriority() < old);
	}
	
	@Test
	public void execute() {
		testTask2.setExecutor(null);
		unit.startDefaultBehaviour();
		testWorld.advanceTime(0.2);
		assertEquals(true, unit.getActivityStatus().equals("work"));
		double time = unit.getWorkTime();
		
		testWorld.advanceTime(time);
		assertEquals(true, testTask2.getActivity().isExecuted(unit));
		testTask2.execute();
		assertEquals(true, testTask2.isTerminated());
	}
	
	@Test
	public void interrupt() {
		testTask2.setExecutor(null);
		unit.startDefaultBehaviour();
		testWorld.advanceTime(2);
		assertEquals(true, unit.getActivityStatus().equals("work"));
		
		testTask2.interrupt();
		assertEquals(true, testTask2.getPriority() < 100);
		assertEquals(true, testTask2.getExecutor() == null);
	}
	
	

}

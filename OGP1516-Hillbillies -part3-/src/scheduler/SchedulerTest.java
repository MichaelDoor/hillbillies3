package scheduler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import expression.position.BoulderExpression;
import expression.position.HereExpression;
import expression.position.LiteralPositionExpression;
import faction.Faction;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import objects.Unit;
import position.PositionVector;
import statement.AssignmentStatement;
import statement.MyStatement;
import statement.SequenceStatement;
import statement.action.MoveToStatement;
import statement.action.WorkStatement;
import task.Task;
import world.World;

public class SchedulerTest {
	
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
		
		// create test faction
		this.testFaction = new Faction();
		
		// create the unit to be used and an enemy and ally of it
		unit = new Unit(new PositionVector(0,0,0), "Ikke", testFaction);
		testWorld.addUnit(unit);
		ally = new Unit(new PositionVector(1,0,0), "Ikke", testFaction);
		enemy = new Unit(new PositionVector(1,1,0), "Ikke", new Faction());
		testWorld.addUnit(ally);
		testWorld.addUnit(enemy);
		testWorld.advanceTime(2);
		
		// create test scheduler
		this.testScheduler = new Scheduler(testFaction);
		
		// create test task 1
		List<MyStatement> statements = new ArrayList<>();
		statements.add(new WorkStatement(new BoulderExpression()));
		statements.add(new MoveToStatement(new LiteralPositionExpression(new PositionVector(0,1,0))));
		this.testActivity = new SequenceStatement(statements);
		testTask = new Task("test", 101, testActivity);
		testScheduler.addTask(testTask);
		
		// create test task 2
		this.testTask2 = new Task("test", 100, new WorkStatement(new LiteralPositionExpression(new PositionVector(0,1,0))));
		testScheduler.addTask(testTask2);;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void prioritySort() {
		Task task1 = new Task("1", -10, new AssignmentStatement<>("w", new HereExpression()));
		Task task2 = new Task("1", -100, new AssignmentStatement<>("w", new HereExpression()));
		Task task3 = new Task("1", 20, new AssignmentStatement<>("w", new HereExpression()));
		List<Task> taskList = new ArrayList<Task>();
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task1);
		Scheduler.prioritySort(taskList);
		assertEquals(true, taskList.get(0).equals(task3));
		assertEquals(true, taskList.get(1).equals(task1));
	}
	
	@Test
	public void hasWork() {
		unit.startDefaultBehaviour();
		assertEquals(true,testFaction.hasWork(unit));
	}
	
	@Test
	public void giveWork() {
		unit.startDefaultBehaviour();
		testScheduler.giveWork(unit);
		assertEquals(true, unit.getActivityStatus().equals("work"));
		assertEquals(true, testScheduler.hasAsWorker(unit));
		assertEquals(true, testScheduler.getTaskOf(unit).equals(testTask));
		
		ally.startDefaultBehaviour();
		testScheduler.giveWork(ally);
		assertEquals(true, ally.getActivityStatus().equals("work"));
		assertEquals(true, testScheduler.hasAsWorker(ally));
		assertEquals(true, testScheduler.getTaskOf(ally).equals(testTask2));
		
		Unit temp = new Unit(new PositionVector(0,2,0), "Yoyo", testFaction);
		testWorld.addUnit(temp);
		temp.startDefaultBehaviour();
		assertEquals(false, testScheduler.hasWork(temp));
	}
	
	// also tests tasks spread over multiple schedulers.
	// also tests if units get tasks assigned automatically
	@Test
	public void finishTasks() {
		Faction temp = new Faction();
		Scheduler tempS = new Scheduler(temp);
		tempS.addTask(testTask);
		tempS.addTask(testTask2);
		
		unit.startDefaultBehaviour();
		testWorld.advanceTime(1);
		assertEquals(true, unit.getActivityStatus().equals("work"));
		assertEquals(true, testScheduler.hasAsWorker(unit));
		assertEquals(true, testScheduler.getTaskOf(unit).equals(testTask));
		// finish work activity of task.
		testWorld.advanceTime(unit.getWorkTime());
		assertEquals(true, unit.isIdle());
		testWorld.advanceTime(30);
		assertEquals(true, testScheduler.getNbTasks() == 0);
		assertEquals(true, tempS.getNbTasks() == 0);
	}
	
	// test scheduler delay
	//catch exceptions when things are inaccessible and interrupt the task.

}

package scheduler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import expression.position.HereExpression;
import statement.AssignmentStatement;
import task.Task;

public class SchedulerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	@Test
	public void test() {
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

}

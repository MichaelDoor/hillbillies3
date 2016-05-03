package objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import position.PositionVector;

public class LogTest {

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
	public void constructor_LegalCase() {
		PositionVector position = new PositionVector(0,0,0);
		Log log = new Log(position);
		assertEquals(true, log.getUnitPosition().equals(position));
		assertEquals(true, log.getWeight() <= 50);
		assertEquals(true, log.getWeight() >= 10);
	}


}

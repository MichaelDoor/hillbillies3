package objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import position.PositionVector;

public class BoulderTest {

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
		Boulder boulder = new Boulder(position);
		assertEquals(true, boulder.getUnitPosition().equals(position));
		assertEquals(true, boulder.getWeight() <= 50);
		assertEquals(true, boulder.getWeight() >= 10);
	}
	

}

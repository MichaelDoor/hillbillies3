package faction;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import objects.Unit;
import position.PositionVector;

public class FactionTest {
	
	private Faction testFaction;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.testFaction = new Faction();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addUnit_IllegalCase() {
		int i = 0;
		while(i < 52){
			Unit unit = (new Unit(new PositionVector(0, 0, 0), "Ikke", new Faction()));
			testFaction.addUnit(unit);
			i++;
		}
		assertEquals(50, testFaction.getUnitSet().size());
	}
}

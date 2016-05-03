package cube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import faction.Faction;
import objects.Boulder;
import objects.Log;
import objects.Unit;
import position.PositionVector;

public class WorkshopTest {
	
	private Workshop testWorkshop;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testWorkshop = new Workshop(new PositionVector(0,0,0));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_LegalCase() {
		PositionVector position = new PositionVector(0,0,0);
		Workshop workshop = new Workshop(position);
		assertEquals(true, workshop.getPosition().equals(position));
		assertEquals(3,workshop.getTerrainType());
		assertEquals(true, workshop.getContent().isEmpty());
	}
	
	@Test
	public void addAsContent_LegalCase() {
		Unit unit = new Unit(testWorkshop.getPosition(), "Ikke", 50, 50, 50, 50, new Faction());
		Log log = new Log(testWorkshop.getPosition());
		Boulder boulder = new Boulder(testWorkshop.getPosition());
		testWorkshop.addAsContent(unit);
		assertEquals(true, testWorkshop.hasAsContent(unit));
		assertEquals(true, testWorkshop.getContent().size() == 1);
		assertEquals(true, testWorkshop.hasProperContent());
		testWorkshop.addAsContent(log);
		assertEquals(true, testWorkshop.hasAsContent(log));
		assertEquals(true, testWorkshop.getContent().size() == 2);
		assertEquals(true, testWorkshop.hasProperContent());
		testWorkshop.addAsContent(boulder);
		assertEquals(true, testWorkshop.hasAsContent(boulder));
		assertEquals(true, testWorkshop.getContent().size() == 3);
		assertEquals(true, testWorkshop.hasProperContent());
		testWorkshop.removeAsContent(unit);
		assertEquals(false, testWorkshop.hasAsContent(unit));
		assertEquals(true, testWorkshop.getContent().size() == 2);
		assertEquals(true, testWorkshop.hasProperContent());
	}
	

}

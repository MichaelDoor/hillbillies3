package cube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import faction.Faction;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import objects.*;
import objects.Unit;
import position.PositionVector;
import world.World;

public class AirTest {
	
	private Air testAir;
	private World testWorld;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testAir = new Air(new PositionVector(6,6,6));
		int nbX = 10;
		int nbY = 20;
		int nbZ = 30;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[6][5][5] = 1;
		matrix[5][5][5] = 1;
		matrix[4][5][5] = 1;
		matrix[3][5][5] = 1;
		matrix[2][5][5] = 1;
		matrix[1][5][5] = 1;
		matrix[0][5][5] = 1;
		this.testWorld = new World(matrix, new DefaultTerrainChangeListener());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_LegalCase() {
		PositionVector position = new PositionVector(0,0,0);
		Air air = new Air(position);
		assertEquals(true, air.getPosition().equals(position));
		assertEquals(0,air.getTerrainType());
		assertEquals(true, air.getContent().isEmpty());
	}
	
	@Test
	public void addAsContent_LegalCase() {
		Unit unit = new Unit(testAir.getPosition(), "Ikke", 50, 50, 50, 50, new Faction());
		Log log = new Log(testAir.getPosition());
		Boulder boulder = new Boulder(testAir.getPosition());
		testAir.addAsContent(unit);
		assertEquals(true, testAir.hasAsContent(unit));
		assertEquals(true, testAir.getContent().size() == 1);
		assertEquals(true, testAir.hasProperContent());
		testAir.addAsContent(log);
		assertEquals(true, testAir.hasAsContent(log));
		assertEquals(true, testAir.getContent().size() == 2);
		assertEquals(true, testAir.hasProperContent());
		testAir.addAsContent(boulder);
		assertEquals(true, testAir.hasAsContent(boulder));
		assertEquals(true, testAir.getContent().size() == 3);
		assertEquals(true, testAir.hasProperContent());
		testAir.removeAsContent(unit);
		assertEquals(false, testAir.hasAsContent(unit));
		assertEquals(true, testAir.getContent().size() == 2);
		assertEquals(true, testAir.hasProperContent());
	}
	
	@Test
	public void hasProperContent_LegalCase2() {
		Unit unit = new Unit(testAir.getPosition(), "Ikke", 50, 50, 50, 50, new Faction());
		unit.changeWorld(testWorld);
		testWorld.addUnit(unit);
		assertEquals(true, testAir.hasProperContent());
		unit.moveTo(new PositionVector(6.5,5,6.5));
		unit.advanceTime(0.19);
		unit.advanceTime(0.19);
		unit.advanceTime(0.19);
		unit.advanceTime(0.19);
		unit.advanceTime(0.19);
		unit.advanceTime(0.19);
		unit.advanceTime(0.19);
		assertEquals(true, testAir.hasProperContent());
	}
}

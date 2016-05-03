package objects;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import faction.Faction;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import position.PositionVector;
import world.World;


public class UnitTest {
	
	private Unit tester;
	private Unit target;
	private World testWorld;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tester = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 50, 50, 50, new Faction());
		target = new Unit(new PositionVector(2.0,1.0,1.0), "Ikke", 50, 50, 50, 50, new Faction());
		int nbX = 10;
		int nbY = 20;
		int nbZ = 30;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[5][5][5] = 1;
		matrix[4][5][5] = 1;
		matrix[3][5][5] = 1;
		matrix[2][5][5] = 1;
		matrix[1][5][5] = 1;
		matrix[0][5][5] = 1;
		testWorld = new World(new int[nbX][nbY][nbZ], new DefaultTerrainChangeListener());
		testWorld.addUnit(tester);
		testWorld.addUnit(target);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_LegalCase() throws Exception {
		Unit man = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 51, 52, 55, new Faction());
		assertEquals((man.getUnitPosition()).equals((new PositionVector(1.5,1.5,1.5))), true);
		assertEquals(((man.getName()).equals("Ikke")), true);
		assertEquals(man.getStrength(),50);
		assertEquals(man.getAgility(),51);
		assertEquals(man.getToughness(),52);
		assertEquals(man.getWeight(),55);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void constructor_IllegalName() throws Exception {
		new Unit(new PositionVector(1.0,1.0,1.0), "ikke", 50, 50, 50, 50, new Faction());
	}
	
	@Test
	public void contructor_IllegalStrength() {
		Unit temp = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 101, 50, 50, 50, new Faction());
		assertEquals(temp.getStrength(),100);
	}
	
	@Test
	public void contructor_IllegalAgility() {
		Unit temp = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 20, 50, 50, new Faction());
		assertEquals(temp.getAgility(),25);
	}
	
	@Test
	public void contructor_IllegalToughness() {
		Unit temp = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 50, 300, 50, new Faction());
		assertEquals(temp.getToughness(),100);
	}
	
	@Test
	public void contructor_IllegalWeight() {
		Unit temp = new Unit(new PositionVector(1.0,1.0,1.0), "Ikke", 50, 50, 50, ((50+50)/2)-1, new Faction());
		assertEquals(temp.getWeight(),(temp.getStrength()+temp.getAgility())/2);
	}
	
	@Test
	public void sprintOn() {
		tester.setSprint(true);
		assertEquals(tester.getSprint(),true);
		tester.setSprint(false);
		assertEquals(tester.getSprint(),false);
	}
	
	
	@Test
	public void getBaseSpeed() {
		assertEquals(1.5,tester.getBaseSpeed(),0.00001);
	}
	

	@Test
	public void terminate() {
		tester.terminate();
		assertEquals(true, tester.isTerminated());
	}
}

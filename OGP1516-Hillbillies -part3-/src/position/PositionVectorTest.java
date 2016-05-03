package position;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PositionVectorTest {
	
	/**
	 * Variables referencing to position vectors (1.0,2.0,3.0) and (5.0,6.0,7.0).
	 */
	private static PositionVector vector1,
		vector2;
	
	/**
	 * Set up an immutable test fixture.
	 * @post 	The variable vector1 references a new position vector (1.0, 2.0, 3.0).
	 * @post	The variable vector2 references a new position vector (5.0, 6.0, 7.0).
	 */
	@BeforeClass
	public static void setUpImmutableFixture()  {
		vector1 = new PositionVector(1.0, 2.0, 3.0);
		vector2 = new PositionVector(5.0, 6.0, 7.0);
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
		new PositionVector(1.0,2.0,3.0);
	}
	
	@Test
	public void sum_LegalCase() {
		PositionVector vector3 = PositionVector.sum(vector1, vector2);
		assertEquals(6.0, vector3.getXArgument(),0.000001);
		assertEquals(8.0, vector3.getYArgument(), 0.000001);
		assertEquals(10.0, vector3.getZArgument(), 0.000001);
	}
	
	@Test
	public void multiplyBy_LegalCase() {
		PositionVector vector3 = PositionVector.multiplyBy(3, vector2);
		assertEquals(15.0, vector3.getXArgument(),0.000001);
		assertEquals(18.0, vector3.getYArgument(), 0.000001);
		assertEquals(21.0, vector3.getZArgument(), 0.000001);
	}
	
	@Test
	public void hashCode_MapCollection(){
		PositionVector vector1 = new PositionVector(12,59,86);
		PositionVector vector2 = new PositionVector(12,59,86);
		int hash1 = vector1.hashCode();
		int hash2 = vector2.hashCode();
		assertEquals(true, hash1 == hash2);
		HashMap<PositionVector,Integer> map = new HashMap<PositionVector,Integer>();
		map.put(vector1, 0);
		assertEquals(true, map.containsKey(vector2));
	}

}

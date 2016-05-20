package world;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cube.Cube;
import faction.Faction;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import objects.GameObject;
import objects.Material;
import objects.Unit;
import position.PositionVector;


public class WorldTest {
	
	private World testWorld;
	//private static World world;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		//world = new World(matrix, new DefaultTerrainChangeListener());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
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
		this.testWorld = new World(matrix, new DefaultTerrainChangeListener());
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void constructor_LegalCase() {
//		int nbX = 10;
//		int nbY = 20;
//		int nbZ = 30;
//		int[][][] matrix = new int[nbX][nbY][nbZ];
//		matrix[0][0][0] = 1;
//		matrix[5][5][5] = 1;
//		matrix[4][5][5] = 1;
//		matrix[3][5][5] = 1;
//		matrix[2][5][5] = 1;
//		matrix[1][5][5] = 1;
//		matrix[0][5][5] = 1;
//
//		World world =  new World(matrix, new DefaultTerrainChangeListener());
//		assertEquals(nbX, world.getNbCubesX());
//		assertEquals(nbY, world.getNbCubesY());
//		assertEquals(nbZ, world.getNbCubesZ());
//		Cube solidCube = world.getCube(5, 5, 5);
//		assertEquals(true, solidCube.isSolid());
//		
//	}
//	
//	@Test
//	public void spawnUnit_worldFull() {
//		int i = 1;
//		while(i < 120){
//			testWorld.spawnUnit(false);
//			int nbOfUnits = testWorld.getUnitSet().size();
//			assertEquals(true, ((nbOfUnits == 100) || (nbOfUnits == i)));
//			i++;
//		}
//		assertEquals(100, testWorld.getUnitSet().size());
//	}
//	
//	@Test
//	public void randomStandingPosition() {
//		PositionVector position = testWorld.randomStandingPosition();
//		Cube cube = testWorld.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
//		boolean flag = testWorld.isValidStandingPosition(cube.getPosition());
//		assertEquals(true, flag);
//	}
//	
//	@Test
//	public void getAllAdjacents_LegalCase(){
//		PositionVector position = testWorld.randomStandingPosition();
//		int x = (int) position.getXArgument();
//		int y = (int) position.getYArgument();
//		int z = (int) position.getZArgument();
//		Set<PositionVector> adjacents = testWorld.getAllAdjacentPositions(position);
//		assertEquals(true, (adjacents.size() <= 26));
//		for(PositionVector adjacent : adjacents){
//			int x2 = (int) adjacent.getXArgument();
//			int y2 = (int) adjacent.getYArgument();
//			int z2 = (int) adjacent.getZArgument();
//			boolean flag = ((Math.abs(x-x2) <= 1) && (Math.abs(y-y2) <= 1) && (Math.abs(z-z2) <= 1));
//			assertEquals(true, flag);
//		}
//	}
//	
//	@Test
//	public void hasSolidAdjacent_True(){
//		Cube cube = null;
//		Random generator = new Random();
//		while((cube == null) || (! testWorld.isSolidConnectedToBorder(cube.getPosition()))){
//			int x = generator.nextInt(testWorld.getNbCubesX());
//			int y = generator.nextInt(testWorld.getNbCubesY());
//			int z = generator.nextInt(testWorld.getNbCubesZ());
//			cube = testWorld.getCube(x, y, z);
//		}
//		Cube solidCube = testWorld.getCube(5, 5, 5);
//		assertEquals(true, solidCube.isSolid());
//		assertEquals(true, testWorld.hasSolidAdjacent(solidCube));
//	}
//	
//	@Test
//	public void spawnUnit_LegalCase(){
//		Unit unit = world.spawnUnit(false);
//		assertEquals(true, world.hasAsUnit(unit));
//		assertEquals(true, world.getCube(unit.getCubePosition()[0], unit.getCubePosition()[1], 
//				unit.getCubePosition()[2]).hasAsContent(unit));
//	}
//	
//	public void fill(int[][][] matrix){
//		Random generator = new Random();
//		int x = matrix.length;
//		int y = matrix[0].length;
//		int z = matrix[0][0].length;
//		int types = 4;
//		int i = 0;
//		while(i < x){
//			int j = 0;
//			while(j < y){
//				int k = 0;
//				while(k < z){
//					matrix[i][j][k] = generator.nextInt(types);
//					k++;
//				}
//				j++;
//			}
//			i++;
//		}
//	}
//	
//	@Test @Ignore
//	public void constructor_80RandomFill(){
//		int nbX = 80;
//		int nbY = 80;
//		int nbZ = 80;
//		int[][][] matrix = new int[nbX][nbY][nbZ];
//
//		World world =  new World(matrix, new DefaultTerrainChangeListener());
//		assertEquals(nbX, world.getNbCubesX());
//		assertEquals(nbY, world.getNbCubesY());
//		assertEquals(nbZ, world.getNbCubesZ());
//	}
//	
//	@Test
//	public void spawnUnit_LegalCase2(){
//		Unit unit = testWorld.spawnUnit(false);
//		testWorld.advanceTime(0.19);
//	}
//	
//	@Test
//	public void randomSpawnPosition() {
//		testWorld.randomSpawnPosition();
//	}
	
	@Test @Ignore
	public void spawnUnit_RandomBehaviour() {
		testWorld.spawnUnit(true);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
	}
	
	@Test @Ignore
	public void moveToAdjacent(){
		Unit unit = new Unit(new PositionVector(0,0,0), "Ikke", new Faction());
		unit.changeWorld(testWorld);
		testWorld.addUnit(unit);
		PositionVector destination = new PositionVector(1,0,0);
		unit.moveToAdjacent(new PositionVector(1,0,0));
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		testWorld.advanceTime(0.19);
		assertEquals(true, unit.getCubePositionVector().equals(destination));
	}
	
	@Test @Ignore
	public void correctSpawnPosition(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 3;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 1; matrix[2][0][0] = 1;
		matrix[0][1][0] = 1; matrix[1][1][0] = 1; matrix[2][1][0] = 1;
		matrix[0][2][0] = 1; matrix[1][2][0] = 1; matrix[2][2][0] = 1;
		
		matrix[0][0][1] = 1; matrix[1][0][1] = 1; matrix[2][0][1] = 1;
		matrix[0][1][1] = 1; matrix[1][1][1] = 0; matrix[2][1][1] = 0;
		matrix[0][2][1] = 1; matrix[1][2][1] = 1; matrix[2][2][1] = 1;
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 0; matrix[2][0][2] = 0;
		matrix[0][1][2] = 0; matrix[1][1][2] = 0; matrix[2][1][2] = 0;
		matrix[0][2][2] = 0; matrix[1][2][2] = 0; matrix[2][2][2] = 0;
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		Unit unit = new Unit(new PositionVector(1,1,1), "Ikke", new Faction());
		unit.changeWorld(world2);
		world2.addUnit(unit);
		int[] unitCubePosition = unit.getCubePosition();
		Cube underneath = world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]-1);
		// unit occupies a passable cube
		assertEquals(false,world2.isSolidPosition(unit.getCubePositionVector()));
		// cube underneath unit is solid
		assertEquals(true,underneath.isSolid());
		// the cube that is occupied by unit contan unit
		assertEquals(true,world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]).getContent().contains(unit));
		unit.moveToAdjacent(new PositionVector(1,0,0));
		world2.advanceTime(5);
		assertEquals(true,unit.getCubePositionVector().equals(new PositionVector(2,1,1)));
		assertEquals(false,world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]).getContent().contains(unit));
		int[] newUnitCubePosition = unit.getCubePosition();
		assertEquals(true,world2.getCube(newUnitCubePosition[0], 
				newUnitCubePosition[1], newUnitCubePosition[2]).getContent().contains(unit));
	}
	
	@Test @Ignore
	public void unitFalls(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 4;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 1; matrix[1][1][0] = 0; matrix[2][1][0] = 1;
		matrix[0][2][0] = 1; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		
		matrix[0][0][1] = 1; matrix[1][0][1] = 0; matrix[2][0][1] = 0;
		matrix[0][1][1] = 1; matrix[1][1][1] = 0; matrix[2][1][1] = 0;
		matrix[0][2][1] = 1; matrix[1][2][1] = 0; matrix[2][2][1] = 0;
		
		matrix[0][0][2] = 1; matrix[1][0][2] = 0; matrix[2][0][2] = 0;
		matrix[0][1][2] = 1; matrix[1][1][2] = 0; matrix[2][1][2] = 0;
		matrix[0][2][2] = 1; matrix[1][2][2] = 0; matrix[2][2][2] = 0;
		
		matrix[0][0][3] = 0; matrix[1][0][3] = 0; matrix[2][0][3] = 0;
		matrix[0][1][3] = 0; matrix[1][1][3] = 0; matrix[2][1][3] = 0;
		matrix[0][2][3] = 0; matrix[1][2][3] = 0; matrix[2][2][3] = 0;
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		Unit unit = new Unit(new PositionVector(0,1,3), "Ikke", new Faction());
		unit.changeWorld(world2);
		world2.addUnit(unit);
		int[] unitCubePosition = unit.getCubePosition();
		Cube underneath = world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]-1);
		// unit occupies a passable cube
		assertEquals(false,world2.isSolidPosition(unit.getCubePositionVector()));
		// cube underneath unit is solid
		assertEquals(true,underneath.isSolid());
		// the cube that is occupied by unit contains unit
		assertEquals(true,world2.getCube(unitCubePosition[0], unitCubePosition[1], unitCubePosition[2]).getContent().contains(unit));
		unit.moveToAdjacent(new PositionVector(1,0,0));
		world2.advanceTime(5);
		unit.moveToAdjacent(new PositionVector(1,0,0));
		world2.advanceTime(5);
		// the unit fell to this position
		assertEquals(true,unit.getCubePositionVector().equals(new PositionVector(2,1,1)));
		assertEquals(true, unit.getActivityStatus().equals("default"));
		assertEquals(true, unit.getCurrentHP() < unit.getMaxHP());
	}
	
	@Test @Ignore
	public void areAdjacents(){
		PositionVector position1 = new PositionVector(0,0,0);
		PositionVector position2 = new PositionVector(0,0,0);
		assertEquals(false, testWorld.areAdjacents(position1, position2));
		PositionVector position3 = new PositionVector(1,1,1);
		assertEquals(true, testWorld.areAdjacents(position1, position3));
		PositionVector position4 = new PositionVector(2,0,0);
		assertEquals(false, testWorld.areAdjacents(position1, position4));
	}
	
	@Test @Ignore
	public void areDirectAdjacents(){
		PositionVector position1 = new PositionVector(0,0,0);
		PositionVector position2 = new PositionVector(0,0,0);
		assertEquals(false, testWorld.areDirectAdjacents(position1, position2));
		PositionVector position3 = new PositionVector(1,1,1);
		assertEquals(false, testWorld.areDirectAdjacents(position1, position3));
		PositionVector position4 = new PositionVector(1,0,0);
		assertEquals(true, testWorld.areAdjacents(position1, position4));
	}
	
	@Test @Ignore
	public void hasSolidCornerInBetween(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0;
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector position1 = new PositionVector(0,1,0);
		PositionVector position2 = new PositionVector(1,0,0);
		boolean flag = world2.hasSolidCornerInBetween(position1, position2);
		assertEquals(true, flag);
		PositionVector position3 = new PositionVector(1,2,0);
		flag = world2.hasSolidCornerInBetween(position1, position3);
		assertEquals(true, flag);
		PositionVector position4 = new PositionVector(0,2,0);
		flag = world2.hasSolidCornerInBetween(position1, position4);
		assertEquals(false, flag);
	}
	
	@Test @Ignore
	public void getReachableAdjacents() {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0;
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector position = new PositionVector(0,1,0);
		Set<PositionVector> reachables = world2.getReachableAdjacents(position);
		assertEquals(true,reachables.size() == 1);
		assertEquals(true,reachables.contains(new PositionVector(0,2,0)));
	}
	
	@Test @Ignore
	public void getReachableAdjacents_3D() {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 3;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 1; matrix[2][0][0] = 1;
		matrix[0][1][0] = 1; matrix[1][1][0] = 1; matrix[2][1][0] = 1;
		matrix[0][2][0] = 0; matrix[1][2][0] = 1; matrix[2][2][0] = 1;
		
		matrix[0][0][1] = 0; matrix[1][0][1] = 1; matrix[2][0][1] = 1;
		matrix[0][1][1] = 0; matrix[1][1][1] = 1; matrix[2][1][1] = 1;
		matrix[0][2][1] = 0; matrix[1][2][1] = 1; matrix[2][2][1] = 1;
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 1; matrix[2][0][2] = 1;
		matrix[0][1][2] = 0; matrix[1][1][2] = 0; matrix[2][1][2] = 1;
		matrix[0][2][2] = 0; matrix[1][2][2] = 1; matrix[2][2][2] = 1;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector position = new PositionVector(0,1,1);
		Set<PositionVector> allAdjacents = world2.getAllAdjacentPositions(position);
		assertEquals(true, (allAdjacents.size() == 17));
		Set<PositionVector> reachables = world2.getReachableAdjacents(position);
		int size = reachables.size();
		assertEquals(true,size == 5);
		assertEquals(false,reachables.contains(new PositionVector(0,2,0)));
		assertEquals(false,reachables.contains(new PositionVector(1,1,2)));
	}
	
	@Test @Ignore
	public void getReachableAdjacents_3D_version2() {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 3;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 1; matrix[2][0][0] = 1;
		matrix[0][1][0] = 1; matrix[1][1][0] = 1; matrix[2][1][0] = 1;
		matrix[0][2][0] = 1; matrix[1][2][0] = 1; matrix[2][2][0] = 1;
		
		matrix[0][0][1] = 0; matrix[1][0][1] = 1; matrix[2][0][1] = 1;
		matrix[0][1][1] = 0; matrix[1][1][1] = 1; matrix[2][1][1] = 1;
		matrix[0][2][1] = 0; matrix[1][2][1] = 1; matrix[2][2][1] = 1;
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 0; matrix[2][0][2] = 1;
		matrix[0][1][2] = 0; matrix[1][1][2] = 0; matrix[2][1][2] = 1;
		matrix[0][2][2] = 0; matrix[1][2][2] = 0; matrix[2][2][2] = 1;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector position = new PositionVector(1,1,2);
		Set<PositionVector> allAdjacents = world2.getAllAdjacentPositions(position);
		assertEquals(true, (allAdjacents.size() == 17));
		Set<PositionVector> reachables = world2.getReachableAdjacents(position);
		int size = reachables.size();
		assertEquals(true,size == 5);
		assertEquals(false,reachables.contains(new PositionVector(0,0,1)));
		assertEquals(false,reachables.contains(new PositionVector(0,2,1)));
	}
	
	@Test @Ignore
	public void reversePositionList(){
		List<PositionVector> list1 = new ArrayList<PositionVector>();
		PositionVector position1 = new PositionVector(0, 0, 0);
		list1.add(position1);
		PositionVector position2 = new PositionVector(1, 0, 0);
		list1.add(position2);
		PositionVector position3 = new PositionVector(2, 0, 0);
		list1.add(position3);
		List<PositionVector> result = World.reversePositionList(list1);
		assertEquals(true, result.get(0).equals(position3));
		assertEquals(true, result.get(1).equals(position2));
		assertEquals(true, result.get(2).equals(position1));
	}
	
	@Test @Ignore
	public void determinePath_Simple_2D(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 0; matrix[1][1][0] = 0; matrix[2][1][0] = 0;
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector start = new PositionVector(0,1,0);
		PositionVector end = new PositionVector(2,1,0);
		List<PositionVector> path = world2.determinePath(start, end);
		assertEquals(true, path.size() == 3);
		assertEquals(true, path.contains(new PositionVector(1,1,0)));
	}
	
	@Test @Ignore
	public void determinePath_VerySimple_2D(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 0; matrix[1][1][0] = 0; matrix[2][1][0] = 0;
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector start = new PositionVector(0,1,0);
		PositionVector end = new PositionVector(1,1,0);
		List<PositionVector> path = world2.determinePath(start, end);
		assertEquals(true, path.size() == 2);
	}
	
	@Test @Ignore
	public void determinePath_2D_Obstacle(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0;
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector start = new PositionVector(0,1,0);
		PositionVector end = new PositionVector(2,1,0);
		List<PositionVector> path = world2.determinePath(start, end);
		assertEquals(true, path.size() == 5);
	}
	
	@Test @Ignore
	public void determinePath_3D_gapInBetween(){
		int nbX = 5;
		int nbY = 3;
		int nbZ = 3;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 0; matrix[2][0][0] = 0; matrix[3][0][0] = 0; matrix[4][0][0] = 1;
		matrix[0][1][0] = 1; matrix[1][1][0] = 0; matrix[2][1][0] = 0; matrix[3][1][0] = 0; matrix[4][1][0] = 1;
		matrix[0][2][0] = 1; matrix[1][2][0] = 0; matrix[2][2][0] = 0; matrix[3][2][0] = 0; matrix[4][2][0] = 1;
		
		matrix[0][0][1] = 1; matrix[1][0][1] = 0; matrix[2][0][1] = 0; matrix[3][0][1] = 0; matrix[4][0][1] = 1;
		matrix[0][1][1] = 1; matrix[1][1][1] = 0; matrix[2][1][1] = 0; matrix[3][1][1] = 0; matrix[4][1][1] = 1;
		matrix[0][2][1] = 1; matrix[1][2][1] = 0; matrix[2][2][1] = 0; matrix[3][2][1] = 0; matrix[4][2][1] = 1;
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 0; matrix[2][0][2] = 0; matrix[3][0][2] = 0; matrix[4][0][2] = 0;
		matrix[0][1][2] = 0; matrix[1][1][2] = 0; matrix[2][1][2] = 0; matrix[3][1][2] = 0; matrix[4][1][2] = 0;
		matrix[0][2][2] = 0; matrix[1][2][2] = 0; matrix[2][2][2] = 0; matrix[3][2][2] = 0; matrix[4][2][2] = 0;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector start = new PositionVector(0,1,2);
		PositionVector end = new PositionVector(4,1,2);
		Unit unit = new Unit(start, "Ikke", new Faction());
		world2.addUnit(unit);
		unit.moveTo(end);
		world2.advanceTime(20);
		assertEquals(unit.getMaxHP(), unit.getCurrentHP());
	}
	
	@Test @Ignore
	public void determinePath_UnreachableSimple(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 1; matrix[2][0][0] = 0;
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0;
		matrix[0][2][0] = 0; matrix[1][2][0] = 1; matrix[2][2][0] = 0;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector position = new PositionVector(0,0,0);
		PositionVector destination = new PositionVector(2,0,0);
		List<PositionVector> path = world2.determinePath(position, destination);
		assertEquals(true, path.isEmpty());
		Unit unit = new Unit(position, "Ikke", new Faction());
		world2.addUnit(unit);
		unit.moveTo(destination);
		world2.advanceTime(20);
		assertEquals(true,unit.getCubePositionVector().equals(position));
		assertEquals(true,unit.getActivityStatus().equals("default"));
	}

	@Test
	public void fallToDeath() {
		int nbX = 5;
		int nbY = 3;
		int nbZ = 4;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 0; 
		matrix[0][1][0] = 0; matrix[1][1][0] = 0; matrix[2][1][0] = 0; 
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0; 
		
		matrix[0][0][1] = 0; matrix[1][0][1] = 0; matrix[2][0][1] = 0; 
		matrix[0][1][1] = 0; matrix[1][1][1] = 0; matrix[2][1][1] = 0; 
		matrix[0][2][1] = 0; matrix[1][2][1] = 0; matrix[2][2][1] = 0; 
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 0; matrix[2][0][2] = 0; 
		matrix[0][1][2] = 0; matrix[1][1][2] = 0; matrix[2][1][2] = 0; 
		matrix[0][2][2] = 0; matrix[1][2][2] = 0; matrix[2][2][2] = 0; 
		
		matrix[0][0][3] = 0; matrix[1][0][3] = 0; matrix[2][0][3] = 0; 
		matrix[0][1][3] = 0; matrix[1][1][3] = 0; matrix[2][1][3] = 0; 
		matrix[0][2][3] = 0; matrix[1][2][3] = 0; matrix[2][2][3] = 0; 
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector position = new PositionVector(1,1,0);
		Unit unit = new Unit(position, "Ikke", new Faction());
		world2.addUnit(unit);
		int i = 0;
		while(i < 20){
			unit.moveToAdjacent(new PositionVector(0,0,1));
			world2.advanceTime(5);
			i++;
		}
		assertEquals(true, unit.isTerminated());
	}
	
	@Test
	public void fall_Material() {
		int nbX = 5;
		int nbY = 3;
		int nbZ = 4;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 0; 
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0; 
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0; 
		
		matrix[0][0][1] = 0; matrix[1][0][1] = 0; matrix[2][0][1] = 0; 
		matrix[0][1][1] = 0; matrix[1][1][1] = 0; matrix[2][1][1] = 0; 
		matrix[0][2][1] = 0; matrix[1][2][1] = 0; matrix[2][2][1] = 0; 
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 0; matrix[2][0][2] = 0; 
		matrix[0][1][2] = 0; matrix[1][1][2] = 0; matrix[2][1][2] = 0; 
		matrix[0][2][2] = 0; matrix[1][2][2] = 0; matrix[2][2][2] = 0; 
		
		matrix[0][0][3] = 0; matrix[1][0][3] = 0; matrix[2][0][3] = 0; 
		matrix[0][1][3] = 0; matrix[1][1][3] = 0; matrix[2][1][3] = 0; 
		matrix[0][2][3] = 0; matrix[1][2][3] = 0; matrix[2][2][3] = 0; 
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		world2.collapse(new PositionVector(1,1,0));
		world2.advanceTime(5);
		Set<Material> materials = world2.getMaterialSet();
		assertEquals(true, materials.size() == 1);
		for(Material material : materials){
			PositionVector position = material.getUnitPosition();
			assertEquals(true, position.equals(new PositionVector(1.5,1.5,0)));
		}
	}
	
	@Test
	public void fight(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 0; matrix[1][1][0] = 0; matrix[2][1][0] = 0;
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		PositionVector defenderPosition = new PositionVector(0,1,0);
		Unit attacker = new Unit(new PositionVector(0,1,0), "Attack", new Faction());
		Unit defender = new Unit(defenderPosition, "Defend", new Faction());
		world2.addUnit(attacker);world2.addUnit(defender);
		attacker.attack(defender);
		world2.advanceTime(5);
		boolean damage = (defender.getCurrentHP() < 200) && (defender.getCubePositionVector().equals(defenderPosition));
		boolean dodge = (defender.getCurrentHP() == 200) && (! defender.getCubePositionVector().equals(defenderPosition));
		boolean block = (defender.getCurrentHP() == 200) && (defender.getCubePositionVector().equals(defenderPosition));
		assertEquals(true, damage || dodge || block);
	}
	
	
	@Test
	public void materialFall_MultipleCubes() {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 3;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 1; matrix[1][0][0] = 1; matrix[2][0][0] = 1; 
		matrix[0][1][0] = 1; matrix[1][1][0] = 1; matrix[2][1][0] = 1; 
		matrix[0][2][0] = 1; matrix[1][2][0] = 1; matrix[2][2][0] = 1; 
		
		matrix[0][0][1] = 1; matrix[1][0][1] = 1; matrix[2][0][1] = 1; 
		matrix[0][1][1] = 1; matrix[1][1][1] = 0; matrix[2][1][1] = 1; 
		matrix[0][2][1] = 1; matrix[1][2][1] = 1; matrix[2][2][1] = 1; 
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 0; matrix[2][0][2] = 0; 
		matrix[0][1][2] = 0; matrix[1][1][2] = 1; matrix[2][1][2] = 0; 
		matrix[0][2][2] = 0; matrix[1][2][2] = 0; matrix[2][2][2] = 0; 
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		world2.collapse(new PositionVector(1,1,2));
		world2.advanceTime(5);
		Set<Material> materialSet = world2.getMaterialSet();
		assertEquals(true, materialSet.size() == 1);
		assertEquals(true, world2.getCube(1, 1, 2).getContent().isEmpty());
		Cube cube = world2.getCube(1, 1, 1);
		HashSet<GameObject> cubeContent = cube.getContent();
		assertEquals(true, materialSet.contains(cubeContent.iterator().next()));
	}
	
	@Test
	public void fight_TillDeath(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 0; 
		matrix[0][1][0] = 0; matrix[1][1][0] = 0; matrix[2][1][0] = 0; 
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0; 
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		Unit attacker = new Unit(new PositionVector(1,1,0), "Ikke", new Faction());
		Unit target = new Unit(new PositionVector(0,1,0), "Wow", new Faction());
		world2.addUnit(attacker);
		world2.addUnit(target);
		world2.advanceTime(1);
		
		while(! target.isTerminated()){
			attacker.attack(target);
			world2.advanceTime(3);
		}
		world2.advanceTime(5);
		assertEquals(true, target.isTerminated());
		world2.advanceTime(2);
		assertEquals(false, world2.hasAsUnit(target));
	}
	
	@Test
	public void isAccesible_TrueAndFalseCases(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 1; matrix[2][0][0] = 0; 
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0; 
		matrix[0][2][0] = 0; matrix[1][2][0] = 1; matrix[2][2][0] = 0; 
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		Unit unit = new Unit(new PositionVector(0,1,0), "Ikke", new Faction());
		world2.addUnit(unit);
		assertEquals(true, world2.isAccessible(new PositionVector(0,0,0), unit.getCubePositionVector()));
		assertEquals(false, world2.isAccessible(new PositionVector(2,1,0), unit.getCubePositionVector()));
	}
	
	@Test
	public void getAccessibleBoulderLog(){
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 1; matrix[2][0][0] = 1; 
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0; 
		matrix[0][2][0] = 0; matrix[1][2][0] = 1; matrix[2][2][0] = 2; 
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		
		world2.advanceTime(2);
		PositionVector position = new PositionVector(0,1,0);
		assertEquals(true, world2.getAccessibleBoulder(position) == null);
		assertEquals(true, world2.getAccessibleLog(position) == null);
		
		world2.collapse(new PositionVector(2,0,0));
		world2.collapse(new PositionVector(2,2,0));
		world2.advanceTime(2);
		
		assertEquals(true, world2.getAccessibleBoulder(position) == null);
		assertEquals(true, world2.getAccessibleLog(position) == null);
		
		world2.collapse(new PositionVector(1,1,0));
		world2.advanceTime(2);
		
		assertEquals(true, (world2.getAccessibleBoulder(position).equals(new PositionVector(2,0,0))
				|| world2.getAccessibleBoulder(position).equals(new PositionVector(1,1,0))));
		assertEquals(true, world2.getAccessibleLog(position).equals(new PositionVector(2,2,0)));
	}
	
	@Test
	public void getAccessibleFriendEnemyAny() {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 1; matrix[2][0][0] = 0; 
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0; 
		matrix[0][2][0] = 0; matrix[1][2][0] = 1; matrix[2][2][0] = 0; 
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		
		world2.advanceTime(2);
		
		Unit mainUnit = new Unit(new PositionVector(0,1,0), "Ikke", new Faction());
		world2.addUnit(mainUnit);
		
		world2.advanceTime(2);
		
		assertEquals(true, world2.getAccessibleAlly(mainUnit) == null);
		assertEquals(true, world2.getAccessibleEnemy(mainUnit) == null);
		assertEquals(true, world2.getAccessibleUnit(mainUnit) == null);
		
		Unit ally = new Unit(new PositionVector(2,0,0), "Ikke", mainUnit.getFaction());
		Unit enemy = new Unit(new PositionVector(2,2,0), "Ikke", new Faction());
		world2.addUnit(ally);
		world2.addUnit(enemy);
		
		world2.advanceTime(2);
		
		assertEquals(true, world2.getAccessibleAlly(mainUnit) == null);
		assertEquals(true, world2.getAccessibleEnemy(mainUnit) == null);
		assertEquals(true, world2.getAccessibleUnit(mainUnit) == null);
		
		world2.collapse(new PositionVector(1,1,0));
		world2.advanceTime(2);
		
		assertEquals(true, world2.getAccessibleAlly(mainUnit).equals(ally));
		assertEquals(true, world2.getAccessibleEnemy(mainUnit).equals(enemy));
		Unit anyUnit = world2.getAccessibleUnit(mainUnit);
		assertEquals(true, anyUnit.equals(enemy) || anyUnit.equals(ally));
	}
	
	@Test
	public void areAllies() {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 1; matrix[2][0][0] = 0; 
		matrix[0][1][0] = 0; matrix[1][1][0] = 1; matrix[2][1][0] = 0; 
		matrix[0][2][0] = 0; matrix[1][2][0] = 1; matrix[2][2][0] = 0; 
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		
		world2.advanceTime(2);
		
		Unit mainUnit = new Unit(new PositionVector(0,1,0), "Ikke", new Faction());
		world2.addUnit(mainUnit);
		Unit ally = new Unit(new PositionVector(2,0,0), "Ikke", mainUnit.getFaction());
		Unit enemy = new Unit(new PositionVector(2,2,0), "Ikke", new Faction());
		world2.addUnit(ally);
		world2.addUnit(enemy);
		world2.advanceTime(2);
		
		assertEquals(true, mainUnit.getWorld().areAllies(mainUnit, ally));
		assertEquals(false, mainUnit.getWorld().areAllies(mainUnit, enemy));
	}
	
	@Test
	public void accessibleWorkshop() {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 1;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 1; matrix[2][0][0] = 0; 
		matrix[0][1][0] = 0; matrix[1][1][0] = 3; matrix[2][1][0] = 0; 
		matrix[0][2][0] = 0; matrix[1][2][0] = 1; matrix[2][2][0] = 0; 
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		world2.advanceTime(2);
		
		assertEquals(true, (! world2.getWorkshopSet().isEmpty()));
	}
	
	@Test
	public void makeValidTerrain_CaveIn() {
		int nbX = 3;
		int nbY = 3;
		int nbZ = 3;
		int[][][] matrix = new int[nbX][nbY][nbZ];
		matrix[0][0][0] = 0; matrix[1][0][0] = 0; matrix[2][0][0] = 0;
		matrix[0][1][0] = 0; matrix[1][1][0] = 0; matrix[2][1][0] = 0;
		matrix[0][2][0] = 0; matrix[1][2][0] = 0; matrix[2][2][0] = 0;
		
		matrix[0][0][1] = 0; matrix[1][0][1] = 0; matrix[2][0][1] = 0;
		matrix[0][1][1] = 0; matrix[1][1][1] = 1; matrix[2][1][1] = 0;
		matrix[0][2][1] = 0; matrix[1][2][1] = 0; matrix[2][2][1] = 0;
		
		matrix[0][0][2] = 0; matrix[1][0][2] = 0; matrix[2][0][2] = 0;
		matrix[0][1][2] = 0; matrix[1][1][2] = 0; matrix[2][1][2] = 0;
		matrix[0][2][2] = 0; matrix[1][2][2] = 0; matrix[2][2][2] = 0;
		
		World world2 =  new World(matrix, new DefaultTerrainChangeListener());
		assertEquals(true, world2.getCube(1, 1, 1).getTerrainType() == 0);
		assertEquals(true, (! world2.getMaterialSet().isEmpty()));
	}
}

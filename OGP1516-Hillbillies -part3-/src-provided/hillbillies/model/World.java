package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.*;
import hillbillies.util.*;
import ogp.framework.util.Util;


/**
 * A class of worlds.
 * 
 * @invar  The terrain matrix of each world must be a valid terrain matrix for any
 *         world.
 *       | isValidTerrainMatrix(getTerrainMatrix())
 * @invar  The cube matrix of this world must be a valid cube matrix for this
 *         world.
 *       | isValidCubeMatrix(getCubeMatrix())
 * @invar  Each cube can have its connected to border checker as connected to border checker.
 *       | canHaveAsConnectedToBorder(this.getConnectedToBorder())
 * @invar  The number of units of each world must be a valid number of units for any
 *         world.
 *       | isValidNumberOfUnits(getNumberOfUnits())
 * @invar  The unit set of each world must be a valid unit set for any
 *         world.
 *       | isValidUnitSet(getUnitSet())
 * @invar  The object set of each world must be a valid object set for any
 *         world.
 *       | isValidObjectSet(getObjectSet())
 * @invar  The faction set of each world must be a valid faction set for any
 *         world.
 *       | isValidFactionSet(getFactionSet())
 * @invar  The workshop set of each world must be a valid workshop set for any
 *         world.
 *       | isValidWorkshopSet(getWorkshopSet())
 * @author Michaël
 * @version 0.7
 */
public class World {
	
	/**
	 * Initialize this new world with given terrain matrix.
	 *
	 * @param  terrainTypes
	 *         The terrain matrix for this new world.
	 * @param	modelListener 
	 * 			The terrainChangeListener for this new world.
	 * @effect The terrain matrix of this new world is set to
	 *         the given terrain matrix.
	 *       | this.setTerrainMatrix(terrainTypes)
	 * @post	The modelListener of this new world equals the given modelListener.
	 * 			| this.modelListener.equals(modelListener)
	 * @effect	The cube matrix of this world is initialized.
	 * @effect	Initializes this world's connected to border checker.
	 * @effect	This world's terrain is made valid.
	 * @effect The unit set of this new world is set to an empty hash set.
	 * @effect The material set of this new world is set to an empty hash set.
	 * @effect The faction set of this new world is set to a new hash set.
	 * @effect The workshop set of this new world is set to the an new hash set.
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener)
			throws NullPointerException {
		this.setUnitSet(new HashSet<Unit>());
		this.setMaterialSet(new HashSet<Material>());
		this.setFactionSet(new HashSet<>());
		this.setWorkshopSet(new HashSet<PositionVector>());
		this.setTerrainMatrix(terrainTypes);
		this.modelListener = modelListener;
		this.initializeCubeMatrix();
		this.connectedToBorder = new ConnectedToBorder(this.getNbCubesX(), this.getNbCubesY(), this.getNbCubesZ());
		this.initializeConnectedToBorder();
		this.makeValidTerrain();
	}
	
	
	/**
	 * Return the terrain matrix of this world.
	 */
	@Basic @Raw
	public int[][][] getTerrainMatrix() {
		return this.terrainTypes;
	}
	
	/**
	 * Check whether the given terrain matrix is a valid terrain matrix for
	 * any world.
	 *  
	 * @param  terrain matrix
	 *         The terrain matrix to check.
	 * @return 
	 *       | result == (terrainTypes != null)
	*/
	public static boolean isValidTerrainMatrix(int[][][] terrainTypes) {
		return (terrainTypes != null);
	}
	
	/**
	 * Set the terrain matrix of this world to the given terrain matrix.
	 * 
	 * @param  terrainTypes
	 *         The new terrain matrix for this world.
	 * @post   The terrain matrix of this new world is equal to
	 *         the given terrain matrix.
	 *       | new.getTerrainMatrix() == terrainTypes
	 * @throws NullPointerException
	 *         The given terrain matrix is not a valid terrain matrix for any
	 *         world.
	 *       | ! isValidTerrainMatrix(getTerrainMatrix())
	 */
	@Raw
	public void setTerrainMatrix(int[][][] terrainTypes) 
			throws NullPointerException {
		if (! isValidTerrainMatrix(terrainTypes))
			throw new NullPointerException();
		this.terrainTypes = terrainTypes;
	}
	
	/**
	 * Variable registering the terrain matrix of this world.
	 */
	private int[][][] terrainTypes;
	
	/**
	 * Return the number of cubes this world has in the x direction.
	 * @return	The x length of this world's terrain matrix.
	 */
	public int getNbCubesX() {
		return this.getTerrainMatrix().length;
	}
	
	/**
	 * Return the number of cubes this world has in the y direction.
	 * @return	The y length of this world's terrain matrix.
	 */
	public int getNbCubesY() {
		return this.getTerrainMatrix()[0].length;
	}
	
	/**
	 * Return the number of cubes this world has in the z direction.
	 * @return	The z length of this world's terrain matrix.
	 */
	public int getNbCubesZ() {
		return this.getTerrainMatrix()[0][0].length;
	}
	
	private TerrainChangeListener modelListener;
	
	/**
	 * Return the cube matrix of this world.
	 */
	@Basic @Raw
	public Cube[][][] getCubeMatrix() {
		return this.cubeMatrix;
	}
	
	/**
	 * Check whether the given cube matrix is a valid cube matrix for
	 * any world.
	 *  
	 * @param  cube matrix
	 *         The cube matrix to check.
	 * @return 
	 *       | result == (cubeMatrix != null) && (cubeMatrix.length == this.getNbCubesX()
	 *       				&& (cubeMatrix[0].length == this.getNbCubesY() && (cubeMatrix[0][0].length == this.getNbCubesZ()))
	*/
	public boolean isValidCubeMatrix(Cube[][][] cubeMatrix) {
		return (cubeMatrix != null) && (cubeMatrix.length == this.getNbCubesX()
				&& (cubeMatrix[0].length == this.getNbCubesY()) && (cubeMatrix[0][0].length == this.getNbCubesZ()));
	}
	
	/**
	 * Set the cube matrix of this world to the given cube matrix.
	 * 
	 * @param  cubeMatrix
	 *         The new cube matrix for this world.
	 * @post   The cube matrix of this new world is equal to
	 *         the given cube matrix.
	 *       | new.getCubeMatrix() == cubeMatrix
	 * @throws NullPointerException
	 *         The given cube matrix is not a valid cube matrix for any
	 *         world.
	 *       | ! isValidCubeMatrix(getCubeMatrix())
	 */
	@Raw
	public void setCubeMatrix(Cube[][][] cubeMatrix) 
			throws NullPointerException {
		if (! isValidCubeMatrix(cubeMatrix))
			throw new NullPointerException();
		this.cubeMatrix = cubeMatrix;
	}
	
	/**
	 * Initialize the cube matrix of this world.
	 * @effect	This world's cube matrix is created and a type of cube is mapped to each cell of it, according to each element 
	 * of this world's terrain matrix. If the element is a workshop, it is also added to this world's workshop set.
	 */
	@Raw
	private void initializeCubeMatrix() {
		int[][][] terrainMatrix = this.getTerrainMatrix();
		Cube[][][] cubeMatrix = 
				new Cube[this.getNbCubesX()][this.getNbCubesY()][this.getNbCubesZ()];
		int x = 0;
		while (x < this.getNbCubesX()) {
			int y = 0;
			while (y < this.getNbCubesY()) {
				int z = 0;
				while (z < this.getNbCubesZ()) {
					cubeMatrix[x][y][z] = this.mapCube(new PositionVector(x,y,z), terrainMatrix[x][y][z]);
					if(terrainMatrix[x][y][z] == 3)
						this.getWorkshopSet().add(new PositionVector(x,y,z));
					z++;
				}
				y++;
			}
			x++;
		}
		this.setCubeMatrix(cubeMatrix);
	}
	
	/**
	 * Return a cube with a given position, content and terrain type, for a given position, content and terrain number.
	 * @param cubePosition	The given position.
	 * @param content	The given content.
	 * @param terrainNb	The given terrain number.
	 * @return	An air, rock, tree or workshop cube, depending on the given terrain number, having the given position as its position
	 * and the given content as it's content. When no valid terrain number is given, air is returned.
	 */
	@Raw @Model
	private Cube mapCube(PositionVector cubePosition, HashSet<GameObject> content, int terrainNb) {
		Cube cube = null;
		if (terrainNb == 0)
			cube = new Air(cubePosition, content);
		else if (terrainNb == 1)
			cube = new Rock(cubePosition, content);
		else if (terrainNb == 2)
			cube = new Tree(cubePosition, content);
		else if (terrainNb == 3)
			cube = new Workshop(cubePosition, content);
		else
			cube = new Air(cubePosition, content);
		return cube;
	}
	
	/**
	 * Return a cube with a given position, being a specific terrain type cube, for a given position and terrain number.
	 * @param cubePosition	The given position.
	 * @param terrainNb	The given terrain number.
	 * @return	An air, rock, tree or workshop cube, depending on the given terrain number, having the given position as its position.
	 * When no valid terrain number is given, air is returned.
	 */
	@Raw @Model
	private Cube mapCube(PositionVector cubePosition, int terrainNb) {
		return this.mapCube(cubePosition, new HashSet<GameObject>(), terrainNb);
	}
	
	/**
	 * Variable registering the cube matrix of this world.
	 */
	private Cube[][][] cubeMatrix;
	
	/**
	 * Return the cube at a given position.
	 * @param x	The given x coordinate of the targeted cube.
	 * @param y	The given y coordinate of the targeted cube.
	 * @param z	The given z coordinate of the targeted cube.
	 * @return	The cube at the give position.
	 * @throws	IllegalArgumentException
	 * 			The given coordinates are out of the bounds of this world.
	 */
	public Cube getCube(int x, int y, int z) throws IllegalArgumentException {
		if ((x < 0) || (y < 0) || (z <0) || (x > this.getNbCubesX()) || (y > this.getNbCubesY()) || (z > this.getNbCubesZ()))
				throw new IllegalArgumentException("Coordinates out of bounds!");
		return this.getCubeMatrix()[x][y][z];
	}
	
	/**
	 * Return the terrain type of the cube at a given position.
	 * @param x	The given x component of the targeted cube.
	 * @param y The given y component of the targeted cube.
	 * @param z The given z component of the targeted cube.
	 * @return	The terrain type of the cube at the given position.
	 * @throws	IllegalArgumentException
	 * 			The given coordinates are out of the bounds of this world.
	 */
	public int getCubeType(int x, int y, int z) throws IllegalArgumentException {
		return this.getCube(x, y, z).getTerrainType();
	}
	
	/**
	 * Set the cube of which the coordinates are given, to a given terrain type.
	 * @param x	The given x component of the targeted cube.
	 * @param y The given y component of the targeted cube.
	 * @param z The given z component of the targeted cube.
	 * @param terrainType	The given terrain type.
	 * @effect	The old cube is replaced by a new cube with the same position and the given terrain type.
	 * @throws IllegalArgumentException
	 * 			The given coordinates are outside of this world.
	 */
	public void setCubeType(int x, int y, int z, int terrainType) throws IllegalArgumentException {
		if(! this.isValidPosition(new PositionVector(x, y, z)))
			throw new IllegalArgumentException("Position not in this world!");
		Cube oldCube = this.getCube(x, y, z);
		this.replaceCube(this.mapCube(oldCube.getPosition(), oldCube.getContent(), terrainType));
	}
	
	/**
	 * Initialize this world's connected to border checker, making it match this world's cube matrix on the aspect of solid 
	 * and non-solid cubes.
	 * @effect	Iterates over this world's cube matrix and changes all cells in connectedToBorder to passable, where the respective
	 * cube is passable.
	 */
	private void initializeConnectedToBorder() {
		Cube[][][] cubeMatrix = this.getCubeMatrix();
		int x = 0;
		while(x < this.getNbCubesX()){
			int y = 0;
			while(y < this.getNbCubesY()){
				int z = 0;
				while(z < this.getNbCubesZ()){
					if (! cubeMatrix[x][y][z].isSolid()){
						this.connectedToBorder.changeSolidToPassable(x, y, z);
					}
					z++;
				}
				y++;
			}
			x++;
		}
	}
	
	/**
	 * Return the connected to border checker of this cube.
	 */
	@Basic @Raw @Immutable
	private ConnectedToBorder getConnectedToBorder() {
		return this.connectedToBorder;
	}
	
	/**
	 * Check whether this cube can have the given connected to border checker as its connected to border checker.
	 *  
	 * @param  connectedToBorder
	 *         The connected to border checker to check.
	 * @return 
	 *       | result == (connectedToBorder != null)
	*/
	@Raw
	private boolean canHaveAsConnectedToBorder(ConnectedToBorder connectedToBorder) {
		return (connectedToBorder != null);
	}
	
	/**
	 * Variable registering the connected to border checker of this cube.
	 */
	private final ConnectedToBorder connectedToBorder;
	
	/**
	 * Check this world's terrain for solid cubes that are not connected to a border and make the cave-in.
	 * @effect	Every solid cube in this world's cube matrix is checked to see if it's connected to a border. If not, it's caves-in.
	 */
	private void makeValidTerrain() {
		Cube[][][] cubeMatrix = this.getCubeMatrix();
		int x = 0;
		while(x < this.getNbCubesX()){
			int y = 0;
			while(y < this.getNbCubesY()){
				int z = 0;
				while(z < this.getNbCubesZ()){
					if(cubeMatrix[x][y][z].isSolid())
						if(! this.connectedToBorder.isSolidConnectedToBorder(x, y, z))
							this.singleCaveIn(x,y,z);
					z++;
				}
				y++;
			}
			x++;
		}
	}
	
	/**
	 * Collapses the cube at a given position, letting it change in air and dropping a material.
	 * @param position	The given position.
	 * @effect	The cube at the given position caves-in, if there is no material spawned by the cave-in, a material is added in the
	 * 			the center of the cube, a log if the cube was a tree, a boulder if the cube was a rock.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position or the cube at the given position is not solid.
	 */
	public void collapse(PositionVector	position) throws IllegalArgumentException {
		if((! this.isValidPosition(position)) || (! this.isSolidPosition(position)))
			throw new IllegalArgumentException("The given position is not a valid position for this world "
					+ "or the cube at it is not solid!");
		int x = (int) position.getXArgument();
		int y = (int) position.getYArgument();
		int z = (int) position.getZArgument();
		int terrain = this.getCubeType(x,y,z);
		Cube oldCube = this.getCube(x, y, z);
		@SuppressWarnings("unchecked")
		Set<GameObject> oldContent = (Set<GameObject>) oldCube.getContent().clone();
		this.caveIn(x, y, z);
		Cube newCube = this.getCube(x, y, z);
		Set<GameObject> newContent = newCube.getContent();
		boolean hasCaveInItem = (oldContent.size() < newContent.size());
		if((terrain == 1) &&(! hasCaveInItem)){
			Boulder boulder = new Boulder(PositionVector.centrePosition(new PositionVector(x, y, z)));
			boulder.changeWorld(this);
			this.addMaterial(boulder);
		}
		if((terrain == 2) &&(! hasCaveInItem)){
			Log log = new Log(PositionVector.centrePosition(new PositionVector(x, y, z)));
			log.changeWorld(this);	
			this.addMaterial(log);
		}
	}
	
	/**
	 * Makes the cube at the given position cave-in.
	 * @param x	The x coordinate of the targeted cube.
	 * @param y	The y coordinate of the targeted cube.
	 * @param z	The z coordinate of the targeted cube.
	 * @effect	Creates a new air cube, injects the content of the old cube into it and adds the item that spawns by the cave-in
	 * (if any is spawned) to it's content and finally replaces the old cube with the new air cube, adds a cave-in item if any is created
	 * and propagates the cave in to neighboring cubes that should cave-in.
	 * @throws	IllegalStateException
	 * 			The targeted cube is passable.
	 * @throws	IllegalArgumentException
	 * 			The given position is out of bounds.
	 * @note	By propagating the cave-in, than rather making the whole terrain of this world valid again, this method has a better
	 * 			performance.
	 */
	public void caveIn(int x, int y, int z) throws IllegalStateException, IllegalArgumentException {
		PositionVector position = new PositionVector(x, y, z);
		Cube cube = this.getCube(x,y,z);
		if(! cube.isSolid())
			throw new IllegalStateException("This cube is passable and thus cannot cave-in!");
		HashSet<GameObject> content = cube.getContent();
		int terrainType = cube.getTerrainType();
		cube = new Air(position, content);
		Material item = null;
		if (caveInItemCheck() == true){
			item = this.caveInItem(position, terrainType);
			item.changeWorld(this);
		}
		List<int[]> others = this.getConnectedToBorder().changeSolidToPassable(x, y, z);
		this.replaceCube(cube);
		if(item != null)
			this.addMaterial(item);
		this.propagateCaveIn(others);
	}
	
	/**
	 * Makes the cube at the given position cave-in.
	 * @param x	The x coordinate of the targeted cube.
	 * @param y	The y coordinate of the targeted cube.
	 * @param z	The z coordinate of the targeted cube.
	 * @effect	Creates a new air cube, injects the content of the old cube into it and adds the item that spawns by the cave-in
	 * (if any is spawned) to it's content and finally replaces the old cube with the new air cube.
	 * @throws	IllegalStateException
	 * 			The targeted cube is passable.
	 * @throws	IllegalArgumentException
	 * 			The given position is out of bounds.
	 */
	public void singleCaveIn(int x, int y, int z) throws IllegalStateException, IllegalArgumentException {
		PositionVector position = new PositionVector(x, y, z);
		Cube cube = this.getCube(x,y,z);
		if(! cube.isSolid())
			throw new IllegalStateException("This cube is passable and thus cannot cave-in!");
		HashSet<GameObject> content = cube.getContent();
		int terrainType = cube.getTerrainType();
		cube = new Air(position, content);
		Material item = null;
		if (caveInItemCheck() == true){
			item = this.caveInItem(position, terrainType);
			item.changeWorld(this);
		}
		this.replaceCube(cube);
		if(item != null)
			this.addMaterial(item);
	}
	/**
	 * Return whether by chance an item is spawned as a result of a cave-in.
	 * @return	0.25 chance for true.
	 */
	@Model @Raw
	private boolean caveInItemCheck() {
		boolean flag = false;
		Random generator = new Random();
		if (generator.nextInt(4) == 1)
			flag = true;
		return flag;
	}
	
	/**
	 * Return an item with a given position, the kind of item depends on the given terrain type.
	 * @param position	The given position.
	 * @param terrainType	The given terrain type.
	 * @return	A boulder with the given position as its position, if the given terrain type is 1 or 
	 * log with the given position as its position, if the given terrain type is 2.
	 * @throws IllegalArgumentException
	 * 			The given terrain type is neither 1 or 2.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	@Model @Raw
	private Material caveInItem(PositionVector position, int terrainType) throws IllegalArgumentException, NullPointerException {
		if(terrainType == 1)
			return new Boulder(PositionVector.centrePosition(position));
		if(terrainType == 2)
			return new Log(PositionVector.centrePosition(position));
		else
			throw new IllegalArgumentException("This terrain type doesn't spawn anything, when it collapses!");
	}
	
	/**
	 * Let the given new cube replace the old cube that is now occupying the new cube's position.
	 * @param newCube	The given new cube.
	 * @effect	The old cube at the new cube's position is replaced by the new cube in this world's cube matrix.
	 * @effect	The terrain type of the old cube is replaced by that of the new cube in this world's terrain matrix.
	 * @effect	Notifies this world's model listener that the terrain has changed.
	 * @effect	If the new cube is a workshop, it's position is added to this world's workshop set.
	 * @throws NullPointerException
	 * 			The given new cube is not effective.
	 */
	@Model
	private void replaceCube(Cube newCube) throws NullPointerException {
		int x = (int) newCube.getPosition().getXArgument();
		int y = (int) newCube.getPosition().getYArgument();
		int z = (int) newCube.getPosition().getZArgument();
		this.getCubeMatrix()[x][y][z] = newCube;
		this.getTerrainMatrix()[x][y][z] = newCube.getTerrainType();
		this.modelListener.notifyTerrainChanged(x, y, z);
		if(newCube.getTerrainType() == 3)
			this.getWorkshopSet().add(new PositionVector(x,y,z));
	}
	
	/**
	 * Causes cave-ins on the given positions if possible.
	 * @param positionsList	The given position collection.
	 * @effect	Checks all the given positions if they refer to a solid cube and if so, makes the cave-in.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 * @throws IllegalArgumentException
	 * 			One of the positions in the given position collection is not solid.
	 */
	private void propagateCaveIn(List<int[]> positionsList) throws NullPointerException, IllegalArgumentException {
		for(int[] position : positionsList){
			PositionVector vector = new PositionVector(position[0],position[1],position[2]);
			if(! this.isValidPosition(vector))
				throw new IllegalArgumentException("One of the given positions is not a valid position for this world!");
			if(this.isSolidPosition(vector))
				this.caveIn(position[0], position[1], position[2]);
		}
	}
	
	/**
	 * Return a set of all direct adjacent cubes of a given cube.
	 * @param cube	The given cube.
	 * @return	All the cubes that are directly adjacent to the given cube and that have a valid position for this world.
	 * @throws IllegalArgumentException
	 * 			The given cube is not located in this world.
	 * @throws NullPointerException
	 * 			The given cube is not effective.
	 */
	@SuppressWarnings("unused")
	private Set<Cube> getDirectAdjacentCubes(Cube cube) throws IllegalArgumentException, NullPointerException {
		if(! this.isValidPosition(cube.getPosition()))
			throw new IllegalArgumentException("Given cube not located in this world");
		int x = (int) cube.getPosition().getXArgument();
		int y = (int) cube.getPosition().getYArgument();
		int z = (int) cube.getPosition().getZArgument();
		Set<Cube> directAdjacents = new HashSet<Cube>();
		PositionVector[] allDirectAdjacents = {new PositionVector(x+1,y,z), new PositionVector(x-1,y,z), new PositionVector(x,y+1,z),
				new PositionVector(x,y-1,z), new PositionVector(x,y,z+1), new PositionVector(x,y,z-1)};
		for(PositionVector position : allDirectAdjacents){
			if(this.isValidPosition(position))
				directAdjacents.add(this.getCube((int) position.getXArgument(), (int) position.getYArgument(),
						(int) position.getZArgument()));
		}
		return directAdjacents;
	}
	
	
	
	/**
	 * Variable registering the maximum number of units allowed in any world.
	 */
	private static int maxNumberOfUnits = 100;
	
	
	/**
	 * Return the unit list of this world.
	 */
	@Basic @Raw
	public Set<Unit> getUnitSet() {
		return this.unitSet;
	}
	
	/**
	 * Check whether the given unit set is a valid unit set for
	 * any world.
	 *  
	 * @param  unitSet
	 *         The unit set to check.
	 * @return 
	 *       | result == (unitSet != null)	
	*/
	public static boolean isValidUnitSet(Set<Unit> unitSet) {
		return (unitSet != null);
	}
	
	/**
	 * Set the unit set of this world to the given unit set.
	 * 
	 * @param  unitSet
	 *         The new unit set for this world.
	 * @post   The unit set of this new world is equal to
	 *         the given unit set.
	 *       | new.getUnitSet() == unitSet
	 * @throws NullPointerException
	 *         The given unit list is not a valid unit set for any
	 *         world.
	 *       | ! isValidUnitSet(getUnitSet())
	 */
	@Raw
	public void setUnitSet(Set<Unit> unitSet) 
			throws NullPointerException {
		if (! isValidUnitSet(unitSet))
			throw new NullPointerException();
		this.unitSet = unitSet;
	}
	
	/**
	 * Variable registering the unit set of this world.
	 */
	private Set<Unit> unitSet;
	
	/**
	 * Add a given unit to this world.
	 * @param unit	The given unit.
	 * @effect	If the unit is from a faction that does not exist in this world, this faction is added to this world. the unit is added
	 * 			to this world's unit set and to the cube corresponding to its position, the given unit's world is set to this world.
	 * @throws	IllegalArgumentException
	 * 			This world can not have the given unit as one of it's units.
	 * @throws IllegalStateException
	 * 			The given unit's faction is not a valid faction for this world.
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {
		try{
			if(! this.canHaveAsUnit(unit))
				throw new IllegalArgumentException();
			if(! this.hasAsFaction(unit.getFaction()))
				this.addFaction(unit.getFaction());
			unit.changeWorld(this);
			this.getUnitSet().add(unit);
			int[] cubePosition = unit.getCubePosition();
			this.getCube(cubePosition[0], cubePosition[1], cubePosition[2]).addAsContent(unit);
		}
		catch (IllegalArgumentException exc){
			
		}
		catch (IllegalStateException exc){
			
		}
	}
	
	/**
	 * Remove a given unit from this world.
	 * @param unit	The given unit.
	 * @effect	The given unit is removed from this world's unit set and from this world's cube that had it as content, the given
	 * 			unit's world is set to null.
	 * @throws	IllegalArgumentException
	 * 			This world does not have the given unit in it's unit set.
	 * @throws	NullPointerException
	 * 			The given unit is not effective.
	 */
	public void removeUnit(Unit unit) throws NullPointerException {
		if(unit == null)
			throw new NullPointerException();
		if(! this.hasAsUnit(unit))
			throw new IllegalArgumentException("This world does not have the given unit as one of its units");
		PositionVector unitCubePosition = unit.getCubePositionVector();
		this.getUnitSet().remove(unit);
		this.getCube((int) unitCubePosition.getXArgument(), (int) unitCubePosition.getYArgument(),
				(int) unitCubePosition.getZArgument()).removeAsContent(unit);
		unit.changeWorld(null);
	}
	
	/**
	 * Check whether this world has the given unit.
	 * @param unit	The given unit.
	 * @return	True if and only if this world's unit set contains the given unit.
	 * @throws	NullPointerException
	 * 			The given unit is not effective.
	 */
	public boolean hasAsUnit(Unit unit) throws NullPointerException {
		if(unit == null)
			throw new NullPointerException();
		return this.getUnitSet().contains(unit);
	}
	
	/**
	 * Check whether this world can have the given unit as a unit.
	 * @param unit	The given unit.
	 * @return	True if and only if the unit's position is in this world and the unit is not already in this world and this world
	 * 			has not yet reached it's maximum amount of allowed units and when this world has reached its maximum amount
	 * 			of allowed faction, the given unit does not have a faction that's not in this world, and the given unit's
	 * 			cube position refers to a passable cube in this world.
	 * @throws 	NullPointerException
	 * 			The given unit is not effective.
	 */
	public boolean canHaveAsUnit(Unit unit) throws NullPointerException {
		if(unit == null)
			throw new NullPointerException();
		boolean flag1 = (this.isValidPosition(unit.getUnitPosition()));
		boolean flag2 = (! this.hasAsUnit(unit));
		boolean flag3 = (this.getUnitSet().size() < maxNumberOfUnits);
		boolean flag4 = (!((this.getFactionSet().size() > maxNbOfActiveFactions - 1) && (! this.hasAsFaction(unit.getFaction()))));
		boolean flag5 = (! this.getCube(unit.getCubePosition()[0],unit.getCubePosition()[1],unit.getCubePosition()[2]).isSolid());
		return (flag1 && flag2 && flag3 && flag4 && flag5);
	}
	
	/**
	 * Spawn a unit with a random position and random attribute values in this world.
	 * @param enableDefaultBehaviour	The status of the default behaviour of the to be spawned unit.
	 * @result	A random center cube position is generated, a name according to the number units in this world is generated.
	 * 			A unit with this generated name and position and with random attribute values is initialized and added to this world
	 * 			and the unit's world is changed to this world. The unit is returned.
	 */
	public Unit spawnUnit(boolean enableDefaultBehaviour) {
		PositionVector position = PositionVector.centrePosition(this.randomSpawnPosition());
		String name = "Unit";
		Unit unit = new Unit(position, name, this.autoFaction());
		if(enableDefaultBehaviour == true)
			unit.startDefaultBehaviour();
		unit.changeWorld(this);
		this.addUnit(unit);
		return unit;
	}
	
	/**
	 * Return a random position (integer coordinates) in this world.
	 * @return	A random position in integer coordinates, located in this world.
	 */
	public PositionVector randomPosition() {
		Random generator = new Random();
		int x = generator.nextInt(this.getNbCubesX());
		int y = generator.nextInt(this.getNbCubesY());
		int z = generator.nextInt(this.getNbCubesZ());
		return new PositionVector(x,y,z);
	}
	
	/**
	 * Return a position at which a game object can stand.
	 * @return	A position that is a valid standing position for a game object.
	 */
	public PositionVector randomStandingPosition() {
		PositionVector position = this.randomPosition();
		while(! isValidStandingPosition(position)){
			position = this.randomPosition();
		}
		return position;
	}
	
	/**
	 * Return a suitable spawn position for a unit.
	 * @return	A position that is not solid, that is either at z = 1 or has a solid cube underneath.
	 */
	public PositionVector randomSpawnPosition(){
		PositionVector position = this.randomPosition();
		while(!(!this.isSolidPosition(position) 
				&& ((((int) position.getZArgument() == 1) || ((int) position.getZArgument() == 0)) 
						|| this.isSolidPosition(new PositionVector(position.getXArgument(),position.getYArgument(),position.getZArgument()-1))))){
			position = this.randomPosition();
		}
		return position;
	}
	
	/**
	 * Check whether a game object could stand at the given position.
	 * @param position	The given position.
	 * @return	True, if and only if the cube at the given position is passable and either the adjacent cube underneath it is solid
	 * or the cube has z = 0.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given position is not located in this world.
	 */
	public boolean isValidStandingPosition(PositionVector position) throws NullPointerException, IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("position is not located in this world!");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		if(cube.isSolid())
			return false;
		if((int) position.getZArgument() == 0)
			return true;
		if(this.hasSolidAdjacent(cube))
			return true;
		else
			return false;
	}
	
	/**
	 * Check whether a given cube has a solid adjacent cube.
	 * @param cube	The given cube.
	 * @return	True if and only if there are positions that are solid in the collection of the given cube's adjacent positions.
	 * @throws	NullPointerException
	 * 			The given cube is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given cube is not from this world.
	 */
	public boolean hasSolidAdjacent(Cube cube) throws NullPointerException, IllegalArgumentException {
		PositionVector cubePosition = cube.getPosition();
		Set<PositionVector> allAdjacents = this.getAllAdjacentPositions(cubePosition);
		return (! this.getSolidsFromPositionSet(allAdjacents).isEmpty());
	}
	
	/**
	 * Check whether a given position is located within this world.
	 * @param position	The given position.
	 * @return	True is and only if all components are greater then or equal to zero and smaller than the number of cubes
	 * in this world along the respective component direction and the position is effective.
	 */
	public boolean isValidPosition(PositionVector position) {
		if(position == null)
			return false;
		double x = position.getXArgument();
		double y = position.getYArgument();
		double z = position.getZArgument();
		if((x < this.getNbCubesX()) && (y < this.getNbCubesY()) && (z < this.getNbCubesZ()) && (x >= 0) && (y >= 0) && (z >= 0))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Return the material set of this world.
	 */
	@Basic @Raw
	public Set<Material> getMaterialSet() {
		return this.materialSet;
	}
	
	/**
	 * Check whether the given material set is a valid material set for
	 * any world.
	 *  
	 * @param  material set
	 *         The material set to check.
	 * @return 
	 *       | result == (materialSet != null)
	*/
	public static boolean isValidMaterialSet(Set<Material> materialSet) {
		return (materialSet != null);
	}
	
	/**
	 * Set the material set of this world to the given material set.
	 * 
	 * @param  materialSet
	 *         The new material set for this world.
	 * @post   The material set of this new world is equal to
	 *         the given material set.
	 *       | new.getMaterialSet() == materialSet
	 * @throws NullPointerException
	 *         The given material set is not a valid material set for any
	 *         world.
	 *       | ! isValidMaterialSet(getMaterialSet())
	 */
	@Raw
	public void setMaterialSet(Set<Material> materialSet) 
			throws NullPointerException {
		if (! isValidMaterialSet(materialSet))
			throw new NullPointerException();
		this.materialSet = materialSet;
	}
	
	/**
	 * Variable registering the material set of this world.
	 */
	private Set<Material> materialSet;
	
	/**
	 * Add a given material to this world.
	 * @param material	The given material.
	 * @effect	The given material is added to the material set of this world and to the cube in which it is located, it's world is
	 * 			change to this world.
	 */
	public void addMaterial(Material material){
		this.getMaterialSet().add(material);
		this.getCube(material.getCubePosition()[0], material.getCubePosition()[1], 
				material.getCubePosition()[2]).addAsContent(material);
		material.changeWorld(this);
	}
	
	/**
	 * Remove a given material from this world's material set.
	 * @param material	The given material.
	 * @effect	The given material is removed from this world's material set and from the cube in which it was located.
	 * @throws	NullPointerException
	 * 			The given material is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given material is not in this world's material set.
	 */
	public void removeMaterial(Material material){
		if(material == null)
			throw new NullPointerException();
		if(! this.hasAsMaterial(material))
			throw new IllegalArgumentException();
		this.getMaterialSet().remove(material);
		this.getCube(material.getCubePosition()[0], material.getCubePosition()[1], 
				material.getCubePosition()[2]).removeAsContent(material);
	}
	
	/**
	 * Check whether a given material is in this world.
	 * @param material	The given material.
	 * @return	True if and only if this world's material set contains the given material.
	 * @throws NullPointerException
	 * 			The given material is not effective.
	 */
	public boolean hasAsMaterial(Material material) throws NullPointerException{
		if(material == null)
			throw new NullPointerException();
		return this.getMaterialSet().contains(material);
	}
	
//	/**
//	 * Check whether this world's material set is a proper material set for this world.
//	 * @return	true if and only if each material in this world's material set has a valid position.
//	 */
//	private boolean hasProperMaterialSet(){
//		for(Material material : this.getMaterialSet()){
//			if(! this.isValidPosition(material.getUnitPosition()))
//				return false;
//		}
//		return true;
//	}
	
	/**
	 * Advance the time for this world by a given amount of time.
	 * @param dt	The given amount of time.
	 * @effect	Time is advanced with the given amount of time for all units and and materials of this world. It's collections are 
	 * 			cleaned.
	 * @throws	IllegalArgumentException
	 * 			Time is negative.
	 */
	public void advanceTime(double dt)throws IllegalArgumentException {
		if (dt < 0) 
			throw new IllegalArgumentException();
		double time = dt;
		while(time > 0){
			double t = time;
			if(Util.fuzzyGreaterThanOrEqualTo(t, 0.2))
				t = 0.19;
			for(Unit unit : this.getUnitSet())
				unit.advanceTime(t);
			for(Material material : this.getMaterialSet())
				material.advanceTime(t);
			this.cleanCollections();
			time = time - t;
		}
	}
	
	/**
	 * Return the faction set of this world.
	 */
	@Basic @Raw @Model
	private Set<Faction> getFactionSet() {
		return this.factionSet;
	}
	
	/**
	 * Check whether the given faction set is a valid faction set for
	 * any world.
	 *  
	 * @param  faction set
	 *         The faction set to check.
	 * @return 
	 *       | result == (factionSet != null)
	*/
	private static boolean isValidFactionSet(Set<Faction> factionSet) {
		return (factionSet != null);
	}
	
	/**
	 * Set the faction set of this world to the given faction set.
	 * 
	 * @param  factionSet
	 *         The new faction set for this world.
	 * @post   The faction set of this new world is equal to
	 *         the given faction set.
	 *       | new.getFactionSet() == factionSet
	 * @throws NullPointerException
	 *         The given faction set is not a valid faction set for any
	 *         world.
	 *       | ! isValidFactionSet(getFactionSet())
	 */
	@Raw @Model
	private void setFactionSet(Set<Faction> factionSet) 
			throws NullPointerException {
		if (! isValidFactionSet(factionSet))
			throw new NullPointerException();
		this.factionSet = factionSet;
	}
	
	/**
	 * Variable registering the faction set of this world.
	 */
	private Set<Faction> factionSet;
	
	/**
	 * Add a given faction to this world.
	 * @param faction	The given faction.
	 * @effect	The given faction is added to this world's faction set.
	 * @throws IllegalStateException
	 * 			This world already reached it's maximum amount of factions.
	 * @throws	NullPointerException
	 * 			The given faction is not effective.
	 */
	public void addFaction(Faction faction) throws  IllegalStateException, NullPointerException{
		if(faction == null)
			throw new NullPointerException();
		try{if(! this.canHaveAsFaction(faction))
				throw new IllegalStateException();
			this.getFactionSet().add(faction);
		}
		catch (IllegalStateException exc) {
			
		}	
	}
	
	/**
	 * Remove the given faction from this world.
	 * @param faction	The given faction.
	 * @effect	Remove the given faction from this world's faction set.
	 * @throws NullPointerException
	 * 			The given faction is not effective.
	 * @throws IllegalArgumentException
	 * 			This world does not have the given faction in it's faction set.
	 */
	public void removeFaction(Faction faction) throws NullPointerException, IllegalArgumentException{
		if(faction == null)
			throw new NullPointerException();
		if(! this.hasAsFaction(faction))
			throw new IllegalArgumentException("Faction does not exist!");
		this.getFactionSet().remove(faction);
	}
	
	/**
	 * Check whether this world can have a given faction.
	 * @param faction	The given faction.	
	 * @return	True if and only if the given faction is effective, is not already a faction of this world, this world has not yet
	 * 			reached its maximum allowed amount of active factions or it has reached it and the given faction does not contain
	 * 			any units (not active).
	 */
	private boolean canHaveAsFaction(Faction faction){
		boolean flag1 = (faction != null);
		boolean flag2 = (! this.getFactionSet().contains(faction));
		boolean flag3 = ((this.getFactionSet().size()) < maxNbOfActiveFactions) || (((this.getFactionSet().size()) == maxNbOfActiveFactions)
				&& (faction.getUnitSet().isEmpty()));
		return (flag1 && flag2 && flag3);
	}
	
	/**
	 * Check whether this world has a given faction as one of its factions.
	 * @param faction	The given faction.
	 * @return	True if and only if this world's faction set contains the given faction.
	 * @throws NullPointerException
	 * 			The given faction is not effective.
	 */
	private boolean hasAsFaction(Faction faction) throws NullPointerException {
		if(faction == null)
			throw new NullPointerException();
		return this.getFactionSet().contains(faction);
	}
	
	/**
	 * Get all active factions of this world.
	 * @return	All factions from this world's faction set that are not empty.
	 */
	public Set<Faction> getActiveFactions() {
		Set<Faction> result = new HashSet<Faction>();
		for(Faction faction : this.getFactionSet()){
			if(! faction.getUnitSet().isEmpty())
				result.add(faction);
		}
		return result;
	}
	
	/**
	 * Variable registering the maximum amount of active factions allowed in any world.
	 */
	private static int maxNbOfActiveFactions = 5;
	
	/**
	 * Return an automatically chosen faction for a unit.
	 * @return	A new faction if this world has not yet reached its maximum amount of allowed active factions, or the faction with the least
	 * 			amount of units, when this world has already reached its maximum amount of factions.
	 */
	private Faction autoFaction() throws IllegalStateException{
		if(this.getActiveFactions().size() < maxNbOfActiveFactions)
			return new Faction();
		else{
			Faction smallestFaction = null;
			int smallestNbOfunits = Faction.getMaxNbOfUnits();
			for(Faction faction : this.getFactionSet()){
				int factionSize = faction.getUnitSet().size();
				if(factionSize < smallestNbOfunits)
					smallestFaction = faction;
					smallestNbOfunits = factionSize;
			}
			if(smallestFaction == null)
				throw new IllegalStateException("Max amount of factions reached and all full!");
			return smallestFaction;
		}
	}
	
	/**
	 * Return the position of the cube under the cube of the given position in this world.
	 * @param position	The given position.
	 * @return	The position of the cube directly under the cube of the given position.
	 * @throws IllegalArgumentException
	 * 			The given position is at the bottom of this world.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 */
	public PositionVector getCubePositionUnderneath(PositionVector position) throws IllegalArgumentException, NullPointerException {
		if(position.getZArgument() == 0)
			throw new IllegalArgumentException("Given position is at the bottom of this world, nothing underneath!");
		int x = (int) position.getXArgument();
		int y = (int) position.getYArgument();
		int z = (int) position.getZArgument() - 1;
		return this.getCube(x, y, z).getPosition();
	}
	
	/**
	 * Return a set with all adjacent cube positions in this world of a given position in this world, the given position excluded.
	 * @param position	The given position.
	 * @return	A set with all the adjacent positions of the given position, that are valid positions of this world.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public Set<PositionVector> getAllAdjacentPositions(PositionVector position) throws NullPointerException, IllegalArgumentException {
		if(position == null)
			throw new NullPointerException();
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world!");
		int x = (int) position.getXArgument();
		int y = (int) position.getYArgument();
		int z = (int) position.getZArgument();
		PositionVector[] allPossibilities = {new PositionVector(x-1,y,z), new PositionVector(x+1, y, z),
				new PositionVector(x, y-1, z), new PositionVector(x, y+1, z), new PositionVector(x, y, z-1),
				new PositionVector(x,y,z+1), new PositionVector(x+1,y+1,z+1), new PositionVector(x-1,y+1,z+1),
				new PositionVector(x+1,y-1,z+1), new PositionVector(x+1,y+1,z-1), new PositionVector(x-1,y+1,z-1),
				new PositionVector(x-1,y-1,z+1), new PositionVector(x+1,y-1,z-1), new PositionVector(x-1,y-1,z-1),
				new PositionVector(x+1,y+1,z), new PositionVector(x-1,y-1,z), new PositionVector(x+1,y-1,z),
				new PositionVector(x-1,y+1,z), new PositionVector(x+1,y,z+1), new PositionVector(x-1,y,z+1),
				new PositionVector(x+1,y,z-1), new PositionVector(x-1,y,z-1), new PositionVector(x,y+1,z+1), 
				new PositionVector(x,y+1,z-1), new PositionVector(x,y-1,z+1), new PositionVector(x,y-1,z-1)};
		HashSet<PositionVector> validAdjacents = new HashSet<PositionVector>();
		for(PositionVector adjacent : allPossibilities){
			if(this.isValidPosition(adjacent))
				validAdjacents.add(new PositionVector((int) adjacent.getXArgument(), 
						(int) adjacent.getYArgument(), (int) adjacent.getZArgument()));
		}
		return validAdjacents;
	}
	
	/**
	 * Check whether the cube at a given position is solid.
	 * @param position	The given position.
	 * @return	True if and only if the cube at the given position is solid.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 */
	public boolean isSolidPosition(PositionVector position) throws IllegalArgumentException, NullPointerException {
		if(position == null)
			throw new NullPointerException();
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("Not a valid position for this world!");
		return this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument()).isSolid();
	}
	
	/**
	 * Return the positions of solid cubes from a given set of positions.
	 * @param positionSet	The given set of positions.
	 * @return	A subset of the given position set, containing only the positions that refer to solid cubes.
	 * @throws	NullPointerException
	 * 			The given set of positions is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given set of positions contains positions that are not valid positions for this world.
	 */
	private Set<PositionVector> getSolidsFromPositionSet(Set<PositionVector> positionSet) 
			throws NullPointerException, IllegalArgumentException {
		if(positionSet == null)
			throw new NullPointerException();
		Set<PositionVector> solidSet = new HashSet<PositionVector>();
		for(PositionVector position : positionSet)	{
			if(this.isSolidPosition(position))
				solidSet.add(position);
		}
		return solidSet;
	}
	
	/**
	 * Return whether the cube at a given position is solid and is connected to the border through adjacent solid cubes.
	 * @param position	The given position.
	 * @return	True if and only if the cube at the given position is solid and connected to the border.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean isSolidConnectedToBorder(PositionVector position){
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world!");
		return this.getConnectedToBorder().isSolidConnectedToBorder((int) position.getXArgument(), (int) position.getYArgument()
				, (int) position.getZArgument());
	}
	
	/**
	 * Update the cubes in this world, so the given unit is in the cube corresponding to its current position.
	 * @param unit	The given unit.
	 * @param oldCubePosition	The old cube position of this unit.
	 * @param newCubePosition	The new cube position of this unit.
	 * @effect	If the given game object is still in the cube referred to by the given old cube position, it is removed from that
	 * 			cube. The given game object us added to the cube of the given new cube position.
	 * @throws NullPointerException
	 * 			The given unit or one of the given positions is not effective.
	 * @throws IllegalArgumentException
	 * 			This world does not contain the given unit or the given new cube position does not correspond to the given unit's
	 * 			current position.
	 */
	public void updateObjectPosition(GameObject gameObject, PositionVector oldCubePosition, PositionVector newCubePosition) 
			throws NullPointerException, IllegalArgumentException{
		if(((gameObject.getClass().equals(Unit.class)) && (! this.hasAsUnit((Unit) gameObject))) ||
				((gameObject.getClass().equals(Material.class)) && (! this.hasAsMaterial((Material) gameObject))))
			throw new IllegalArgumentException("This world does not contain the given unit!");
		Cube oldCube = this.getCube((int) oldCubePosition.getXArgument(), (int) oldCubePosition.getYArgument(),
				(int) oldCubePosition.getZArgument());
		if(oldCube.hasAsContent(gameObject))
			oldCube.removeAsContent(gameObject);
		Cube newCube = this.getCube((int) newCubePosition.getXArgument(), (int) newCubePosition.getYArgument(),
				(int) newCubePosition.getZArgument());
		newCube.addAsContent(gameObject);
	}
	
	/**
	 * Check whether the cube that is referred to by the given position is a workshop.
	 * @param position	The given position.
	 * @return	True if and only if the terrain type of the cube of the given position equals 3.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean isWorkshop(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return (cube.getTerrainType() == 3);
	}
	
	/**
	 * Check whether the cube that is referred to by the given position is a tree.
	 * @param position	The given position.
	 * @return	True if and only if the terrain type of the cube of the given position equals 2.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean isWood(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return (cube.getTerrainType() == 2);
	}
	
	/**
	 * Check whether the cube that is referred to by the given position is a rock.
	 * @param position	The given position.
	 * @return	True if and only if the terrain type of the cube of the given position equals 1.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean isRock(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return (cube.getTerrainType() == 1);
	}
	
	/**
	 * Check whether the cube that is referred to by a given position contains a boulder.
	 * @param position	The given position.
	 * @return	True if and only if the cube at the given position contains a boulder.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean containsBoulder(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return cube.containsBoulder();
	}
	
	/**
	 * Check whether the cube that is referred to by a given position contains a log.
	 * @param position	The given position.
	 * @return	True if and only if the cube at the given position contains a log.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public boolean containsLog(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position for this world.");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return cube.containsLog();
	}
	
	
	/**
	 * Get a boulder from the cube at a given position.
	 * @param position	The given position.
	 * @return	A boulder from the cube that is referred to by the given position.
	 * @throws IllegalArgumentException
	 * 			The cube that is referred to by the given position does not contain a boulder.
	 */
	public Boulder getABoulder(PositionVector position) throws IllegalArgumentException {
		if(! this.containsBoulder(position))
			throw new IllegalArgumentException("The given position does not contain a boulder!");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return cube.getABoulder();
	}
	
	/**
	 * Get a log from the cube at a given position.
	 * @param position	The given position.
	 * @return	A log from the cube that is referred to by the given position.
	 * @throws IllegalArgumentException
	 * 			The cube that is referred to by the given position does not contain a log.
	 */
	public Log getALog(PositionVector position) throws IllegalArgumentException {
		if(! this.containsLog(position))
			throw new IllegalArgumentException("The given position does not contain a log!");
		Cube cube = this.getCube((int) position.getXArgument(), (int) position.getYArgument(), (int) position.getZArgument());
		return cube.getALog();
	}
	
	/**
	 * Return a set of all adjacent standing positions of a given position, the given position excluded.
	 * @param position	The given position.
	 * @return	A set of positions that are standing positions and adjacent positions of the given position.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public Set<PositionVector> getAdjacentStandingPositions(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("The given position is not a valid position!");
		Set<PositionVector> allAdjacents = this.getAllAdjacentPositions(position);
		Set<PositionVector> standingAdjacents = new HashSet<PositionVector>();
		for(PositionVector adjacent : allAdjacents){
			if(this.isValidStandingPosition(adjacent)){
				standingAdjacents.add(adjacent);
			}
		}
		return standingAdjacents;
	}
	
	/**
	 * Return a set of all units in the cube of the given position and all units in adjacent cubes of the cube of the given position.
	 * @param position	The given position.
	 * @return	A set containing all the not-terminated units of the cube of the given position and its adjacent cubes.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public Set<Unit> getAdjacentUnits(PositionVector position) throws IllegalArgumentException {
		if(! this.isValidPosition(position))
			throw new IllegalArgumentException("Not a valid position for this world!");
		Set<PositionVector> adjacentStandingPositions = this.getAdjacentStandingPositions(position);
		Set<Unit> adjacentUnits = new HashSet<Unit>();
		adjacentStandingPositions.add(position);
		for(PositionVector adjacent : adjacentStandingPositions){
			Cube cube = this.getCube((int) adjacent.getXArgument(), (int) adjacent.getYArgument(), (int) adjacent.getZArgument());
			for(Unit unit : cube.getUnits()){
				if(! unit.isTerminated())
					adjacentUnits.add(unit);
			}
		}
		return adjacentUnits;
	}
	
	/**
	 * Get the enemies in neighboring cubes of this unit, including the unit's cube.
	 * @param unit	The given unit.
	 * @return	All adjacent units, that are not from this unit's faction.
	 * @throws IllegalArgumentException
	 * 			The given world does not have the given unit as one of it's units.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 */
	public Set<Unit> getAdjacentEnemies(Unit unit) throws IllegalArgumentException, NullPointerException {
		if(! this.hasAsUnit(unit))
			throw new IllegalArgumentException("This world does not have the given unit as one of its units!");
		Faction allyFaction = unit.getFaction();
		Set<Unit> adjacentUnits = this.getAdjacentUnits(unit.getCubePositionVector());
		Set<Unit> result = new HashSet<>();;
		for(Unit adjacentUnit : adjacentUnits)
			result.add(adjacentUnit);
		for(Unit adjacentUnit : adjacentUnits){
			if(adjacentUnit.getFaction().equals(allyFaction))
				result.remove(adjacentUnit);
		}
		return result;
	}
	
	/**
	 * Get all boulders in this world.
	 * @return	A set of all boulders that are in this unit's material set.
	 */
	public Set<Boulder> getBoulders() {
		Set<Boulder> result = new HashSet<Boulder>();
		for(Material material : this.getMaterialSet()){
			if(material.getClass().equals(Boulder.class))
				result.add((Boulder) material);
		}
		return result;
	}
	
	/**
	 * Get all logs in this world.
	 * @return	A set of all logs that are in this unit's material set.
	 */
	public Set<Log> getLogs() {
		Set<Log> result = new HashSet<Log>();
		for(Material material : this.getMaterialSet()){
			if(material.getClass().equals(Log.class))
				result.add((Log) material);
		}
		return result;
	}

	/**
	 * Return the length of every cube.
	 * @return	The cube length of the cube class.
	 */
	public int getCubeLength() {
		return Cube.getSideLength();
	}
	
	/**
	 * Check whether the straight line between the centers of the cubes of the given positions goes through a solid cube in this world.
	 * @param position	The given position.
	 * @param adjacent	The given adjacent position, being adjacent to the given position.
	 * @return	True if and only if one of the cubes of the given positions is solid or a cube through which the path between the 2 
	 * 			centers passes is solid.
	 * @throws IllegalArgumentException
	 * 			The given positions are not adjacent.
	 */
	public boolean hasSolidCornerInBetween(PositionVector position, PositionVector adjacent) throws IllegalArgumentException{
		if(! this.areAdjacents(position,adjacent))
			throw new IllegalArgumentException();
		// one of the given positions is solid
		if((this.isSolidPosition(position)) || (this.isSolidPosition(adjacent)))
			return true;
		PositionVector path = PositionVector.calcDifferenceVector(PositionVector.centrePosition(position), 
				PositionVector.centrePosition(adjacent));
		double x = path.getXArgument();
		double y = path.getYArgument();
		double z = path.getZArgument();
		// they are direct adjacent, thus no corners
		if(this.areDirectAdjacents(position,adjacent))
			return false;
		PositionVector[] inBetweenPositions = {new PositionVector(x,0,0),new PositionVector(0,y,0),new PositionVector(0,0,z),
				new PositionVector(x,y,0),new PositionVector(x,0,z),new PositionVector(0,y,z)};
		boolean flag = false;
		for(PositionVector inBetweenPosition : inBetweenPositions){
			PositionVector realPosition = PositionVector.sum(inBetweenPosition, position);
			if((this.isValidPosition(realPosition)) && (this.isSolidPosition(realPosition))){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * Check whether 2 given positions refer to 2 adjacent cubes in this world.
	 * @param position1	The first position that is given.
	 * @param position2	The second position that is given.
	 * @return	True if and only if all the absolute values of the components of the difference vector between the 
	 * 			2 given positions are equal to or smaller than 1 and the 2 given positions don't refer to the same cube.
	 * @throws IllegalArgumentException
	 * 			The given positions are not valid positions for this world.
	 */
	public boolean areAdjacents(PositionVector position1, PositionVector position2) throws IllegalArgumentException {
		if((! this.isValidPosition(position1)) || (! this.isValidPosition(position2)))
			throw new IllegalArgumentException();
		PositionVector temp1 = PositionVector.centrePosition(position1);
		PositionVector temp2 = PositionVector.centrePosition(position2);
		PositionVector differenceVector = PositionVector.calcDifferenceVector(temp1, temp2);
		return((Math.abs(differenceVector.getXArgument()) <= 1.0) && (Math.abs(differenceVector.getYArgument()) <= 1.0)
				&& (Math.abs(differenceVector.getZArgument()) <= 1.0) && (Math.abs(differenceVector.getXArgument()) + 
						Math.abs(differenceVector.getYArgument()) + Math.abs(differenceVector.getZArgument()) != 0.0));
		
	}
	
	/**
	 * Check whether the cubes of 2 given positions are directly adjacent in this world.
	 * @param position1	The first position that is given.
	 * @param position2	The second position that is given.
	 * @return	True if and only if the distance between the centers of the cubes of the given positions is 1 and the cubes
	 * 			are adjacent.
	 */
	public boolean areDirectAdjacents(PositionVector position1, PositionVector position2) throws IllegalArgumentException{
		if(! this.areAdjacents(position1, position2))
			return false;
		PositionVector temp1 = PositionVector.centrePosition(position1);
		PositionVector temp2 = PositionVector.centrePosition(position2);
		PositionVector differenceVector = PositionVector.calcDifferenceVector(temp1, temp2);
		return (Math.abs(differenceVector.getXArgument()) + Math.abs(differenceVector.getYArgument()) 
															+ Math.abs(differenceVector.getZArgument()) == 1);
	}
	
	/**
	 * Return a collection of all positions of adjacent that are reachable from the cube of the given standing position in this world.
	 * @param standingPosition	The given standing position.
	 * @return	A set with all adjacent standing positions that don't have a solid corner in between them and the given 
	 * 			standing position.
	 * @throws IllegalArgumentException
	 * 			The given standing position is not a valid standing position in this world.
	 */
	public Set<PositionVector> getReachableAdjacents(PositionVector standingPosition) throws IllegalArgumentException{
		if(! this.isValidStandingPosition(standingPosition))
			throw new IllegalArgumentException();
		Set<PositionVector> adjacentStandingPositions = this.getAdjacentStandingPositions(standingPosition);
		Set<PositionVector> reachablePositions = new HashSet<PositionVector>();
		for(PositionVector position : adjacentStandingPositions){
			if(! this.hasSolidCornerInBetween(standingPosition, position))
				reachablePositions.add(position);
		}
		return reachablePositions;
	}
	
	/**
	 * Returns a list of all positions of all cubes that make up the path from the given start position to the given destination
	 * in this world (start and destination included), returns an empty set if the destination is not reachable from 
	 * the given start position.
	 * @param startPosition	The given start position.
	 * @param destination	The given destination.
	 * @return	The shortest walkable path (as a list) that is in the map of all the path possibilities.
	 * @return	An empty list if the given destination can't be reached, starting from the given start position.
	 * @throws IllegalArgumentException
	 * 			The given start and/or destination are not valid standing positions in this world.
	 * @throws	IllegalArgumentException
	 * 			The given positions are the same.
	 */
	public List<PositionVector> determinePath(PositionVector startPosition, PositionVector destination) throws IllegalArgumentException{
		if(startPosition.equals(destination))
			throw new IllegalArgumentException();
		PositionVector start = PositionVector.getIntegerPositionVector(startPosition);
		PositionVector end = PositionVector.getIntegerPositionVector(destination);
		Map<PositionVector,Integer> allPossibilities = this.getAllPathPossibilities(start, end);
		List<PositionVector> path = new ArrayList<PositionVector>();
		if(allPossibilities.isEmpty())
			return path;
		path.add(start);
		allPossibilities.remove(start);
		PositionVector i = start;
		while(! i.equals(end)){
			Set<PositionVector> reachables = this.getReachableAdjacents(i);
			PositionVector best = null;
			int distance = -1;
			double accurateDistance = 0.0;
			double distanceToDestination = 0.0;
			for(PositionVector reachable: reachables){
				if((allPossibilities.containsKey(reachable)) && ((allPossibilities.get(reachable) <= distance) || (distance == -1))){
					if((allPossibilities.get(reachable) < distance) || (distance == -1)){
						distance = allPossibilities.get(reachable);
						accurateDistance = PositionVector.calcDistance(i, reachable);
						distanceToDestination = PositionVector.calcDistance(reachable, end);
						best = reachable;
					}
					else if((allPossibilities.get(reachable) == distance) && 
							((PositionVector.calcDistance(i, reachable) < accurateDistance))){
						accurateDistance = PositionVector.calcDistance(i, reachable);
						distanceToDestination = PositionVector.calcDistance(reachable, end);
						best = reachable;
					}
					else if((allPossibilities.get(reachable) == distance) && 
							((PositionVector.calcDistance(i, reachable) == accurateDistance))
							&& (PositionVector.calcDistance(reachable, end) <= distanceToDestination)){
						distanceToDestination = PositionVector.calcDistance(reachable, end);
						best = reachable;
					}
				}
			}
			path.add(best);
			i = best;
		}
		return path;
	}
	
	/**
	 * Returned the reversed version of the given list of positions, last position first and first position last.
	 * @param positionList	The given list of positions.
	 * @return	A list having the positions of the given position list in reversed order.
	 * @throws NullPointerException
	 * 			The given list of positions is not effective.
	 */
	public static List<PositionVector> reversePositionList(List<PositionVector> positionList) throws NullPointerException{
		List<PositionVector> reversedList = new ArrayList<PositionVector>();
		int i = positionList.size() - 1;
		while(i >= 0){
			reversedList.add(positionList.get(i));
			i--;
		}
		return reversedList;
	}
	
	/**
	 * Return a map, containing all possible positions of cubes on a path between a given start position and destination in this
	 * world, map is empty if the destination can't be reached.
	 * @param startPosition	The given start position.
	 * @param destination	The given destination.
	 * @return	A map containing all the positions of the cubes of the possible shortest paths, mapped with their distance from 
	 * 			the destination and containing the destination itself.
	 * @return	An empty map if the given destination is not reachable, when starting from the given start position.
	 * @throws IllegalArgumentException
	 * 			The given start and/or destination are not valid standing positions in this world.
	 */
	public Map<PositionVector,Integer> getAllPathPossibilities(PositionVector startPosition, PositionVector destination) 
																							throws IllegalArgumentException{
		if((! this.isValidStandingPosition(startPosition)) || (! this.isValidStandingPosition(destination)))
			throw new IllegalArgumentException();
		PositionVector start = PositionVector.getIntegerPositionVector(startPosition);
		PositionVector end = PositionVector.getIntegerPositionVector(destination);
		Map<PositionVector,Integer> possibilities = new HashMap<>();
		possibilities.put(end, 0);
		List<PositionVector> queue = new ArrayList<>();
		queue.add(end);
		int distance = 1;
		while((! possibilities.containsKey(start)) && (! queue.isEmpty())){
			Set<PositionVector> temp = new HashSet<>();
			for(PositionVector position : queue){
				Set<PositionVector> reachables = this.getReachableAdjacents(position);
				for(PositionVector reachable : reachables){
					if(! possibilities.containsKey(reachable))
						temp.add(reachable);
				}
			}
			queue.removeAll(queue);
			for(PositionVector tempPosition : temp){
				queue.add(tempPosition);
				possibilities.put(tempPosition, distance);
			}
			distance++;
		}
		if(queue.isEmpty())
			return new HashMap<PositionVector,Integer>();
		return possibilities;
	}
	
	/**
	 * Makes this world's unit and material set proper sets.
	 * @effect	Goes over this world's unit and material set, checks whether this world can have its materials as it's materials and 
	 * 			its units as it's units. If that's not possible for one, it is removed from the list it belonged to.
	 */
	private void cleanCollections() {
		Set<Unit> unitSet = new HashSet<>();
		for(Unit unit : this.getUnitSet())
			unitSet.add(unit);
		Set<Material> materialSet = new HashSet<>();
		for(Material material : this.getMaterialSet())
			materialSet.add(material);
		for(Unit unit : unitSet){
			if(! this.isValidWorldUnit(unit))
				this.removeUnit(unit);
		}
		for(Material material : materialSet) {
			if(! this.canHaveAsMaterial(material))
				this.removeMaterial(material);
		}
	}
	
	/**
	 * Check whether this world can have a given material as it's material.
	 * @param material	The given material.
	 * @return	True if and only if the given material's position is a valid position for this world.
	 * @throws NullPointerException
	 * 			The given material is not effective.
	 */
	private boolean canHaveAsMaterial(Material material) throws NullPointerException{
		return this.isValidPosition(material.getCubePositionVector());
	}
	
	/**
	 * Check whether a given unit from this world is a valid unit for this world.
	 * @param unit	The given unit.
	 * @return	True if and only if the given unit has a valid position for this world and is not located in a solid cube and is
	 * 			not terminated.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given unit is not in this world.
	 */
	private boolean isValidWorldUnit(Unit unit) throws NullPointerException{
		if(unit == null)
			throw new NullPointerException();
		if(! this.hasAsUnit(unit))
			throw new IllegalArgumentException();
		boolean flag1 = (this.isValidPosition(unit.getUnitPosition()));
		boolean flag2 = (! this.getCube(unit.getCubePosition()[0],unit.getCubePosition()[1],unit.getCubePosition()[2]).isSolid());
		boolean flag3 = (! unit.isTerminated());
		return (flag1 && flag2 && flag3);
	}
	
	/**
	 * Check whether a given target position can be reached from a given current position.
	 * @param targetPosition	The targeted position.
	 * @param currentPosition	The current position.
	 * @return	True if and only if no path can be determined to reach the given target position, starting from the given current
	 * 			position.
	 * @throws NullPointerException
	 * 			The given target position and/or current position is/are not effective.
	 * @throws IllegalArgumentException
	 * 			The given target position and/or current position is/are not valid positions for this world or are the same.
	 */
	public boolean isAccessible(PositionVector targetPosition, PositionVector currentPosition) throws NullPointerException,
																										IllegalArgumentException {
		return (! this.determinePath(currentPosition, targetPosition).isEmpty());
	}
	
	/**
	 * Return the position of a boulder in this world, that is accessible from a given position.
	 * @param position	The given position.
	 * @return	The position of a material from this world's material list, that is a boulder and is accessible from the given
	 * 			position. If none exist, return null.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public PositionVector getAccessibleBoulder(PositionVector position) throws NullPointerException, IllegalArgumentException{
		// since isAccessible throws exception when positions are the same
		if(this.cubeHasBoulder(position))
			return this.getABoulder(position).getCubePositionVector();
		for(Material material : this.getMaterialSet()){
			if(material.getClass().equals(Boulder.class)){
				if(this.isAccessible(material.getCubePositionVector(), position))
					return material.getCubePositionVector();
			}
		}
		return null;
	}
	
	/**
	 * Return the position of a log in this world, that is accessible from a given position.
	 * @param position The given position.
	 * @return	The position of a material from this world's material list, that is a log and is accessible from the given
	 * 			position. If none exist, return null.
	 * @throws	NullPointerException
	 * 			The given position is not effective.
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public PositionVector getAccessibleLog(PositionVector position) throws NullPointerException, IllegalArgumentException{
		// since isAccessible throws exception when positions are the same
		if(this.cubeHasLog(position))
			return this.getALog(position).getCubePositionVector();
		for(Material material : this.getMaterialSet()){
			if(material.getClass().equals(Log.class)){
				if(this.isAccessible(material.getCubePositionVector(), position))
					return material.getCubePositionVector();
			}
		}
		return null;
	}
	
	/**
	 * Return the position of a workshop in this world, that is accessible from a given position.
	 * @param position	The given position.
	 * @return	An element from this world's workshop set, that is accessible from the given position.
	 * 			If non exist, return null.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public PositionVector getAccesibleWorkshop(PositionVector position) throws NullPointerException, IllegalArgumentException{
		for(PositionVector workshop : this.getWorkshopSet()){
			if(this.isAccessible(workshop, position))
				return workshop;
		}
		return null;
	}
	
	/**
	 * Return the workshop set of this world.
	 */
	@Basic @Raw
	public Set<PositionVector> getWorkshopSet() {
		return this.workshopSet;
	}
	
	/**
	 * Check whether the given workshop set is a valid workshop set for
	 * any world.
	 *  
	 * @param  workshop set
	 *         The workshop set to check.
	 * @return 
	 *       | result == (workshopSet != null)
	*/
	public static boolean isValidWorkshopSet(Set<PositionVector> workshopSet) {
		return (workshopSet != null);
	}
	
	/**
	 * Set the workshop set of this world to the given workshop set.
	 * 
	 * @param  workshopSet
	 *         The new workshop set for this world.
	 * @post   The workshop set of this new world is equal to
	 *         the given workshop set.
	 *       | new.getWorkshopSet() == workshopSet
	 * @throws NullPointerException
	 *         The given workshop set is not a valid workshop set for any
	 *         world.
	 *       | ! isValidWorkshopSet(getWorkshopSet())
	 */
	@Raw
	private void setWorkshopSet(Set<PositionVector> workshopSet) 
			throws NullPointerException {
		if (! isValidWorkshopSet(workshopSet))
			throw new NullPointerException();
		this.workshopSet = workshopSet;
	}
	
	/**
	 * Variable registering the workshop set of this world.
	 */
	private Set<PositionVector> workshopSet;
	
	/**
	 * Check whether a cube at a given position in this world, contains a log.
	 * @param position	The given position.
	 * @return	True if and only if there is an object in the cube's content that is a log. The cube being the cube referred to
	 * 			by the given position.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 */
	public boolean cubeHasLog(PositionVector position) throws IllegalArgumentException, NullPointerException{
		Cube cube = this.getCube((int)position.getXArgument(), (int)position.getYArgument(), (int)position.getZArgument());
		for(GameObject item : cube.getContent()){
			if(item.getClass().equals(Log.class))
				return true;
		}
		return false;
	}
	
	/**
	 * Check whether a cube at a given position in this world, contains a boulder.
	 * @param position	The given position.
	 * @return	True if and only if there is an object in the cube's content that is a boulder. The cube being the cube referred to
	 * 			by the given position.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 */
	public boolean cubeHasBoulder(PositionVector position) throws IllegalArgumentException, NullPointerException{
		Cube cube = this.getCube((int)position.getXArgument(), (int)position.getYArgument(), (int)position.getZArgument());
		for(GameObject item : cube.getContent()){
			if(item.getClass().equals(Boulder.class))
				return true;
		}
		return false;
	}
	
	/**
	 * Return an accessible ally for this unit in this world.
	 * @param unit	The given unit.
	 * @return	A unit from this world's unit set that is not the given unit, but belongs to the same faction and is accessible
	 * 			from the given unit's position. Null if none were found.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * @throws IllegalArgumentException
	 * 			The given unit does not belong to this world.
	 */
	public Unit getAccessibleAlly(Unit unit) throws NullPointerException, IllegalArgumentException{
		if(! this.getUnitSet().contains(unit))
			throw new IllegalArgumentException();
		Faction faction = unit.getFaction();
		for(Unit ally : this.getUnitSet()){
			if((! ally.equals(unit)) && (ally.getFaction().equals(faction)) 
					&& (this.isAccessible(ally.getCubePositionVector(), unit.getCubePositionVector())))
				return ally;
		}
		return null;
	}
	
	/**
	 * Return an accessible enemy for this unit in this world.
	 * @param unit	The given unit.
	 * @return	A unit from this world's unit set that belongs to a different faction than the given unit and is accessible
	 * 			from the given unit's position. Null if none were found.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * @throws IllegalArgumentException
	 * 			The given unit does not belong to this world.
	 */
	public Unit getAccessibleEnemy(Unit unit) throws NullPointerException, IllegalArgumentException{
		if(! this.getUnitSet().contains(unit))
			throw new IllegalArgumentException();
		Faction faction = unit.getFaction();
		for(Unit enemy : this.getUnitSet()){
			if((! enemy.getFaction().equals(faction)) 
					&& (this.isAccessible(enemy.getCubePositionVector(), unit.getCubePositionVector())))
				return enemy;
		}
		return null;
	}
	
	/**
	 * Return an accessible unit for this unit in this world.
	 * @param unit	The given unit.
	 * @return	A unit from this world's unit set that is not this unit and is accessible
	 * 			from the given unit's position. Null if none were found.
	 * @throws NullPointerException
	 * 			The given unit is not effective.
	 * @throws IllegalArgumentException
	 * 			The given unit does not belong to this world.
	 */
	public Unit getAccessibleUnit(Unit unit) throws NullPointerException, IllegalArgumentException{
		if(! this.getUnitSet().contains(unit))
			throw new IllegalArgumentException();
		for(Unit resultUnit : this.getUnitSet()){
			if((! resultUnit.equals(unit)) 
					&& (this.isAccessible(resultUnit.getCubePositionVector(), unit.getCubePositionVector())))
				return resultUnit;
		}
		return null;
	}
	
	/**
	 * Return an adjacent standing position in this world for the given position.
	 * @param position	The given position.
	 * @return	An element from the list of all adjacent standing positions for the given position.
	 * @throws NullPointerException
	 * 			The given position is not effective.
	 * @throws IllegalArgumentException
	 * 			The given position is not a valid position for this world.
	 */
	public PositionVector getAdjacentStandingPosition(PositionVector position) throws NullPointerException, IllegalArgumentException{
		Set<PositionVector> adjacents = this.getAdjacentStandingPositions(position);
		return adjacents.iterator().next();
		
	}
	
	/**
	 * Check whether two given units of this world are allies.
	 * @param unit	The first given unit.
	 * @param otherUnit	The second given unit.
	 * @return	True if and only if the two given units belong to the same faction.
	 * @throws NullPointerException
	 * 			At least one of the given units is not effective.
	 * @throws IllegalArgumentException
	 * 			At least one of the given unit's does not belong to this world.
	 */
	public boolean areAllies(Unit unit, Unit otherUnit) throws NullPointerException, IllegalArgumentException{
		if((! this.hasAsUnit(unit)) && (! this.hasAsUnit(otherUnit)))
			throw new IllegalArgumentException();
		return unit.getFaction().equals(otherUnit.getFaction());
	}
}

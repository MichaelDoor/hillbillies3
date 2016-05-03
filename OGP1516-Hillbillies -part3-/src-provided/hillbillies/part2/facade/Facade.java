package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.PositionVector;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {

	public Facade() {
	}

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		try{Unit hillbillie = new Unit((new PositionVector(initialPosition[0],initialPosition[1],initialPosition[2])), name,
				strength, agility, toughness, weight);
		if(enableDefaultBehavior == true) {
			hillbillie.startDefaultBehaviour();
		}
		else {
			hillbillie.stopDefaultBehaviour();
		}
		return hillbillie;
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		try{
			PositionVector position = unit.getUnitPosition();
			double[] coordinates = {position.getXArgument(),position.getYArgument(),position.getZArgument()};
			return coordinates;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		return unit.getCubePosition();
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try {
			unit.changeName(newName);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getEffectiveWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return unit.getMaxHP();
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return unit.getCurrentHP();
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return unit.getMaxStamina();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return unit.getCurrentStamina();
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		try{
			unit.advanceTime(dt);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		try{
			unit.moveToAdjacent((new PositionVector(dx, dy, dz)));
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
		catch (IllegalStateException exc){
			throw new ModelException();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		PositionVector velocity = unit.getCurrentVelocity();
		double speed = PositionVector.calcDistance((new PositionVector(0,0,0)), velocity);
		return speed;
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		String activityStatus = unit.getActivityStatus();
		if(activityStatus == null)
			return false;
		return (unit.getActivityStatus().equals("move"));
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		unit.setSprint(true);
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		unit.setSprint(false);
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		return unit.getSprint();
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		try{
			unit.moveTo((new PositionVector(cube[0], cube[1], cube[2])));
		}
		catch (IllegalArgumentException exc){
			throw new ModelException("Move problem");
		}
		catch (NullPointerException exc){
			throw new ModelException("Move problem");
		}
	}

	@Override
	public void work(Unit unit) throws ModelException {

	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		String activityStatus = unit.getActivityStatus();
		if(activityStatus == null)
			return false;
		return (unit.getActivityStatus().equals("work"));
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		try{
			attacker.attack(defender);
		}
		catch (IllegalStateException exc){
			throw new ModelException("fight problem");
		}
		catch (IllegalArgumentException exc){
			throw new ModelException("fight problem");
		}
		catch (NullPointerException exc){
			throw new ModelException("fight problem");
		}
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		String  activityStatus = unit.getActivityStatus();
		if(activityStatus == null)
			return false;
		return (unit.getActivityStatus().equals("attack"));
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		try{
			unit.rest();
		}
		catch (IllegalStateException exc){
			throw new ModelException("rest problem");
		}
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		String activityStatus = unit.getActivityStatus();
		if(activityStatus == null)
			return false;
		return (unit.getActivityStatus().equals("rest"));
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		if(value){
			unit.startDefaultBehaviour();
		}
		else {
			unit.stopDefaultBehaviour();
		}
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return unit.getDefaultBehaviour();
	}

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		try{ 
			return new World(terrainTypes, modelListener);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		return world.getNbCubesX();
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		return world.getNbCubesY();
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		return world.getNbCubesZ();
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		try{
			world.advanceTime(dt);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		try{
			return world.getCubeType(x, y, z);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException();
		}
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		try{
			world.setCubeType(x, y, z, value);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		try{
			return world.isSolidConnectedToBorder(new PositionVector(x, y, z));
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		return world.spawnUnit(enableDefaultBehavior);
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		try{
			unit.changeWorld(world);
			world.addUnit(unit);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.getUnitSet();
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		return unit.isCarryingLog();
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		return unit.isCarryingBoulder();
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		return (! unit.isTerminated());
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		return unit.getExp();
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		try{
			unit.work(new PositionVector(x,y,z));
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
		catch (IllegalStateException exc) {
			throw new ModelException();
		}
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		return unit.getFaction();
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		return faction.getUnitSet();
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		return world.getActiveFactions();
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		PositionVector position = boulder.getUnitPosition();
		double[] result = {position.getXArgument(), position.getYArgument(), position.getZArgument()};
		return result;
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		return world.getBoulders();
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		PositionVector position = log.getUnitPosition();
		double[] result = {position.getXArgument(), position.getYArgument(), position.getZArgument()};
		return result;
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		return world.getLogs();
	}

}

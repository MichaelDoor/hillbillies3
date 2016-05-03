package hillbillies.part1.facade;

import hillbillies.model.PositionVector;
import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{
	
	public Facade(){
		
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
		PositionVector position = unit.getUnitPosition();
		double[] coordinates = {position.getXArgument(),position.getYArgument(),position.getZArgument()};
		return coordinates;
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
		return unit.getWeight();
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
		try{
			unit.work();
		}
		catch (IllegalStateException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		return (unit.getActivityStatus().equals("work"));
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		try{
			attacker.attack(defender);
			defender.defend(attacker);
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

}

package expression.unit;

import objects.Unit;

public class EnemyExpression extends UnitExpression {

	public EnemyExpression() {
		super();
	}
	
	public Unit evaluate(Unit unit){
		return unit.getWorld().getAccessibleEnemy(unit);
	}

}

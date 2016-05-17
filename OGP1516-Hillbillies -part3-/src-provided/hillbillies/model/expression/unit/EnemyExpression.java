package hillbillies.model.expression.unit;

import hillbillies.model.Unit;

public class EnemyExpression extends UnitExpression {

	public EnemyExpression() {
		super();
	}
	
	public Unit evaluate(Unit unit){
		return unit.getWorld().getAccessibleEnemy(unit);
	}

}

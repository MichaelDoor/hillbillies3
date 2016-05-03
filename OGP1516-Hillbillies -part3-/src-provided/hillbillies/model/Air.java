package hillbillies.model;

import java.util.HashSet;

import hillbillies.model.GameObject;
import hillbillies.model.PositionVector;

public class Air extends PassableCube {

	public Air(PositionVector position) throws IllegalArgumentException {
		super(position);
	}
	
	public Air(PositionVector position, HashSet<GameObject> content) throws IllegalArgumentException {
		super(position, content);
	}

	@Override
	public int getTerrainType() {
		return 0;
	}

}

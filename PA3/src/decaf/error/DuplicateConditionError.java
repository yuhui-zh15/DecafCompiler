package decaf.error;

import decaf.Location;

/**
 * example：condition is not unique<br>
 * PA2
 */

public class DuplicateConditionError extends DecafError {
	
	public DuplicateConditionError(Location location) {
		super(location);
	}

	@Override
	protected String getErrMsg() {
		return "condition is not unique";
	}

}
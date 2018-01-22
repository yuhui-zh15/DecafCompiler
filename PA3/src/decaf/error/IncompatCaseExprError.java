package decaf.error;

import decaf.Location;

/**
 * example：incompatible case expr: bool given, but int expected<br>
 * PA2
 */
public class IncompatCaseExprError extends DecafError {

	private String type;

	public IncompatCaseExprError(Location location, String type) {
		super(location);
		this.type = type;
	}

	@Override
	protected String getErrMsg() {
		return "incompatible case expr: " + type + " given, but int expected";
	}

}

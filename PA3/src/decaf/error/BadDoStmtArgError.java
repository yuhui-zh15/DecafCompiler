package decaf.error;

import decaf.Location;

/**
 * example：The condition of Do Stmt requestd type bool but int given<br>
 * PA2
 */
public class BadDoStmtArgError extends DecafError {

	private String type;

	public BadDoStmtArgError(Location location, String type) {
		super(location);
		this.type = type;
	}

	@Override
	protected String getErrMsg() {
		return "The condition of Do Stmt requestd type bool but " + type + " given";
	}

}

package decaf.error;

import decaf.Location;

/**
 * example：For copy expr, the source class : animal and the destination class : people are not same<br>
 * PA2
 */
public class IncompatCopyAssignError extends DecafError {

	private String left;

	private String right;

	public IncompatCopyAssignError(Location location, String left, String right) {
		super(location);
		this.left = left;
		this.right = right;
	}

	@Override
	protected String getErrMsg() {
		return "For copy expr, the source " + right + " and the destination " + left + " are not same";
	}

}

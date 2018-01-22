package decaf.error;

import decaf.Location;

/**
 * example：type: complex is different with other expr's type int<br>
 * PA2
 */
public class DifferentCaseTypeError extends DecafError {

	private String casetype;
    private String defaulttype;

	public DifferentCaseTypeError(Location location, String casetype, String defaulttype) {
		super(location);
        this.casetype = casetype;
        this.defaulttype = defaulttype;
	}

	@Override
	protected String getErrMsg() {
		return "type: " + casetype + " is different with other expr's type " + defaulttype;
	}

}
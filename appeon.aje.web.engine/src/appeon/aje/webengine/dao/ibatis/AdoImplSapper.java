package appeon.aje.webengine.dao.ibatis;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.JavaSapper;

public class AdoImplSapper extends JavaSapper {

	private static final String TEMPLATE = "adoImpl.vm";

	public AdoImplSapper(Context ctx) {
		super(ctx);
	}

	protected String getSubPackage() {
		return "dao.ibatis";
	}

	protected String getSuffix() {
		return "AdoImpl";
	}

	protected String getTemplate() {
		return TEMPLATE;
	}

}
